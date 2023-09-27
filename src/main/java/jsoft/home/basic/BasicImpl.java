package jsoft.home.basic;

import java.sql.*;
import java.util.ArrayList;

import jsoft.*;

public class BasicImpl implements Basic {
	
	//Bộ quản lý kết nối của riêng basic
	private ConnectionPool cp;
	
	//Kết nối để basic sử dụng
	protected Connection con;
	
	//Đối tượng làm việc với basic
	private String objectName;
	
	public BasicImpl(ConnectionPool cp, String objectName) {
		//Xác định đối tượng làm việc
		this.objectName = objectName;
		
		//Xác định bộ quản lý kết nối
		if(cp == null) {
			this.cp = new ConnectionPoolImpl();
		}else {
			this.cp = cp;
		}
		
		//Xin kết nối để làm việc
		try {
			this.con = this.cp.getConnection(this.objectName);
			
			
			//Kiẻm tra kết nối
			if(this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet get(String sql, int id) {
		// TODO Auto-generated method stub
		
		//Biên dịch câu lệnh
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			
			if(id>0) {
				pre.setInt(1, id);
			}
			
			return pre.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			
			sql = null;
			
		}
		
		return null;
	}

	@Override
	public synchronized ResultSet get(ArrayList<String> sql, String name, String pass) {
		// TODO Auto-generated method stu
		
		try {
			String str_select = sql.get(0);
			PreparedStatement pre = this.con.prepareStatement(str_select);
			
			pre.setString(1, name);
			pre.setString(2, pass);
			
			ResultSet rs = pre.executeQuery();
			if(rs != null) {
				String str_upd = sql.get(1);
				PreparedStatement preupd = this.con.prepareStatement(str_upd);
				preupd.setString(1, name);
				preupd.setString(2, pass);
				int result = preupd.executeUpdate();
				if(result == 0) {
					this.con.rollback();
					return null;
				}else {
					if(!this.con.getAutoCommit()) {
						this.con.commit();	
					}
					return rs;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			name = null;
			pass = null;
		}
		
		return null;
		
	}

	@Override
	public ResultSet gets(String sql) {
		// TODO Auto-generated method stub
		return this.get(sql, 0);
	}

	@Override
	public ConnectionPool getCP() {
		// TODO Auto-generated method stub
		
		//Chia sẻ bộ quản lý kết nối
		return this.cp;
	}

	@Override
	public void releaseConnection() {
		// TODO Auto-generated method stub

		//ra lệnh trả về kết nối
		try {
			this.cp.releaseConnection(this.con, this.objectName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public ArrayList<ResultSet> getReList(String multiSelect) {
		// TODO Auto-generated method stub
		ArrayList<ResultSet> res = new ArrayList<>();
		
		try {
			PreparedStatement pre = this.con.prepareStatement(multiSelect);
			boolean result = pre.execute();
			do {
				res.add(pre.getResultSet());
				
				
				//Sang ResultSet tiếp theo
				//getMoreResults() không có tham số thì ResultSet hiện tại sẽ bị đóng;
				result = pre.getMoreResults(Statement.KEEP_CURRENT_RESULT);
			}while(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		return res;
	}

}
