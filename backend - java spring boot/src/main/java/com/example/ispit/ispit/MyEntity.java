package com.example.ispit.ispit;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "my_entity")
@Builder
@Data
public class MyEntity {
    @Id
    @SequenceGenerator(name = "my_entity_id_seq", sequenceName = "my_entity_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_entity_id_seq")
    private Integer id;
}
