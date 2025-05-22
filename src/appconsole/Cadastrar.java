package appconsole;

import com.db4o.ObjectContainer;
import modelo.Jogo;
import modelo.Time;

public class Cadastrar {
    public static void main(String[] args) {
        ObjectContainer db = Util.conectarBanco();

        Time t1 = new Time("Barcelona", 0);
        Time t2 = new Time("Real Madrid", 16);
        Time t3 = new Time("Palmeiras", 2);
        Time t4 = new Time("Fluminense", 6);
        Time t5 = new Time("Cruzeiro", 12);

        Jogo j1 = new Jogo(1, "2024-06-01 15:00", "Camp Nou", t1, t2, 2, 1);
        Jogo j2 = new Jogo(2, "2024-20-01 20:00", "Allianz", t3, t2, 0, 3);
        Jogo j3 = new Jogo(3, "2024-22-06 19:00", "Morumbi ", t2, t5, 0, 3);

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

        db.commit();
        Util.desconectar();
        System.out.println("Times e jogos cadastrados.");
    }
}