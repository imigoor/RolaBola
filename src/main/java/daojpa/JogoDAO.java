package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Jogo;
import modelo.Time;
import java.util.List;

public class JogoDAO extends DAO<Jogo> {
    public Jogo read(Object chave) {
        try {
            int id = (int) chave;
            TypedQuery<Jogo> q = manager.createQuery("select j from Jogo j where j.id = :id", Jogo.class);
            q.setParameter("id", id);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Jogo> jogosPorData(String data) {
        TypedQuery<Jogo> q = manager.createQuery("select j from Jogo j where j.dataHora = :data", Jogo.class);
        q.setParameter("data", data);
        return q.getResultList();
    }

    public List<Jogo> jogosTimeCasa(String nomeTime) {
        TypedQuery<Jogo> q = manager.createQuery("select j from Jogo j where j.timeCasa.nome = :nome", Jogo.class);
        q.setParameter("nome", nomeTime);
        return q.getResultList();
    }

    public List<Time> timesComMaisDeNJogos(int n) {
        TypedQuery<Time> q = manager.createQuery(
                "select t from Time t where size(t.jogosEmCasa) + size(t.jogosFora) > :n", Time.class);
        q.setParameter("n", n);
        return q.getResultList();
    }
}
