package domain.entities;

import java.util.ArrayList;

public class Loja {
    private String cnpj;
    private String nome;
    private ArrayList<Empregado> empregados = new ArrayList<>();

    public Loja(String cnpj, String nome, Gerente gerente) {
        this.cnpj = cnpj;
        this.nome = nome;
        adicionar(gerente);
    }

    public double getSalarioTotal() {
        double soma=0;
        for(Empregado e : empregados)
            soma += e.getSalario();
        return soma;
    }

    public double getDescontoTotal() {
        double desc=0;
        for(Empregado e : empregados)
            desc += e.getDesconto();
        return desc;
    }

    public Empregado getMaraja() {
        double max=0;
        Empregado maraja=null;
        for(Empregado e : empregados)
            if(e.getSalario() > max){
                max = e.getSalario();
                maraja = e;
            }
        return maraja;
    }

    public ArrayList<Empregado> getEmpregados() {
        return empregados;
    }

    public Gerente getGerente() {
        return (Gerente) empregados.get(0);
    }

    public void adicionar(Empregado e) {
        empregados.add(e);
        e.setLoja(this);
    }

    public void remover(Empregado e) {
        empregados.remove(e);
        e.setLoja(null);
    }


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        String listaEmpregados = "";
        for (Empregado empregado : empregados) {
            listaEmpregados += empregado.getMatricula() + ", ";
        }
        // Remove a última vírgula e espaço
        if (listaEmpregados.length() > 0) {
            listaEmpregados = listaEmpregados.substring(0, listaEmpregados.length() - 2);
        }

        return "CNPJ: " + cnpj + "\n" +
                "Nome: " + nome + "\n" +
                "Empregados: " + listaEmpregados + "\n";
    }
}
