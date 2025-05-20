package modelo;

public class Jogo {
    private int id;
    private String dataHora;
    private String local;
    private Time timeCasa;
    private Time timeVisita;
    private int gols1;
    private int gols2;
    private int ingressos;

    public Jogo(int id, String dataHora, String local, Time timeCasa, Time timeVisita, int gols1, int gols2, int ingressos) {
        this.id = id;
        this.dataHora = dataHora;
        this.local = local;
        this.timeCasa = timeCasa;
        this.timeVisita = timeVisita;
        this.gols1 = gols1;
        this.gols2 = gols2;
        this.ingressos = ingressos;
    }

    public Time getTimeCasa() { return timeCasa; }

    public void setTimeCasa(Time timeCasa) { this.timeCasa = timeCasa; }

    public Time getTimeVisita() { return timeVisita; }

    @Override
    public String toString() {
        return "Jogo -> " +
                "id: " + id +
                ", dataHora: '" + dataHora + '\'' +
                ", local: '" + local + '\'' +
                ", timeCasa: " + (timeCasa != null ? timeCasa.getNome() : "N/A") +
                ", timeVisita: " + (timeVisita != null ? timeVisita.getNome() : "N/A") +
                ", gols1: " + gols1 +
                ", gols2: " + gols2 +
                ", ingressos: " + ingressos ;
    }
}