package com.ecommerce.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class User implements UserDetails  {
  private final Long id;
  private final String fullName;
  private final Date dateBirth;
  private final String email;
  private final String password;
  private final boolean accountNonExpired;
  private final boolean credentialsNonExpired;
  private final boolean accountNonLocked;
  private final boolean enabled;
  private final List<SimpleGrantedAuthority> authorities;

  public User(Long id,
              String fullName,
              Date dateBirth,
              String email,
              String password,
              boolean accountNonExpired,
              boolean credentialsNonExpired,
              boolean accountNonLocked,
              boolean enabled,
              List<SimpleGrantedAuthority> authorities) {
    this.id = id;
    this.fullName = fullName;
    this.dateBirth = dateBirth;
    this.email = email;
    this.password = password;
    this.accountNonExpired = accountNonExpired;
    this.credentialsNonExpired = credentialsNonExpired;
    this.accountNonLocked = accountNonLocked;
    this.enabled = enabled;
    this.authorities = authorities;
  }

  public Long getId() { return id; }
  public String getFullName() { return fullName; }
  public Date getDateBirth() { return dateBirth; }
  public String getEmail() { return email; }
  public String getPassword() { return password; }
  public boolean isAccountNonExpired() { return accountNonExpired; }
  public boolean isCredentialsNonExpired() { return credentialsNonExpired; }
  public boolean isAccountNonLocked() { return accountNonLocked; }
  public boolean isEnabled() { return enabled; }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
  @Override
  public String getUsername() { return fullName; }


}
