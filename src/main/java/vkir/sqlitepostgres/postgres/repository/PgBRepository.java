package vkir.sqlitepostgres.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vkir.sqlitepostgres.postgres.entity.PgB;

@Repository
public interface PgBRepository extends JpaRepository<PgB, Long> {

}