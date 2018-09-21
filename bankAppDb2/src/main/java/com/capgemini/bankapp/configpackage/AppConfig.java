package com.capgemini.bankapp.configpackage;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages= {"com.capgemini.bankapp"})
@PropertySource("classpath:dbutil.properties")
public class AppConfig {

	@Value("${path}")
	private String driverClassName;
	@Value("${DB_URL}")
	private String dburl;
	@Value("${db.USERNAME}")
	private String username;
	@Value("${db.PASSWORD}")
	private String password;
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(dburl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	@Bean
	public JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jdbcTemplate= new JdbcTemplate(getDataSource());
		//jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate;
	}

	}


