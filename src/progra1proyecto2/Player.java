package progra1proyecto2;

// Steve Rivera, Carlos Nuñez, José Rene Girard

import java.util.ArrayList;

public class Player {

    String nombreUsuario, contraseña;
    private int puntos;
    ArrayList <String> historial;

    public Player(String nombreUsuarioT, String contraseñaT) {
        nombreUsuario = nombreUsuarioT;
        contraseña = contraseñaT;
        puntos = 0;
        historial = new ArrayList();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombre){
        nombreUsuario = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }
    
    public void setContraseña(String contra){
        contraseña = contra;
    }

    public void SetPuntos(int Score) {
        this.puntos = Score;
    }

    public int getPuntos() {
        return puntos;
    }
    
    public void setOracionHistorial(byte ans){
        String oracion;
         switch (ans) {

            case 0:
                oracion= "a Ganado contra";

            case 1:

                oracion ="a Perdido contra";

            case 2:

                oracion ="se ha Retirado contra";

            default:
               oracion = null;
        }

    
        if(historial.size() < 10)
            historial.add(oracion);
        else{
            historial.remove(0);
            historial.add(oracion);
        } 
    }
    
    public void imprimirHistorial() {
        for(int control1=1, control2=9; control2 > -1;control2--) {
            if(control2 < historial.size()) { 
                System.out.println(control1+". "+historial.get(control2));
                control1++;
            }
        }
    }
    
}
    
