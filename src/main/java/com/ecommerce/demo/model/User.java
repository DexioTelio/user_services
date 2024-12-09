package com.ecommerce.demo.model;

import java.util.Date;

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

  public User(Long id, String fullName, Date dateBirth, String email, String password, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, boolean enabled) {
    this.id = id;
    this.fullName = fullName;
    this.dateBirth = dateBirth;
    this.email = email;
    this.password = password;
    this.accountNonExpired = accountNonExpired;
    this.credentialsNonExpired = credentialsNonExpired;
    this.accountNonLocked = accountNonLocked;
    this.enabled = enabled;
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
}
