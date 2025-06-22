package modelo;


import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Time {
    @Id
    private String nome;

    private int pontuacao;

    @OneToMany(mappedBy = "timeCasa", cascade = CascadeType.ALL)
    private List<Jogo> jogosEmCasa = new ArrayList<>();

    @OneToMany(mappedBy = "timeVisita", cascade = CascadeType.ALL)
    private List<Jogo> jogosFora = new ArrayList<>();

    public Time() {
    }

    public Time(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
    }

    public String getNome() { return nome; }

    // public List<Jogo> getJogosEmCasa() { return jogosEmCasa; }

    // public List<Jogo> getJogosFora() { return jogosFora; }

    public List<Jogo> getTodosOsJogos() {
        List<Jogo> todosJogos = new ArrayList<>();
        todosJogos.addAll(jogosEmCasa);
        todosJogos.addAll(jogosFora);
        return todosJogos;
    }

    public void adicionarJogo(Jogo jogo) {
        if (jogo.getTimeCasa() != null && jogo.getTimeCasa().equals(this)) {
            if (!jogosEmCasa.contains(jogo)) {
                jogosEmCasa.add(jogo);
            }
        } else if (jogo.getTimeVisita() != null && jogo.getTimeVisita().equals(this)) {
            if (!jogosFora.contains(jogo)) {
                jogosFora.add(jogo);
            }
        }
    }

    public void adicionarPontos(int pontos) { this.pontuacao += pontos; }

    public void removerJogo(Jogo jogo) {
        jogosEmCasa.remove(jogo);
        jogosFora.remove(jogo);
    }

    @Override
    public String toString() {
        return "Time -> " +
                "nome: '" + nome + '\'' +
                ", pontuacao: " + pontuacao +
                ", total de jogos: " + getTodosOsJogos().size();
    }
}