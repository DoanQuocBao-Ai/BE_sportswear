package com.store.sportswear.api;

import com.store.sportswear.entity.EUser;
import com.store.sportswear.request.UserCreateRequest;
import com.store.sportswear.request.UserDeleteRequest;
import com.store.sportswear.request.UserLoginRequest;
import com.store.sportswear.security.JwtTokenProvider;
import com.store.sportswear.service.SendEmailService;
import com.store.sportswear.service.user.UserService;
import com.store.sportswear.shared.ECommerceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import java.util.Date;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final SendEmailService sendEmailService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder, SendEmailService sendEmailService) {
        this.authenticationManager =authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.sendEmailService = sendEmailService;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        EUser EUser = userService.getByUserName(loginRequest.getUserName());
        sendEmailService.sendEmails(EUser.getEMail(), ECommerceMessage.LOGIN_BODY, ECommerceMessage.LOGIN_TOPIC);
        return "Bearer " + jwtToken;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCreateRequest user) throws AddressException {
        if(userService.findByEMail(user.getEMail()) != null) {
            return new ResponseEntity<>(ECommerceMessage.EMAIL_ALREADY_IN_USE, HttpStatus.BAD_REQUEST);
        }

        EUser newEUser = new EUser();
        newEUser.setUserName(user.getUserName());
        newEUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newEUser.setEMail(user.getEMail());
        newEUser.setUserCreateDate(new Date());
        userService.add(newEUser);
        sendEmailService.sendEmails(String.valueOf(user.getEMail()), ECommerceMessage.REGISTER_BODY, ECommerceMessage.REGISTER_TOPIC + ECommerceMessage.REGISTER_TOPIC_EMOJI);
        return new ResponseEntity<>(ECommerceMessage.USER_CREATED, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteByUser")
    public ResponseEntity<?> deleteByUser(@RequestBody UserDeleteRequest userDeleteRequest) {
        userService.authDeleteByUser(userDeleteRequest);
        sendEmailService.sendEmails(userDeleteRequest.getEMail(), ECommerceMessage.AUTH_DELETE_BODY, ECommerceMessage.AUTH_DELETE_TOPIC + ECommerceMessage.AUTH_DELETE_TOPIC_EMOJI);
        return new ResponseEntity<>(ECommerceMessage.USER_DELETED, HttpStatus.OK);
    }

}
