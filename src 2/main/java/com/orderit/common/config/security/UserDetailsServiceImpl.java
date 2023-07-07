package com.orderit.common.config.security;


import com.orderit.common.entity.CompanyUser;
import com.orderit.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CompanyUser companyUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("CompanyUser Not Found with email: " + email));

        return new UserDetailsImpl(companyUser);
    }

}