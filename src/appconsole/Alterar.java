package appconsole;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import modelo.Jogo;
import modelo.Time;

public class Alterar {
    public static void main(String[] args) {
        ObjectContainer db = Util.conectarBanco();

        Jogo jogo = buscarJogoPorId(db, 1);

        Time timeCasaAtual = jogo.getTimeCasa();
        Time timeVisitaAtual = jogo.getTimeVisita();

        if (timeCasaAtual != null) {
            timeCasaAtual.removerJogo(jogo);
            db.store(timeCasaAtual);
        }

        if (timeVisitaAtual != null) {
            timeVisitaAtual.removerJogo(jogo);
            db.store(timeVisitaAtual);
        }

        jogo.setTimeCasa(timeVisitaAtual);
        jogo.setTimeVisita(timeCasaAtual);
        db.store(jogo);

        if (jogo.getTimeCasa() != null) {
            jogo.getTimeCasa().adicionarJogo(jogo);
            db.store(jogo.getTimeCasa());
        }

        if (jogo.getTimeVisita() != null) {
            jogo.getTimeVisita().adicionarJogo(jogo);
            db.store(jogo.getTimeVisita());
        }

        System.out.println("Times trocados com sucesso.");

        db.commit();
        Util.desconectar();
    }

    private static Jogo buscarJogoPorId(ObjectContainer db, int id) {
        Query q = db.query();
        q.constrain(Jogo.class);
        q.descend("id").constrain(id);
        return (Jogo) q.execute().get(0);
    }
}

