package vkir.sqlitepostgres.sqlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vkir.sqlitepostgres.sqlite.entity.SQLiteA;

import java.util.UUID;

@Repository
public interface SQLiteARepository extends JpaRepository<SQLiteA, UUID> {

}