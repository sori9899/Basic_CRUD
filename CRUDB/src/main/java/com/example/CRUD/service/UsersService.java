package com.example.CRUD.service;


import com.example.CRUD.entity.Attempt;
import com.example.CRUD.entity.Users;
import com.example.CRUD.repository.AttemptRepository;
import com.example.CRUD.repository.UsersRepository;
import com.example.CRUD.security.JwtTokenProvider;
import com.example.CRUD.security.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class UsersService implements UserDetailsService {

    private UsersRepository usersRepository;

    private AttemptRepository attemptRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository usersRepository,
                        AttemptRepository attemptRepository,
                        PasswordEncoder passwordEncoder){

        this.usersRepository = usersRepository;
        this.attemptRepository = attemptRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("111");
        Users users = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not present"));
        return users;
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not present"));
        return users;
    }

    public void create(String username, String password) throws Exception
    {
        if (!usersRepository.existsByUsername(username) && !username.equals("anonymousUser")) {
            Users user = new Users(
                    username,
                    passwordEncoder.encode(password),
                    "USER"
            );
            System.out.println("here1");
            usersRepository.save(user);

            System.out.println("here2");

            Attempt attempt = new Attempt();
            attemptRepository.save(attempt);

            user.setAttempt(attempt);
            usersRepository.save(user);
        }
        else {
            throw new Exception("username is not unique");
        }
    }

    public void update(String username, String password, String newPassword) throws Exception
    {
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not present"));

        if (passwordEncoder.matches(password, user.getPassword())) {
            if (newPassword != null && !newPassword.isBlank()) {
                user.setPassword(passwordEncoder.encode(newPassword));
            }
            usersRepository.save(user);
        }
        else {
            throw new IllegalArgumentException("Password not matched");
        }
    }

    public String tryLogin(String username, String password) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not present"));

        if(!passwordEncoder.matches(password, user.getPassword())){
            int failed = user.getAttempt().getAttempts();
            if (failed + 1 < 100) {
                user.getAttempt().setAttempts(failed + 1);
                System.out.println(OffsetDateTime.now());
                user.setDeletedAt(OffsetDateTime.now());
                usersRepository.save(user);
            }
            else
                this.lock(user);
            throw new IllegalArgumentException("Password not matched");
        }

        if (user.getAttempt().getAttempts() != 0) {
            user.getAttempt().setAttempts(0);
            usersRepository.save(user);
        }

        Authentication authentication = new UserAuthentication(username, password, user.getAuthorities());
        String token = JwtTokenProvider.generateToken(authentication);

        return token;
    }

    public boolean canUseAsUsername(String username) {
        return !usersRepository.existsByUsername(username) && !username.equals("anonymousUser");
    }

    public void lock(Users user) {
        user.setLocked(true);
        usersRepository.save(user);
    }

    public void ban(Users user) {
        user.setBanned(true);
        usersRepository.save(user);
    }


}
