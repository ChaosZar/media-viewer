package org.rynios.media.viewer.picture.db;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Directory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String path;

    @Column
    private LocalDateTime lastChanged;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Picture> pictures = new ArrayList<>();

    public Directory() {
    }

    public Directory(String path) {
        this.path = path;
    }
}