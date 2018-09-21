package com.capgemini.bankapp.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:dbutil.properties")
public class DbUtil {
	@Value("${DB_URL}")
	private String dburl;
	@Value("${db.USERNAME}")
	private  String username;
	@Value("${db.PASSWORD}")
	private  String password;
	@Value("${path}")
	private String path;

	
	public  Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName(path);
//			System.out.println(dburl+"\t"+username+"\t"+password);
			connection = DriverManager.getConnection(dburl, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
