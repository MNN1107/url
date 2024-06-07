package org.example.url;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UrlMapper {
    public Url toUrl(DtoCreateUrlRequest request, User user, String shortUrl) {

            return Url.builder()
                    .longUrl(request.getLongUrl())
                    .shortUrl(shortUrl)
                    .creationDate(LocalDateTime.now())
                    .expiryDate(request.getExpiryDate())
                    .clickCount(0L)
                    .user(user)
                    .build();
        }
}