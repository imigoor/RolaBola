package modelo;

import java.util.ArrayList;
import java.util.List;

public class Time {
    private String nome;
    private int pontuacao;
    private List<Jogo> jogos;

    public Time(String nome, int pontuacao) {
        this.nome = nome;
        this.pontuacao = pontuacao;
        this.jogos = new ArrayList<>();
    }

    // Getters e setters
    public String getNome() { return nome; }

    public List<Jogo> getJogos() { return jogos; }

    public void adicionarJogo(Jogo jogo) { if (!jogos.contains(jogo)) { jogos.add(jogo); } }

    public void removerJogo(Jogo jogo) { jogos.remove(jogo); }

    @Override
    public String toString() {
        return "Time -> " +
                "nome: '" + nome + '\'' +
                ", pontuacao: " + pontuacao +
                ", jogos: " + jogos.size() + " jogo(s)";
    }
}