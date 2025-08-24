package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Time;

public class TimeDAO extends DAO<Time> {
    public Time read(Object chave) {
        try {
            String nome = (String) chave;
            TypedQuery<Time> q = manager.createQuery("select t from Time t where t.nome = :n", Time.class);
            q.setParameter("n", nome);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
