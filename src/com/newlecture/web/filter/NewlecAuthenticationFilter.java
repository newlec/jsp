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
			// ������ ���ߴٸ� �ϰ� ��.
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
			
			//�޽��� �Ѹ��� (��ȭ���ڷ� ���� + Ȯ��[OK] ��ư�� ������)
			//�α��� �������� �̵�
		
		chain.doFilter(request, response);
		
		
	}

}
