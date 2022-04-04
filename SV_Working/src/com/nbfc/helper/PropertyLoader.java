package com.nbfc.helper;

import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * @author VT8150
 *
 * This class is used to load the config.properties file while starting up.
 * If any property is changed after the server start-up, user has to reload
 * this application to make the changes effective.
 *
 */
public class PropertyLoader
{
	private PropertyLoader()
	{
	}
	//private static Properties CONFIG_PROPERTIES=null;
	private static Properties CONFIG_PROPERTIES;
	public static final String CONFIG_DIRECTORY="/WEB-INF/Log";
	public static final String PROPERTY_CONFIG_DIRECTORY="/WEB-INF";
	private static final String CONFIG_FILE="config.properties";
	private static long PROPERTY_LOADED_TIME=System.currentTimeMillis();
	private static String JAVA_PATH;
	public static final String FILE_DOWNLOAD_DIRECTORY="Download";
	//private static DbConnectionBroker dbBroker = null;
	//private static String JAVA_PATH=null;
/**
 * @param path
 */
	public static String changeToOSpath(String realPath) throws Exception
	{
		if(realPath==null || realPath.equals(""))
		{
			throw new Exception("real path is null");
		}
		return  realPath.replace('\\','/');
	}
	public static String changeToJavapath(String osPath) throws Exception
	{
		if(osPath==null || osPath.equals(""))
		{
			throw new Exception("real path is null");
		}
		return  osPath.replace('/','\\');
	}
	public static void loadProperties(Properties properties)
	{
		//Check if the property already loaded.
		if(CONFIG_PROPERTIES!=null)
		{
			return;
		}
		else
		{
			CONFIG_PROPERTIES=properties;
		}
	}
	public static void loadProperties(String realPath) throws Exception
	{
		if(CONFIG_PROPERTIES!=null)
		{
			//if property already loaded, no need to load again.
			return;
		}
		FileInputStream fin=null;
		JAVA_PATH=realPath;
		String changedPath=changeToOSpath(realPath);

		//System.out.println("changedPath "+changedPath);
		//System.out.println("realPath "+realPath);

		CONFIG_PROPERTIES=new Properties();
		//Check if Log and FileUpload folders are present.
		String logFile="Log";
		String fileUpload="FileUpload";

		String download="Download";

		File log=new File(changedPath+"/WEB-INF",logFile);
		File fileUploadFile=new File(changedPath+"/WEB-INF",fileUpload);
		File downloadFile=new File(changedPath,FILE_DOWNLOAD_DIRECTORY);

		//Create Log Directory.
		if(!log.isDirectory())
		{
			//Directory does not exist.So, create.
			boolean created=log.mkdir();
			System.out.println("Log dir created "+created);

		}
		else
		{
			System.out.println("Log Directory already Exists");
		}

		log=null;

		//Create File Upload Directory.
		if(!fileUploadFile.isDirectory())
		{
			//Directory does not exist.So, create.
			boolean created=fileUploadFile.mkdir();
			System.out.println("File Upload dir created "+created);

		}
		else
		{
			System.out.println("File Upload Directory already Exists");
		}

		fileUploadFile=null;


		//Create Download Directory.
		if(!downloadFile.isDirectory())
		{
			//Directory does not exist.So, create.
			boolean created=downloadFile.mkdir();
			System.out.println("Download dir created "+created);

		}
		else
		{
			System.out.println("Download Directory already Exists");
		}

		downloadFile=null;


		//Load the properties file located in the application root directory
		File config=new File(changedPath+PROPERTY_CONFIG_DIRECTORY,CONFIG_FILE);

		try {
			fin=new FileInputStream(config);
			CONFIG_PROPERTIES.load(fin);
			CONFIG_PROPERTIES.setProperty("contextpath",changedPath);
			fin.close();
			fin=null;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		finally
		{
			if(fin!=null)
			{
				try
				{
					fin.close();
				}
				catch(IOException ignore){}
			}
		}
	}

}

