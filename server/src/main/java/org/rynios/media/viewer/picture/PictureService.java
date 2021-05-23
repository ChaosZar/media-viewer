package org.rynios.media.viewer.picture;

import org.openapi.example.model.PictureV1;
import org.rynios.media.viewer.picture.db.DirectoryRepository;
import org.rynios.media.viewer.picture.db.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureService {

    @Autowired
    private DirectoryRepository directoryRepository;

    public List<PictureV1> findAllPictures(Integer directoryId) {
        return directoryRepository.findById(directoryId).getPictures().stream()
                .sorted(Comparator.comparing(Picture::getLastChanged).reversed())
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private PictureV1 convert(Picture picture) {
        return new PictureV1()
                .id(picture.getId())
                .name(picture.getFilename())
                .mediatype(picture.getMediaType());
    }

}
