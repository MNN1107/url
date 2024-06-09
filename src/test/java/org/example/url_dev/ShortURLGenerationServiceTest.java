package org.example.url_dev;

import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ShortURLGenerationServiceTest {

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private ShortURLGenerationService shortURLGenerationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateShortURL() {
        User user = new User();
        user.setId(1L);

        when(urlRepository.getMaxId()).thenReturn(Optional.of(1L));

        String shortUrl = shortURLGenerationService.generateShortURL(user);

        assertEquals("110", shortUrl);
    }
}