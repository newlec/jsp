import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hoho/bb/calc")
public class Calc extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
    	
		PrintWriter out = response.getWriter();
		
		int x = 0;
		int y = 0;
		int result = 0;
		
		String x_ = request.getParameter("x");
		String y_ = request.getParameter("y");		
		String operator = request.getParameter("op");
				
		if(x_ != null && !x_.equals(""))
			x = Integer.parseInt(x_);
		
		if(y_ != null && !y_.equals(""))
			y = Integer.parseInt(y_);
		
		switch(operator) {
		case "µ¡¼À":
			result = x + y;
			break;
		case "»¬¼À":
			result = x - y;
			break;
		}		
		
		out.printf("°á°ú: %d<br >", result);
	}
}
