package com.frank.dbtest;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.frank.dbtest.config.SystemConfig;
import com.frank.dbtest.dao.DB_Proxy;
import com.frank.dbtest.model.Log;
import com.frank.dbtest.utils.DBHelper;

public class JunitTest {

	public static AtomicInteger index = new AtomicInteger(0); 


	@Test
	public void testNoBatchInsert() throws SQLException, Exception {

//		long start = System.currentTimeMillis();
//		for(int i = 0; i < 10; i ++) {
//			String sql = "INSERT INTO `t_access_log`(`F_Area_ID`,`F_LOG_TIME`) VALUES ('000001','2018-08-07 19:48:55');";
//			DB_Proxy.noBatchInsert(sql);
//		}
//		System.out.println("10万条数据非批量插入不用数据库连接池总共耗时时间"+(System.currentTimeMillis()-start)/1000+"秒");
		
		for(int i=38000;i<39000;i++) {
			String sql = "INSERT INTO `t_access_log`(`F_ID`,`F_Area_ID`,`F_LOG_TIME`) VALUES ("+i+",'000001','2018-08-07 19:48:55');";
			DB_Proxy.noBatchInsert(sql);
		}
		
	}

	@Test
	public void testBatchInsert() throws SQLException, Exception {

		long start = System.currentTimeMillis();
		List<String> stringList = new ArrayList<String>();
		for(int i = 0; i < 100000000; i ++) {
			String sql = "INSERT INTO `t_access_log` VALUES ("+i
					+ ", '000001', '8BqVKn', '8BqVKn', '2018-7-31 11:05:47', 25, 1, 0, 'Men', 0, -1, 0, 'null', 'mUwMvRqWv7t5sl+6B5ZXvcNyuby1HgQ+HU65PQ2Xojvj7Sa93R+IvRfc0b0kPuw98bIlvSg/NjsFIt695Rf+PHJlb7vABZK8Io52ORt9yT0NRCI9tklLvAiqyLp91pS7WynoPeF9v7wD0Is71OHwPPwD/ztwuxo9uWqyPOq6570FtAC9GdkuvV6Ydr5KutA9w38ePTcyRzutU3u9xoM8vCjher0yGQ29yHiUOzCOJb0MyqE9+Fv/Pas7yj26Fry8gOXlPNPjez2Gi1A9fgv6vN8PHL4W3IY99fyRvEkW3bw61ug8TnFLvbkFED3B2Ms9Wv7dvXQZZTzwfuY8Mk7ZvBYLHLz4/Ny6YcaJvJcGez1nYOO79Vh3PYQNobzZOKa9q9lHvdJ26jwRP4q9LnsVPHYviTwzkSG8/QUlvckqKr5lSHU8X/erPLOOfr0YagC9kFVRPcMv/zzBSM08qm2AuxT8mz2o+b08nsNfPDhbFbzRNxy9ndQTvTTqC75O6FA8h+UrvQFMlT0Usik7b5UKvKrkn70+M1w9PhE2vXW9LD1gpm48FokwvGrvuDwrvLY8Fi0KPmGSAzxeRaa9psvUvdFjFD1tRx88BzpzPY8o1L1GTik8fCoivXMDJL2Bxcq8lnLQvC/t3TzVo7S9UAkBvsKbMz2BFRw8IB6HPXhSIL6bp9A9BvqmvRGalrw3slO9URfPPdb7bjxdF4I8/WhOvekpwLwoO5w8ncu7vWqmUDyTUak8i5/yPVGMGz1Thhy9ZcSIO+KRpDy194q9T81LPcveOLwAeq89T7aOPeGxDz6wcD862vDbvOJl2L3lwUw93hO3PJQgdzyTANE8ZF2KO5lGBr1Qj9k8e+WGvLtJCj3Ru5w93tw/PFhTqbz6ktU9Hef+PV/kS7xMOOG98MWcvGhJ8bwSn7y9rq/4vffNTz3VqmY91XiTPQPgtDxWvTk9koaIvf4QiDyw6LC9MnmjPMXubjwdJK48o0XsvBC+1DyU0OE8BMFHPYRCKb0PKt49/eLCuuPJXL3rURO+01ytvWzCRb1x+PQ98+AcO0alGTz/fhA9sJZXvU7CXLkY0+48bd7Evewm8LtbKAU7xm+RvdriJ779fzE9GA/GPcTgMr0F0Iq9HguhOtAAKD3fW767+SKvPOf8MTxMs2m813YsvgqD0b2vvpo7QCAcvjDCmr3iyqi8L9XlvI9jej1w8L29sfg0vINPg71IFYK8ep9Tvap5rrqTNHA9O5V3PTqcvzvrRLM9mE0uvKTW1L1uHr28hgvVvI9rDz3HZjI7F7sFux141Dx1uNM8EamAPEyd1rxYb6E9m+wtveM/qbml6L87UUiBPeZXirylPRW9wyh/vQ==', '153300634723200166', 100, 1, 1, 1, '2018-7-31 11:05:49', 0, 0, 0, 0, 0, 0, NULL, NULL, NULL, 0, 'aLOC:432562451/data_LIBIMAGE/20180731/10/a899862dd5d8cc5f033ac10ecacb481f_3173', NULL, 'aLOC:432562451/data_LIBIMAGE/20180731/10/a899862dd5d8cc5f033ac10ecacb481f_3159', NULL, 0, '')";
			stringList.add(sql);
			if(i !=0 && i%5000 == 0) {
				DBHelper.batchExecute(stringList);
				stringList = new ArrayList<String>();
				System.out.println("索引到---i="+i);
			}
		}
		System.out.println("10万条数据批量插入不用数据库连接池总共耗时时间"+(System.currentTimeMillis()-start)/1000+"秒");
	}

