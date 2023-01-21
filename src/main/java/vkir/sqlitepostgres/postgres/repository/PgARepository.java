package vkir.sqlitepostgres.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vkir.sqlitepostgres.postgres.entity.PgA;

import java.util.UUID;

@Repository
public interface PgARepository extends JpaRepository<PgA, UUID> {

}