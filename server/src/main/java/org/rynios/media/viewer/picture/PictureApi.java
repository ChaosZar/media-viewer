package org.rynios.media.viewer.picture;

import org.openapi.example.api.MediaApi;
import org.openapi.example.model.DirectoryV1;
import org.openapi.example.model.PictureV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PictureApi implements MediaApi {

    @Autowired
    private PictureService pictureService;

    @Autowired
    private PictureDirectoryService pictureDirectoryService;

    @Override
    public ResponseEntity<List<PictureV1>> mediaPicturesGet(Integer directoryId) {
        return ResponseEntity.ok(pictureService.findAllPictures(directoryId));
    }

    @Override
    public ResponseEntity<List<DirectoryV1>> getAllDirectories() {
        return ResponseEntity.ok(pictureDirectoryService.getAllDirectories());
    }
}
