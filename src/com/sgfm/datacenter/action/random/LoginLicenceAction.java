package com.sgfm.datacenter.action.random;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 生成验证码
 */
@SuppressWarnings("serial")
public class LoginLicenceAction extends HttpServlet {

	public LoginLicenceAction() {
	}

	protected void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse) throws ServletException, IOException {
		HttpSession httpsession;
		String s;
		httpsession = httpservletrequest.getSession(true);
		// 返回一个字符串，其中包含分配给本次会话的唯一标识符。
		s = httpsession.getId();
	
		synchronized (s) {
			httpservletresponse.setHeader("Pragma", "No-cache");
			httpservletresponse.setHeader("Cache-Control", "no-cache");
			httpservletresponse.setDateHeader("Expires", 0L);
			String s2 = "image/jpeg";
			int licence_x = 5;
			int licence_y = 18;
			String licence_fontColor = "000000";
			int licence_width = 59;
			int licence_height = 22;
			String s4 = generateLicence();
			//httpservletrequest.getSession().setAttribute("code", s4);
			httpsession.setAttribute("captcha",s4);
			String s5 = s4;
			httpservletresponse.setContentType(s2);
			BufferedImage bufferedimage = new BufferedImage(licence_width, licence_height, 1);
			Graphics g = bufferedimage.getGraphics();
			g.setColor(new Color(255, 255, 255));
			g.fillRect(0, 0, licence_width, licence_height);
			int j1 = (int) (Math.random() * 200D);
			int ai[] = new int[j1];
			int ai1[] = new int[j1];
			for (int k1 = 0; k1 < ai.length; k1++) {
				ai[k1] = (int) (Math.random() * 200D);
				ai1[k1] = (int) (Math.random() * 200D);
			}

			g.setColor(_$2());
			g.drawPolyline(ai, ai1, ai.length);
			g.setColor(new Color(102, 102, 102));
			g.drawRect(0, 0, licence_width - 1, licence_height - 1);
			g.setColor(new Color(Integer.parseInt(licence_fontColor, 16)));
			//Font font = MyFont.getFont();
		//	Font font1 = font.deriveFont(0, licence_fontSize);
			//g.setFont(font1);
			
			//Font font =Font.getFont("");
		//	Font font1 = font.deriveFont(0, licence_fontSize);
			Font font1 = new Font("Arial",Font.BOLD,20);
			g.setFont(font1);
			
			g.drawString(s5, licence_x, licence_y);
			ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
			JPEGImageEncoder jpegimageencoder = JPEGCodec.createJPEGEncoder(servletoutputstream);
			jpegimageencoder.encode(bufferedimage);
			servletoutputstream.close();
		}

	}

	Color _$1(int i, int j) {
		Random random = new Random();
		int k;
		int l;
		int i1;
		if (i > 255)
			i = 255;
		if (j > 255)
			j = 255;
		k = i + random.nextInt(j - i);
		l = i + random.nextInt(j - i);
		i1 = i + random.nextInt(j - i);
		return new Color(k, l, i1);
	}

	Color _$2() {
		Random random = new Random();
		int i = random.nextInt(3);
		int ai[][] = new int[5][];
		int ai2[] = new int[3];
		ai2[0] = 0;
		ai2[1] = 204;
		ai2[2] = 255;
		ai[0] = ai2;
		int ai3[] = new int[3];
		ai3[0] = 153;
		ai3[1] = 255;
		ai3[2] = 0;
		ai[1] = ai3;
		int ai4[] = new int[3];
		ai4[0] = 255;
		ai4[1] = 102;
		ai4[2] = 255;
		ai[2] = ai4;
		int ai5[] = new int[3];
		ai5[0] = 255;
		ai5[1] = 51;
		ai5[2] = 51;
		ai[3] = ai5;
		int ai6[] = new int[3];
		ai6[0] = 255;
		ai6[1] = 255;
		ai6[2] = 0;
		ai[4] = ai6;
		return new Color(ai[i][0], ai[i][1], ai[i][2]);
	}

	String generateLicence() {
		Random random = new Random();
		String s;
		s = "";
		for (int i = 0; i < 4; i++) {
			s = new StringBuffer().append(s).append(random.nextInt(10)).toString();
		}
		return s;
	}
}
