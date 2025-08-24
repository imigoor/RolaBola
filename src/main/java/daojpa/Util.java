package daojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Util {
    private static EntityManagerFactory factory;
    private static EntityManager manager;

    public static EntityManager conectarBanco() {
        if (manager == null || !manager.isOpen()) {
            factory = Persistence.createEntityManagerFactory("RolaBolaPU");
            manager = factory.createEntityManager();
        }
        return manager;
    }

    public static void desconectarBanco() {
        if (manager != null && manager.isOpen()) {
            manager.close();
        }
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
        manager = null;
        factory = null;
    }
}
