package org.rynios.media.viewer.picture;

import org.openapi.example.model.DirectoryV1;
import org.rynios.media.viewer.picture.db.Directory;
import org.rynios.media.viewer.picture.db.DirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureDirectoryService {

    @Autowired
    private DirectoryRepository directoryRepository;

    public List<DirectoryV1> getAllDirectories() {
        return directoryRepository.findAllOrderByLastChangedDesc().stream()
                .filter(dir -> !dir.getPictures().isEmpty())
                .filter(dir -> !dir.getPath().isBlank())
                .map(this::toDirectoryResponse)
                .collect(Collectors.toList());

    }

    private DirectoryV1 toDirectoryResponse(Directory directory) {
        return new DirectoryV1().id(directory.getId())
                .path(directory.getPath());
    }

}
