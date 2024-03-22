package br.fiap.spg30.backend.dto;

import br.fiap.spg30.backend.entities.Ponto;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class RelatorioDTO {

    private Long userId;
    private LocalDate peridioInicial;
    private LocalDate periodoFinal;
    private List<Marcacoes> marcacoes; // <Entrada, Saida>
    private Duration duracaoTotal;

    public static class Marcacoes {
        private final LocalDate data;
        private final LocalDateTime entrada;
        private final LocalDateTime saida;
        private final Duration duracao;

        public Marcacoes(LocalDate data, LocalDateTime entrada, LocalDateTime saida) {
            this.data = data;
            this.entrada = entrada;
            this.saida = saida;
            this.duracao = Duration.between(entrada, saida);
        }

        public LocalDateTime getEntrada() {
            return entrada;
        }

        public LocalDateTime getSaida() {
            return saida;
        }

        public Duration getDuracao() {
            return duracao;
        }

        public LocalDate getData() {
            return data;
        }
    }

    public static RelatorioDTO from(List<Ponto> pontos) {
        RelatorioDTO relatorio = new RelatorioDTO();
        if (pontos.isEmpty()) {
            return relatorio;
        }
        relatorio.userId = pontos.getFirst().userId;
        obterPeriodosRelatorio(pontos, relatorio);
        for (LocalDateTime marcacao : pontos.stream().map(p -> p.marcacoes).flatMap(List::stream).sorted().toList()) {
            //WIP - Implementar o calculo de duracao total
            relatorio.marcacoes.add(new Marcacoes(marcacao.toLocalDate(), marcacao, null));
        }
        return relatorio;
    }

    private static void obterPeriodosRelatorio(List<Ponto> pontos, RelatorioDTO relatorio) {
        relatorio.peridioInicial = pontos.stream().min(Comparator.comparing(p -> p.data)).map(p -> p.data).orElse(null);
        relatorio.periodoFinal = pontos.stream().max(Comparator.comparing(p -> p.data)).map(p -> p.data).orElse(null);
    }


}
