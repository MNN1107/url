package org.example.url;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

class ShortUrlGeneratorTest {

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private ShortUrlGenerator shortUrlGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateShortUrl_Unique() {

        when(urlRepository.findByShortUrl(anyString())).thenReturn(Optional.empty());

        String shortUrl = shortUrlGenerator.generateShortUrl();
        assertNotNull(shortUrl, "Generated short URL should not be null");
        assertTrue(shortUrl.length() >= 6 && shortUrl.length() <= 8, "Generated short URL should be 6 to 8 characters long");
    }

    @Test
    void testGenerateShortUrl_Duplicate() {

        when(urlRepository.findByShortUrl(anyString()))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of(new Url()));

        String shortUrl = shortUrlGenerator.generateShortUrl();
        assertNotNull(shortUrl, "Generated short URL should not be null");
        assertTrue(shortUrl.length() >= 6 && shortUrl.length() <= 8, "Generated short URL should be 6 to 8 characters long");
    }
}