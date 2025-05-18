package domain.modelos;

public class Jogo {
    private int id;
    private String dataHora;
    private String local;
    private Time timeCasa;
    private Time timeVisita;
    private int gols1;
    private int gols2;
    private int ingressos;

    // Construtor padrão exigido pelo db4o
    public Jogo() {
    }

    // Construtor completo
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

    //public int getId() { return id; }

    //public void setId(int id) { this.id = id; }

    //public String getDataHora() { return dataHora; }

    //public void setDataHora(String dataHora) { this.dataHora = dataHora; }

    //public String getLocal() { return local; }

    //public void setLocal(String local) { this.local = local; }

    public Time getTimeCasa() { return timeCasa; }

    public void setTimeCasa(Time timeCasa) { this.timeCasa = timeCasa; }

    public Time getTimeVisita() { return timeVisita; }

    public void setTimeVisita(Time timeVisita) { this.timeVisita = timeVisita; }

    //public int getGols1() { return gols1; }

    //public void setGols1(int gols1) { this.gols1 = gols1; }

    //public int getGols2() { return gols2; }

    //public void setGols2(int gols2) { this.gols2 = gols2; }

    //public int getIngressos() { return ingressos; }

    //public void setIngressos(int ingressos) { this.ingressos = ingressos; }

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