package br.fiap.spg30.backend.dto;

import br.fiap.spg30.backend.entities.Ponto;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
public class RelatorioDTO {

    private Long userId;
    private LocalDate peridioInicial;
    private LocalDate periodoFinal;
    private List<Marcacoes> marcacoes = new ArrayList<>(); // <Entrada, Saida>
    private Duration duracaoTotal;

    @Data
    public static class Marcacoes {
        private final LocalDate data;
        private final LocalDateTime entrada;
        private final LocalDateTime saida;
        private final Duration duracao;

        public Marcacoes(LocalDate data, LocalDateTime entrada, LocalDateTime saida) {
            this.data = data;
            this.entrada = entrada;
            this.saida = saida;
            this.duracao = saida != null ? Duration.between(entrada, saida) : null;
        }
    }


    public static RelatorioDTO from(List<Ponto> pontos) {
        RelatorioDTO relatorio = new RelatorioDTO();
        if (pontos.isEmpty()) {
            return relatorio;
        }
        relatorio.userId = pontos.getFirst().userId;
        obterPeriodosRelatorio(pontos, relatorio);
        obterMarcacoes(pontos, relatorio);
        relatorio.duracaoTotal = relatorio.marcacoes.stream()
                .filter(m -> m.duracao != null)
                .map(m -> m.duracao)
                .reduce(Duration.ZERO, Duration::plus);
        return relatorio;
    }

    private static void obterMarcacoes(List<Ponto> pontos, RelatorioDTO relatorio) {
        List<LocalDateTime> marcacoes = pontos.stream().map(p -> p.marcacoes).flatMap(List::stream).toList();
        for (int i = 0; i < marcacoes.size(); i += 2) {
            LocalDateTime entrada = marcacoes.get(i);
            LocalDateTime saida = i + 1 >= marcacoes.size() ? null : marcacoes.get(i + 1);
            relatorio.marcacoes.add(new Marcacoes(entrada.toLocalDate(), entrada, saida));
        }
    }

    private static void obterPeriodosRelatorio(List<Ponto> pontos, RelatorioDTO relatorio) {
        relatorio.peridioInicial = pontos.stream().min(Comparator.comparing(p -> p.data)).map(p -> p.data).orElse(null);
        relatorio.periodoFinal = pontos.stream().max(Comparator.comparing(p -> p.data)).map(p -> p.data).orElse(null);
    }


}
