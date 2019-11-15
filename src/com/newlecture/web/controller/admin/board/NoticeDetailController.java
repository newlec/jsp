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

@WebServlet("/admin/board/notice/detail")
public class NoticeDetailController extends HttpServlet {

	
	private NoticeService noticeService;
	
	public NoticeDetailController() {
		noticeService = new NewlecNoticeService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자가 요청한 id를 입력 받아서
		int id = Integer.parseInt(request.getParameter("id"));
		// 2. 데이터를 마련하고
		Notice notice = noticeService.getNotice(id);
		// 3. 출력하는 서블릿으로 전달
		request.setAttribute("n", notice);
		
		request
		.getRequestDispatcher("/WEB-INF/view/admin/board/notice/detail.jsp") 
		.forward(request, response);
	
	}
	
	
}
