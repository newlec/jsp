package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
/member-list
/admin-notice-list
/member-notice-list
/event-list
/cart-list

/member/list
/admin/notice/list

-> http://localhost:8080/notice/list   ==><a href="detail" >자세한 페이지</a>

/notice/list
/notice/detail
/notice/reg
/notice/edit

/event/list
/cart/list*/

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORN", "newlec");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			List<Map<String, Object>> list = new ArrayList<>();
			
			while(rs.next()){ 
				
				Map<String, Object> notice = new HashMap<>();
				
				notice.put("id", rs.getInt("ID"));
				notice.put("title", rs.getString("TITLE"));
				notice.put("writerId", rs.getString("WRITER_ID"));
				notice.put("regdate", rs.getDate("REGDATE"));
				notice.put("hit", rs.getInt("HIT"));
				
				list.add(notice);		
			}

			rs.close();
			st.close();
			con.close();
			
			request.setAttribute("list", list);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request
			.getRequestDispatcher("/WEB-INF/view/notice/list.jsp") 
			.forward(request, response);
		
	}
}
