/*
 * ModifyEmpServlet.java
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
 * 修改数据库
 * @author LT
 * @version 1.0, 2015年9月10日
 */
public class ModifyEmpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		long id = Long.parseLong(req.getParameter("id"));
		String name = req.getParameter("name");
		double salary = Double.parseDouble(req.getParameter("salary"));
		int age = Integer.parseInt(req.getParameter("age"));
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			PreparedStatement sta = con.prepareStatement("update t_emp set name=? ,salary=?,age=? where id = ?");
			sta.setString(1,name);
			sta.setDouble(2, salary);
			sta.setInt(3, age);
			sta.setLong(4, id);
			sta.executeUpdate();
			resp.sendRedirect("list");
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
