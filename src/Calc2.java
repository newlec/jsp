import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	
	// GET은 껍데기 처리를 하고
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
    	
		PrintWriter out = response.getWriter();
		// 출력 코드
		int result = 0;
		
		// Object -> Integer -> int
		Object result_ = request.getAttribute("result");
		if(result_ != null)
			result = (Integer)result_;
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("</head>");
		out.write("<body>");
		out.write("	<form action=\"calc2\" method=\"post\">");
		out.printf("		<input type=\"text\"  name=\"num\"  readonly=\"readonly\" value=\"%d\" dir=\"rtl\"><br>"
								, result);
		out.write("		<input type=\"submit\" name=\"btn\"  value=\"1\">");
		out.write("		<input type=\"submit\" name=\"btn\"  value=\"2\">");
		out.write("		<input type=\"submit\" name=\"btn\"  value=\"3\"><br>");
		out.write("		<input type=\"submit\" name=\"btn\"  value=\"덧셈\">");
		out.write("		<input type=\"submit\"  name=\"btn\" value=\"뺄셈\">");
		out.write("		<input type=\"submit\"  name=\"btn\" value=\"계산\">");
		out.write("	</form>");
		out.write("</body>");
			out.write("</html>");
	}
	
	// POST는 업무 처리....
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int result = 0;
		String num = request.getParameter("num");		
		String btn = request.getParameter("btn");
		String status = num;// + btn;
		
		switch(btn) {
		case "1":
		case "2":
		case "3":
			status += btn;
			break;
		}		
		
		result = Integer.parseInt(status);
		
		request.setAttribute("result", result); // int -> (Integer) -> Object
		
		// 생각이나 구조는 맞으나 서블릿/웹이 생각하는 개념과 통하지 않아서
		// 막혔다. 이것은 마치 관람하는 곳에서 경로를 이탈하는 행위로 인식이 된디ㅏ.
		
		doGet(request, response);
		
	}
}
