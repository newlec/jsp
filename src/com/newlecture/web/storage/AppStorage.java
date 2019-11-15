package com.newlecture.web.storage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/hoho/app")
public class AppStorage extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count = 0;		
		// 저장소에 count 값이 있다면 읽어와서 설정해야지..
		// 1. application 저장소 사용
		//ServletContext application = request.getServletContext();
		//Object count_ = application.getAttribute("count");
		
		// 2. session 저장소 사용
		//HttpSession session = request.getSession();
		//Object count_ = session.getAttribute("count");		
		
		// 3. Cookie 저장소 사용 : 클라이언트(웹브라우저) 저장소
		/*
		Object count_ = null;
		Cookie[] cookies = request.getCookies();		
		if(cookies != null)
			for(Cookie c : cookies)
				if(c.getName().equals("count")) {
					count_ = c.getValue();
					break;
				}
		*/
		
		// 4. TextFiled를 이용한 저장소
		Object count_ = request.getParameter("count");
		
		if(count_ != null)
			count = Integer.parseInt(count_.toString());
		
		response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	out.printf("count : %d<br >", count++);
    	
    	out.printf("<form>"
    			+ "<input type=\"hidden\" name=\"count\" value=\"%d\" >"
    			+ "<input type=\"submit\" value=\"+\">"
    			+ "</form>", count);
    	//application.setAttribute("count", count);
    	//session.setAttribute("count", count);
    	/*
    	Cookie cookie = new Cookie("count", String.valueOf(count));
    	cookie.setPath("/"); // -> /admin/*
    	*/
    	//cookie.setMaxAge(60*30);
    	// 쿠키의 키가 같아도 경로가 다르면 여러개가 있게 된다.
    	// 브라우저에 경로에 따라서 그 개수가 여러개 만들어진다.
    	//response.addCookie(cookie);
    	
    	
	}
}
