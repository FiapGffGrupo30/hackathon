package br.fiap.spg30.backend.manager;

import br.fiap.spg30.backend.dto.RelatorioDTO;
import br.fiap.spg30.backend.entities.Ponto;

import java.time.LocalDate;
import java.util.List;

public class PontoManager {

    public static Ponto getByUserIdAndDate(Long userId, LocalDate date) {
        return Ponto.findByUserIdAndData(userId, date);
    }

    public static List<Ponto> getAllByUserId(Long userId) {
        return Ponto.findAllByUserId(userId);
    }

    public static void addMarcacao(Long userId) {
        Ponto.addMarcacao(userId);
    }

    public static RelatorioDTO getRelatorio(Long userId, LocalDate startDate, LocalDate endDate) {
        return RelatorioDTO.from(getAllBetweenDates(userId, startDate, endDate));
    }

    private static List<Ponto> getAllBetweenDates(Long userId, LocalDate startDate, LocalDate endDate) {
        return Ponto.findAllBetweenDates(userId, startDate, endDate);
    }
}
