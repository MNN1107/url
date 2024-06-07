package org.example.url;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UrlCrudServiceImpl implements UrlCrudService {
    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private UrlMapper urlMapper;

    @Autowired
    private ShortUrlGenerator shortUrlGenerator;

    @Override
    @Transactional
    public Url createShortUrl(DtoCreateUrlRequest request, User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (!URLValidator.isValid(request.getLongUrl()) || !URLValidator.isAccessibleUrl(request.getLongUrl())) {
            throw new IllegalArgumentException("Invalid or inaccessible URL");
        }

        String shortUrl = shortUrlGenerator.generateShortUrl();
        Url url = urlMapper.toUrl(request, user);
        url.setShortUrl(shortUrl);
        url.setCreationDate(LocalDateTime.now());
        url.setClickCount(0L);

        return urlRepository.save(url);
    }

    @Override
    public Optional<Url> getShortUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }

    @Override
    public List<Url> getUserUrls(User user) {
        return urlRepository.findAllByUser(user);
    }

    @Override
    @Transactional
    public void incrementClickCount(String shortUrl) {
        Optional<Url> optionalUrl = getShortUrl(shortUrl);
        if (optionalUrl.isPresent()) {
            Url url = optionalUrl.get();
            url.setClickCount(url.getClickCount() + 1);
            urlRepository.save(url);
        } else {
            throw new IllegalArgumentException("Short URL not found");
        }
    }



    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;
    private final ShortUrlGeneratorUser shortUrlGenerator;

    @Autowired
    public UrlCrudServiceImpl(UrlRepository urlRepository, UrlMapper urlMapper, ShortUrlGeneratorUser shortUrlGenerator) {
        this.urlRepository = urlRepository;
        this.urlMapper = urlMapper;
        this.shortUrlGenerator = shortUrlGenerator;
    }

    @Override
    public Url createUrl(DtoCreateUrlRequest request, User user) {
        if (!URLValidator.isValid(request.getLongUrl())) {
            throw new IllegalArgumentException("Invalid URL");
        }

        String shortUrl = shortUrlGenerator.generateShortUrl(user);
        Url url = urlMapper.toUrl(request, user, shortUrl);
        return urlRepository.save(url);
    }

    @Override
    public void deleteUrl(Long id) {
        urlRepository.deleteById(id);
    }

    @Override
    public List<Url> getUrlsByUser(User user) {
        return urlRepository.findAllByUser(user);
    }

    @Override
    public Optional<Url> getUrlByShortUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }

    @Override
    public void incrementClickCount(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new IllegalArgumentException("Short URL not found"));
        url.setClickCount(url.getClickCount() + 1);
        urlRepository.save(url);
    }
}
