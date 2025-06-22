package appconsole;

import jakarta.persistence.*;
import modelo.Jogo;
import modelo.Time;

public class Apagar {
    private EntityManager manager;

    public Apagar() {
        try {
            manager = Util.conectarBanco();

            TypedQuery<Time> query = manager.createQuery("SELECT t FROM Time t WHERE t.nome = :nome", Time.class);
            query.setParameter("nome", "Vasco");

            Time time = query.getSingleResult();

            manager.getTransaction().begin();

            if (!time.getTodosOsJogos().isEmpty()) {
                for (Jogo jogo : time.getTodosOsJogos()) {
                    jogo.removerPontuacaoDosTimes();

                    Time timeCasa = jogo.getTimeCasa();
                    Time timeVisita = jogo.getTimeVisita();

                    if (timeCasa != null && !timeCasa.equals(time)) {
                        timeCasa.removerJogo(jogo);
                    }

                    if (timeVisita != null && !timeVisita.equals(time)) {
                        timeVisita.removerJogo(jogo);
                    }

                    manager.remove(jogo);
                }

                manager.remove(time);
                System.out.println("Time apagado com sucesso (possui jogos associados).");
            } else {
                manager.remove(time);
                System.out.println("Time apagado com sucesso (sem jogos associados).");
            }

            manager.getTransaction().commit();

        } catch (NoResultException e) {
            System.out.println("Time não encontrado.");
        } catch (Exception e) {
            System.out.println("Exceção: " + e.getMessage());
        }

        Util.fecharBanco();
        System.out.println("Fim da aplicação");
    }

    public static void main(String[] args) {
        new Apagar();
    }
}
