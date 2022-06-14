package com.example.portfolio.service.user;

import com.example.portfolio.domain.user.User;
import com.example.portfolio.domain.user.UserRepository;
import com.example.portfolio.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;

    @Transactional
    public User join(String name, String email, String password){
        userRepository.findByEmail(email).ifPresent((user)->{
            throw new IllegalStateException("email already exists");
        });

        User newUserForJoin = User.builder(name, email, password)
            .build();

        newUserForJoin.encodePassword();
        userRepository.save(newUserForJoin);

        return userRepository.findById(newUserForJoin.getId())
            .orElseThrow(()->new NotFoundException("id not exists"));
    }

    @Transactional(readOnly = true)
    public User login(String email, String password, final PasswordEncoder encoder){
        User user = userRepository.findByEmail(email)
            .orElseThrow(()->new NotFoundException("id not exists"));

        if(!encoder.matches(password, user.getPassword())){
            throw new IllegalStateException("password does not match");
        }

        return user;
    }

}
