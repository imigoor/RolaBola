package requisito;

import daojpa.DAO;
import daojpa.JogoDAO;
import daojpa.TimeDAO;
import modelo.Jogo;
import modelo.Time;
import java.util.List;

public class Fachada {
    private Fachada() {}

    private static JogoDAO jogoDAO = new JogoDAO();
    private static TimeDAO timeDAO = new TimeDAO();

    public static void inicializar() {
        DAO.open();
    }
    public static void finalizar() {
        DAO.close();
    }

    // --- CADASTRO ---
    public static void cadastrarTime(String nome, byte[] foto) throws Exception {
        DAO.begin();
        if (timeDAO.read(nome) != null) {
            DAO.rollback();
            throw new Exception("Time já existe:" + nome);
        }
        Time time = new Time(nome, foto);
        timeDAO.create(time);
        DAO.commit();
    }

    public static void cadastrarJogo(String dataHora, String local, String nomeTimeCasa, String nomeTimeVisita, int gols1, int gols2) throws Exception {
        DAO.begin();
        Time timeCasa = timeDAO.read(nomeTimeCasa);
        Time timeVisita = timeDAO.read(nomeTimeVisita);

        if (timeCasa == null || timeVisita == null) {
            DAO.rollback();
            throw new Exception("Um dos times não existe.");
        } else if (timeCasa.equals(timeVisita))
        {
            DAO.rollback();
            throw new Exception("Um time não pode jogar contra ele mesmo.");
        }

        Jogo jogo = new Jogo(dataHora, local, timeCasa, timeVisita, gols1, gols2);
        timeCasa.adicionarJogo(jogo);
        timeVisita.adicionarJogo(jogo);
        jogoDAO.create(jogo);
        DAO.commit();
    }

    // --- LISTAGEM ---
    public static List<Time> listarTimes() {
        return timeDAO.readAll();
    }

    public static List<Jogo> listarJogos() {
        return jogoDAO.readAll();
    }

    // --- ALTERAÇÃO ---
    public static void alterarFotoTime(String nome, byte[] foto) throws Exception {
        DAO.begin();
        Time time = timeDAO.read(nome);
        if (time == null) {
            DAO.rollback();
            throw new Exception("alterar foto - time inexistente:" + nome);
        }
        time.setFoto(foto);
        DAO.commit();
    }

    public static void alterarJogoTrocarTimes(int jogoId) throws Exception {
        DAO.begin();
        Jogo jogo = jogoDAO.read(jogoId);
        if (jogo == null) {
            DAO.rollback();
            throw new Exception("Jogo não encontrado: " + jogoId);
        }

        jogo.removerPontuacaoDosTimes();
        Time timeCasaAtual = jogo.getTimeCasa();
        Time timeVisitaAtual = jogo.getTimeVisita();
        timeCasaAtual.removerJogo(jogo);
        timeVisitaAtual.removerJogo(jogo);

        jogo.setTimeCasa(timeVisitaAtual);
        jogo.setTimeVisita(timeCasaAtual);
        int golsCasa = jogo.getGols1();
        int golsVisita = jogo.getGols2();
        jogo.setGols1(golsVisita);
        jogo.setGols2(golsCasa);

        jogo.atualizarPontuacaoDosTimes();

        jogoDAO.update(jogo);

        DAO.commit();
    }

    // --- EXCLUSÃO ---
    public static void excluirTime(String nome) throws Exception {
        DAO.begin();
        Time time = timeDAO.read(nome);
        if (time == null) {
            DAO.rollback();
            throw new Exception("Time não encontrado para exclusão: " + nome);
        }
        if (!time.getTodosOsJogos().isEmpty()) {
            DAO.rollback();
            throw new Exception("Não é possível excluir o time, pois ele tem jogos associados.");
        }
        timeDAO.delete(time);
        DAO.commit();
    }

    public static void excluirJogo(int id) throws Exception {
        DAO.begin();
        Jogo jogo = jogoDAO.read(id);
        if (jogo == null) {
            DAO.rollback();
            throw new Exception("Jogo não encontrado para exclusão: " + id);
        }

        jogo.removerPontuacaoDosTimes();

        Time timeCasa = jogo.getTimeCasa();
        Time timeVisita = jogo.getTimeVisita();

        timeCasa.removerJogo(jogo);
        timeVisita.removerJogo(jogo);

        jogoDAO.delete(jogo);

        DAO.commit();
    }

    // --- CONSULTAS JPQL ---
    public static List<Jogo> jogosPorData(String data) {
        return jogoDAO.jogosPorData(data);
    }

    public static List<Jogo> jogosTimeCasa(String nomeTime) {
        return jogoDAO.jogosTimeCasa(nomeTime);
    }

    public static List<Time> timesComMaisDeNJogos(int n) {
        return jogoDAO.timesComMaisDeNJogos(n);
    }

    // --- AUXILIARES ---
    public static Time localizarTime(String nome) {
        return timeDAO.read(nome);
    }

    public static Jogo localizarJogo(int id) {
        return jogoDAO.read(id);
    }
}