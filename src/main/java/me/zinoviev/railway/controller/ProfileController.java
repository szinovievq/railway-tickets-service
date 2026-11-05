package me.zinoviev.railway.controller;

import me.zinoviev.railway.auth.AppUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @GetMapping
    public String getProfile(@AuthenticationPrincipal AppUserDetails principal){
        Long userId = principal.getUserId();
        return "Current user ID: " + userId;
    }

}
