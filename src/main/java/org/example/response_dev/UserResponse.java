package org.example.response_dev;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.auth_dev.Role;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Data
public class UserResponse {

    private Long id;
    private String email;
    private Set<Role> roles = new HashSet<>();
}
