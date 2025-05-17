package application;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import domain.entities.Time;
import domain.entities.Jogo;
import infrastructure.Util;

public class Apagar {
    public static void main(String[] args) {
        ObjectContainer db = Util.conectarBanco();

        Query q = db.query();
        q.constrain(Time.class);
        q.descend("nome").constrain("Fluminense");

        ObjectSet result = q.execute();

        if (!result.isEmpty()) {
            Time time = (Time) result.get(0);

            if (time.getJogos() != null && !time.getJogos().isEmpty()) {
                db.delete(time);
                System.out.println("Time apagado com sucesso (possui jogos associados).");
            } else {
                System.out.println("O time não possui jogos associados e não será apagado.");
            }
        } else {
            System.out.println("Time não encontrado.");
        }

        Util.desconectar();
    }
}

//for (Jogo jogo : time.getJogos()) {
//        db.delete(jogo); // remove os jogos
//                }
//                        db.delete(time); // remove o time
//                System.out.println("Time e jogos associados foram apagados (modo destrutivo).");
//
