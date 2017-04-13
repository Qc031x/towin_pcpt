package com.sgfm.datacenter.util;

import java.io.File;
import java.io.IOException;
/**
 * 文件锁定工具类。
 * @author 罗军林
 * 创建时间：2011-7-19
 *
 */
public class LockFile{
	private File file;
	private File lockFile;
	
	private static String waitMonitor = "waitMonitor";
	public LockFile(File file) {
		this.lockFile = new File(file.getPath()+".lock");
	}
	
	/**
	 * 如果该文件锁定，线程暂时挂起直到该文件被解锁.
	 * @throws InterruptedException
	 */
	private void waitIfLock() throws InterruptedException{
		while( lockFile.exists()) { 
			synchronized (waitMonitor) {
				waitMonitor.wait(500L);
			}
		}//如果存在
	}
	
	/**
	 * 锁定该文件。
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void lock() {
		try{
			waitIfLock();
			lockFile.createNewFile();
			lockFile.setReadOnly();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 解锁。
	 */
	public void unLock(){
		lockFile.delete();
	}

	public File getFile() {
		return file;
	}
	
}