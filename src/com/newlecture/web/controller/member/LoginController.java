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
		String id = request.getParameter("username");  // 사용자가 전달한 내용
		String pwd = request.getParameter("password");
		String returnUrl = request.getParameter("returnUrl");
		
		boolean isValid = memberService.isValidMember(id, pwd);
		
		if(!isValid)
			response.sendRedirect("login?error=1");
		else{// 인증성공 + 권한 : Authentication & Authority
			//동네 서블릿 여러분 ..현재 유저는 인증이 되었음을 알립니다.
			// 인증되었음 상태를 남기는 것
			// 상태를 남긴다고? 어떤 상태를? 어디에?						
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
