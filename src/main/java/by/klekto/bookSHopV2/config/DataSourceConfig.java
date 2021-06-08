/*
package by.klekto.bookSHopV2.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
class DataSourceConfig {


    public DataSourceConfig getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost/web_app_db3?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("postgres");
        return (DataSourceConfig) dataSourceBuilder.build();
    }
}*/
