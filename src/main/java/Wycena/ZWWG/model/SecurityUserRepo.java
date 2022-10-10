package Wycena.ZWWG.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import java.util.Optional;

public interface SecurityUserRepo extends JpaRepository<SecurityUser , Long> {
    Optional<SecurityUser> findByUsername (String username) ;
}

