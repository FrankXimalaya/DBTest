package com.frank.dbtest.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;

public class DruidUtils {
	
	private DruidUtils(){
		
	}
	
	private static DruidDataSource dataSourceSingle;
	
	public static  DruidDataSource getDataSource(){
		if(dataSourceSingle == null){
			synchronized (DruidUtils.class) {
				if(dataSourceSingle == null){
					DruidDataSource dataSource = new DruidDataSource();
					dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			        dataSource.setUrl("jdbc:mysql://10.0.100.110:8066/TESTDB?useUnicode=true&characterEncoding=UTF-8");
			        dataSource.setUsername("root");
			        dataSource.setPassword("123456");
			        dataSourceSingle = dataSource;
				}
			}
		}
		return dataSourceSingle;
	}
	
	public static Connection getConnection(){
		
		try {
			return DruidUtils.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
