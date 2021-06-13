
package progra1proyecto2;

public class Tablero_BarcosYCasillas {
    
    
  private boolean barco1,barco2,barco3,barco4,barco5; 
  public int cantidadBarcos;
  
  
    public Tablero_BarcosYCasillas(boolean barco,int tipodeBarco){
        
        if(barco==true && tipodeBarco==1)
        {
        barco1=true;
        int vida1 = 5;}
        else{barco1=false;}
        
        if(barco==true && tipodeBarco==2){
        barco2=true;
        int vida2 = 4;} 
        else {barco2=false;}
        
        if(barco==true && tipodeBarco==3){
        barco3=true;
        int vida3 = 3;
        }
        else{barco3=false;}
        
        if(barco==true && tipodeBarco==4){
        barco3=true;
        int vida4 = 2;
        } 
        else{barco4=false;}
        
    }
        
        //clase para definir la dificultad
        public int dificultad(int dificultad){
        
        switch (dificultad){
        
            case 1:
                cantidadBarcos =1;
        
                break;
                
             case 2:
                cantidadBarcos =2;
        
                break;
                
            case 3:
                cantidadBarcos =3;
        
                break;
            case 4:
                cantidadBarcos =4;
        
                break;  
            case 5:
                cantidadBarcos =5;
        
                break;
        }
        return cantidadBarcos;

        }
    
/* lo que hice aqui fue que al dar clic en la dificultad se tiene que colocar esta clase
        la cual evaluara la dificultad y este brindara un numero de barcos a cambio 
        los cuales son los que definen la dificultad del juego
        */ 
                
                
                
 /*
   int[][] Posicion ={{int columna =1},{int fila = 2}};
   */
    
// Tipos de Barcos y sus hitpoints        
  /*      
    boolean PA = true;//PA = Porta Aviones
    int vidaPA = 5;
    
    
    boolean AZ = true; //AZ = Acorazados
    int VidaAZ = 4;
    boolean SM = true;// SM = SubMarino
    int vidaSM = 3;
    boolean DT = true;//DT= Destructor
    int vidaDT = 2;
    */
    }