	@Test
	public void testQuery() throws SQLException, Exception {

		long start = System.currentTimeMillis();

		Statement statement = DBHelper.getInstance().createStatement();
		int index = 0;
		int pageSize = 1000000;
		while(index != 10000000) {
			ResultSet resultSet = statement.executeQuery("Select F_Area_ID,F_Task_ID,F_Log_Time From t_access_log limit "+index+","+pageSize);
			List<Log> logList = new ArrayList<Log>();
			while(resultSet.next()) {
				Log log = new Log();
				log.setAreaId(resultSet.getString("F_Area_ID"));
				log.setTaskId(resultSet.getString("F_Task_ID"));
				log.setLogTime(resultSet.getString("F_Log_Time"));
				logList.add(log);
			}
			index+=pageSize;
			System.out.println("index --"+index);
		}
		System.out.println("单线程总耗时"+(System.currentTimeMillis()-start)/1000+"秒");
	}


	@Test
	public void testMultiQuery() throws SQLException, Exception {

		final CountDownLatch latch = new CountDownLatch(2);
		long start = System.currentTimeMillis();
		final int pageSize = 1000000;
		for(int i = 0; i<2;i++) {
			new Thread(new Runnable() {
				public void run() {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						String connString = String.format("jdbc:mysql://%s/%s?user=%s&password=%s&rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8",
								SystemConfig.getInstance().getDBIPP(), SystemConfig.getInstance().getDBName(), 
								SystemConfig.getInstance().getDBUser(), SystemConfig.getInstance().getDBPwd());
						final java.sql.Connection CONNECTION = DriverManager.getConnection(connString);
						while(index.get() < 10000000) {
							Statement statement;
							statement = CONNECTION.createStatement();
							String sql = "Select F_Area_ID,F_Task_ID,F_Log_Time From t_access_log limit "+index.getAndAdd(1000000)+","+pageSize;
							ResultSet resultSet = statement.executeQuery(sql);
							List<Log> logList = new ArrayList<Log>();
							while(resultSet.next()) {
								Log log = new Log();
								log.setAreaId(resultSet.getString("F_Area_ID"));
								log.setTaskId(resultSet.getString("F_Task_ID"));
								log.setLogTime(resultSet.getString("F_Log_Time"));
								logList.add(log);
							}
							resultSet.close();
							statement.close();
							System.out.println("执行完成"+sql);
						}

					} catch (ClassNotFoundException e1) {
					} catch (SQLException e) {
						e.printStackTrace();
					}
					latch.countDown();
				}
			}).start();
		}
		latch.await();
		System.out.println("多线程总耗时"+(System.currentTimeMillis()-start)/1000+"秒");
	}
}

