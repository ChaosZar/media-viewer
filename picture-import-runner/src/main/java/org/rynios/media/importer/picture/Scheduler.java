package org.rynios.media.importer.picture;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Scheduled(cron = "${scheduler.cron}")
    public void scan() {

    }

}
