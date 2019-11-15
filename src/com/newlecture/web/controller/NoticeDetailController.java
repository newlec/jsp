package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 출력에 사용되는 변수 : Model
		String noticeTitle = null;
		int noticeId = 0;
		String noticeContent = null;


		Integer id = Integer.parseInt(request.getParameter("id")); 

		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE WHERE ID=?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORN", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			rs.next();
			noticeTitle /* 모델 변수에 데이터 담기*/ = rs.getString("TITLE");
			noticeContent = rs.getString("CONTENT");

			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// redirection
		//response.sendRedirect("detail.jsp");
		
		// 모델을 전달
		request.setAttribute("title", noticeTitle);
		request.setAttribute("content", noticeContent);
		// forwarding
		request
			.getRequestDispatcher("detail.jsp")
			.forward(request, response);
	}
}



