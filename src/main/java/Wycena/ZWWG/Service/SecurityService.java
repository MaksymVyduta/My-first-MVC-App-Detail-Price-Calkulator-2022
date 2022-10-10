package Wycena.ZWWG.Service;

import Wycena.ZWWG.model.SecurityUser;
import Wycena.ZWWG.model.SecurityUserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {private SecurityUserRepo securityUserRepo;
    private PasswordEncoder passwordEncoder;

    public SecurityService(SecurityUserRepo securityUserRepo, PasswordEncoder passwordEncoder) {
        this.securityUserRepo = securityUserRepo;
        this.passwordEncoder = passwordEncoder;
    }
    public void addUser(SecurityUser securityUser) {
        securityUser.setPassword(passwordEncoder.encode(securityUser.getPassword()));
        securityUser.setRole("ROLE_USER");
        securityUserRepo.save(securityUser);

    }


}