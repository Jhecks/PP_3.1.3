package ru.jhecks.config;

import org.springframework.stereotype.Component;
import ru.jhecks.model.Role;
import ru.jhecks.model.User;
import ru.jhecks.repository.RoleRepository;
import ru.jhecks.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DataInit {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DataInit(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    void init() {
        roleRepository.save(new Role("ROLE_ADMIN", "Admin"));
        roleRepository.save(new Role("ROLE_USER", "User"));

        userRepository.save(new User("Michael", "Semenov", 22, "admin@gmail.com",
                "admin", Set.of(roleRepository.findByName("ROLE_ADMIN"))));
        userRepository.save(new User("Bogdan", "Ivanov", 10, "user@gmail.com",
                 "1111", Set.of(roleRepository.findByName("ROLE_USER"))));
    }
}