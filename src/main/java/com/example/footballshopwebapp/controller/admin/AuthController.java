package com.example.footballshopwebapp.controller.admin;


import com.example.footballshopwebapp.dto.Otp;
import com.example.footballshopwebapp.dto.request.ChangePassword;
import com.example.footballshopwebapp.dto.request.CheckEmailResetPassword;
import com.example.footballshopwebapp.dto.response.AuthenticationResponse;
import com.example.footballshopwebapp.dto.request.LoginRequest;
import com.example.footballshopwebapp.dto.RefreshTokenRequest;
import com.example.footballshopwebapp.dto.request.RegisterRequest;
import com.example.footballshopwebapp.service.AuthService;
import com.example.footballshopwebapp.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful",
                OK);
    }

    @GetMapping("accountVerification/{token}")
    public RedirectView verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
//        return new RedirectView("/api/subreddit");  // khi verifyAccount thì redirect sang route "/api/subreddit"
        return new RedirectView("https://www.youtube.com/");  // khi verifyAccount thì redirect sang route "/api/subreddit"
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody @Validated LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }

    @PostMapping(value = "/checkAccount")
    public ResponseEntity<Boolean> checkAccount(@RequestBody @Validated CheckEmailResetPassword checkEmailResetPassword){
        return ResponseEntity.status(OK).body(authService.checkAccount(checkEmailResetPassword));
    }

    @PostMapping(value = "/sendOtpEmail")
    public ResponseEntity<Boolean> sendOtpEmail(@RequestParam(value = "email") String email){
        authService.sendOtpEmail(email);
        return ResponseEntity.status(OK).body(true);
    }

    @PostMapping("/verifyOtpEmail")
    public Boolean verifyOtpEmail(@RequestBody @Validated Otp otp) {
        return authService.verifyOtpEmail(otp);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Boolean> changePassword(@RequestBody @Validated ChangePassword changePassword) {
        return ResponseEntity.status(OK).body( authService.changePassword(changePassword));
    }

}
