package application;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import domain.entities.*;
import infrastructure.Util;

public class Listar {
    public static void main(String[] args) {
        ObjectContainer db = Util.conectarBanco();

        System.out.println("=== TIMES CADASTRADOS ===");
        Query queryTimes = db.query();
        queryTimes.constrain(Time.class);
        for (Object o : queryTimes.execute()) {
            System.out.println(o);
        }

        System.out.println("\n=== JOGOS CADASTRADOS ===");
        Query queryJogos = db.query();
        queryJogos.constrain(Jogo.class);
        for (Object o : queryJogos.execute()) {
            System.out.println(o);
        }

        Util.desconectar();
    }
}
