package appconsole;

import requisito.Fachada;

public class Cadastrar {
    public Cadastrar() {
        try {
            Fachada.inicializar();

            System.out.println("Cadastrando Times...");
            Fachada.cadastrarTime("Barcelona", null);
            Fachada.cadastrarTime("Real Madrid", null);
            Fachada.cadastrarTime("Palmeiras", null);
            Fachada.cadastrarTime("Fluminense", null);
            Fachada.cadastrarTime("Flamengo", null);
            Fachada.cadastrarTime("Vasco", null);
            Fachada.cadastrarTime("Fortaleza", null);
            System.out.println("Times Cadastrados com Sucesso!");

            System.out.println("Cadastrando Jogos...");
            // O Fachada.cadastrarJogo agora recebe os nomes dos times como Strings
            Fachada.cadastrarJogo("01-06-2024 15:00", "Olímpico Lluís Companys", "Barcelona", "Real Madrid", 4, 3);
            Fachada.cadastrarJogo("20-01-2024 20:00", "Allianz", "Palmeiras", "Real Madrid", 0, 3);
            Fachada.cadastrarJogo("22-06-2024 19:00", "Morumbi", "Real Madrid", "Flamengo", 4, 2);
            Fachada.cadastrarJogo("28-02-2025 19:00", "Allianz Parque", "Flamengo", "Palmeiras", 2, 0);
            Fachada.cadastrarJogo("01-06-2024 15:00", "São Januário", "Vasco", "Fortaleza", 3, 3);
            System.out.println("Jogos Cadastrados com Sucesso!");

        } catch (Exception e) {
            System.out.println("Algo deu errado!! Erro: " + e.getMessage());
        } finally {
            Fachada.finalizar();
            System.out.println("Fim da aplicação");
        }
    }

    // =================================================
    public static void main(String[] args) {  new Cadastrar(); }
    // =================================================
}