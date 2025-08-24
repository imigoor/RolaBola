package appconsole;

import modelo.Jogo;
import modelo.Time;
import requisito.Fachada;

public class Listar {
    public Listar() {
        try {
            Fachada.inicializar();
            System.out.println("\n--- TIMES ---");
            for (Time t : Fachada.listarTimes()) {
                System.out.println(t);
            }
            System.out.println("\n--- JOGOS ---");
            for (Jogo j : Fachada.listarJogos()) {
                System.out.println(j);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Fachada.finalizar();
        }
    }

    public static void main(String[] args) {
        new Listar();
    }
}
