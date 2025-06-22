package appconsole;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.Jogo;
import modelo.Time;

public class Cadastrar {
    private EntityManager manager;

    public Cadastrar() {
        try {
            manager = Util.conectarBanco();

            System.out.println("Cadastrando Times...");
            Time t1 = new Time("Barcelona");
            Time t2 = new Time("Real Madrid");
            Time t3 = new Time("Palmeiras");
            Time t4 = new Time("Fluminense");
            Time t5 = new Time("Flamengo");
            Time t6 = new Time("Vasco");
            Time t7 = new Time("Fortaleza");
            System.out.println("Times Cadastrados com Sucesso!");

            manager.getTransaction().begin();
            manager.persist(t1);
            manager.persist(t2);
            manager.persist(t3);
            manager.persist(t4);
            manager.persist(t5);
            manager.persist(t6);
            manager.persist(t7);
            manager.getTransaction().commit();

            System.out.println("Cadastrando Jogos...");
            Jogo j1 = new Jogo(0, "01-06-2024 15:00", "Olímpico Lluís Companys", t1, t2, 4, 3);
            Jogo j2 = new Jogo(0, "20-01-2024 20:00", "Allianz", t3, t2, 0, 3);
            Jogo j3 = new Jogo(0, "22-06-2024 19:00", "Morumbi", t2, t5, 4, 2);
            Jogo j4 = new Jogo(0, "28-02-2025 19:00", "Allianz Parque", t5, t3, 2, 0);
            Jogo j5 = new Jogo(0, "01-06-2024 15:00", "São Januário", t6, t7, 3, 3);
            System.out.println("Jogos Cadastrados com Sucesso!");

            manager.getTransaction().begin();
            manager.persist(j1);
            manager.persist(j2);
            manager.persist(j3);
            manager.persist(j4);
            manager.persist(j5);
            manager.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Algo deu errado!! Erro: " + e.getMessage());
        }

        Util.fecharBanco();
        System.out.println("Fim da aplicação");
    }

    // =================================================
    public static void main(String[] args) {  new Cadastrar(); }
    // =================================================
}