package org.example.url_dev;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrlMapperTest {

    @InjectMocks
    private UrlMapper urlMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToDTO() {
        Url url = new Url();
        url.setId(1L);
        url.setLongUrl("http://long.url");

        UrlDto urlDto = urlMapper.toDTO(url);

        assertEquals(1L, urlDto.getId());
        assertEquals("http://long.url", urlDto.getLongURL());
    }

    @Test
    void testToEntity() {
        UrlDto urlDto = new UrlDto();
        urlDto.setId(1L);
        urlDto.setLongURL("http://long.url");

        Url url = urlMapper.toEntity(urlDto);

        assertEquals(1L, url.getId());
        assertEquals("http://long.url", url.getLongUrl());
    }
}
