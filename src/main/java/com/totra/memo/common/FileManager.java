package com.totra.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	public static final String FILE_UPLOAD_PATH = "C:\\Users\\t9864\\OneDrive\\바탕 화면\\Spring\\memo\\upload\\image";

	public static String saveFile(int userId, MultipartFile file) {
		
		if(file == null) {
			return null;
		}
		
		// 파일 이름 유지
		// 같은 이름의 파일이 전달 될 경우를 대비해서 디렉토리를 만들어서 파일 저장
		// 디렉토리 이름에 사용자 정보 포함
		// 시간을 디렉토리 이름에 포함
		// UNIX TIME : 1970년 1월 1일 부터 흐른 시간을 milli second(1/ 1000초)로 표현한 값
		// ex ) 2_89723598273
		
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
	
		// 디렉토리 만들기
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			// 디렉토리 생성 실패
			return null;
		}
		
		// 파일 저장
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath);
			
			Files.write(path, bytes);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
			// 파일 저장 실패
			return null;
			
		}
		
		// /images/directory/file.name
		
		return "/images" + directoryName + "/" + file.getOriginalFilename();

	}
	
	public static boolean removeFile(String filePath) {
		if(filePath == null) {
			return true;
		}
		
		String fullFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", "");
	
		Path path = Paths.get(fullFilePath);
		
		Path directoryPath = path.getParent();
		
		try {
			Files.delete(path);
			Files.delete(directoryPath);
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}	
	}
	
}
