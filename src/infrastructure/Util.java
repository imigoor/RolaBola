package infrastructure;

import com.db4o.*;
import com.db4o.config.EmbeddedConfiguration;
import domain.entities.Jogo;
import domain.entities.Time;

public class Util {
    private static final String DB_FILE = "rolabola.db4o";
    private static ObjectContainer manager;

    public static ObjectContainer conectarBanco() {
        if (manager != null) return manager;

        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().messageLevel(0);

        config.common().objectClass(Time.class).cascadeOnUpdate(true);
        config.common().objectClass(Time.class).cascadeOnDelete(true);
        config.common().objectClass(Time.class).cascadeOnActivate(true);

        config.common().objectClass(Jogo.class).cascadeOnUpdate(true);
        config.common().objectClass(Jogo.class).cascadeOnDelete(true);
        config.common().objectClass(Jogo.class).cascadeOnActivate(true);

        manager = Db4oEmbedded.openFile(config, DB_FILE);
        return manager;
    }

    public static void desconectar() {
        if (manager != null) {
            manager.close();
            manager = null;
        }
    }
}
