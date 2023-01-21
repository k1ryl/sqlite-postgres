package vkir.sqlitepostgres.sqlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vkir.sqlitepostgres.sqlite.entity.SQLiteB;

@Repository
public interface SQLiteBRepository extends JpaRepository<SQLiteB, Long> {

}