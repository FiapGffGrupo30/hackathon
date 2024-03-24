package br.fiap.spg30.backend.entities;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PontoTest {

    @Test
    void shouldFindAllByUserId() {
        PanacheMock.mock(Ponto.class);
        Mockito.when(Ponto.findAllByUserId(1L)).thenReturn(new ArrayList<>());
        List<Ponto> pontos = Ponto.findAllByUserId(1L);
        assertEquals(0, pontos.size());
    }

    @Test
    void findByUserIdAndData() {
        PanacheMock.mock(Ponto.class);
        Ponto p = new Ponto();
        Mockito.when(Ponto.findByUserIdAndData(1L, LocalDate.now())).thenReturn(p);
        Ponto ponto = Ponto.findByUserIdAndData(1L, LocalDate.now());
        assertNotNull(ponto);
        assertEquals(p, ponto);
    }

}