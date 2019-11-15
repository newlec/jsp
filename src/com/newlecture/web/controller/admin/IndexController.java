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
			// ������ ���ߴٸ� �ϰ� ��.
			response.setCharacterEncoding("UTF-8");
	    	response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>\r\n" + 
					"	window.alert(\"������ �ʿ��� ��û�Դϴ�. \\r\\n\" +\r\n" + 
					"			\"�α��� �������� �̵��մϴ�.\");\r\n" + 
					"	\r\n" + 
					"	window.location.href=\"../member/login\";    	\r\n" + 
					"</script>");
			
			return;
		}
			
			//�޽��� �Ѹ��� (��ȭ���ڷ� ���� + Ȯ��[OK] ��ư�� ������)
			//�α��� �������� �̵�
		
		request
		.getRequestDispatcher("/WEB-INF/view/admin/index.jsp") 
		.forward(request, response);
	}
}
