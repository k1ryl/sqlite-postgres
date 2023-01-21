package vkir.sqlitepostgres.sqlite.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vkir.sqlitepostgres.sqlite.entity.SQLiteA;
import vkir.sqlitepostgres.sqlite.repository.SQLiteARepository;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = SQLiteARepository.class,
        entityManagerFactoryRef = "sqliteEntityManagerFactory",
        transactionManagerRef = "sqliteTransactionManager"
)
@EntityScan(
        basePackageClasses = SQLiteA.class
)
public class SqliteJpaConfiguration {

    @Value("${spring.sqlite.datasource.hibernate.dialect}")
    private String dialect;

    @Bean
    public LocalContainerEntityManagerFactoryBean sqliteEntityManagerFactory(
            @Qualifier("sqliteDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = Map.ofEntries(
                Map.entry("hibernate.dialect", dialect)
        );

        return builder
                .dataSource(dataSource)
                .properties(properties)
                .packages(SQLiteA.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager sqliteTransactionManager(
            @Qualifier("sqliteEntityManagerFactory") LocalContainerEntityManagerFactoryBean todosEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(todosEntityManagerFactory.getObject()));
    }
}
