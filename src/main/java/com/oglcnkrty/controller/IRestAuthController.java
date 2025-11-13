package com.oglcnkrty.controller;

import com.oglcnkrty.dto.DtoUser;
import com.oglcnkrty.jwt.AuthRequest;
import com.oglcnkrty.jwt.AuthResponse;
import com.oglcnkrty.model.RefreshToken;

public interface IRestAuthController {

    public DtoUser register(AuthRequest request);

    public AuthResponse authenticate(AuthRequest request);

    public AuthResponse refreshToken(RefreshToken refreshToken);

}
