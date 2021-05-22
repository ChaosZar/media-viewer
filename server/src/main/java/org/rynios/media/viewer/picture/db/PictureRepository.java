package org.rynios.media.viewer.picture.db;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface PictureRepository extends Repository<Picture, Integer> {

    List<Picture> findAll();

}
