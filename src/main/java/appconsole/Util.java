package appconsole;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.log4j.Logger;

public class Util {
    private static EntityManagerFactory factory;
    private static EntityManager manager;
    private static final Logger logger = Logger.getLogger(Util.class);

    public static EntityManager conectarBanco() {
        if (manager == null || !manager.isOpen()) {
            factory = Persistence.createEntityManagerFactory("RolaBolaPU");
            manager = factory.createEntityManager();
            logger.debug("Conexão com o banco estabelecida.");
        }
        return manager;
    }

    public static void fecharBanco() {
        if (manager != null && manager.isOpen()) {
            manager.close();
            logger.debug("EntityManager fechado.");
        }
        if (factory != null && factory.isOpen()) {
            factory.close();
            logger.debug("EntityManagerFactory fechado.");
        }
        manager = null;
        factory = null;
    }
}
