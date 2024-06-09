package org.example.response_dev;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlResponse {

    private Long id;
    private Long userId;
    private String shortId;
    private String longUrl;
    private LocalDateTime creationDate;
    private LocalDateTime expiryDate;
    private int clickCount;
}