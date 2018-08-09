package com.frank.dbtest.dao;

import java.sql.SQLException;
import java.sql.Statement;

import com.frank.dbtest.utils.DruidUtils;

public class DB_Proxy {
	
	
	public static void noBatchInsert(String sql) throws SQLException, Exception {
		
		Statement statement = DruidUtils.getConnection().createStatement();
		statement.execute(sql);
		statement.close();
	}
    

    
}
