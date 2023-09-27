package jsoft;

import java.sql.*;

public interface ConnectionPool {

	//Phương thức cung ccaaps kết nối khi một đối tượng cần
	public Connection getConnection(String objectName)throws SQLException;
	
	//Phương thức thu hồi kết nối
	public void releaseConnection(Connection con, String objectName)throws SQLException;
	
	
	
}
