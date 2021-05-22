package org.rynios.media.viewer.picture;

import org.openapi.example.model.PictureV1;
import org.rynios.media.viewer.picture.db.Picture;
import org.rynios.media.viewer.picture.db.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    public List<PictureV1> findAllPictures() {
        return pictureRepository.findAll().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private PictureV1 convert(Picture picture) {
        return new PictureV1()
                .id(picture.getId())
                .path(picture.getPath());
    }

}
