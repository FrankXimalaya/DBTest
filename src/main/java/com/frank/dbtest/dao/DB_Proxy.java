package com.frank.dbtest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.frank.dbtest.utils.DBHelper;

public class DB_Proxy {
	
	
	public static void noBatchInsert(String sql) throws SQLException, Exception {
		
		Statement statement = DBHelper.getInstance().createStatement();
		statement.execute(sql);
		statement.close();
	}
    

    
}
