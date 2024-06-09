package org.example.url_dev;

import org.apache.catalina.User;
import org.example.auth_dev.UserRepository;
import org.example.exception_dev.exceptions.longURLExceptions.InvalidLongURLException;
import org.example.exception_dev.exceptions.shortURLExceptions.ShortURLNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UrlCrudServiceImplTest {

    @Mock
    private UrlRepository urlRepository;

    @Mock
    private UrlMapper urlMapper;

    @Mock
    private ShortURLGenerationService shortURLGenerationService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UrlCrudServiceImpl urlCrudServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateURL() throws InvalidLongURLException {
        UrlDto urlDto = new UrlDto();
        urlDto.setLongURL("http://valid.url");

        when(shortURLGenerationService.generateShortURL(any(User.class))).thenReturn("shortURL");
        when(urlMapper.toEntity(any(UrlDto.class))).thenReturn(new Url());
        when(urlRepository.save(any(Url.class))).thenReturn(new Url());
        when(urlMapper.toDTO(any(Url.class))).thenReturn(urlDto);

        UrlDto createdUrl = urlCrudServiceImpl.createURL(urlDto);

        assertEquals(urlDto, createdUrl);
    }

    @Test
    void testGetURLByShortId() {
        when(urlRepository.findByShortId("shortId")).thenReturn(Optional.of(new Url()));
        when(urlMapper.toDTO(any(Url.class))).thenReturn(new UrlDto());

        Optional<UrlDto> urlDto = urlCrudServiceImpl.getURLByShortId("shortId");

        assertTrue(urlDto.isPresent());
    }

    @Test
    void testDeleteByShortIdAndUser() throws ShortURLNotFoundException {
        User user = new User();
        when(urlRepository.deleteUrlByShortIdAndUser("shortId", user)).thenReturn(1);

        urlCrudServiceImpl.deleteByShortIdAndUser("shortId", user);

        verify(urlRepository, times(1)).deleteUrlByShortIdAndUser("shortId", user);
    }
}