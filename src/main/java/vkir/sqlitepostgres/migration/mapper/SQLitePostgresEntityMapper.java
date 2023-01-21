package vkir.sqlitepostgres.migration.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import vkir.sqlitepostgres.postgres.entity.PgA;
import vkir.sqlitepostgres.postgres.entity.PgB;
import vkir.sqlitepostgres.sqlite.entity.SQLiteA;
import vkir.sqlitepostgres.sqlite.entity.SQLiteB;

import java.util.List;

@Mapper
public interface SQLitePostgresEntityMapper {

    @Named("TO_PGA")
    @Mapping(target = "uuid", ignore = true)
    PgA mapToPgA(SQLiteA entity);

    @Named("TO_PGB")
    @Mapping(target = "id", ignore = true)
    PgB mapToPgB(SQLiteB entity);

    @IterableMapping(qualifiedByName = "TO_PGA")
    List<PgA> mapToPgA(List<SQLiteA> list);

    @IterableMapping(qualifiedByName = "TO_PGB")
    List<PgB> mapToPgB(List<SQLiteB> list);
}
