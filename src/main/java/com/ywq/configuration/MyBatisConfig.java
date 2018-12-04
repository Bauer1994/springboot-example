package com.ywq.configuration;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.Properties;

import javax.sql.DataSource;

@Configuration
public class MyBatisConfig {
	
	@Value("${mybatis.type-aliases-package}")
	private String entityLocation;
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());

		bean.setTypeAliasesPackage("com.ywq.entity"); //等价 bean.setTypeAliasesPackage(entityLocation);

		// //分页插件设置
		// PageHelper pageHelper = new PageHelper();
		// Properties properties = new Properties();
		// properties.setProperty("reasonable", "true");
		// properties.setProperty("supportMethodsArguments", "true");
		// properties.setProperty("returnPageInfo", "check");
		// properties.setProperty("params", "count=countSql");
		// pageHelper.setProperties(properties);
		//
		// //添加分页插件
		// bean.setPlugins(new Interceptor[]{pageHelper});

		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			// 基于注解扫描Mapper，不需配置xml路径
			// bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	
}