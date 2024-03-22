package br.fiap.spg30.backend.entities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@MongoEntity(collection = "pontos")
public class Ponto extends PanacheMongoEntity {
    public Long userId;
    public LocalDate data;
    public List<LocalDateTime> marcacoes = new ArrayList<>();

    public static List<Ponto> findAllByUserId(Long userId) {
        return list("userId", userId);
    }

    public static Ponto findByUserIdAndData(Long userId, LocalDate data) {
        return find("userId = ?1 and data = ?2", userId, data).firstResult();
    }

    public static void addMarcacao(Long userId) {
        Ponto ponto = Ponto.find("userId = ?1 and data = ?2", userId, LocalDate.now()).firstResult();
        if (Objects.isNull(ponto)) {
            ponto = Ponto.createFrom(userId, LocalDate.now());
            ponto.persist();
        }
        ponto.marcacoes.add(LocalDateTime.now());
        ponto.update();
    }

    public static List<Ponto> findAllBetweenDates(Long userId, LocalDate startDate, LocalDate endDate) {
        return list("userId = ?1 and data >= ?2 and data <= ?3", userId, startDate, endDate);
    }

    private static Ponto createFrom(Long userId, LocalDate data) {
        Ponto ponto = new Ponto();
        ponto.userId = userId;
        ponto.data = data;
        return ponto;
    }
}
