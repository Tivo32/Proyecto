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
    private ArrayList<Player> jugadores = new ArrayList<>();

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
    public void Ranking ( ArrayList<Player> ){
        
        for (int control = 0; control < ; control++) {
            
        }
        }
            }

  /*  public static void main(String[] args) {

        int id1[] = new int[3];
        int id2[] = new int[3];
        int respuesta[] = new int[3];
        

        for (int control = 0; control < 3; control++) {
            id1[control] = control;
            id2[control] = control;
            respuesta[control] = control;
            
            Reportes RE = new Reportes(id1[control], id2[control], respuesta[control]);
            System.out.println(id1[control]+ " " + (RE.getRes(respuesta[control])+ "  "+ id2[control]));
            
            
        }
    }*/
}
