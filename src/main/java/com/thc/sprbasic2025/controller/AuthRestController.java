package com.thc.sprbasic2025.controller;

import com.thc.sprbasic2025.constants.AuthConstants;
import com.thc.sprbasic2025.util.TokenFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
public class AuthRestController {

    final TokenFactory tokenFactory;

    @PostMapping("")
    public ResponseEntity<Void> getAccessToken(HttpServletRequest request){
        String accessKey =null;

        //리프레시 토큰이 유요한지 확인!
        String refreshToken = request.getHeader("RefreshToken");
        System.out.println("1 refreshToken : " + refreshToken);
        if(refreshToken != null && refreshToken.startsWith(AuthConstants.TOKEN_PREFIX)){
            refreshToken = refreshToken.substring(AuthConstants.TOKEN_PREFIX.length());
            System.out.println("2 refreshToken : " + refreshToken);
            accessKey = tokenFactory.generateAccessKey(refreshToken);
        }
        return ResponseEntity.status(HttpStatus.OK).header(AuthConstants.HEADER_STRING,accessKey).build();
    }

}
