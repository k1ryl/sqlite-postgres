package vkir.sqlitepostgres.migration.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import vkir.sqlitepostgres.postgres.entity.PgA;
import vkir.sqlitepostgres.postgres.entity.PgB;
import vkir.sqlitepostgres.sqlite.entity.SQLiteA;
import vkir.sqlitepostgres.sqlite.entity.SQLiteB;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SQLitePostgresEntityMapper {

    @Named("TO_PGA")
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "sqliteUUID", source = "sqliteUUID")
    PgA mapToPgA(SQLiteA entity, String sqliteUUID);

    @Named("TO_PGB")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sqliteUUID", source = "sqliteUUID")
    PgB mapToPgB(SQLiteB entity, String sqliteUUID);

    @IterableMapping(qualifiedByName = "TO_PGA")
    default List<PgA> mapToPgA(List<SQLiteA> list, String sqliteUUID) {
        if (list == null) {
            return null;
        }

        List<PgA> mapped = new ArrayList<>(list.size());
        for (SQLiteA sqliteA : list) {
            mapped.add(mapToPgA(sqliteA, sqliteUUID));
        }
        return mapped;
    }

    @IterableMapping(qualifiedByName = "TO_PGB")
    default List<PgB> mapToPgB(List<SQLiteB> list, String sqliteUUID) {
        if (list == null) {
            return null;
        }

        List<PgB> mapped = new ArrayList<>(list.size());
        for (SQLiteB sqliteB : list) {
            mapped.add(mapToPgB(sqliteB, sqliteUUID));
        }

        return mapped;
    }
}
