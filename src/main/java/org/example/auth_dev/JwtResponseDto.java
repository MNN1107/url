package org.example.auth_dev;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDto {

    private String token;
}
