package org.somerville.james;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.somerville.james.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	logger.debug("This is a log");
    	
    	ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    	
 
    	//Get the file encryptor
    	CryptorService cryptorService = appContext.getBean("cryptorService", CryptorService.class);
    	//System.out.println(((SpringCryptorServiceImpl) cryptorService).getPassword());
    }
}
