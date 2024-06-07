package org.example.url2;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class ShortURLGenerationService {

    private final ShortURLRepository shortURLRepository;

    @Autowired
    public ShortURLGenerationService(ShortURLRepository shortURLRepository) {
        this.shortURLRepository = shortURLRepository;
    }

    public String generateShortURL(User user) {
        Optional<Long> maxIdOptional = shortURLRepository.getMaxId();
        long maxId = maxIdOptional.orElse(0L) + 1;

        String userHexId = Long.toHexString(user.getName());
        String maxIdHex = Long.toHexString(maxId);

        String shortUrl = userHexId + maxIdHex;

        return shortUrl.replaceAll("^0[xX]+", "");
    }

}
