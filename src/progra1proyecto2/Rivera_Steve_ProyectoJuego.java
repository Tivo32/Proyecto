
package progra1proyecto2;

// Steve Rivera, Carlos Nuñez, José Rene

import java.awt.FontFormatException;

public class Rivera_Steve_ProyectoJuego {
    public static void main(String[] args) {
        try {
        Battleship ventanaJuego = new Battleship();
        
        ventanaJuego.setVisible(true);
        } catch (FontFormatException e) {
        }
        
        
    }
}
