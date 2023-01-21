package vkir.sqlitepostgres.sqlite.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sqlite.SQLiteDataSource;

@Configuration
public class SqliteDatasourceConfiguration {

    public static final String SQLITE_DATASOURCE_BEAN_NAME = "sqliteDataSource";
    public static final String SQLITE_DATASOURCE_URL_PREFIX = "jdbc:sqlite:sqlite/";

    @Bean
    @ConfigurationProperties("spring.sqlite.datasource")
    public DataSourceProperties sqliteDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = SQLITE_DATASOURCE_BEAN_NAME)
    public SQLiteDataSource sqliteDataSource(
            @Qualifier("sqliteDataSourceProperties") DataSourceProperties sqliteDataSourceProperties) {
        SQLiteDataSource sqliteDataSource = new SQLiteDataSource();
        sqliteDataSource.setUrl(sqliteDataSourceProperties.getUrl());
        return sqliteDataSource;
    }
}
