package com.frank.dbtest.config;

import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 系统配置文件类对象
 * 
 * @author xyl
 *
 */
public class SystemConfig {

	static final Logger logger = Logger.getLogger(SystemConfig.class);
	static final String SYSTEM_CONFIG_FILE_NAME = "SystemConfig.properties";

	static SystemConfig instance = null;

	String DBIPP;
	String DBName;
	String DBUser;
	String DBPwd;

	public static SystemConfig getInstance() {
		if (instance == null)
			instance = new SystemConfig();
		return instance;
	}

	private SystemConfig() {
		initData();
	}

	private void initData() {
		Properties properties = new Properties();

		try {
			properties.load(new InputStreamReader(
					SystemConfig.class.getClassLoader().getResourceAsStream("SystemConfig.properties"), "UTF-8"));

			String temp = null;

			temp = null;
			if (properties.containsKey("DBIPP"))
				temp = properties.getProperty("DBIPP");
			if (!StringUtils.isEmpty(temp)) {
				DBIPP = temp;
			}

			temp = null;
			if (properties.containsKey("DBName"))
				temp = properties.getProperty("DBName");
			if (!StringUtils.isEmpty(temp)) {
				DBName = temp;
			}

			temp = null;
			if (properties.containsKey("DBUser"))
				temp = properties.getProperty("DBUser");
			if (!StringUtils.isEmpty(temp)) {
				DBUser = temp;
			}
			
			temp = null;
			if (properties.containsKey("DBPwd"))
				temp = properties.getProperty("DBPwd");
			if (!StringUtils.isEmpty(temp)) {
				DBPwd = temp;
			}
			properties = null;
		} catch (Exception ex) {
			instance = null;
			logger.error("load SystemConfig.properties file error", ex);
		}
	}

	public String getDBIPP() {
		return DBIPP;
	}

	public String getDBName() {
		return DBName;
	}

	public String getDBUser() {
		return DBUser;
	}

	public String getDBPwd() {
		return DBPwd;
	}
	
	
}
