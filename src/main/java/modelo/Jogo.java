package modelo;

import jakarta.persistence.*;

@Entity
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String dataHora;
    private String local;

    @ManyToOne
    @JoinColumn(name = "time_casa_id")
    private Time timeCasa;

    @ManyToOne
    @JoinColumn(name = "time_visita_id")
    private Time timeVisita;

    private int gols1;
    private int gols2;

    public Jogo() { }

    public Jogo(int id, String dataHora, String local, Time timeCasa, Time timeVisita, int gols1, int gols2) {
        this.id = id;
        this.dataHora = dataHora;
        this.local = local;
        this.timeCasa = timeCasa;
        this.timeVisita = timeVisita;
        this.gols1 = gols1;
        this.gols2 = gols2;
        atualizarPontuacaoDosTimes();
    }

    private void atualizarPontuacaoDosTimes()
    {
        if (gols1 > gols2) {
            timeCasa.adicionarPontos(3);
        } else if (gols1 < gols2) {
            timeVisita.adicionarPontos(3);
        } else {
            timeCasa.adicionarPontos(1);
            timeVisita.adicionarPontos(1);
        }
    }

    public void removerPontuacaoDosTimes() {
        if (gols1 > gols2) {
            timeCasa.adicionarPontos(-3);
        } else if (gols1 < gols2) {
            timeVisita.adicionarPontos(-3);
        } else {
            timeCasa.adicionarPontos(-1);
            timeVisita.adicionarPontos(-1);
        }
    }

    public Time getTimeCasa() { return timeCasa; }

    public void setTimeCasa(Time timeCasa) { this.timeCasa = timeCasa; }

    public Time getTimeVisita() { return timeVisita; }

    public void setTimeVisita(Time timeVisita) { this.timeVisita = timeVisita; }

    public int getGols1() { return gols1; }

    public void setGols1(int gols1) { this.gols1 = gols1; }

    public int getGols2() { return gols2; }

    public void setGols2(int gols2) { this.gols2 = gols2; }

    @Override
    public String toString() {
        return "Jogo -> " +
                "id: " + id +
                ", dataHora: '" + dataHora + '\'' +
                ", local: '" + local + '\'' +
                ", timeCasa: " + (timeCasa != null ? timeCasa.getNome() : "N/A") +
                ", timeVisita: " + (timeVisita != null ? timeVisita.getNome() : "N/A") +
                ", gols1: " + gols1 +
                ", gols2: " + gols2;
    }
}