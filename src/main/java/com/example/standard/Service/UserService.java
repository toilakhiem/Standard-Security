package com.example.standard.Service;

import com.example.standard.Core.Application.UserServiceInterface;
import com.example.standard.Core.Entity.Permission;
import com.example.standard.Core.Entity.Role;
import com.example.standard.Core.Entity.User;
import com.example.standard.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserServiceInterface,UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    @Override
    public void saveRole(Role role) {

    }

    @Override
    public void savePermission(Permission permission) {

    }
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("User not found in database");
        Set<GrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(
                role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getRolename()));
                    role.getPermissions().forEach(
                            permission -> {
                                authorities.add(new SimpleGrantedAuthority(permission.getPermissionname()));
                            }
                    );
                }
        );
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
}
