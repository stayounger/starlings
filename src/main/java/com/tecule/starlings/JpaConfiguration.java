package com.tecule.starlings;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.cache.HashtableCacheProvider;
import org.hibernate.dialect.H2Dialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JpaConfiguration {

	@Value("#{dataSource}")
	private javax.sql.DataSource dataSource;	

	@Bean
	public Map<String, Object> jpaProperties() {
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("hibernate.dialect", H2Dialect.class.getName());
		props.put("hibernate.cache.provider_class", HashtableCacheProvider.class.getName());
		return props;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(false);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.H2);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager( localContainerEntityManagerFactoryBean().getObject() );
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
//		lef.setDataSource(this.dataSource);
//		lef.setJpaPropertyMap(this.jpaProperties());
//		lef.setJpaVendorAdapter(this.jpaVendorAdapter());
		
		lef.setDataSource(this.getDataSource());
		lef.setJpaProperties(this.getJpaProperties());
		lef.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return lef;
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://192.168.101.151:3306/tecule");
		dataSource.setUsername("tecule");
		dataSource.setPassword("123456");
		
		return dataSource;
	}
	
	public Properties getJpaProperties() {
		Properties jpaProperties = new Properties();
		
		jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		jpaProperties.setProperty("hibernate.show_sql", "true");
		jpaProperties.setProperty("hibernate.format_sql", "true");
		jpaProperties.setProperty("hibernate.use_sql_comments", "false");
		jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		
		return jpaProperties;
	}
}
