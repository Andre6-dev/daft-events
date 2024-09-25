package dp.devandre.daftevents.user.infrastructure.security.service;

import dp.devandre.daftevents.user.infrastructure.persistence.model.UserJpaEntity;
import dp.devandre.daftevents.user.infrastructure.persistence.repository.UserRepository;
import dp.devandre.daftevents.user.infrastructure.security.model.UserDetailsSecurityImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserJpaEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
        return UserDetailsSecurityImpl.build(user);
    }
}
