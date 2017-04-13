package com.sgfm.datacenter.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * 压缩、解压工具。
 * @author 罗军林
 * 创建时间：2011-8-15
 *
 */
public class GzipTools {
	
	/***
	 * 压缩GZip
	 * 
	 * @param data
	 * @return
	 */
	public static String gZip(String input) {
		byte[] bytes = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bos);
			gzip.write(input.getBytes("utf-8"));
			gzip.finish();
			gzip.close();
			bytes = bos.toByteArray();
			bos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new BASE64Encoder().encodeBuffer(bytes);
	}

	/***
	 * 解压GZip
	 * 
	 * @param data
	 * @return
	 */
	public static String unGZip(String input) {
		byte[] bytes = null;
		String out = "";
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(input));
			GZIPInputStream gzip = new GZIPInputStream(bis);
			byte[] buf = new byte[1024];
			int num = -1;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((num = gzip.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, num);
			}
			bytes = baos.toByteArray();
			baos.flush();
			baos.close();
			gzip.close();
			bis.close();
			out = new String(bytes, "utf-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return out;
	}

}
