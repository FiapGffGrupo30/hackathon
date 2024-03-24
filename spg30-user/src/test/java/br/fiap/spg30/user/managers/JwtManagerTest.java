package br.fiap.spg30.user.managers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class JwtManagerTest {

    @Test
    public void shouldGenerateToken() {
        JwtManager jwtManager = new JwtManager();
        String token = jwtManager.generate(Set.of("admin"));
        assertNotNull(token);
    }

}