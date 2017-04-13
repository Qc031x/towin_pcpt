package com.sgfm.datacenter.util.token;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sgfm.datacenter.SysConstant;

public class TokenProcessor {

	private static TokenProcessor instance = new TokenProcessor();

	/**
	 * getInstance()方法得到单例类的实例。
	 */
	public static TokenProcessor getInstance() {
		return instance;
	}

	/**
	 * 最近一次生成令牌值的时间戳。
	 */
	private long previous;

	/**
	 * 判断请求参数中的令牌值是否有效。
	 */
	public synchronized boolean isTokenValid(HttpServletRequest request) {
		// 得到请求的当前Session对象。

		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}

		// 从Session中取出保存的令牌值。

		String saved = (String) session.getAttribute(SysConstant.TW_TOKEN);
		if (saved == null) {
			return false;
		}

		// 清除Session中的令牌值。

		resetToken(request);

		// 得到请求参数中的令牌值。

		String token = request.getParameter(SysConstant.TW_TOKEN);
		if (token == null) {
			return false;
		}

		return saved.equals(token);
	}
	
	public synchronized boolean isTokenValid(HttpServletRequest request, String token) {
		// 得到请求的当前Session对象。

		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}

		// 从Session中取出保存的令牌值。

		String saved = (String) session.getAttribute(SysConstant.TW_TOKEN);
		if (saved == null) {
			return false;
		}

		// 清除Session中的令牌值。

		//resetToken(request);

		// 得到请求参数中的令牌值。

		//String token = request.getParameter(SysConstant.YK_TOKEN);
		if (token == null) {
			return false;
		}

		return saved.equals(token);
	}
	
	/**
	 * 清除Session中的令牌值。
	 */
	public synchronized void resetToken(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(SysConstant.TW_TOKEN);
	}
	
	/**
	 * 产生一个新的令牌值，保存到Session中， 如果当前Session不存在，则创建一个新的Session。
	 */
	public synchronized String saveToken(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String token = generateToken(request);
		if (token != null) {
			session.setAttribute(SysConstant.TW_TOKEN, token);
		}
		return token;
	}
	

	/**
	 * 根据用户会话ID和当前的系统时间生成一个唯一的令牌。
	 */
	public synchronized String generateToken(HttpServletRequest request) {

		HttpSession session = request.getSession();
		try {
			byte id[] = session.getId().getBytes();
			long current = System.currentTimeMillis();
			if (current == previous) {
				current++;
			}
			previous = current;
			byte now[] = new Long(current).toString().getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(id);
			md.update(now);
			return toHex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	/**
	 * 将一个字节数组转换为一个十六进制数字的字符串。
	 */
	private String toHex(byte buffer[]) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		}
		return sb.toString();
	}

	/**
	 * 从Session中得到令牌值，如果Session中没有保存令牌值，则生成一个新的令牌值。
	 */
	public synchronized String getToken(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (null == session)
			return null;
		String token = (String) session.getAttribute(SysConstant.TW_TOKEN);
		if (null == token) {
			token = generateToken(request);
			if (token != null) {
				session.setAttribute(SysConstant.TW_TOKEN, token);
				return token;
			} else
				return null;
		} else
			return token;
	}
	
}
