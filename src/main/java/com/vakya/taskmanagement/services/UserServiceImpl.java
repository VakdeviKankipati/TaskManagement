package com.vakya.taskmanagement.services;

import com.vakya.taskmanagement.models.User;
import com.vakya.taskmanagement.repositories.UserRepository;
import com.vakya.taskmanagement.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    protected BCryptPasswordEncoder bCryptPasswordEncoder;
    private JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User registerUser(String name, String password){

        User user = new User();
        user.setUsername(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public String loginUser(String username, String password) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user == null || !bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Invalid username or password");
        }
        return jwtUtil.generateToken(username);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}

