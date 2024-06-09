package org.example.auth_dev;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public Role findByName(String name) {
        log.info(String.format("%s request on retrieving role by name %s was sent", LogEnum.SERVICE, name));
        return roleRepository.findByName(name).orElse(null);
    }
}
