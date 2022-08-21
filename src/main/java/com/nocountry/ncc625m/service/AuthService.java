package com.nocountry.ncc625m.service;

import com.nocountry.ncc625m.dto.request.RegisterRequest;
import com.nocountry.ncc625m.dto.response.AuthResponse;
import com.nocountry.ncc625m.dto.response.UserResponse;

public interface AuthService {

	AuthResponse register(RegisterRequest user);

	AuthResponse login(String email, String password) throws Exception;

	UserResponse userAuth(String token);


}
