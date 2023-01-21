package vkir.sqlitepostgres.sqlite.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "B")
@Data
public class SQLiteB {

    @Id
    private Long id;
    private String subject;
}