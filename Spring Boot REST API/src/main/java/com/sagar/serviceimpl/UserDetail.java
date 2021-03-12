/*
 * package com.sagar.serviceimpl;
 * 
 * import java.util.Arrays; import java.util.Collection; import java.util.List;
 * import java.util.stream.Collectors;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails;
 * 
 * import com.sagar.entity.UserDetailss; import com.sagar.service.UserService;
 * 
 * public class UserDetail implements UserDetails {
 * 
 * private String userName; private String password; private boolean active;
 * private List<GrantedAuthority> authorities;
 * 
 * @Autowired UserService userService;
 * 
 * public UserDetail(UserDetailss user) { this.userName = user.getUserName();
 * this.password = user.getPassword(); this.active = user.isActive();
 * this.authorities = Arrays.stream(user.getRoles().split(","))
 * .map(SimpleGrantedAuthority::new) .collect(Collectors.toList()); }
 * 
 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
 * return authorities; // return Arrays.asList(new
 * SimpleGrantedAuthority("ROLE_USER")); }
 * 
 * @Override public String getPassword() { return password; }
 * 
 * @Override public String getUsername() { return userName; }
 * 
 * @Override public boolean isAccountNonExpired() {
 * 
 * return true; }
 * 
 * @Override public boolean isAccountNonLocked() {
 * 
 * return true; }
 * 
 * @Override public boolean isCredentialsNonExpired() {
 * 
 * return true; }
 * 
 * @Override public boolean isEnabled() {
 * 
 * return active; }
 * 
 * }
 */