package com.kiraly93.wobdemo.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FTPUtil {

	@Value("${ftp.server.ip}")
	private String ip;
	@Value("${ftp.server.port}")
	private int port;
	@Value("${ftp.user}")
	private String user;
	@Value("${ftp.pass}")
	private String pwd;
	private FTPClient ftpClient;

	public FTPUtil() {
	}

	private boolean connectServer(String ip, int port, String user, String pwd) {
		ftpClient = new FTPClient();
		Boolean isSuccess = false;
		try {
			ftpClient.connect(ip);
			isSuccess = ftpClient.login(user, pwd);
		} catch (IOException e) {
		}
		return isSuccess;
	}

	public boolean uploadFile(File file) throws IOException {
		boolean upload = true;
		FileInputStream fileInputStream = null;
		if (connectServer(this.ip, this.port, this.user, this.pwd)) {
			try {
				ftpClient.setBufferSize(1024);
				ftpClient.setControlEncoding("UTF-8");
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				ftpClient.enterLocalPassiveMode();
				fileInputStream = new FileInputStream(file);
				ftpClient.storeFile(file.getName(), fileInputStream);
			} catch (IOException e) {
				upload = false;
			} finally {
				fileInputStream.close();
				ftpClient.disconnect();
			}
		}
		return upload;
	}

}
