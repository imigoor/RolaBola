package appconsole;

import jakarta.persistence.*;
import modelo.*;
import java.util.List;

public class Consultar {
    private EntityManager manager;

    public Consultar() {
        try {
            manager = Util.conectarBanco();

            System.out.println("\n[JOGOS NA DATAHORA '01-06-2024 15:00']");
            List<Jogo> jogosData = manager.createQuery("SELECT j FROM Jogo j WHERE j.dataHora = :data", Jogo.class)
                    .setParameter("data", "01-06-2024 15:00")
                    .getResultList();
            jogosData.forEach(System.out::println);

            System.out.println("\n[JOGOS ONDE TIME 'Real Madrid' JOGA EM CASA]");
            List<Jogo> jogosCasa = manager.createQuery("SELECT j FROM Jogo j WHERE j.timeCasa.nome = :nome", Jogo.class)
                    .setParameter("nome", "Real Madrid")
                    .getResultList();
            jogosCasa.forEach(System.out::println);

            System.out.println("\n[TIMES COM MAIS DE 1 JOGO]");
            List<Time> times = manager.createQuery(
                    "SELECT t FROM Time t " +
                            "JOIN Jogo j ON j.timeCasa = t OR j.timeVisita = t " +
                            "GROUP BY t " +
                            "HAVING COUNT(j) > 1", Time.class
            ).getResultList();
            times.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Exceção: " + e.getMessage());
        }

        Util.fecharBanco();
        System.out.println("Fim da aplicação");
    }

    public static void main(String[] args) {
        new Consultar();
    }
}

