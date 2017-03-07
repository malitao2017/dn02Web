/*
 * LoadEmpServlet.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 加载修改页面
 * @author LT
 * @version 1.0, 2015年9月10日
 */
public class LoadEmpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		long id = Long.parseLong(req.getParameter("id"));
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			PreparedStatement sta = con.prepareStatement("select * from t_emp where id=?");
			sta.setLong(1, id);
			ResultSet rs = sta.executeQuery();
			
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			while(rs.next()){
				out.println("<form action='modify?id="+id+"' method='post'>");
				String name = rs.getString("name");
				double salary = rs.getDouble("salary");
				int age = rs.getInt("age");
				out.println("id:"+id+"<br/>");
				out.println("姓名:<input name='name' value='"+name+"'/><br/>");
				out.println("工资:<input name='salary' value='"+salary+"'/><br/>");
				out.println("年龄:<input name='age' value='"+age+"'/>");
				out.println("<input type='submit' value='提交'/>"
						+ "</form>");
			}
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
