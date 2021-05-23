package org.rynios.media.importer.picture

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Ignore
import spock.lang.Specification

@SpringBootTest
class DirectoryScannerSpec extends Specification {

    @Autowired
    private DirectoryScanner directoryScanner;

    @Ignore
    def "should scan directory for media files"() {
        given: 'nothing'

        when: 'no exception'
        directoryScanner.scan()

        then: 'no Exception is thrown'
        noExceptionThrown()
    }
}
