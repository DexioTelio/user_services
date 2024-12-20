package com.ecommerce.demo.services;

import com.ecommerce.demo.model.User;
import com.ecommerce.demo.repositories.PersonQueryRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final PersonQueryRepositoryImpl personQueryRepository;

  @Autowired
  public CustomUserDetailsService(PersonQueryRepositoryImpl personQueryRepository) {
    this.personQueryRepository = personQueryRepository;
  }

    @Override
    public UserDetails loadUserByUsername(String email) {
      return personQueryRepository.findByEmail(email)
              .map(u -> new User(
                      u.getId(),
                      u.getFullName(),
                      u.getDateBirth(),
                      u.getEmail(),
                      u.getPassword(),
                      u.isAccountNonExpired(),
                      u.isCredentialsNonExpired(),
                      u.isAccountNonLocked(),
                      u.isEnabled(),
                      personQueryRepository.getAuthorities(u.getEmail())
              ))
              .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }
}
