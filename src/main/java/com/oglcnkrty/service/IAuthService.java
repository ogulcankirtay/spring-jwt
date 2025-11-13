package com.oglcnkrty.service;

import com.oglcnkrty.dto.DtoUser;
import com.oglcnkrty.jwt.AuthRequest;
import com.oglcnkrty.jwt.AuthResponse;

public interface IAuthService {

    public DtoUser register(AuthRequest request);

    public AuthResponse authenticate(AuthRequest request);
}
