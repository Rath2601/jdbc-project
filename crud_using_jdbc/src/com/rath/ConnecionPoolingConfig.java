package com.rath;

import com.zaxxer.hikari.HikariConfig;

public class ConnecionPoolingConfig {

	private static final String URL = "jdbc:postgresql://localhost:5432/practice-db?useSSL=false";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Ramadoss6*";

	public static HikariConfig getConfig() {
		HikariConfig config = new HikariConfig();

		config.setJdbcUrl(URL);
		config.setUsername(USER);
		config.setPassword(PASSWORD);
		config.setMaximumPoolSize(10);
		config.setIdleTimeout(300000); //useless If we configured fixed size pool
		config.setAutoCommit(true);
		config.setDriverClassName("org.postgresql.Driver");
		config.setMaxLifetime(180000);

		return config;
	}
}
