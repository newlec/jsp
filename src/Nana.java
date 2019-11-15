import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;

/*

서블릿 : 자바 웹 프로그램
웹+ : UI -> 입/출력 방법이 새로운 것이 등장했다.
자바 + (웹)입/출력 도구 : request. response
response : getWriter(), serContentType, setChar....
request : getParameter(), setChar....

getParameter : QueryString -> Form : get/post

한글 (입력)문제 : request.setChar...("UTF-8");
 

 */

@WebServlet("/hello")
public class Nana extends HttpServlet
{
    public void service(HttpServletRequest request
			, HttpServletResponse response) 
			throws IOException, ServletException
    {
    	//request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");

    	PrintWriter out = response.getWriter();
    	
    	// http://localhost:8080/hello?cnt=3
    	// http://localhost:8080/hello           -> 10번
    	int cnt = 10;
    	String cnt_ = request.getParameter("cnt");
    	
    	if(cnt_ != null && !cnt_.equals(""))
    		cnt = Integer.parseInt(request.getParameter("cnt"));   	
    	
    	for(int i=0; i<cnt; i++)
    		out.println("안녕? Servlet hehehe~ AnoAnotation<br>");
    	
    	out.println("<a href=\"index.html\">이전페이지</a>");
    	
        System.out.println("Hello Servlet");
    }
}
