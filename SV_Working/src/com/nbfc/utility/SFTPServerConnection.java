package com.nbfc.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
/*import org.springframework.stereotype.Component;*/

/*@Component*/
public class SFTPServerConnection {
	

	/*
	 * @Value("${sftp.host}") private String sftpHost;
	 * 
	 * @Value("${sftp.port}") private int sftpPort;
	 * 
	 * @Value("${sftp.user}") private String sftpUser;
	 * 
	 * @Value("${sftp.password}") private String sftpPasword;
	 */
	/*
	 * public DefaultSftpSessionFactory getSftpFactory() { DefaultSftpSessionFactory
	 * factory = new DefaultSftpSessionFactory(); factory.setHost(sftpHost);
	 * factory.setPort(sftpPort); factory.setAllowUnknownKeys(true);
	 * factory.setUser(sftpUser); factory.setPassword(sftpPasword); return factory;
	 * }
	 */   public static void main(String[] args) {
		     String sftpHost="210.212.195.66";
		     int sftpPort=22;
		     String sftpUser="cgtmse";
		     String sftpPasword="p@ssw0rd@210.212.195.66";
		     FTPClient ftpClient = new FTPClient();
	        try {
	        	
	            ftpClient.connect(sftpHost, sftpPort);
	            ftpClient.login(sftpUser, sftpPasword);
	            ftpClient.enterLocalPassiveMode();
	 
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	            
	            File firstLocalFile = new File("E:/software/sts-4.5.1.RELEASE/177992021041900001.xml");
	            
	            String firstRemoteFile = "177992021041900001.xml";
	            InputStream inputStream = new FileInputStream(firstLocalFile);
	 
	            System.out.println("Start uploading first file");
	            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
	            inputStream.close();
	            if (done) {
	                System.out.println("The first file is uploaded successfully.");
	            }
	        }catch(IOException ex) {
	            System.out.println("Error: " + ex.getMessage());
	            ex.printStackTrace();
	        }finally {
	            try {
	                if (ftpClient.isConnected()) {
	                    ftpClient.logout();
	                    ftpClient.disconnect();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	 }
}

