package org.example.url_dev;

import org.example.exception_dev.LogEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class URLValidatorTest {

    private static final Logger log = LoggerFactory.getLogger(URLValidator.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsValid() {
        assertTrue(URLValidator.isValid("http://valid.url"));
        assertFalse(URLValidator.isValid("invalid-url"));
    }

    @Test
    void testIsAccessibleUrl() throws IOException {
        URL mockUrl = mock(URL.class);
        HttpURLConnection mockConnection = mock(HttpURLConnection.class);

        when(mockUrl.openConnection()).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);

        try (var mockedStatic = mockStatic(URL.class)) {
            mockedStatic.when(() -> new URL("http://accessible.url")).thenReturn(mockUrl);

            assertTrue(URLValidator.isAccessibleUrl("http://accessible.url"));

            when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_NOT_FOUND);
            assertFalse(URLValidator.isAccessibleUrl("http://inaccessible.url"));
        }
    }

    @Test
    void testIsAccessibleUrlMalformed() {
        assertFalse(URLValidator.isAccessibleUrl("invalid-url"));
    }

    @Test
    void testIsAccessibleUrlIOException() throws IOException {
        URL mockUrl = mock(URL.class);

        when(mockUrl.openConnection()).thenThrow(new IOException("Connection failed"));

        try (var mockedStatic = mockStatic(URL.class)) {
            mockedStatic.when(() -> new URL("http://inaccessible.url")).thenReturn(mockUrl);

            assertFalse(URLValidator.isAccessibleUrl("http://inaccessible.url"));
        }
    }
}
