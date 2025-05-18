package domain.modelos;

import java.util.ArrayList;
import java.util.List;

public class Time {
    private String nome;
    private int pontuacao;
    private List<Jogo> jogos;

    // Construtor padrão exigido pelo db4o
    public Time() {
        this.jogos = new ArrayList<>();
    }

    // Construtor com nome
    public Time(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
        this.jogos = new ArrayList<>();
    }

    // Getters e setters
    public String getNome() { return nome; }

    //public void setNome(String nome) { this.nome = nome; }

    //public int getPontuacao() { return pontuacao; }

    //public void setPontuacao(int pontuacao) { this.pontuacao = pontuacao; }

    public List<Jogo> getJogos() { return jogos; }

    //public void setJogos(List<Jogo> jogos) { this.jogos = jogos; }

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