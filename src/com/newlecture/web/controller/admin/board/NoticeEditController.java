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

	//��Ĺ -> ������ ���� -> init() -> service() -> doXXX() 
	// -> timeout ī��Ʈ �ٿ� -> �Ҹ�
	
	private NoticeService noticeService;
	
	public NoticeEditController() {
		noticeService = new NewlecNoticeService();
	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("�������� �о���� �׸��� ��ü �����");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		request
		.getRequestDispatcher("/WEB-INF/view/admin/board/notice/edit.jsp") 
		.forward(request, response);
	
	}
	
}
