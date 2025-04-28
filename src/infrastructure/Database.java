package infrastructure;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import domain.entities.*;

public class Database {
    private static ObjectContainer manager;

    public static ObjectContainer open() {
        if (manager != null)
            return manager;

        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().messageLevel(0);

        config.common().objectClass(Loja.class).cascadeOnDelete(false);
        config.common().objectClass(Loja.class).cascadeOnUpdate(true);
        config.common().objectClass(Loja.class).cascadeOnActivate(true);

        config.common().objectClass(Empregado.class).cascadeOnDelete(false);
        config.common().objectClass(Empregado.class).cascadeOnUpdate(true);
        config.common().objectClass(Empregado.class).cascadeOnActivate(true);

        config.common().objectClass(Gerente.class).cascadeOnDelete(false);
        config.common().objectClass(Gerente.class).cascadeOnUpdate(true);
        config.common().objectClass(Gerente.class).cascadeOnActivate(true);

        manager = Db4oEmbedded.openFile(config, "lojas.db4o");
        return manager;
    }

    public static void close() {
        if (manager != null) {
            manager.close();
            manager = null;
        }
    }
}