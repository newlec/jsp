import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sum")
public class Sum extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
    	
		PrintWriter out = response.getWriter();
		String[] xs = request.getParameterValues("x");
		
		int sum = 0;
		for(int i=0; i<xs.length; i++)				
			if(!xs[i].equals(""))
				sum += Integer.parseInt(xs[i]);
					
		out.printf("°á°ú: %d<br >", sum);
	}
}
