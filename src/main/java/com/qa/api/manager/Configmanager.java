package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configmanager {

 private static Properties prop = new Properties();
 
  static {	 
	  
//	  mvn clean install -Denv = "qa"
	  String envName = System.getProperty("env" , "prod");
	  System.out.println("Running the test cases on: "+ envName);
	  String Filename = "config_"+envName+".properties";
	  
	  
	  
	  
 // String Filename = "config.properties";
  InputStream input =Configmanager.class.getClassLoader().getResourceAsStream(Filename);
          if(input!=null) {
        	  try {
				prop.load(input);
				System.out.println("Config peoperties are: "+prop);
			} catch (IOException e) {
				e.printStackTrace();
			}
          }
          
    
 }
   public static String get(String key) {
	   
           String value =  prop.getProperty(key);	
           if(value == null) {
        	   throw new RuntimeException("Property not found : "+ key);
           }
           return value.trim();
   }
   
   public static void set(String key , String value){
	   
      prop.setProperty(key, value);
   }
	
	
}
