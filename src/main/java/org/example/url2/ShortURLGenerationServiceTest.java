package org.example.url2;

import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ShortURLGenerationServiceTest {

    @Mock
    private ShortURLRepository shortURLRepository;

    private ShortURLGenerationService shortURLGenerationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        shortURLGenerationService = new ShortURLGenerationService(shortURLRepository);
    }

    @Test
    public void generateShortURL_ShouldGenerateUniqueShortURL() {
        User user = new User();
        user.setId(1L);

        when(shortURLRepository.getMaxId()).thenReturn(Optional.of(5L));

        String shortURL = shortURLGenerationService.generateShortURL(user);

        assertNotNull(shortURL);
        assertEquals("15", shortURL);
    }
}