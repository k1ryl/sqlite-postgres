package vkir.sqlitepostgres.migration.entity;

import lombok.Data;
import vkir.sqlitepostgres.sqlite.entity.SQLiteA;
import vkir.sqlitepostgres.sqlite.entity.SQLiteB;

import java.util.List;

@Data
public class MigrationData {

    private List<SQLiteA> sqliteARecords;
    private List<SQLiteB> sqliteBRecords;
}
