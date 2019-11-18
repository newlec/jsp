package com.newlecture.web.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Member;
import com.newlecture.web.service.MemberService;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {

	private MemberService memberService;
	
	public LoginController() {
		memberService = new NewlecMemberService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String returnUrl = request.getParameter("returnUrl");
		
		request.setAttribute("returnUrl", returnUrl);
		
		request
		.getRequestDispatcher("/WEB-INF/view/member/login.jsp") 
		.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("username");  // ����ڰ� ������ ����
		String pwd = request.getParameter("password");
		String returnUrl = request.getParameter("returnUrl");
		
		boolean isValid = memberService.isValidMember(id, pwd);
		
		if(!isValid)
			response.sendRedirect("login?error=1");
		else{// �������� + ���� : Authentication & Authority
			//���� ���� ������ ..���� ������ ������ �Ǿ����� �˸��ϴ�.
			// �����Ǿ��� ���¸� ����� ��
			// ���¸� ����ٰ�? � ���¸�? ���?						
			// /admin/index	
			request.getSession().setAttribute("userName", id);
			
			if(!returnUrl.equals(""))
				response.sendRedirect(returnUrl);
			else
				response.sendRedirect("/index");
			
			//  /member/login -> /admin/index
			//  ../admin/index
		}

	}
}
