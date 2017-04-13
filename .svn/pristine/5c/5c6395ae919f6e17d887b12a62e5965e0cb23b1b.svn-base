package com.sgfm.datacenter.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author LIFUPING
 * @version 1.0
 */

public class AppContext
{
    static ApplicationContext appContext;

    public static synchronized ApplicationContext getAppContext()
    {
        if (AppContext.appContext == null)
        {
            AppContext.appContext = AppContext.createContext();
        }
        return AppContext.appContext;
    }

    private static ApplicationContext createContext()
    {
      //  final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] { "applicationContext_*.xml" });
    	  final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] { "applicationContext_core.xml","applicationContext_ext.xml" });
    	
        return applicationContext;
    }
}
