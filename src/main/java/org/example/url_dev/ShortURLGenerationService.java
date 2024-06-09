package org.example.url_dev;


import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.example.exception_dev.LogEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ShortURLGenerationService {

    private final UrlRepository urlRepository;

    @Autowired
    public ShortURLGenerationService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String generateShortURL(User user) {
        Optional<Long> maxIdOptional = urlRepository.getMaxId();
        long maxId = maxIdOptional.orElse(0L) + 1;

        String userHexId = Long.toHexString(user.getId());
        String maxIdHex = Long.toHexString(maxId);

        String shortUrl = userHexId + maxIdHex;

        log.info(String.format("%s short url for user %s was created", LogEnum.SERVICE, user));
        return shortUrl.replaceAll("^0[xX]+", "");
    }

}
