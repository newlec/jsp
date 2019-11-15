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

	//톰캣 -> 서블릿을 생성 -> init() -> service() -> doXXX() 
	// -> timeout 카운트 다운 -> 소멸
	
	private NoticeService noticeService;
	
	public NoticeRegController() {
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
		.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp") 
		.forward(request, response);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//1. 파일을 사용하는 방법 2가지
		//2. 다중 파일을 업로드하는 방법		
		
		Collection<Part> parts = request.getParts();
		
		String fileNames = "";
		
		for(Part p : parts) {
			if(!p.getName().equals("file")) continue; // 넌 아니고 패스 다음?
			
			
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
				System.out.println("경로가 존재합니다.");
			
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
		
		//fileNames에서 마지막 꼬랑지 (,)때기 연산을 수행한 후에
		fileNames = fileNames.substring(0, fileNames.length()-1);
		
		int result = noticeService.insertNotice(
								new Notice(title, content, "newlec", fileNames));
		
		//int result = 1;
		if(result == 0)
			response.sendRedirect("/error?code=2");
		else
			response.sendRedirect("list");
		//String sql = "insert into ";
		
		//개인 프로젝트
		/*
		 * 1. 소규모
		 * 2. 전문가들이 같이 참여하지 않아요
		 * 3. 수정? 소스 코드 수정
		 *  
		 */
		
		
	}
	
}
