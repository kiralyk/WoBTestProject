package com.kiraly93.wobdemo.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.minidev.json.JSONObject;

@Service
public class FileServiceImpl {

	private final static String FILE_NAME = "report.txt";

	@Autowired
	private FTPUtil ftpUtil;

	public FileServiceImpl() {
	}

	public void upload(JSONObject reportJson) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(FILE_NAME);
			fileWriter.write(reportJson.toJSONString());
			File reportFile = new File(FILE_NAME);
			ftpUtil.uploadFile(reportFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
