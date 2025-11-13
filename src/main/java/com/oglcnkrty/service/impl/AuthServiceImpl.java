package com.oglcnkrty.service.impl;

import com.oglcnkrty.dto.DtoUser;
import com.oglcnkrty.jwt.AuthRequest;
import com.oglcnkrty.jwt.AuthResponse;
import com.oglcnkrty.jwt.JwtService;
import com.oglcnkrty.model.RefreshToken;
import com.oglcnkrty.model.User;
import com.oglcnkrty.repository.UserRepository;
import com.oglcnkrty.service.IAuthService;
import com.oglcnkrty.service.IRefreshTokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AuthenticationProvider authenticationProvider;
    @Autowired
    IRefreshTokenService refreshTokenService;
    @Autowired
    JwtService jwtService;

    @Override
    public DtoUser register(AuthRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        DtoUser dtoUser = new DtoUser();
        BeanUtils.copyProperties(savedUser, dtoUser);
        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationProvider.authenticate(auth);
            Optional<User> user = userRepository.findByUsername(request.getUsername());
            if (user.isPresent()) {
                String accessToken = jwtService.generateToken(user.get());
                RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.get());
                return new AuthResponse(accessToken, refreshToken.getRefreshToken());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
