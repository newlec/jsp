import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;

/*

���� : �ڹ� �� ���α׷�
��+ : UI -> ��/��� ����� ���ο� ���� �����ߴ�.
�ڹ� + (��)��/��� ���� : request. response
response : getWriter(), serContentType, setChar....
request : getParameter(), setChar....

getParameter : QueryString -> Form : get/post

�ѱ� (�Է�)���� : request.setChar...("UTF-8");
 

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
    	// http://localhost:8080/hello           -> 10��
    	int cnt = 10;
    	String cnt_ = request.getParameter("cnt");
    	
    	if(cnt_ != null && !cnt_.equals(""))
    		cnt = Integer.parseInt(request.getParameter("cnt"));   	
    	
    	for(int i=0; i<cnt; i++)
    		out.println("�ȳ�? Servlet hehehe~ AnoAnotation<br>");
    	
    	out.println("<a href=\"index.html\">����������</a>");
    	
        System.out.println("Hello Servlet");
    }
}
