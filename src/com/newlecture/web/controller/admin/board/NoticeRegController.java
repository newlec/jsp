package com.newlecture.web.controller.admin.board;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;
import com.newlecture.web.service.newlec.NewlecNoticeService;

@WebServlet("/admin/board/notice/reg")
@MultipartConfig(		
		fileSizeThreshold = 1024*1024,
		maxFileSize = 1024*1024*100,
		maxRequestSize = 1024*1024*100*5
)
public class NoticeRegController extends HttpServlet {

	//��Ĺ -> ������ ���� -> init() -> service() -> doXXX() 
	// -> timeout ī��Ʈ �ٿ� -> �Ҹ�
	
	private NoticeService noticeService;
	
	public NoticeRegController() {
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
		.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp") 
		.forward(request, response);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//1. ������ ����ϴ� ��� 2����
		//2. ���� ������ ���ε��ϴ� ���		
		
		Collection<Part> parts = request.getParts();
		
		String fileNames = "";
		
		for(Part p : parts) {
			if(!p.getName().equals("file")) continue; // �� �ƴϰ� �н� ����?
			
			
			//Part filePart = request.getPart("file");
			Part filePart = p;
			String fileName = filePart.getSubmittedFileName();
			fileNames += fileName+",";
	
			ServletContext application = request.getServletContext();
			String urlPath = "/upload";
			String realPath = application.getRealPath(urlPath);
			
			File file = new File(realPath);
			if(!file.exists())
				file.mkdirs();
			else
				System.out.println("��ΰ� �����մϴ�.");
			
			System.out.println(realPath);
			
			InputStream fis = filePart.getInputStream();
			OutputStream fos = new FileOutputStream(realPath+File.separator+fileName);
			
			byte[] buf = new byte[1024];
			
			int size = 0;
			while((size = fis.read(buf)) != -1)
				fos.write(buf, 0, size);
			
			fos.close();
		}		
		System.out.printf("title : %s, content : %s", title, content);
		
		//fileNames���� ������ ������ (,)���� ������ ������ �Ŀ�
		fileNames = fileNames.substring(0, fileNames.length()-1);
		
		int result = noticeService.insertNotice(
								new Notice(title, content, "newlec", fileNames));
		
		//int result = 1;
		if(result == 0)
			response.sendRedirect("/error?code=2");
		else
			response.sendRedirect("list");
		//String sql = "insert into ";
		
		//���� ������Ʈ
		/*
		 * 1. �ұԸ�
		 * 2. ���������� ���� �������� �ʾƿ�
		 * 3. ����? �ҽ� �ڵ� ����
		 *  
		 */
		
		
	}
	
}
