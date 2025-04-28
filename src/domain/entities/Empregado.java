package domain.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Empregado {
    private int matricula;
    private String dataAdmissao;
    private double quantidade;
    private static double salarioMinimo;
    private Loja loja;

    public Empregado(int matricula, double quantidade) {
        this.matricula = matricula;
        this.quantidade = quantidade;
        this.dataAdmissao = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public double getSalario() {
        return (quantidade * salarioMinimo) - getDesconto();
    }

    public double getDesconto() {
        return salarioMinimo / 10;    //10% do salario minimo
    }

    public static void setSalarioMinimo(double valor) {
        salarioMinimo = valor;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public static double getSalarioMinimo() {
        return salarioMinimo;
    }

    @Override
    public String toString() {
        return "Empregado: matricula=" + matricula + ", dataAdmissao=" + dataAdmissao + ", quantidade=" + quantidade
                + ", loja=" + loja.getNome() + ", getSalario()=" + getSalario() + "]";
    }
}
