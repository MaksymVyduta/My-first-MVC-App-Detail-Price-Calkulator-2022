package Wycena.ZWWG.Service;

import Wycena.ZWWG.model.SecurityUser;
import Wycena.ZWWG.model.SecurityUserRepo;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Run {
    public Run(SecurityUserRepo securityUserRepo, PasswordEncoder passwordEncoder) {
  // SecurityUser user1 = SecurityUser.builder()
    //         .username("nowawycena@mail.ru")
    //     .password(passwordEncoder.encode("nowawycena")).role("ROLE_ADMIN")
     //     .isEnabled(true).build();
   // securityUserRepo.save(user1);
    }
}
