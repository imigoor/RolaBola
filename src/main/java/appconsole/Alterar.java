package appconsole;

import requisito.Fachada;

public class Alterar {
    public Alterar() {
        try {
            Fachada.inicializar();
            System.out.println("Alterando Jogo 1...");
            Fachada.alterarJogoTrocarTimes(1);
            System.out.println("Alteração concluída.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Fachada.finalizar();
        }
    }

    public static void main(String[] args) {
        new Alterar();
    }
}

