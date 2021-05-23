package org.rynios.media.importer.picture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    private DirectoryScanner directoryScanner;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        startAllScanner();
    }

    @Scheduled(cron = "${scheduler.cron}")
    public void startAllScanner() {
        directoryScanner.scan();
    }

}
