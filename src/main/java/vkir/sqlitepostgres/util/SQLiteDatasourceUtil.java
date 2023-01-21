package vkir.sqlitepostgres.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.sqlite.SQLiteDataSource;
import vkir.sqlitepostgres.sqlite.repository.SQLiteARepository;

import static vkir.sqlitepostgres.sqlite.config.SqliteDatasourceConfiguration.SQLITE_DATASOURCE_BEAN_NAME;
import static vkir.sqlitepostgres.sqlite.config.SqliteDatasourceConfiguration.SQLITE_DATASOURCE_URL_PREFIX;

@Component
@RequiredArgsConstructor
public class SQLiteDatasourceUtil {

    private final ApplicationContext applicationContext;
    private final SQLiteARepository sqliteARepository;

    public void switchDatasourceToDefault() {
        switchDatasource("default.db");
        sqliteARepository.flush();
    }

    public void switchDatasource(String filename) {
        SQLiteDataSource sqliteDataSource = applicationContext.getBean(SQLITE_DATASOURCE_BEAN_NAME, SQLiteDataSource.class);
        sqliteDataSource.setUrl(SQLITE_DATASOURCE_URL_PREFIX + filename);
    }
}
