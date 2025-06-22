package appconsole;

import jakarta.persistence.*;
import java.util.List;
import modelo.Jogo;
import modelo.Time;

public class Listar {
    private EntityManager manager;

    public Listar() {
        try {
            manager = Util.conectarBanco();

            System.out.println("\n=== TIMES CADASTRADOS ===");
            List<Time> times = manager.createQuery("SELECT t FROM Time t", Time.class).getResultList();
            times.forEach(System.out::println);

            System.out.println("\n=== JOGOS CADASTRADOS ===");
            List<Jogo> jogos = manager.createQuery("SELECT j FROM Jogo j", Jogo.class).getResultList();
            jogos.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Algo deu errado!! Erro: " + e.getMessage());
        }

        Util.fecharBanco();
        System.out.println("\n Fim da aplicação");
    }

    public static void main(String[] args) {
        new Listar();
    }
}
