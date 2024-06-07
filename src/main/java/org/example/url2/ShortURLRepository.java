package org.example.url2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ShortURLRepository extends JpaRepository<ShortURL, Long> {
    Optional<ShortURL> findByUrl(String shortURL);
    List<ShortURL> findAllByCreatorId(Long userId);

    @Query("SELECT MAX(id) FROM ShortURL")
    Optional<Long> getMaxId();
}


