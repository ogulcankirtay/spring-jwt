package com.oglcnkrty.service;

import com.oglcnkrty.jwt.AuthResponse;
import com.oglcnkrty.model.RefreshToken;
import com.oglcnkrty.model.User;

public interface IRefreshTokenService {

    public AuthResponse refreshToken(RefreshToken refreshToken);

    public RefreshToken createRefreshToken(User user);

}
