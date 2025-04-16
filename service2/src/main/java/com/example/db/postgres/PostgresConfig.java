package com.example.db.postgres;

import com.atomikos.icatch.jta.UserTransactionManager;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.SystemException;
import java.util.Properties;

@Configuration
@MapperScan(
        basePackages = "com.example.common.mappers.postgres", // 이 패키지 아래 Mapper는 이 DB로
        sqlSessionFactoryRef = "postgresSqlSessionFactory"
)
public class PostgresConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.postgres")
    public DataSourceProperties postgresProps() {
        return new DataSourceProperties();
    }

//    @Bean
//    public DataSource postgresDataSource(DataSourceProperties postgresProps) {
//        return postgresProps.initializeDataSourceBuilder().build();
//    }

    @Bean
    public DataSource postgresDataSource(DataSourceProperties postgresProps) {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setLocalTransactionMode(true);
        ds.setUniqueResourceName("service2");
        ds.setXaDataSourceClassName("org.postgresql.xa.PGXADataSource");
        Properties xaProperties = new Properties();
        xaProperties.setProperty("URL", "jdbc:postgresql://192.168.11.38:5432/test");
        xaProperties.setProperty("user", "tmax");
        xaProperties.setProperty("password", "1234");
        ds.setXaProperties(xaProperties);
        // OPTIONAL pool size
        ds.setPoolSize(5);
        // OPTIONAL timeout in secs between pool cleanup tasks
        ds.setBorrowConnectionTimeout(60);
        return ds;
    }

    @Bean(initMethod="init", destroyMethod="close")
    public UserTransactionManager userTransactionManager() throws SystemException {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setTransactionTimeout(300);
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }

    @Bean
    public JtaTransactionManager jtaTransactionManager(UserTransactionManager userTransactionManager) {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(userTransactionManager); //ref="userTransactionManager"
        jtaTransactionManager.setUserTransaction(userTransactionManager); //ref="userTransactionManager"
        return jtaTransactionManager;
    }

    @Bean
    public SqlSessionFactory postgresSqlSessionFactory(DataSource postgresDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(postgresDataSource);
        // Mapper XML 파일 위치 지정
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources("classpath:/mappers/postgres/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate postgresSqlSessionTemplate(SqlSessionFactory postgresSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(postgresSqlSessionFactory);
    }
}
