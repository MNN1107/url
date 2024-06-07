package org.example.url;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UrlCrudService {

    Url createShortUrl(DtoCreateUrlRequest dtoCreateUrlRequest, User user);
    Optional<Url> getUrlById(Long id);
    List<Url> getAllUrls();
    Url updateUrl(Long id, DtoCreateUrlRequest dtoCreateUrlRequest);
    void deleteUrl(Long id);
    void incrementClickCount(String shortUrl);
    Optional<Url> getUrlByShortUrl(String shortUrl);
    List<Url> getUrlsByUser(User user);
}
