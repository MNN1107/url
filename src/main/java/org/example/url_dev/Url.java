package org.example.url_dev;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "urls")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_id", nullable = false, unique = true)
    private String shortId;

    @Column(name = "long_url", nullable = false)
    private String longUrl;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;

    @Column(name = "click_count", nullable = false)
    private int clickCount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}