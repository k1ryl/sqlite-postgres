package vkir.sqlitepostgres.sqlite.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "A")
@Data
public class SQLiteA {

    @Id
    @Column(columnDefinition = "varchar")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID uuid;

    private String title;

    @Column(columnDefinition = "bigint")
    private Long number;
}