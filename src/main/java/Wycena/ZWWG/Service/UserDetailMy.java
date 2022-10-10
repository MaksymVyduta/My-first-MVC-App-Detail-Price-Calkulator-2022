package Wycena.ZWWG.Service;

import Wycena.ZWWG.model.SecurityUser;
import Wycena.ZWWG.model.SecurityUserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailMy implements UserDetailsService {
    private SecurityUserRepo securityUserRepo ;
    public UserDetailMy (SecurityUserRepo securityUserRepo) {
        this.securityUserRepo = securityUserRepo ;
    }
    @Override
    public UserDetails loadUserByUsername (String name) throws UsernameNotFoundException {
        return securityUserRepo .findByUsername(name).get() ;
    }


}
