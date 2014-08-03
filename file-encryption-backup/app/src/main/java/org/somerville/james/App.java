package org.somerville.james;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.somerville.james.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    	
 
    	//Get the file encryptor
    	CryptorService cryptorService = appContext.getBean("cryptorService", CryptorService.class);
    	//System.out.println(((SpringCryptorServiceImpl) cryptorService).getPassword());
    }
}
