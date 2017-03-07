/*
 * AddEmpServlet.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 添加雇员
 * 1.重定向模式
 * @author LT
 * @version 1.0, 2015年9月9日
 */
public class AddEmpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		double salary = Double.valueOf(request.getParameter("salary"));
		int age = Integer.valueOf(request.getParameter("age"));
		System.out.println("name:"+name);
		System.out.println("salaty:"+salary);
		System.out.println("age:"+age);
		
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			PreparedStatement sta = con.prepareStatement("insert into t_emp(name,salary,age) values(?,?,?)");
			sta.setString(1, name);
			sta.setDouble(2, salary);
			sta.setInt(3, age);
			sta.executeUpdate();
			/*
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("添加雇员成功:"+name);
			out.println("<a href='list'>查看雇员</a>");
			out.close();
			*/
			//更改为重定向模式
			//重定向之前不能有out.close();
			response.sendRedirect("list");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}finally{
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
