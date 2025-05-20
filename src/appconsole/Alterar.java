package appconsole;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import modelo.Jogo;
import modelo.Time;

public class Alterar {
    public static void main(String[] args) {
        ObjectContainer db = Util.conectarBanco();

        Time time = buscarTimePorNome(db, "Fluminense");
        Jogo jogo = buscarJogoPorId(db, 1);

        // Para adicionar relacionamento:
         adicionarRelacionamento(db, time, jogo);

        // Para remover relacionamento:
//        removerRelacionamento(db, time, jogo);

        Util.desconectar();
    }

    private static void adicionarRelacionamento(ObjectContainer db, Time time, Jogo jogo) {
        if (!time.getJogos().contains(jogo)) {
            Time timeAntigo = jogo.getTimeCasa();
            if (timeAntigo != null) {
                timeAntigo.removerJogo(jogo);
                db.store(timeAntigo);
            }

            jogo.setTimeCasa(time);
            db.store(jogo);

            time.adicionarJogo(jogo);
            db.store(time);

            System.out.println("Relacionamento adicionado: Jogo ao Time.");
        } else {
            System.out.println("Relacionamento já existe. Nada adicionado.");
        }
    }

    private static void removerRelacionamento(ObjectContainer db, Time time, Jogo jogo) {
        if (time.getJogos().contains(jogo)) {
            jogo.getTimeCasa().removerJogo(jogo);
            jogo.getTimeVisita().removerJogo(jogo);

            db.store(jogo.getTimeVisita());
            db.store(jogo.getTimeCasa());

//            jogo.setTimeCasa(null);
//            jogo.setTimeVisita(null);
//            db.store(jogo);

            db.delete(jogo);

            System.out.println("Relacionamento removido.");
        } else {
            System.out.println("Relacionamento não existe. Nada removido.");
        }
    }


    // Métodos auxiliares para evitar repetição
    private static Time buscarTimePorNome(ObjectContainer db, String nome) {
        Query q = db.query();
        q.constrain(Time.class);
        q.descend("nome").constrain(nome);
        return (Time) q.execute().get(0);
    }

    private static Jogo buscarJogoPorId(ObjectContainer db, int id) {
        Query q = db.query();
        q.constrain(Jogo.class);
        q.descend("id").constrain(id);
        return (Jogo) q.execute().get(0);
    }
}

