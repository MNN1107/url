package org.example.url2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShortURLCrudServiceImpl implements ShortURLCrudService {

    private final ShortURLRepository shortURLRepository;
    private final ShortURLMapper shortURLMapper;
    private final ShortURLGenerationService shortURLGenerationService;

    @Autowired
    public ShortURLCrudServiceImpl(ShortURLRepository shortURLRepository, ShortURLMapper shortURLMapper, ShortURLGenerationService shortURLGenerationService) {
        this.shortURLRepository = shortURLRepository;
        this.shortURLMapper = shortURLMapper;
        this.shortURLGenerationService = shortURLGenerationService;
    }

    @Override
    public ShortURLDTO createShortURL(ShortURLDTO shortURLDTO) {
        if (!URLValidator.isValid(shortURLDTO.getLongURL()) || !URLValidator.isAccessibleUrl(shortURLDTO.getLongURL())) {
            throw new IllegalArgumentException("Invalid long URL");
        }

        String generatedShortURL = shortURLGenerationService.generateShortURL(shortURLDTO.getCreator());
        shortURLDTO.setUrl(generatedShortURL);

        ShortURL shortURL = shortURLMapper.toEntity(shortURLDTO);
        ShortURL savedShortURL = shortURLRepository.save(shortURL);

        return shortURLMapper.toDTO(savedShortURL);
    }

    @Override
    public Optional<ShortURLDTO> getShortURLById(Long id) {
        return shortURLRepository.findById(id).map(shortURLMapper::toDTO);
    }

    @Override
    public List<ShortURLDTO> getAllShortURLsByCreatorId(Long userId) {
        return shortURLRepository.findAllByCreatorId(userId)
                .stream()
                .map(shortURLMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ShortURLDTO updateShortURL(ShortURLDTO shortURLDTO) {
        if (shortURLDTO.getId() == null) {
            throw new IllegalArgumentException("Short URL ID cannot be null");
        }

        ShortURL shortURL = shortURLMapper.toEntity(shortURLDTO);
        ShortURL updatedShortURL = shortURLRepository.save(shortURL);

        return shortURLMapper.toDTO(updatedShortURL);
    }

    @Override
    public void deleteShortURL(Long id) {
        shortURLRepository.deleteById(id);
    }

    @Override
    public String redirectShortURL(String shortUrl) {
        Optional<ShortURL> optionalShortURL = shortURLRepository.findByUrl(shortUrl);
        if (optionalShortURL.isEmpty()) {
            throw new IllegalArgumentException("Short URL not found");
        }

        ShortURL shortURL = optionalShortURL.get();
        shortURL.setClickCount(shortURL.getClickCount() + 1);
        shortURLRepository.save(shortURL);

        return shortURL.getLongURL();
    }
}