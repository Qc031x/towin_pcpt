package com.sgfm.base.exception;

import org.apache.log4j.Logger;


/**
 * @author LiuPing
 *信用体育异常处理基类
 */
public class SGFMException extends Exception{

	protected static Logger logger = Logger.getLogger(SGFMException.class.getName());
	protected Throwable cause;
	/*
	 * SGFM默认的异常，异常信息内置为Error occurred in SGFM application.
	 * */
	public SGFMException() {
        super("Error occurred in SGFM application.");
    }
	/*
	 * 自定义异常信息的SGFM应用异常
	 * */
    public SGFMException(String message)  {
        super(message);
    }
    /*
     * 转化外部异常为SGFM应用异常
     * */
    public SGFMException(String message, Throwable cause)  {
        super(message);
        this.cause = cause;
    }
    /*
     * 获取被包装的异常
     * */
    public Throwable initCause(Throwable cause)  {
        this.cause = cause;
        return cause;
    }
    /*
     * 获取异常信息
     * */
    public String getMessage() {
        // Get this exception"s message.
        String msg = super.getMessage();
        msg = msg == null ? "":msg;
        if(cause != null){
        	msg += " ; "+cause.getMessage();
        } 
        return msg;
    }
    /*
     * 打印堆栈追踪
     * */
    public void printStackTrace() {
        if(cause != null){
        	cause.printStackTrace();
        }else{
        	super.printStackTrace();
        }
        
    }

    private String makeStackTrace(){
    	StackTraceElement[] arr = super.getStackTrace();
    	if(arr == null || arr.length <= 0){
    		arr = cause.getStackTrace();
    	}
    	StringBuilder strBld = new StringBuilder();
    	for (int i = 0; i < arr.length; i++) {
    		strBld.append(arr[i]);
    		if(i >= 3){
    			break;
    		}
		}
    	return strBld.toString();
    }
    /*
     * 记录Error级别的日志到日志文件，记录堆栈的开始部分信息
     * */
    public void logStackTrace() {
    	
    	logger.error(makeStackTrace());
    }
    /*
     * 获取简单的堆栈信息
     * */
    public String getSimpleStackTrace(){
    	return makeStackTrace();
    }
    /*
     * 记录异常信息到日志文件
     * */
    public void logMessage() {
    	logger.error(getMessage());
    }
  
    public Throwable getCause()  {
        return cause;
    }

}
