package com.oglcnkrty.controller.impl;

import com.oglcnkrty.controller.IRestAuthController;
import com.oglcnkrty.dto.DtoUser;
import com.oglcnkrty.jwt.AuthRequest;
import com.oglcnkrty.jwt.AuthResponse;
import com.oglcnkrty.model.RefreshToken;
import com.oglcnkrty.service.IAuthService;
import com.oglcnkrty.service.IRefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {
    @Autowired
    private IAuthService authService;

    @Autowired
    IRefreshTokenService refreshTokenService;

    @PostMapping("/refreshToken")
    public AuthResponse refreshToken(@RequestBody RefreshToken refreshToken) {
        return refreshTokenService.refreshToken(refreshToken);
    }

    @PostMapping("/register")
    @Override
    public DtoUser register(@Valid @RequestBody AuthRequest request) {
        return authService.register(request);
    }

    @PostMapping("/authenticate")
    @Override
    public AuthResponse authenticate(@Valid @RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }
}
