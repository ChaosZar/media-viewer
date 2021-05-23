package org.rynios.media.viewer.picture.db;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String filename;

    @Column
    private LocalDateTime lastChanged;

    @Column
    private String mediaType;

}
