package org.example.url_dev;


import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UrlCrudServiceIntegrationTest {

    @Autowired
    private UrlCrudServiceImpl urlCrudServiceImpl;

    @Autowired
    private UrlRepository urlRepository;

    @Test
    void testCreateAndRetrieveUrl() {
        UrlDto urlDto = new UrlDto();
        urlDto.setLongURL("http://valid-url.com");
        urlDto.setCreationDate(LocalDateTime.now());
        urlDto.setExpiryDate(LocalDateTime.now().plusDays(30));
        User user = new User();
        user.setId(1L);
        urlDto.setUser(user);

        UrlDto createdUrlDto = urlCrudServiceImpl.createURL(urlDto);

        Optional<Url> retrievedUrl = urlRepository.findById(createdUrlDto.getId());
        assertTrue(retrievedUrl.isPresent());
        assertEquals("http://valid-url.com", retrievedUrl.get().getLongUrl());
    }

    @Test
    void testGetURLByShortId() {
        // Similar logic to above, create a URL and then retrieve by shortId
    }
}
