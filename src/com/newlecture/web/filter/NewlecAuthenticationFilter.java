package com.newlecture.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class NewlecAuthenticationFilter implements Filter {

	
	private static final String[] authUrls = {
			"/admin/*",
			"/member/mypage"
	};
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpSession session = request.getSession();

		// /index
		// /admin/index
		// /admin/board/notice/list
		String requestURI = request.getRequestURI();
		boolean isAuthRequired = false;
		
		for(String url : authUrls)
			if(url.endsWith("*")) {
				String path = url.substring(0, url.length()-1);//-> /admin/*
				if(requestURI.startsWith(path)) {
					isAuthRequired = true;
					break;
				}
			}		
			else
				if(url.equals(requestURI)) {
					isAuthRequired = true;
					break;
				}
		
		boolean isAuthenticated = session.getAttribute("userName") != null;
		if(!isAuthenticated  && isAuthRequired) { 
			// ������ �ʿ��� ��� :: �����ϰ� ��.
			response.setCharacterEncoding("UTF-8");
	    	response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>\r\n" + 
					"	window.alert(\"������ �ʿ��� ��û�Դϴ�. \\r\\n\" +\r\n" + 
					"			\"�α��� �������� �̵��մϴ�.\");\r\n" + 
					"	\r\n" + 
					"	window.location.href=\"../member/login?returnUrl=../admin/index\";    	\r\n" + 
					"</script>");
			
			return;
		}
		else {
			// ������ �̹� �Ǿ� �ְ� �Ǵ� ������ �ʿ����� �ʴ� ���
			// ������ ������� �ʴٸ�
			//if()
			//	response.sendRedirct("/error403");
			// ������ ����ϴٸ�...
			//else
			//	chain.doFilter(request, response);
		}
	}

}
