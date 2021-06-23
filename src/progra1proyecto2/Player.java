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

    public String getContraseña() {
        return contraseña;
    }

    public void SetPuntos(int Score) {
        this.puntos = Score;
    }

    public int getPuntos() {
        return puntos;
    }
    
    public void setOracionHistorial(String oracion){
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
    
