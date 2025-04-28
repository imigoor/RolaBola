import application.LojaUseCase;
import domain.entities.Empregado;
import domain.entities.Gerente;

public class Main {
    public static void main(String[] args) {
        LojaUseCase sistema = new LojaUseCase();

        Empregado.setSalarioMinimo(1500);

        Gerente gerente = new Gerente(1, 2, 500); // matrícula 1, quantidade 2, gratificação 500
        sistema.criarLoja("21345676521", "lojas americanas", gerente);

        sistema.adicionarEmpregadoNaLoja("12345678910", new Empregado(2, 1)); // matrícula 2
        sistema.adicionarEmpregadoNaLoja("12345678910", new Empregado(3, 3)); // matrícula 3

        System.out.println("\n--- Listagem de lojas:");
        sistema.listarLojas().forEach(System.out::println);

        System.out.println("\n--- Buscar loja:");
        System.out.println(sistema.buscarLoja("21345676521"));

        //sistema.removerEmpregadoDaLoja("11111111", 2); // Remove empregado matrícula 2

        //System.out.println("\n--- Loja após remoção de empregado:");
        //System.out.println(sistema.buscarLoja("11111111"));

        //sistema.removerLoja("11111111");

        //System.out.println("\n--- Lojas após remoção:");
        //sistema.listarLojas().forEach(System.out::println);

        sistema.fecharSistema();
    }
}