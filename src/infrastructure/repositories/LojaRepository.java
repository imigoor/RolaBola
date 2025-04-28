package infrastructure.repositories;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import domain.entities.Loja;
import infrastructure.Database;

import java.util.List;

public class LojaRepository {
    private ObjectContainer manager;

    public LojaRepository() {
        this.manager = Database.open();
    }

    public void salvar(Loja loja) {
        manager.store(loja);
        manager.commit();
    }

    public List<Loja> listarTodos() {
        Query q = manager.query();
        q.constrain(Loja.class);
        return q.execute();
    }

    public Loja buscarPorCnpj(String cnpj) {
        Query q = manager.query();
        q.constrain(Loja.class);
        q.descend("cnpj").constrain(cnpj);
        List<Loja> resultado = q.execute();
        if (!resultado.isEmpty()) {
            return resultado.get(0);
        }
        return null;
    }

    public void deletar(Loja loja) {
        manager.delete(loja);
        manager.commit();
    }

    public void fechar() {
        Database.close();
    }
}