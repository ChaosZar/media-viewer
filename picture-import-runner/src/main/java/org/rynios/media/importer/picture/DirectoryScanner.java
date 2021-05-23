package org.rynios.media.importer.picture;

import lombok.extern.slf4j.Slf4j;
import org.rynios.media.importer.picture.db.Directory;
import org.rynios.media.importer.picture.db.DirectoryRepository;
import org.rynios.media.importer.picture.db.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;

@Component
@Slf4j
public class DirectoryScanner implements MediaScanner {

    @Autowired
    private DirectoryRepository directoryRepository;

    @Value("${scanner.files.path}")
    private String rootDir;

    @EventListener(ApplicationReadyEvent.class)
    public void buildOnStartup() {
    }


    @Override
    public void scan() {
        try {
            dropOldEntries();
            walkTreeSaveBitches();
        } catch (IOException e) {
            log.error("Failed to scan file system!", e);
        }
    }

    private void dropOldEntries() {
        directoryRepository.deleteAll();
    }

    private void walkTreeSaveBitches() throws IOException {
        Path rootDir = Path.of(this.rootDir);

        final HashMap<String, Directory> pathToDirectory = new HashMap<>();

        Files.walk(rootDir).forEach(path -> {
            log.info(path.toString());
            if (Files.isDirectory(path)) {
                pathToDirectory.put(path.toString(), onDirectory(path));
            } else {
                onFile(pathToDirectory.get(path.getParent().toString()), path);
            }
        });

        directoryRepository.saveAll(pathToDirectory.values());
    }

    private void onFile(Directory directory, Path path) {
        Picture picture = new Picture();
        picture.setFilename(path.getFileName().toString());
        picture.setLastChanged(getLastModifiedTime(path));
        picture.setMediaType(getMediaType(path));
        directory.getPictures().add(picture);
    }

    private String getMediaType(Path path) {
        try {
            return Files.probeContentType(path);
        } catch (IOException e) {
            log.error("failed to get mediatype for file: " + path, e);
            return "";
        }
    }

    private Directory onDirectory(Path path) {
        return new Directory(
                path.toString().replace('\\', '/').replace(rootDir, ""),
                getLastModifiedTime(path)
        );
    }

    private LocalDateTime getLastModifiedTime(Path path) {
        LocalDateTime lastModifiedTime = null;
        try {
            lastModifiedTime = LocalDateTime.ofInstant(Files.getLastModifiedTime(path).toInstant(), ZoneId.systemDefault());
        } catch (IOException e) {
            log.error("Failed to get lastModified of file: " + path, e);
        }
        return lastModifiedTime;
    }
}
