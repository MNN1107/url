package org.example.url2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class URLValidatorTest {

    @Test
    public void isValid_ShouldReturnTrueForValidURL() {
        assertTrue(URLValidator.isValid("http://example.com"));
    }

    @Test
    public void isValid_ShouldReturnFalseForInvalidURL() {
        assertFalse(URLValidator.isValid("htp:/invalid-url"));
    }

    @Test
    public void isAccessibleUrl_ShouldReturnTrueForAccessibleURL() {
        assertTrue(URLValidator.isAccessibleUrl("http://www.google.com"));
    }

    @Test
    public void isAccessibleUrl_ShouldReturnFalseForInaccessibleURL() {
        assertFalse(URLValidator.isAccessibleUrl("http://nonexistentwebsite123456.com"));
    }
}
