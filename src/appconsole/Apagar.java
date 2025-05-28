package appconsole;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import modelo.Time;
import modelo.Jogo;

public class Apagar {
    public static void main(String[] args) {
        ObjectContainer db = Util.conectarBanco();

        Query q = db.query();
        q.constrain(Time.class);
        q.descend("nome").constrain("Vasco");

        ObjectSet result = q.execute();

        if (!result.isEmpty()) {
            Time time = (Time) result.get(0);

            if (time.getJogos() != null && !time.getJogos().isEmpty()) {
                for (Jogo jogo : time.getJogos()) {
                    Time timeCasa = jogo.getTimeCasa();
                    Time timeVisita = jogo.getTimeVisita();

                    // remove o jogo da lista do time da casa, se não for o time que será removido
                    if (timeCasa != null && timeCasa != time) {
                        timeCasa.getJogos().remove(jogo);
                        db.store(timeCasa);
                    }

                    // remove o jogo da lista do time visitante, se não for o time que será removido
                    if (timeVisita != null && timeVisita != time) {
                        timeVisita.getJogos().remove(jogo);
                        db.store(timeVisita);
                    }
                }
                db.delete(time);
                System.out.println("Time apagado com sucesso (possui jogos associados).");
            } else {
                System.out.println("O time não possui jogos associados e não será apagado.");
            }
            db.commit();
        } else {
            System.out.println("Time não encontrado.");
        }

        Util.desconectar();
    }
}


