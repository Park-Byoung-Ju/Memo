package com.totra.memo.interceptor;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request
							, HttpServletResponse response
							, Object handler) throws IOException {
		
		// 로그인이 안된 상태에서 로그인 이외에 페이지 접근 금지
		// 로그인 페이지로 이동
		HttpSession session = request.getSession();
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		String url = request.getRequestURI();
		
		
		if(userId == null) {
			// post로 시작하는 url path 확인
			if(url.startsWith("/post")) {
				// 로그인이 안된 채로 post로 접근
				// 로그인 페이지로 redirect
				response.sendRedirect("/user/login-view");
				
				return false;
			}
			
		}else {
			// 로그인이 되어 있는 경우 사용자와 관련된 페이지 접근을 막는다
			if(url.startsWith("/user")) {
				response.sendRedirect("/post/list-view");
				
				return false;
			}
		}
		
		
		return true;
	}
}