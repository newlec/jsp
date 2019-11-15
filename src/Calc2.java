import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	
	// GET�� ������ ó���� �ϰ�
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
    	
		PrintWriter out = response.getWriter();
		// ��� �ڵ�
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
		out.write("		<input type=\"submit\" name=\"btn\"  value=\"����\">");
		out.write("		<input type=\"submit\"  name=\"btn\" value=\"����\">");
		out.write("		<input type=\"submit\"  name=\"btn\" value=\"���\">");
		out.write("	</form>");
		out.write("</body>");
			out.write("</html>");
	}
	
	// POST�� ���� ó��....
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
		
		// �����̳� ������ ������ ����/���� �����ϴ� ����� ������ �ʾƼ�
		// ������. �̰��� ��ġ �����ϴ� ������ ��θ� ��Ż�ϴ� ������ �ν��� �ȵ�.
		
		doGet(request, response);
		
	}
}
