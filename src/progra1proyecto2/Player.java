
package progra1proyecto2;

// Steve Rivera, Carlos Nuñez, José Rene
public class Player {
    String nombreUsuario, contraseña;
   private int puntos;
   
    
    public Player(String nombreUsuarioT, String contraseñaT){
        nombreUsuario = nombreUsuarioT;
        contraseña = contraseñaT;
        puntos=0;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public String getContraseña() {
        return contraseña;
    }
    
    public void SetPuntos(int Score){
    this.puntos=Score;}
    
    public int GetPuntos(int Score){
    return Score;
    }
}
