package br.fiap.spg30.user.entities;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "TB_USERS")
@UserDefinition
public class User extends PanacheEntity {

    @Username
    @Column(unique = true)
    public String username;

    @Email
    public String email;

    @Password
    public String password;

    @Roles
    public String role;

    public static void add(String username, String email, String password, String role) {
        User user = new User();
        user.username = username;
        user.email = email;
        user.password = BcryptUtil.bcryptHash(password);
        user.role = role;
        user.persist();
    }

    public static User findByUserName(String username) {
        return find("username", username).firstResult();
    }
}
