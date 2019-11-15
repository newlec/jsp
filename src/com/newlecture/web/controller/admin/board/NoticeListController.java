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

// 공지 사항 목록을 주세욧!!!
@WebServlet("/admin/board/notice/list")
public class NoticeListController extends HttpServlet {

	
	private NoticeService noticeService;
	
	public NoticeListController() {
		noticeService = new NewlecNoticeService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page = 1;
		String field = "title";
		String query = "";
		
		String page_ = request.getParameter("p");
		if(page_ !=  null && !page_.equals(""))
			page = Integer.parseInt(page_);
		
		String field_ = request.getParameter("f");
		if(field_ !=  null && !field_.equals(""))
			field = field_;
		
		String query_ = request.getParameter("q");
		if(query_ !=  null && !query_.equals(""))
			query = query_;

		request.setAttribute("list", noticeService.getNoticeList(page, field, query));
		request.setAttribute("listCount", noticeService.getNoticeListCount(field, query));
		
		request
		.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp") 
		.forward(request, response);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		
		switch(cmd) {
		case "일괄공개":
			break;
		case "일괄삭제":
			String[] ids_ = request.getParameterValues("del");
			
			int[] ids = new int[ids_.length];
			for(int i=0; i<ids.length; i++)
				ids[i] = Integer.parseInt(ids_[i]);
						
			noticeService.deleteNoticeList(ids);
			break;
		}
		
		response.sendRedirect("list");
	}
	
	
}
