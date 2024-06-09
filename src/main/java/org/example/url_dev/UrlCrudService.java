package org.example.url_dev;


import org.apache.catalina.User;
import org.example.exception_dev.exceptions.longURLExceptions.InvalidLongURLException;
import org.example.exception_dev.exceptions.shortURLExceptions.ShortURLNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UrlCrudService {
    UrlDto createURL(UrlDto urlDto) throws InvalidLongURLException;

    Optional<UrlDto> getURLById(Long id);
    Optional<UrlDto> getURLByShortId(String shortId);
    Optional<UrlDto> getURLByShortIdAndUser(String shortId, User user);

    List<UrlDto> getAllByUserId();
    List<UrlDto> getAllActiveByUserId();
    void updateClicksCount(String shortId);
    void deleteByShortIdAndUser(String shortId, User user) throws ShortURLNotFoundException;
}
