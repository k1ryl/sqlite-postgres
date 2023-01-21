package vkir.sqlitepostgres.postgres.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "a")
@Data
public class PgA {

    @Id
    @GeneratedValue
    private UUID uuid;
    @Column(name = "sqlite_uuid")
    private String sqliteUUID;
    private String title;
    private Long number;
}