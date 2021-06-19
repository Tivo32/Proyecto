package progra1proyecto2;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Tablero {

    private JButton[][] tablero = new JButton[8][8];
    private byte dificultad;
    private byte barco;

    public Tablero(JPanel panel, byte dificultad) {
        
        panel.setVisible(true);
        this.dificultad = dificultad;
        int x = 20, y = 20;
        for (JButton[] fila : tablero) {
            for (int columna = 0; columna < fila.length; columna++) {
                fila[columna] = new JButton();
                fila[columna].setBounds(x, y, 110, 110);
                fila[columna].setBackground(Color.BLUE);
                panel.add(fila[columna]);
                x += 110;
            }
            x = 20;
            y += 110;
        }
        y = 20;
        
    }

}

