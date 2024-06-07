package org.example.url2;

import java.util.List;
import java.util.Optional;

public interface ShortURLCrudService {
    ShortURLDTO createShortURL(ShortURLDTO shortURLDTO);

    Optional<ShortURLDTO> getShortURLById(Long id);

    List<ShortURLDTO> getAllShortURLsByCreatorId(Long userId);

    ShortURLDTO updateShortURL(ShortURLDTO shortURLDTO);

    void deleteShortURL(Long id);

    String redirectShortURL(String shortUrl);
}