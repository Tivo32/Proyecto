/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progra1proyecto2;

/**
 *
 * @author Dell
 */
public class Casillas {

    private int barco;
    private int vida;

    public Casillas(int barco, boolean reposicion) {

        this.barco = barco;
        if (!reposicion) {
            switch (barco) {
                case 1:
                case 5:
                    vida = 2;
                    break;
                case 2:
                    vida = 3;
                    break;
                case 3:
                    vida = 4;
                    break;
                case 4:
                    vida = 5;
                    break;
            }
        }

    }

    public void bomba() {

        if (barco != 0) {
            if (vida > 0) {
                vida--;
                System.out.println("Le diste");
            }
            if (vida == 0) {
                System.out.println("Barco destruido");
                barco = 0;
            }
        } else {
            System.out.println("fallaste");
        }
        

    }

    public int getVida() {

        return vida;

    }

    public int getBarco() {

        return barco;

    }

    public void reiniciar() {

        barco = 0;
        vida = 0;

    }

}
