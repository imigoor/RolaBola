package appconsole;

import jakarta.persistence.*;
import modelo.Jogo;
import modelo.Time;

public class Alterar {
    private EntityManager manager;

    public Alterar() {
        try {
            manager = Util.conectarBanco();

            Jogo jogo = manager.find(Jogo.class, 1);

            if (jogo == null) {
                System.out.println("Jogo não encontrado.");
                return;
            }

            manager.getTransaction().begin();

            Time timeCasaAtual = jogo.getTimeCasa();
            Time timeVisitaAtual = jogo.getTimeVisita();

            if (timeCasaAtual != null) {
                timeCasaAtual.removerJogo(jogo);
                manager.merge(timeCasaAtual);
            }

            if (timeVisitaAtual != null) {
                timeVisitaAtual.removerJogo(jogo);
                manager.merge(timeVisitaAtual);
            }

            jogo.setTimeCasa(timeVisitaAtual);
            jogo.setTimeVisita(timeCasaAtual);

            int golsCasa = jogo.getGols1();
            int golsVisita = jogo.getGols2();
            jogo.setGols1(golsVisita);
            jogo.setGols2(golsCasa);

            manager.merge(jogo);

            if (jogo.getTimeCasa() != null) {
                jogo.getTimeCasa().adicionarJogo(jogo);
                manager.merge(jogo.getTimeCasa());
            }

            if (jogo.getTimeVisita() != null) {
                jogo.getTimeVisita().adicionarJogo(jogo);
                manager.merge(jogo.getTimeVisita());
            }

            manager.getTransaction().commit();
            System.out.println("Times trocados com sucesso.");

        } catch (Exception e) {
            System.out.println("Exceção: " + e.getMessage());
        }

        Util.fecharBanco();
        System.out.println("Fim da aplicação");
    }

    public static void main(String[] args) {
        new Alterar();
    }
}

