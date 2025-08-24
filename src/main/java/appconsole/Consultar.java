package appconsole;

import modelo.*;
import requisito.Fachada;

public class Consultar {
    public Consultar() {
        try {
            Fachada.inicializar();
            System.out.println("\n--- JOGOS POR DATA ('01-06-2024 15:00') ---");
            for (Jogo j : Fachada.jogosPorData("01-06-2024 15:00")) { System.out.println(j); }

            System.out.println("\n--- JOGOS ONDE O REAL MADRID É O TIME DA CASA ---");
            for (Jogo j : Fachada.jogosTimeCasa("Real Madrid")) { System.out.println(j); }

            System.out.println("\n--- TIMES COM MAIS DE 1 JOGO ---");
            for (Time t : Fachada.timesComMaisDeNJogos(1)) { System.out.println(t); }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Fachada.finalizar();
        }
    }

    public static void main(String[] args) {
        new Consultar();
    }
}

