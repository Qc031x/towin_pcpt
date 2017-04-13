package com.sgfm.datacenter.exception;

/**
 * 业务异常，程序中如果出错应该主动抛出本异常。
 * 
 * @author 罗军林
 * 
 */
public class AppException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /** 警告 */
    private static final int WARNING = 0;

    /** 错误 */
    private static final int ERROR = 1;

    /** 失败 */
    private static final int FAILURE = 2;

    private int errorFlag = 0;

    /**
     * 创建错误异常。
     * 
     * @param msg
     * @param cause
     * @return
     */
    public static AppException createErrorException(String msg)
    {
        AppException exception = new AppException(msg, AppException.ERROR);
        return exception;
    }

    /**
     * 创建操作异常。
     * 
     * @param msg
     * @param cause
     * @return
     */
    public static AppException createFailureException(String msg)
    {
        AppException exception = new AppException(msg, AppException.FAILURE);
        return exception;
    }

    /**
     * 如果对象为null，
     * 
     * @param o
     */
    public static void isNull(Object o)
    {
        if (o == null || o.equals(""))
        {
            throw AppException.createErrorException("主键不能为空");
        }
    }

    /**
     * 如果对象为null，自定义错误信息
     * 
     * @param o
     */
    public static void isNull(Object obj, String msg)
    {
        if (obj == null || obj.equals(""))
        {
            throw AppException.createErrorException(msg + "不能为空！");
        }
    }

    /**
     * 创建警告异常。
     * 
     * @param msg
     * @param cause
     * @return
     */
    public static AppException createWarningException(String msg)
    {
        AppException exception = new AppException(msg, AppException.WARNING);
        return exception;
    }

    public int getErrorFlag()
    {
        return errorFlag;
    }

    public void setErrorFlag(int errorFlag)
    {
        this.errorFlag = errorFlag;
    }

    private AppException(String message, int errorFlag)
    {
        super(message);
        this.errorFlag = errorFlag;
    }

    private AppException(String message)
    {
        super(message);
        // TODO Auto-generated constructor stub
    }

    private AppException(Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
