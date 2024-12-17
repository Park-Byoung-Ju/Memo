package com.totra.memo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.totra.memo.common.FileManager;
import com.totra.memo.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		FileManager fileManger = new FileManager();
		
		registry.addResourceHandler("/images/**") // 이미지와 url path를 연결하는 과정
		.addResourceLocations("file:///" + fileManger.FILE_UPLOAD_PATH + "/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// registry 변수에 interceptor 등록
		registry.addInterceptor(new PermissionInterceptor())
											.addPathPatterns("/**")
											//예외로 하는 주소
											// 로그아웃 static(view) images업로드는 interceptor를 거치지 않게한다
											.excludePathPatterns("/user/logout", "/static/**", "/images/**");
	}
}
