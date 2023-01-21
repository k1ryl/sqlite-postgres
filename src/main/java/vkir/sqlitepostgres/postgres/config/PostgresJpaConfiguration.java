package vkir.sqlitepostgres.postgres.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vkir.sqlitepostgres.postgres.entity.PgA;
import vkir.sqlitepostgres.postgres.repository.PgARepository;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = PgARepository.class,
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresTransactionManager"
)
@EntityScan(
        basePackageClasses = PgA.class
)
public class PostgresJpaConfiguration {

    @Value("${spring.postgres.datasource.hibernate.dialect}")
    private String dialect;

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
            @Qualifier("postgresDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = Map.ofEntries(
                Map.entry("hibernate.dialect", dialect)
        );

        return builder
                .dataSource(dataSource)
                .properties(properties)
                .packages(PgA.class)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresEntityManagerFactory") LocalContainerEntityManagerFactoryBean todosEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(todosEntityManagerFactory.getObject()));
    }
}
