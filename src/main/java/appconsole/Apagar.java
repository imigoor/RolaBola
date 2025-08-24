package appconsole;

import requisito.Fachada;

public class Apagar {
    public Apagar() {
        try {
            Fachada.inicializar();
            System.out.println("Apagando Jogo 1...");
            Fachada.excluirJogo(1);
            System.out.println("Apagando Time 'Fortaleza'...");
            Fachada.excluirTime("Fortaleza");
            System.out.println("Exclusão concluída.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Fachada.finalizar();
        }
    }

    public static void main(String[] args) {
        new Apagar();
    }
}
