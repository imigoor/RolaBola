package appconsole;

import com.db4o.ObjectContainer;
import modelo.Jogo;
import modelo.Time;

public class Cadastrar {
    public static void main(String[] args) {
        ObjectContainer db = Util.conectarBanco();

        Time t1 = new Time("Barcelona", 0);
        Time t2 = new Time("Real Madrid", 15);
        Time t3 = new Time("Palmeiras", 3);
        Time t4 = new Time("Fluminense", 6);
        Time t5 = new Time("Flamengo", 9);
        Time t6 = new Time("Vasco", 1);
        Time t7 = new Time("Fortaleza", 1);

        Jogo j1 = new Jogo(1, "01-06-2024 15:00", "Olímpico Lluís Companys", t1, t2, 4, 3);
        Jogo j2 = new Jogo(2, "20-01-2024 20:00", "Allianz", t3, t2, 0, 3);
        Jogo j3 = new Jogo(3, "22-06-2024 19:00", "Morumbi ", t2, t5, 4, 2);
        Jogo j4 = new Jogo(4, "28-02-2025 19:00", "Allianz Parque", t5, t3, 2, 0);
        Jogo j5 = new Jogo(5, "01-06-2024 15:00", "São Januário", t6, t7, 3, 3);


        t1.adicionarJogo(j1);
        t2.adicionarJogo(j1);
        t2.adicionarJogo(j2);
        t3.adicionarJogo(j2);
        t2.adicionarJogo(j3);
        t5.adicionarJogo(j3);
        t5.adicionarJogo(j4);
        t3.adicionarJogo(j4);
        t6.adicionarJogo(j5);
        t7.adicionarJogo(j5);

        db.store(t1);
        db.store(t2);
        db.store(t3);
        db.store(t4);
        db.store(t5);
        db.store(t6);
        db.store(t7);

        db.commit();
        Util.desconectar();
        System.out.println("Times e jogos cadastrados.");
    }
}