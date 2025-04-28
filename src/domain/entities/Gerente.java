package domain.entities;

public class Gerente extends Empregado {
    private double gratificacao;

    public Gerente(int matricula, double quantidade, double gratificacao) {
        super(matricula, quantidade);
        this.gratificacao = gratificacao;
    }

    public double getSalario() {
        return super.getSalario() + gratificacao;
    }

    public double getGratificacao() {
        return gratificacao;
    }

    @Override
    public String toString() {
        return "Gerente:   matricula=" + getMatricula() + ", dataAdmissao=" + getDataAdmissao()
                + ", quantidade=" + getQuantidade() + ", loja=" + getLoja().getNome() + ", gratificacao=" + gratificacao
                + ", getSalario=" + getSalario() ;
    }
}
