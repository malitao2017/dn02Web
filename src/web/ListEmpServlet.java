/*
 * ListEmpServlet.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 展示全部雇员
 * @author LT
 * @version 1.0, 2015年9月10日
 */
public class ListEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "root");
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery("select * from t_emp ");
//			PreparedStatement sta = con.prepareStatement("select * from t_emp ");
//			ResultSet rs = sta.executeQuery();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<table border='1' width='60%' cellpadding='0' cellspacing='0'>"
					+ "<tr>"
						+ "<td>序号</td>"
						+ "<td>姓名</td>"
						+ "<td>工资</td>"
						+ "<td>年龄</td>"
						+ "<td>操作</td>"
					+ "</tr>");
			while (rs.next()) {
				long id = rs.getLong("id");
				String name = rs.getString("name");
				double salary = rs.getDouble("salary");
				int age = rs.getInt("age");
				out.println("<tr>"
						+ "<td>"+id+"</td>"
						+ "<td>"+name+"</td>"
						+ "<td>"+salary+"</td>"
						+ "<td>"+age+"</td>"
						+ "<td><a href='del?id="+id+"'>删除</a>&nbsp;<a href='load?id="+id+"'>修改</a></td>"
						+ "</tr>");
			}
			out.println("</table>");
			out.println("<a href='addEmp.html' >添加雇员</a>");
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new ServletException(e);
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
}
