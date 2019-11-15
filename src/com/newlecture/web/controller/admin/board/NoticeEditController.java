package com.newlecture.web.controller.admin.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;
import com.newlecture.web.service.newlec.NewlecNoticeService;

@WebServlet("/admin/board/notice/edit")
public class NoticeEditController extends HttpServlet {

	//톰캣 -> 서블릿을 생성 -> init() -> service() -> doXXX() 
	// -> timeout 카운트 다운 -> 소멸
	
	private NoticeService noticeService;
	
	public NoticeEditController() {
		noticeService = new NewlecNoticeService();
	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("설정에서 읽어오기 그리고 객체 만들기");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		request
		.getRequestDispatcher("/WEB-INF/view/admin/board/notice/edit.jsp") 
		.forward(request, response);
	
	}
	
}
