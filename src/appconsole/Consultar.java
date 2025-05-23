package appconsole;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import modelo.*;

public class Consultar {
    public static void main(String[] args) {
        ObjectContainer db = Util.conectarBanco();

        System.out.println("\n[JOGOS NA DATAHORA '2024-06-01 15:00']");
        Query q1 = db.query();
        q1.constrain(Jogo.class);
        q1.descend("dataHora").constrain("2024-06-01 15:00");
        q1.execute().forEach(System.out::println);

        System.out.println("\n[JOGOS ONDE TIME 'Real Madrid' JOGA EM CASA]");
        Query q2 = db.query();
        q2.constrain(Jogo.class);
        q2.descend("timeCasa").descend("nome").constrain("Real Madrid");
        q2.execute().forEach(System.out::println);

        System.out.println("\n[TIMES COM MAIS DE 10 PONTOS]");
        Query q3 = db.query();
        q3.constrain(Time.class);
        q3.descend("pontuacao").constrain(10).greater();
        q3.execute().forEach(System.out::println);

        Util.desconectar();
    }
}
