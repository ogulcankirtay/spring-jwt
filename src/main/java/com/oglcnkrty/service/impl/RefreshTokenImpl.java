package com.oglcnkrty.service.impl;

import com.oglcnkrty.jwt.AuthResponse;
import com.oglcnkrty.jwt.JwtService;
import com.oglcnkrty.model.RefreshToken;
import com.oglcnkrty.model.User;
import com.oglcnkrty.repository.RefreshTokenRepository;
import com.oglcnkrty.service.IRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenImpl implements IRefreshTokenService {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    JwtService jwtService;


    public Boolean isRefreshTokenExpired(Date expiration) {
        return new Date().before(expiration);
    }


    @Override
    public AuthResponse refreshToken(RefreshToken request) {
        Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
        if (optional.isPresent()) {
            RefreshToken refreshToken = optional.get();
            if (!isRefreshTokenExpired(refreshToken.getExpiresDate())) {
                System.out.println("Refresh token expired");
            } else {
                String accessToken = jwtService.generateToken(refreshToken.getUser());
                RefreshToken refreshTokenResponse = createRefreshToken(refreshToken.getUser());
                return new AuthResponse(accessToken,refreshTokenResponse.getRefreshToken());
            }
        } else {
            System.out.println("refreshToken not found");
        }
        return null;
    }

    @Override
    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        refreshToken.setExpiresDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

}
