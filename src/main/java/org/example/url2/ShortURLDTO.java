package org.example.url2;
import lombok.*;
import org.apache.catalina.User;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortURLDTO {
    private Long id;
    private String url;
    private String longURL;
    private User creator;
    private List<User> users;
    private LocalDate expiryDate;
    private Long clickCount;
}