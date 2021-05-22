package org.rynios.media.viewer.picture;

import org.openapi.example.api.MediaApi;
import org.openapi.example.model.PictureV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PictureApi implements MediaApi {

    @Autowired
    private PictureService pictureService;

    @Override
    public ResponseEntity<List<PictureV1>> mediaPicturesGet() {
        return ResponseEntity.ok(pictureService.findAllPictures());
    }
}
