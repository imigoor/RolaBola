package application;

import com.db4o.ObjectContainer;
import domain.modelos.*;

public class Cadastrar {
    public static void main(String[] args) {
        ObjectContainer db = Util.conectarBanco();

        Time t1 = new Time("Barcelona");
        Time t2 = new Time("Real Madrid");
        Time t3 = new Time("Palmeiras");
        Time t4 = new Time("Fluminense");
        Time t5 = new Time("Cruzeiro");

        Jogo j1 = new Jogo(1, "2024-06-01 15:00", "Camp Nou", t1, t2, 2, 1, 5000);
        Jogo j2 = new Jogo(2, "2024-20-01 20:00", "Allianz", t3, t2, 0, 3, 8000);
        Jogo j3 = new Jogo(3, "2024-22-06 19:00", "Morumbi ", t2, t5, 0, 3, 10000);

        t1.adicionarJogo(j1);
        t2.adicionarJogo(j1);

        t2.adicionarJogo(j2);
        t3.adicionarJogo(j2);

        t2.adicionarJogo(j3);
        t5.adicionarJogo(j3);

        db.store(t1);
        db.store(t2);
        db.store(t3);
        db.store(t4);
        db.store(t5);

        Util.desconectar();
        System.out.println("Times e jogos cadastrados.");
    }
}