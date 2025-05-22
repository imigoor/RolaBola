package modelo;

public class Jogo {
    private int id;
    private String dataHora;
    private String local;
    private Time timeCasa;
    private Time timeVisita;
    private int gols1;
    private int gols2;

    public Jogo(int id, String dataHora, String local, Time timeCasa, Time timeVisita, int gols1, int gols2) {
        this.id = id;
        this.dataHora = dataHora;
        this.local = local;
        this.timeCasa = timeCasa;
        this.timeVisita = timeVisita;
        this.gols1 = gols1;
        this.gols2 = gols2;
    }

    public Time getTimeCasa() { return timeCasa; }

    public void setTimeCasa(Time timeCasa) { this.timeCasa = timeCasa; }

    public Time getTimeVisita() { return timeVisita; }

    public void setTimeVisita(Time timeVisita) { this.timeVisita = timeVisita; }

    //public int getGols1() { return gols1; }

    //public void setGols1(int gols1) { this.gols1 = gols1; }

    //public int getGols2() { return gols2; }

    //public void setGols2(int gols2) { this.gols2 = gols2; }

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
                //"+ , ingressos: " + ingressos ;
    }
}