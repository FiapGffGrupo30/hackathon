package br.fiap.spg30.user;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;

import java.util.Set;

@Singleton
public class JwtService {

    public String generate(Set<String> roles) {
        return Jwt.issuer("spg30")
                .subject("backend")
                .groups(roles)
                .expiresAt(System.currentTimeMillis() + 3600)
                .sign();
    }
}
