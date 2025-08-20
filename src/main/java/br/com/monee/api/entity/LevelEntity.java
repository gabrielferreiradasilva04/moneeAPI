package br.com.monee.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "level")
public class LevelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 255)
    private String title;
    @Column(nullable = false, length = 255)
    private String description;
    private int xpMinimum;
    private int xpMaximum;

    public LevelEntity (String title, String description, int xpMinimum, int xpMaximum){
        this.title = title;
        this.description = description;
        this.xpMinimum = xpMinimum;
        this.xpMaximum = xpMaximum;
    }
}
