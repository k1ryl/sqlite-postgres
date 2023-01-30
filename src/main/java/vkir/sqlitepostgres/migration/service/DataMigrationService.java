package vkir.sqlitepostgres.migration.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vkir.sqlitepostgres.migration.entity.MigrationData;
import vkir.sqlitepostgres.migration.mapper.SQLitePostgresEntityMapper;
import vkir.sqlitepostgres.postgres.repository.PgARepository;
import vkir.sqlitepostgres.postgres.repository.PgBRepository;
import vkir.sqlitepostgres.sqlite.entity.SQLiteA;
import vkir.sqlitepostgres.sqlite.entity.SQLiteB;
import vkir.sqlitepostgres.sqlite.repository.SQLiteARepository;
import vkir.sqlitepostgres.sqlite.repository.SQLiteBRepository;
import vkir.sqlitepostgres.util.SQLiteDatasourceUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DataMigrationService {

    private final SQLiteDatasourceUtil sqliteDatasourceUtil;
    private final SQLitePostgresEntityMapper mapper;
    private final SQLiteARepository sqliteARepository;
    private final SQLiteBRepository sqliteBRepository;
    private final PgARepository pgARepository;
    private final PgBRepository pgBRepository;

    public void migrate(MultipartFile file) throws IOException {
        String sqliteUUID = UUID.randomUUID().toString();
        String filename = sqliteUUID + ".db";
        Path filePath = Paths.get("sqlite", filename);
        Files.createDirectories(filePath.getParent());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        sqliteDatasourceUtil.switchDatasource(filename);
        List<SQLiteA> sqliteARecords = sqliteARepository.findAll();
        List<SQLiteB> sqliteBRecords = sqliteBRepository.findAll();
        sqliteDatasourceUtil.switchDatasourceToDefault();
        Files.delete(filePath);

        MigrationData migrationData = new MigrationData();
        migrationData.setSqliteARecords(sqliteARecords);
        migrationData.setSqliteBRecords(sqliteBRecords);
        pgARepository.saveAll(mapper.mapToPgA(migrationData.getSqliteARecords(), sqliteUUID));
        pgBRepository.saveAll(mapper.mapToPgB(migrationData.getSqliteBRecords(), sqliteUUID));
    }
}
