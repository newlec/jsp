package com.newlecture.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class NewlecAuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("userName") == null) { 
			// 인증도 안했다면 하고 와.
			response.setCharacterEncoding("UTF-8");
	    	response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>\r\n" + 
					"	window.alert(\"인증이 필요한 요청입니다. \\r\\n\" +\r\n" + 
					"			\"로그인 페이지로 이동합니다.\");\r\n" + 
					"	\r\n" + 
					"	window.location.href=\"../member/login?returnUrl=../admin/index\";    	\r\n" + 
					"</script>");
			
			return;
		}
			
			//메시지 뿌리고 (대화상자로 띄우기 + 확인[OK] 버튼을 누르면)
			//로그인 페이지로 이동
		
		chain.doFilter(request, response);
		
		
	}

}
