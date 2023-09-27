package jsoft;

import java.sql.*;
import java.util.*;

public class ConnectionPoolImpl implements ConnectionPool {
	
	//Trình điều khiển làm việc với csdl
	private String driver;
	
	//Đường dẫn thực thi SQL
	private String url;
	
	//Tài khoản sử dụng sql
	private String username;
	private String userpass;
	
	//Đối tượng lưu trữ kết nối
	private Stack<Connection> pool;
	
	public ConnectionPoolImpl() {
		
		//Xác định trình điều khiển của mysql
		this.driver = "com.mysql.jdbc.Driver";
		
		//Xác định đường dẫn thực thi của mysql
		this.url = "jdbc:mysql://localhost:3306/jp210302_data?allowMultiQueries=true";
		
		//Xác định tài khoản làm việc
		this.username = "root";
		this.userpass = "123456";
		
		//Nạp trình điều khiển
		this.loadDriver();
		
		//Khởi tại bộ nhớ đối tượng lưu trữ
		this.pool = new Stack<>();
		
	}
	
	/**
	 * Phương thức nạp trình điều khiển
	 */
	private void loadDriver() {
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Connection getConnection(String objectName) throws SQLException {
		// TODO Auto-generated method stub
		
		if(this.pool.isEmpty()) {
			//Khởi tạo kết nối
			System.out.println(objectName + " have created a new Connection");
			return DriverManager.getConnection(this.url, this.username, this.userpass);
		}else {
			//Lấy một kết nối đã lưu cho đối tượng
			System.out.println(objectName + " have popped the Connection");
			return this.pool.pop();
		}
		
	}

	@Override
	public void releaseConnection(Connection con, String objectName) throws SQLException {
		// TODO Auto-generated method stub
		
		//Yêu cầu các đối tượng trả về kết nối
		System.out.println(objectName + " have pushed the Connection");
		this.pool.push(con);

	}
	
	/**
	 * Phương thức dọn dẹp
	 */
	protected void finalized()throws Throwable{
		this.pool.clear();//Loại bỏ các đối tượng
		this.pool = null;
		
		System.gc();
		
		System.out.println("ConnectionPool is closed");
	}

}
