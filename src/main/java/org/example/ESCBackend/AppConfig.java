package org.example.ESCBackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
public class AppConfig {

    @Value("${db.driver.name}")
    private String driverClassName;
    @Value("${db.url}")
    private String dbUrl;
    @Value("${db.user}")
    private String dbUsername;
    @Value("${db.password}")
    private String dbPassword;

    @Bean
    public DataSource springDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }
}

//когда буду делать бэк для сайта то там вынести это дело в yaml
//а в этом проекте пусть так для наглядности