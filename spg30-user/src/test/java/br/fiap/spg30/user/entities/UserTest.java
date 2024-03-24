package br.fiap.spg30.user.entities;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


@QuarkusTest
class UserTest {

    @InjectMock
    Session session;


    @BeforeEach
    public void setup() {
        Mockito.doNothing().when(session).persist(Mockito.any());
    }


    @Test
    void shouldAddUser() {
        User.add("username", "email", "password", "role");
        Mockito.verify(session, Mockito.times(1)).persist(Mockito.any());
    }

    @Test
    void shouldFindByUserName() {
        PanacheMock.mock(User.class);
        User u = new User();
        Mockito.when(User.findByUserName(Mockito.anyString())).thenReturn(u);
        User user = User.findByUserName("username");
        Assertions.assertSame(u, user);
    }
}