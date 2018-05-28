package org.lanqiao.dbutil;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Dbutils {
	//定义ThreadLocal对象，用于存放 Connection对象
	private static ThreadLocal<Connection> threadlocal=new ThreadLocal<Connection>();
	//定义数据源对象
	private static ComboPooledDataSource ds =new ComboPooledDataSource("lanqiao");
	
	public static DataSource getDataSource(){
		
		  return ds ; 
		 }
	//从c3p0连接池中获取Connection对象
	public static Connection getConnection() {
		Connection conn =threadlocal.get();
		if(conn ==null) {
			try {
				conn=ds.getConnection();
			} 
			
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				threadlocal.set(conn);
			}
		}
		
		return conn;
	}
	//开启事务
	public static void beginTransaction() {
		Connection conn=getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//提交事物
	public static void commitTransaction() {
		Connection conn=threadlocal.get();
		if(conn !=null) {
			try {
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//回滚事物]
	public static void rollbackTransaction() {
		Connection conn =threadlocal.get();
		if(conn !=null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//关闭连接
	public static void close() {
		Connection conn =threadlocal.get();
		if(conn !=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				threadlocal.remove();
			}
		}
	}
	//

}
