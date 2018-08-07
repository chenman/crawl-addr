package util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private ConnectionPool connectionPool = null;

    public DBUtil() {
        this.connectionPool = ConnectionPool.getConnectionPoolInstance();
    }
    

    private void afterQueryProcess(Statement statement, Connection connection, ResultSet result) {
        try {
            if (result != null) {
                result.close();
                result = null;
            }
        } catch (SQLException ex) {

        }

        try {
            if (statement != null) {
                statement.close();
                statement = null;
            }
        } catch (SQLException ex) {

        }

        try {
            if (connection != null) {
                if (!(connection.isClosed()))
                    connection.close();
                connection = null;
            }
        } catch (SQLException ex) {

        }
    }


	public void insertAddr(String qx,String village,String address,String addid,String addqr,String latitude,String longitude) {
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet result = null;
		try {


			conn = this.connectionPool.getConnection();

			// 执行查询
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
		} catch (SQLException se) {
			se.printStackTrace();
			afterQueryProcess(stmt, conn, result);
		} finally {
			afterQueryProcess(stmt, conn, result);
		}
	}
}
