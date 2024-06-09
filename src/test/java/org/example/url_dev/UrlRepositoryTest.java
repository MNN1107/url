package org.example.url_dev;

import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UrlRepositoryTest {

    @Autowired
    private UrlRepository urlRepository;

    @Test
    void testFindByShortId() {
        Url url = new Url();
        url.setShortId("shortId");
        url.setLongUrl("http://long.url");
        url.setUser(new User());
        urlRepository.save(url);

        Optional<Url> foundUrl = urlRepository.findByShortId("shortId");

        assertTrue(foundUrl.isPresent());
        assertEquals("http://long.url", foundUrl.get().getLongUrl());
    }
}
