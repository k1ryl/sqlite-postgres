package vkir.sqlitepostgres.postgres.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "b")
@Data
public class PgB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
}