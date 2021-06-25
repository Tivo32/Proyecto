package progra1proyecto2;

// Steve Rivera, Carlos Nuñez, José Rene Girard

import java.util.ArrayList;

public class Player {

    String nombreUsuario, contraseña;
    private int puntos;
    ArrayList <String> historial = new ArrayList();

    public Player(String nombreUsuarioT, String contraseñaT) {
        nombreUsuario = nombreUsuarioT;
        contraseña = contraseñaT;
        puntos = 0;
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

    public void setPuntos(int Score) {
        this.puntos += Score;
    }

    public int getPuntos() {
        return puntos;
    }
    
    public ArrayList getHistorial() {
        return historial;
    }
    
    public void setOracionHistorial(int ans, String nombre2){
        String oracion;
         switch (ans) {

            case 0:
                oracion= nombreUsuario + " a ganado contra " + nombre2;
                break;
            case 1:
                oracion= nombreUsuario + " a perdido contra " + nombre2;
                break;
            case 2:
                oracion= nombreUsuario + " se ha retirado contra " + nombre2;
                break;
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
                System.out.println(control1+". "+historial.get(control1-1));
                control1++;
            }
        }
    }
    
}
    
