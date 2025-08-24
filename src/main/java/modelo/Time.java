package modelo;


import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Time {
    @Id
    private String nome;

    private int pontuacao;
    private byte[] foto;

    @OneToMany(mappedBy = "timeCasa", cascade = CascadeType.ALL)
    private List<Jogo> jogosEmCasa = new ArrayList<>();

    @OneToMany(mappedBy = "timeVisita", cascade = CascadeType.ALL)
    private List<Jogo> jogosFora = new ArrayList<>();

    public Time() {}

    public Time(String nome, byte[] foto) {
        this.nome = nome;
        this.pontuacao = 0;
        this.foto = foto;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getPontuacao() { return pontuacao; }
    public void setPontuacao(int pontuacao) { this.pontuacao = pontuacao; }
    public byte[] getFoto() { return foto; }
    public void setFoto(byte[] foto) { this.foto = foto; }


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
    public void removerJogo(Jogo jogo) { jogosEmCasa.remove(jogo); jogosFora.remove(jogo); }

    @Override
    public String toString() {
        return "Time -> " +
                "nome: '" + nome + '\'' +
                ", pontuacao: " + pontuacao +
                ", total de jogos: " + getTodosOsJogos().size();
    }
}