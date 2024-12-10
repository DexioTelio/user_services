package com.ecommerce.demo.model;

import java.util.Date;
import java.util.List;

public class User {
  private final Long id;
  private final String fullName;
  private final Date dateBirth;
  private final String email;
  private final String password;
  private final boolean accountNonExpired;
  private final boolean credentialsNonExpired;
  private final boolean accountNonLocked;
  private final boolean enabled;
  private final List<String> roles;

  public User(Long id,
              String fullName,
              Date dateBirth,
              String email,
              String password,
              boolean accountNonExpired,
              boolean credentialsNonExpired,
              boolean accountNonLocked,
              boolean enabled,
              List<String> roles) {
    this.id = id;
    this.fullName = fullName;
    this.dateBirth = dateBirth;
    this.email = email;
    this.password = password;
    this.accountNonExpired = accountNonExpired;
    this.credentialsNonExpired = credentialsNonExpired;
    this.accountNonLocked = accountNonLocked;
    this.enabled = enabled;
    this.roles = roles;
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
  public List<String> getRoles() { return roles; }
}
