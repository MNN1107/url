package org.example.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ShortUrlGenerator {
    private final UrlRepository urlRepository;

    @Autowired
    public ShortUrlGenerator(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String generateShortUrl() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortUrlBuilder = new StringBuilder();
        Random random = new Random();
        int length = 6 + random.nextInt(3);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            shortUrlBuilder.append(characters.charAt(index));
        }
        String generatedShortUrl = shortUrlBuilder.toString();

        if (urlRepository.findByShortUrl(generatedShortUrl).isEmpty()) {
            return generatedShortUrl;
        } else {
            return generateShortUrl();
        }
    }
}

