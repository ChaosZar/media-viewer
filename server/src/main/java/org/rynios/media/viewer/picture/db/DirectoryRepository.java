package org.rynios.media.viewer.picture.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface DirectoryRepository extends Repository<Directory, Integer> {

    @Query("from Directory dir order by dir.lastChanged desc")
    List<Directory> findAllOrderByLastChangedDesc();

    Directory findById(Integer directoryId);
}

