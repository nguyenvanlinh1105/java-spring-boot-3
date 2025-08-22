package com.linhnguyen.springbootbasic.service;

import com.linhnguyen.springbootbasic.dto.request.AuthenticationRequest;
import com.linhnguyen.springbootbasic.dto.request.IntrospectRequest;
import com.linhnguyen.springbootbasic.dto.response.AuthenticationResponse;
import com.linhnguyen.springbootbasic.dto.response.IntrospectResponse;
import com.linhnguyen.springbootbasic.entity.User;
import com.linhnguyen.springbootbasic.exception.AppException;
import com.linhnguyen.springbootbasic.exception.ErrorCode;
import com.linhnguyen.springbootbasic.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @NonFinal
    @Value("${jwt.singerKey}")
    protected String SIGNER_KEY ;

    public AuthenticationResponse authenticate(AuthenticationRequest req) throws JOSEException {
        var user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUD));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        boolean authenticate = passwordEncoder.matches(req.getPassword(),user.getPassword());

        if(!authenticate){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var token = generateToken(user.getUsername());
        return AuthenticationResponse.builder()
                .authenticated(authenticate)
                .token(token)
                .build();

    }

    private String generateToken(String username) throws JOSEException {

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("linhnguyen.com")
                .issueTime(new Date())
                .expirationTime(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .build();

        // Payload
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        // kí token
        jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
        return jwsObject.serialize();
    }


    public IntrospectResponse introspect (IntrospectRequest req) throws JOSEException, ParseException {
        var token = req.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && expiryTime.after(new Date()))
                .build();
    }
}
