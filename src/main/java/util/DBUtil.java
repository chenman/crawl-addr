package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtil {

	// JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/addrdb?serverTimezone=GMT";

	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "chenman";
	static final String PASS = "123456";

	public static void insertAddr(String qx,String village,String address,String addid,String addqr,String latitude,String longitude) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 注册 JDBC 驱动
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 打开链接
			System.out.println("连接数据库...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 执行查询
			System.out.println(" 实例化Statement对象...");
			stmt = conn.prepareStatement("insert into address_tbl (qx,village,address,addid,addqr,latitude,longitude) "
			        + "values(?,?,?,?,?,?,?)");
			stmt.setString(1, qx);
			stmt.setString(2, village);
			stmt.setString(3, address);
			stmt.setString(4, addid);
			stmt.setString(5, addqr);
			stmt.setString(6, latitude);
			stmt.setString(7, longitude);
			stmt.executeUpdate();

			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException se) {
			se.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
