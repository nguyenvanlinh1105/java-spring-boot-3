package com.linhnguyen.springbootbasic.controller;

import com.linhnguyen.springbootbasic.dto.request.IntrospectRequest;
import com.linhnguyen.springbootbasic.dto.response.ApiResponse;
import com.linhnguyen.springbootbasic.dto.request.AuthenticationRequest;
import com.linhnguyen.springbootbasic.dto.response.AuthenticationResponse;
import com.linhnguyen.springbootbasic.dto.response.IntrospectResponse;
import com.linhnguyen.springbootbasic.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
// inject bean depen
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest req) throws JOSEException {
        ApiResponse apiResponse = new ApiResponse<>();
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(req);
        apiResponse.setResult(authenticationResponse);
        return apiResponse;
    }


    @PostMapping ("/introspect")
    public ApiResponse<IntrospectResponse> introspect (@RequestBody IntrospectRequest req) throws ParseException, JOSEException {
        IntrospectResponse introspectResponse = authenticationService.introspect(req);

        ApiResponse<IntrospectResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(introspectResponse);

        return apiResponse;
    }

}
