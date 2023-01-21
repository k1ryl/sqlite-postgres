package vkir.sqlitepostgres.postgres.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "a")
@Data
public class PgA {

    @Id
    @GeneratedValue
    private UUID uuid;
    private String title;
    private Long number;
}