package com.newlecture.web.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin/index")
public class IndexController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
					"	window.location.href=\"../member/login\";    	\r\n" + 
					"</script>");
			
			return;
		}
			
			//메시지 뿌리고 (대화상자로 띄우기 + 확인[OK] 버튼을 누르면)
			//로그인 페이지로 이동
		
		request
		.getRequestDispatcher("/WEB-INF/view/admin/index.jsp") 
		.forward(request, response);
	}
}
