package application;

import domain.entities.Empregado;
import domain.entities.Gerente;
import domain.entities.Loja;
import infrastructure.repositories.LojaRepository;

import java.util.List;

public class LojaUseCase {

    private final LojaRepository lojaRepository;

    public LojaUseCase() {
        this.lojaRepository = new LojaRepository();
    }

    public void criarLoja(String cnpj, String nome, Gerente gerente) {
        Loja loja = new Loja(cnpj, nome, gerente);
        lojaRepository.salvar(loja);
    }

    public List<Loja> listarLojas() {
        return lojaRepository.listarTodos();
    }

    public Loja buscarLoja(String cnpj) {
        return lojaRepository.buscarPorCnpj(cnpj);
    }

    public void adicionarEmpregadoNaLoja(String cnpj, Empregado empregado) {
        Loja loja = buscarLoja(cnpj);
        if (loja != null) {
            loja.adicionar(empregado);
            lojaRepository.salvar(loja); // update simples no db4o
        }
    }

    public void removerEmpregadoDaLoja(String cnpj, int matricula) {
        Loja loja = buscarLoja(cnpj);
        if (loja != null) {
            loja.getEmpregados().removeIf(e -> e.getMatricula() == matricula);
            lojaRepository.salvar(loja);
        }
    }

    public void removerLoja(String cnpj) {
        Loja loja = buscarLoja(cnpj);
        if (loja != null) {
            lojaRepository.deletar(loja);
        }
    }

    public void fecharSistema() {
        lojaRepository.fechar();
    }
}