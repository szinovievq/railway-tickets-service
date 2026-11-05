package me.zinoviev.railway.auth.dto;

public record AuthResponse(
        String token,
        String email,
        String role
) {
}
