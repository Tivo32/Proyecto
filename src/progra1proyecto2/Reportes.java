package progra1proyecto2;

import java.util.ArrayList;

/**
 *
 * @author Jose Girard, Carlos Uziel, Steve Rivera
 */
public class Reportes {

    private int Id1;
    private int Id2;
    private int Res;
    private ArrayList<Player> jugador = new ArrayList<>();

    public Reportes(int id1, int id2, int res) {

        int Id1 = id1;
        int Id2 = id2;
        int Res = res;

    }

    public int getId1(int code) {

        return code;

    }

    public int getId2(int code2) {

        return code2;
    }

    public String getRes(int ans) {

        switch (ans) {

            case 0:
                return "win";

            case 1:

                return "lose";

            case 2:

                return "Tie";

            default:
                return null;
        }

    }

    public void Ranking(ArrayList<Player> jugador) {

        Player Tp = new Player("", "");

        for (int control = 0; control < jugador.size(); control++) {

            int numero = 0;

            ArrayList<Player> Score = new ArrayList<>();
            ArrayList<Player> Rank = new ArrayList<>();

            Score.add(jugador.get(control));

            for (int control2 = 1; control2 < jugador.size(); control2++) {

                if (Score.get(control).getPuntos() > jugador.get(control2).getPuntos()) {

                    Rank.add(jugador.get(control));
                    
                    
                }

            }
        }

    }

    public static void main(String[] args) {

        ArrayList<Player> Score = new ArrayList<>();
        Reportes Rk = new Reportes(0, 0, 0);
        Player Tp = new Player("", "");

        String Array[] = new String[10];
        int punto[] = new int[10];

        for (int control = 0; control < Array.length; control++) {
            Rk.Ranking(Score);
            
            System.out.println(Score);
        }

    }
}
