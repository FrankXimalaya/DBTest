package com.frank.dbtest.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.frank.dbtest.config.SystemConfig;

public class DBHelper {

	private static final Logger logger = Logger.getLogger(DBHelper.class);

	private static Connection CONNECTION;

    private DBHelper() {
    	
    }
    
	public static synchronized Connection getInstance() throws Exception {
		
		if (null == CONNECTION) {
			Class.forName("com.mysql.jdbc.Driver");
			String connString = String.format("jdbc:mysql://%s/%s?user=%s&password=%s&rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8",
					SystemConfig.getInstance().getDBIPP(), SystemConfig.getInstance().getDBName(), 
					SystemConfig.getInstance().getDBUser(), SystemConfig.getInstance().getDBPwd());
			CONNECTION = DriverManager.getConnection(connString);
		}
		return CONNECTION;
	}

	public int findCount(String sql) throws Exception {
		int rowTotal = 0;
		Connection conn = getInstance();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				rowTotal = rs.getInt(1);
		} catch (Exception ex) {
			throw new Exception(String.format("findCount sql:%s error:%s", sql, ex.getMessage()));
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}
		return rowTotal;
	}

	public long[] findMinMaxValue(String sql) throws Exception {
		long[] result = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getInstance();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			result = new long[] { rs.getLong(1), rs.getLong(2) };
		} catch (Exception ex) {
			throw new Exception(String.format("findMinMaxValue sql:%s error:%s", sql, ex.getMessage()));
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}
		return result;
	}

	public String findId(String sql) throws Exception {
		String result = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getInstance();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				result = rs.getString(1);
		} catch (Exception ex) {
			throw new Exception(String.format("findId sql:%s error:%s", sql, ex.getMessage()));
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}
		return result;
	}

	public static boolean batchExecute(List<String> insertSqlArray) {
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getInstance();
			conn.setAutoCommit(false);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			for (String item : insertSqlArray) {
				stmt.addBatch(item);
			}
			stmt.executeBatch();
			conn.commit();
			result = true;
		} catch (Exception ex) {
			logger.error("batchExecute error-------------------:" + ex);
		}
		return result;
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
	}

	public static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
	}

	public void closePreparedStatement(PreparedStatement prep) {
		if (prep != null) {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			prep = null;
		}
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
}
