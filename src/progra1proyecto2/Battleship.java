
package progra1proyecto2;

// Steve Rivera, Carlos Nuñez, José Rene Girard

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Battleship extends JFrame {

    //Declarar variables
    private JPanel panel;
    private JComboBox selDificultad;
    private JButton botonIniciarSesion, botonCrearUsuario, botonSalirMI, botonSalirMP,
            botonAceptarIniciarSesion, botonAceptarCrearUsuario, botonJugar, botonConfig,
            botonReportes, botonPerfil, botonSalirC, botonRanking,botonHistorial;
    private JButton boton1a1, boton1a2, boton1a3, boton1a4, boton1a5, boton1a6, boton1a7, boton1a8,
            boton2a1, boton2a2, boton2a3, boton2a4, boton2a5, boton2a6, boton2a7, boton2a8,
            boton3a1, boton3a2, boton3a3, boton3a4, boton3a5, boton3a6, boton3a7, boton3a8,
            boton4a1, boton4a2, boton4a3, boton4a4, boton4a5, boton4a6, boton4a7, boton4a8,
            boton5a1, boton5a2, boton5a3, boton5a4, boton5a5, boton5a6, boton5a7, boton5a8,
            boton6a1, boton6a2, boton6a3, boton6a4, boton6a5, boton6a6, boton6a7, boton6a8,
            boton7a1, boton7a2, boton7a3, boton7a4, boton7a5, boton7a6, boton7a7, boton7a8,
            boton8a1, boton8a2, boton8a3, boton8a4, boton8a5, boton8a6, boton8a7, boton8a8;
    private JLabel cuadradoMI, cuadradoMI2, etiquetaFondo1, tituloUsuario, tituloContra, dificultad,
            gamemode, errorEspacios1, errorEspacios2;
    private JTextField ingresarUsuario, ingresarContra;
    ArrayList<Player> jugadores = new ArrayList<>();
    ArrayList<Player> historial = new ArrayList<>(); 
    String nombreUsuario, contraseña;
    String[] dificultades = {"Easy","Normal","Expert","Genius"};
    int controlJugadorCreado = 0, puntos, usuarioCreado = 0;
    Font fuente;

    //Creacion de Ventana
    public Battleship() throws FontFormatException {
        setBounds(0, 0, 925, 950);
        setTitle("Battleship");
        setLocationRelativeTo(null);
        setResizable(false);
        ImageIcon icono = new ImageIcon("battleships-game.jpg");
        setIconImage(icono.getImage());
        try {
            fuente = Font.createFont(Font.TRUETYPE_FONT, new File("Manti.otf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fuente);
        } catch (IOException | FontFormatException e) {
        }

        iniciarComponentes();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void iniciarComponentes() {
        colocarPanel();
        menuInicio();
    }

    private void colocarPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);
    }

    private void menuInicio() {
        panel.setVisible(true);

        botonIniciarSesion = new JButton("Iniciar Sesión");
        botonIniciarSesion.setBounds(260, 240, 400, 80);
        botonIniciarSesion.setFont(fuente.deriveFont(30f));
        panel.add(botonIniciarSesion);

        if (usuarioCreado < 2) {
            botonIniciarSesion.setEnabled(false);
        }

        botonCrearUsuario = new JButton("Crear Usuario");
        botonCrearUsuario.setBounds(260, 390, 400, 80);
        botonCrearUsuario.setFont(fuente.deriveFont(30f));
        panel.add(botonCrearUsuario);

        botonSalirMI = new JButton("Salir");
        botonSalirMI.setBounds(260, 540, 400, 80);
        botonSalirMI.setFont(fuente.deriveFont(30f));
        panel.add(botonSalirMI);

        cuadradoMI = new JLabel();
        cuadradoMI.setBounds(200, 190, 520, 480);
        cuadradoMI.setOpaque(true);
        cuadradoMI.setBackground(new Color(0, 0, 0, 170));
        panel.add(cuadradoMI);

        ImageIcon fondo1 = new ImageIcon("fondo1.jpeg");
        etiquetaFondo1 = new JLabel();
        etiquetaFondo1.setBounds(0, 0, 925, 950);
        etiquetaFondo1.setIcon(new ImageIcon(fondo1.getImage().getScaledInstance(etiquetaFondo1.getWidth(), etiquetaFondo1.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaFondo1);

        oyentesDeAccionMI();
    }

    private void menuPrincipal() {
        panel.setVisible(true);

        botonJugar = new JButton("JUGAR");
        botonJugar.setBounds(230, 350, 450, 80);
        botonJugar.setFont(fuente.deriveFont(30f));
        panel.add(botonJugar);

        botonConfig = new JButton("Configuración");
        botonConfig.setBounds(230, 450, 450, 80);
        botonConfig.setFont(fuente.deriveFont(30f));
        panel.add(botonConfig);

        botonReportes = new JButton("Reportes");
        botonReportes.setBounds(230, 550, 450, 80);
        botonReportes.setFont(fuente.deriveFont(30f));
        panel.add(botonReportes);

        botonPerfil = new JButton("Mi Perfil");
        botonPerfil.setBounds(230, 650, 450, 80);
        botonPerfil.setFont(fuente.deriveFont(30f));
        panel.add(botonPerfil);

        botonSalirMP = new JButton("Salir");
        botonSalirMP.setBounds(20, 840, 120, 60);
        botonSalirMP.setFont(new Font("copperplate gothic bold", 0, 25));
        panel.add(botonSalirMP);

        ImageIcon titulo = new ImageIcon("titulo.png");
        JLabel etiquetaTitulo = new JLabel();
        etiquetaTitulo.setBounds(160, -20, 600, 300);
        etiquetaTitulo.setIcon(new ImageIcon(titulo.getImage().getScaledInstance(etiquetaTitulo.getWidth(), etiquetaTitulo.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaTitulo);

        ImageIcon fondo2 = new ImageIcon("fondo2.jpg");
        JLabel etiquetaFondo2 = new JLabel();
        etiquetaFondo2.setBounds(0, 0, 925, 950);
        etiquetaFondo2.setIcon(new ImageIcon(fondo2.getImage().getScaledInstance(etiquetaFondo2.getWidth(), etiquetaFondo2.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaFondo2);

        oyentesDeAccionMP();

    }
    
    private void menuConfig() {
        panel.setVisible(true);

        dificultad = new JLabel("Dificultad");
        dificultad.setBounds(110, 400, 450, 80);
        dificultad.setForeground(Color.white);
        dificultad.setFont(fuente.deriveFont(30f));
        panel.add(dificultad);
        
        selDificultad = new JComboBox(dificultades);
        selDificultad.setBounds(550, 415, 250, 65);
        selDificultad.setFont(fuente.deriveFont(30f));
        panel.add(selDificultad);

        gamemode = new JLabel("Modo de Juego");
        gamemode.setBounds(110, 500, 550, 80);
        gamemode.setForeground(Color.white);
        gamemode.setFont(fuente.deriveFont(30f));
        panel.add(gamemode);

        botonSalirC = new JButton("Volver");
        botonSalirC.setBounds(230, 650, 450, 80);
        botonSalirC.setFont(fuente.deriveFont(30f));
        panel.add(botonSalirC);
        
        cuadradoMI = new JLabel();
        cuadradoMI.setBounds(80, 380, 765, 220);
        cuadradoMI.setOpaque(true);
        cuadradoMI.setBackground(new Color(0, 0, 0, 170));
        panel.add(cuadradoMI);

        ImageIcon titulo = new ImageIcon("titulo.png");
        JLabel etiquetaTitulo = new JLabel();
        etiquetaTitulo.setBounds(160, -20, 600, 300);
        etiquetaTitulo.setIcon(new ImageIcon(titulo.getImage().getScaledInstance(etiquetaTitulo.getWidth(), etiquetaTitulo.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaTitulo);

        ImageIcon fondo2 = new ImageIcon("fondo2.jpg");
        JLabel etiquetaFondo2 = new JLabel();
        etiquetaFondo2.setBounds(0, 0, 925, 950);
        etiquetaFondo2.setIcon(new ImageIcon(fondo2.getImage().getScaledInstance(etiquetaFondo2.getWidth(), etiquetaFondo2.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaFondo2);

        oyentesDeAccionMC();

    }

    //menu de inicio de sesion
    private void inicioDeSesion() {
        panel.setVisible(true);

        tituloUsuario = new JLabel("Nombre de Usuario: ", SwingConstants.LEFT);
        tituloUsuario.setBounds(200, 230, 500, 100);
        tituloUsuario.setForeground(Color.WHITE);
        tituloUsuario.setFont(fuente.deriveFont(30f));
        panel.add(tituloUsuario);

        ingresarUsuario = new JTextField();
        ingresarUsuario.setBounds(220, 320, 480, 50);
        ingresarUsuario.setFont(fuente.deriveFont(30f));
        panel.add(ingresarUsuario);

        tituloContra = new JLabel("Contraseña: ", SwingConstants.LEFT);
        tituloContra.setBounds(215, 400, 320, 100);
        tituloContra.setForeground(Color.WHITE);
        tituloContra.setFont(fuente.deriveFont(30f));
        panel.add(tituloContra);

        ingresarContra = new JTextField();
        ingresarContra.setBounds(220, 490, 480, 50);
        ingresarContra.setFont(fuente.deriveFont(30f));
        panel.add(ingresarContra);

        botonAceptarIniciarSesion = new JButton("Iniciar");
        botonAceptarIniciarSesion.setFont(fuente.deriveFont(30f));
        botonAceptarIniciarSesion.setBounds(310, 590, 300, 50);
        panel.add(botonAceptarIniciarSesion);

        cuadradoMI = new JLabel();
        cuadradoMI.setBounds(200, 190, 520, 480);
        cuadradoMI.setOpaque(true);
        cuadradoMI.setBackground(new Color(0, 0, 0, 170));
        panel.add(cuadradoMI);

        ImageIcon fondo1 = new ImageIcon("fondo1.jpeg");
        etiquetaFondo1 = new JLabel();
        etiquetaFondo1.setBounds(0, 0, 925, 950);
        etiquetaFondo1.setIcon(new ImageIcon(fondo1.getImage().getScaledInstance(etiquetaFondo1.getWidth(), etiquetaFondo1.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaFondo1);

        oyentesDeAccionIniciarSesion();
    }

    //menu de creacion de usuario
    private void creacionDeUsuario() {
        panel.setVisible(true);

        tituloUsuario = new JLabel("Nombre de Usuario: ", SwingConstants.LEFT);
        tituloUsuario.setBounds(200, 230, 500, 110);
        tituloUsuario.setForeground(Color.WHITE);
        tituloUsuario.setFont(fuente.deriveFont(30f));
        panel.add(tituloUsuario);

        ingresarUsuario = new JTextField();
        ingresarUsuario.setBounds(220, 320, 480, 50);
        ingresarUsuario.setFont(fuente.deriveFont(30f));
        panel.add(ingresarUsuario);

        tituloContra = new JLabel("Contraseña: ", SwingConstants.LEFT);
        tituloContra.setBounds(215, 400, 320, 105);
        tituloContra.setForeground(Color.WHITE);
        tituloContra.setFont(fuente.deriveFont(30f));
        panel.add(tituloContra);

        ingresarContra = new JTextField();
        ingresarContra.setBounds(220, 490, 480, 50);
        ingresarContra.setFont(fuente.deriveFont(30f));
        panel.add(ingresarContra);

        botonAceptarCrearUsuario = new JButton("Crear");
        botonAceptarCrearUsuario.setFont(fuente.deriveFont(30f));
        botonAceptarCrearUsuario.setBounds(310, 590, 300, 50);
        panel.add(botonAceptarCrearUsuario);
        
        errorEspacios1 = new JLabel("El nombre y la contraseña", SwingConstants.CENTER);
        errorEspacios2 = new JLabel("no pueden contener espacios.", SwingConstants.CENTER);  
        errorEspacios1.setBounds(160, 670, 600, 100);
        errorEspacios2.setBounds(160, 700, 600, 100);
        errorEspacios1.setFont(new Font("copperplate gothic bold",0,30));
        errorEspacios2.setFont(new Font("copperplate gothic bold",0,30));
        errorEspacios1.setForeground(Color.RED);
        errorEspacios2.setForeground(Color.RED);
        panel.add(errorEspacios1);
        panel.add(errorEspacios2);

        cuadradoMI = new JLabel();
        cuadradoMI.setBounds(200, 190, 520, 480);
        cuadradoMI.setOpaque(true);
        cuadradoMI.setBackground(new Color(0, 0, 0, 170));
        panel.add(cuadradoMI);
        
        cuadradoMI2 = new JLabel();
        cuadradoMI2.setBounds(200, 690, 520, 90);
        cuadradoMI2.setOpaque(true);
        cuadradoMI2.setBackground(new Color(0, 0, 0, 170));
        panel.add(cuadradoMI2);

        ImageIcon fondo1 = new ImageIcon("fondo1.jpeg");
        etiquetaFondo1 = new JLabel();
        etiquetaFondo1.setBounds(0, 0, 925, 950);
        etiquetaFondo1.setIcon(new ImageIcon(fondo1.getImage().getScaledInstance(etiquetaFondo1.getWidth(), etiquetaFondo1.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaFondo1);

        oyentesDeAccionCrearUsuario();
    }
    
    private void Reportes(){
        panel.setVisible(true);
        botonRanking = new JButton("Ranking");
        botonRanking.setBounds(230, 350, 450, 80);
        botonRanking.setFont(fuente.deriveFont(30f));
        panel.add(botonRanking);

        botonHistorial = new JButton("Historial");
        botonHistorial.setBounds(230, 450, 450, 80);
        botonHistorial.setFont(fuente.deriveFont(30f));
        panel.add(botonHistorial);
        
        botonSalirMP = new JButton("Volver");
        botonSalirMP.setBounds(230, 550, 450, 80);
        botonSalirMP.setFont(fuente.deriveFont(30f));
        panel.add(botonSalirMP);

        ImageIcon titulo = new ImageIcon("titulo.png");
        JLabel etiquetaTitulo = new JLabel();
        etiquetaTitulo.setBounds(160, -20, 600, 300);
        etiquetaTitulo.setIcon(new ImageIcon(titulo.getImage().getScaledInstance(etiquetaTitulo.getWidth(), etiquetaTitulo.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaTitulo);

        ImageIcon fondo2 = new ImageIcon("fondo2.jpg");
        JLabel etiquetaFondo2 = new JLabel();
        etiquetaFondo2.setBounds(0, 0, 925, 950);
        etiquetaFondo2.setIcon(new ImageIcon(fondo2.getImage().getScaledInstance(etiquetaFondo2.getWidth(), etiquetaFondo2.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaFondo2);

        oyentesDeAccionMC();
}
    
    private void menuRanking(){
         panel.setVisible(true);
         panel.setBounds(0, 0, 725, 650);
         panel.setBackground(Color.green);
         
          oyentesDeAccionMR();
}
    private void menuHistorial(){
         panel.setVisible(true);
    
    }
    
    private void imprimirTablero() {
        panel.setVisible(true);

        boton1a1 = new JButton();
        boton1a1.setBounds(20, 20, 110, 110);
        boton1a1.setBackground(Color.BLUE);
        panel.add(boton1a1);

        boton1a2 = new JButton();
        boton1a2.setBounds(130, 20, 110, 110);
        boton1a2.setBackground(Color.BLUE);
        panel.add(boton1a2);

        boton1a3 = new JButton();
        boton1a3.setBounds(240, 20, 110, 110);
        boton1a3.setBackground(Color.BLUE);
        panel.add(boton1a3);

        boton1a4 = new JButton();
        boton1a4.setBounds(350, 20, 110, 110);
        boton1a4.setBackground(Color.BLUE);
        panel.add(boton1a4);

        boton1a5 = new JButton();
        boton1a5.setBounds(460, 20, 110, 110);
        boton1a5.setBackground(Color.BLUE);
        panel.add(boton1a5);

        boton1a6 = new JButton();
        boton1a6.setBounds(570, 20, 110, 110);
        boton1a6.setBackground(Color.BLUE);
        panel.add(boton1a6);

        boton1a7 = new JButton();
        boton1a7.setBounds(680, 20, 110, 110);
        boton1a7.setBackground(Color.BLUE);
        panel.add(boton1a7);

        boton1a8 = new JButton();
        boton1a8.setBounds(790, 20, 110, 110);
        boton1a8.setBackground(Color.BLUE);
        panel.add(boton1a8);

        boton2a1 = new JButton();
        boton2a1.setBounds(20, 130, 110, 110);
        boton2a1.setBackground(Color.BLUE);
        panel.add(boton2a1);

        boton2a2 = new JButton();
        boton2a2.setBounds(130, 130, 110, 110);
        boton2a2.setBackground(Color.BLUE);
        panel.add(boton2a2);

        boton2a3 = new JButton();
        boton2a3.setBounds(240, 130, 110, 110);
        boton2a3.setBackground(Color.BLUE);
        panel.add(boton2a3);

        boton2a4 = new JButton();
        boton2a4.setBounds(350, 130, 110, 110);
        boton2a4.setBackground(Color.BLUE);
        panel.add(boton2a4);

        boton2a5 = new JButton();
        boton2a5.setBounds(460, 130, 110, 110);
        boton2a5.setBackground(Color.BLUE);
        panel.add(boton2a5);

        boton2a6 = new JButton();
        boton2a6.setBounds(570, 130, 110, 110);
        boton2a6.setBackground(Color.BLUE);
        panel.add(boton2a6);

        boton2a7 = new JButton();
        boton2a7.setBounds(680, 130, 110, 110);
        boton2a7.setBackground(Color.BLUE);
        panel.add(boton2a7);

        boton2a8 = new JButton();
        boton2a8.setBounds(790, 130, 110, 110);
        boton2a8.setBackground(Color.BLUE);
        panel.add(boton2a8);

        boton3a1 = new JButton();
        boton3a1.setBounds(20, 240, 110, 110);
        boton3a1.setBackground(Color.BLUE);
        panel.add(boton3a1);

        boton3a2 = new JButton();
        boton3a2.setBounds(130, 240, 110, 110);
        boton3a2.setBackground(Color.BLUE);
        panel.add(boton3a2);

        boton3a3 = new JButton();
        boton3a3.setBounds(240, 240, 110, 110);
        boton3a3.setBackground(Color.BLUE);
        panel.add(boton3a3);

        boton3a4 = new JButton();
        boton3a4.setBounds(350, 240, 110, 110);
        boton3a4.setBackground(Color.BLUE);
        panel.add(boton3a4);

        boton3a5 = new JButton();
        boton3a5.setBounds(460, 240, 110, 110);
        boton3a5.setBackground(Color.BLUE);
        panel.add(boton3a5);

        boton3a6 = new JButton();
        boton3a6.setBounds(570, 240, 110, 110);
        boton3a6.setBackground(Color.BLUE);
        panel.add(boton3a6);

        boton3a7 = new JButton();
        boton3a7.setBounds(680, 240, 110, 110);
        boton3a7.setBackground(Color.BLUE);
        panel.add(boton3a7);

        boton3a8 = new JButton();
        boton3a8.setBounds(790, 240, 110, 110);
        boton3a8.setBackground(Color.BLUE);
        panel.add(boton3a8);

        boton4a1 = new JButton();
        boton4a1.setBounds(20, 350, 110, 110);
        boton4a1.setBackground(Color.BLUE);
        panel.add(boton4a1);

        boton4a2 = new JButton();
        boton4a2.setBounds(130, 350, 110, 110);
        boton4a2.setBackground(Color.BLUE);
        panel.add(boton4a2);

        boton4a3 = new JButton();
        boton4a3.setBounds(240, 350, 110, 110);
        boton4a3.setBackground(Color.BLUE);
        panel.add(boton4a3);

        boton4a4 = new JButton();
        boton4a4.setBounds(350, 350, 110, 110);
        boton4a4.setBackground(Color.BLUE);
        panel.add(boton4a4);

        boton4a5 = new JButton();
        boton4a5.setBounds(460, 350, 110, 110);
        boton4a5.setBackground(Color.BLUE);
        panel.add(boton4a5);

        boton4a6 = new JButton();
        boton4a6.setBounds(570, 350, 110, 110);
        boton4a6.setBackground(Color.BLUE);
        panel.add(boton4a6);

        boton4a7 = new JButton();
        boton4a7.setBounds(680, 350, 110, 110);
        boton4a7.setBackground(Color.BLUE);
        panel.add(boton4a7);

        boton4a8 = new JButton();
        boton4a8.setBounds(790, 350, 110, 110);
        boton4a8.setBackground(Color.BLUE);
        panel.add(boton4a8);

        boton5a1 = new JButton();
        boton5a1.setBounds(20, 460, 110, 110);
        boton5a1.setBackground(Color.BLUE);
        panel.add(boton5a1);

        boton5a2 = new JButton();
        boton5a2.setBounds(130, 460, 110, 110);
        boton5a2.setBackground(Color.BLUE);
        panel.add(boton5a2);

        boton5a3 = new JButton();
        boton5a3.setBounds(240, 460, 110, 110);
        boton5a3.setBackground(Color.BLUE);
        panel.add(boton5a3);

        boton5a4 = new JButton();
        boton5a4.setBounds(350, 460, 110, 110);
        boton5a4.setBackground(Color.BLUE);
        panel.add(boton5a4);

        boton5a5 = new JButton();
        boton5a5.setBounds(460, 460, 110, 110);
        boton5a5.setBackground(Color.BLUE);
        panel.add(boton5a5);

        boton5a6 = new JButton();
        boton5a6.setBounds(570, 460, 110, 110);
        boton5a6.setBackground(Color.BLUE);
        panel.add(boton5a6);

        boton5a7 = new JButton();
        boton5a7.setBounds(680, 460, 110, 110);
        boton5a7.setBackground(Color.BLUE);
        panel.add(boton5a7);

        boton5a8 = new JButton();
        boton5a8.setBounds(790, 460, 110, 110);
        boton5a8.setBackground(Color.BLUE);
        panel.add(boton5a8);

        boton6a1 = new JButton();
        boton6a1.setBounds(20, 570, 110, 110);
        boton6a1.setBackground(Color.BLUE);
        panel.add(boton6a1);

        boton6a2 = new JButton();
        boton6a2.setBounds(130, 570, 110, 110);
        boton6a2.setBackground(Color.BLUE);
        panel.add(boton6a2);

        boton6a3 = new JButton();
        boton6a3.setBounds(240, 570, 110, 110);
        boton6a3.setBackground(Color.BLUE);
        panel.add(boton6a3);

        boton6a4 = new JButton();
        boton6a4.setBounds(350, 570, 110, 110);
        boton6a4.setBackground(Color.BLUE);
        panel.add(boton6a4);

        boton6a5 = new JButton();
        boton6a5.setBounds(460, 570, 110, 110);
        boton6a5.setBackground(Color.BLUE);
        panel.add(boton6a5);

        boton6a6 = new JButton();
        boton6a6.setBounds(570, 570, 110, 110);
        boton6a6.setBackground(Color.BLUE);
        panel.add(boton6a6);

        boton6a7 = new JButton();
        boton6a7.setBounds(680, 570, 110, 110);
        boton6a7.setBackground(Color.BLUE);
        panel.add(boton6a7);

        boton6a8 = new JButton();
        boton6a8.setBounds(790, 570, 110, 110);
        boton6a8.setBackground(Color.BLUE);
        panel.add(boton6a8);

        boton7a1 = new JButton();
        boton7a1.setBounds(20, 680, 110, 110);
        boton7a1.setBackground(Color.BLUE);
        panel.add(boton7a1);

        boton7a2 = new JButton();
        boton7a2.setBounds(130, 680, 110, 110);
        boton7a2.setBackground(Color.BLUE);
        panel.add(boton7a2);

        boton7a3 = new JButton();
        boton7a3.setBounds(240, 680, 110, 110);
        boton7a3.setBackground(Color.BLUE);
        panel.add(boton7a3);

        boton7a4 = new JButton();
        boton7a4.setBounds(350, 680, 110, 110);
        boton7a4.setBackground(Color.BLUE);
        panel.add(boton7a4);

        boton7a5 = new JButton();
        boton7a5.setBounds(460, 680, 110, 110);
        boton7a5.setBackground(Color.BLUE);
        panel.add(boton7a5);

        boton7a6 = new JButton();
        boton7a6.setBounds(570, 680, 110, 110);
        boton7a6.setBackground(Color.BLUE);
        panel.add(boton7a6);

        boton7a7 = new JButton();
        boton7a7.setBounds(680, 680, 110, 110);
        boton7a7.setBackground(Color.BLUE);
        panel.add(boton7a7);

        boton7a8 = new JButton();
        boton7a8.setBounds(790, 680, 110, 110);
        boton7a8.setBackground(Color.BLUE);
        panel.add(boton7a8);

        boton8a1 = new JButton();
        boton8a1.setBounds(20, 790, 110, 110);
        boton8a1.setBackground(Color.BLUE);
        panel.add(boton8a1);

        boton8a2 = new JButton();
        boton8a2.setBounds(130, 790, 110, 110);
        boton8a2.setBackground(Color.BLUE);
        panel.add(boton8a2);

        boton8a3 = new JButton();
        boton8a3.setBounds(240, 790, 110, 110);
        boton8a3.setBackground(Color.BLUE);
        panel.add(boton8a3);

        boton8a4 = new JButton();
        boton8a4.setBounds(350, 790, 110, 110);
        boton8a4.setBackground(Color.BLUE);
        panel.add(boton8a4);

        boton8a5 = new JButton();
        boton8a5.setBounds(460, 790, 110, 110);
        boton8a5.setBackground(Color.BLUE);
        panel.add(boton8a5);

        boton8a6 = new JButton();
        boton8a6.setBounds(570, 790, 110, 110);
        boton8a6.setBackground(Color.BLUE);
        panel.add(boton8a6);

        boton8a7 = new JButton();
        boton8a7.setBounds(680, 790, 110, 110);
        boton8a7.setBackground(Color.BLUE);
        panel.add(boton8a7);

        boton8a8 = new JButton();
        boton8a8.setBounds(790, 790, 110, 110);
        boton8a8.setBackground(Color.BLUE);
        panel.add(boton8a8);
    }

    private void oyentesDeAccionMI() {
        //Al tocar boton Iniciar Sesion
        ActionListener tocarInicioSesion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                inicioDeSesion();
            }
        };

        //Al tocar boton de Crear Usuario
        ActionListener tocarCrearUsuario = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                creacionDeUsuario();
            }
        };

        //Al tocar boton de salir del menu de inicio
        ActionListener tocarSalirMI = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        };

        botonSalirMI.addActionListener(tocarSalirMI);
        botonCrearUsuario.addActionListener(tocarCrearUsuario);
        botonIniciarSesion.addActionListener(tocarInicioSesion);
    }

    private void oyentesDeAccionMP() {

        //Al tocar boton de salir del menu de principal
        ActionListener tocarSalirMP = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                menuInicio();
            }
        };

        ActionListener tocarJugar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                imprimirTablero();
            }
        };
        
        ActionListener tocarConfig = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                menuConfig();
            }
        };
                ActionListener tocarReferencias = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                Reportes();
            }
        };
        
        botonReportes.addActionListener(tocarReferencias);        
        botonSalirMP.addActionListener(tocarSalirMP);
        botonConfig.addActionListener(tocarConfig);
        botonJugar.addActionListener(tocarJugar);
    }
    
    private void oyentesDeAccionMC() {

        //Al tocar boton de salir del menu de principal
        ActionListener tocarSalirC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                menuPrincipal();
            }
        };

        ActionListener tocarJugar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        };

        botonSalirC.addActionListener(tocarSalirC);
        botonJugar.addActionListener(tocarJugar);
    }

    private void oyentesDeAccionMR(){
        
            ActionListener tocarRanking = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                menuRanking();
            }
        };
            
         botonRanking.addActionListener(tocarRanking);   
    }
    
    private void oyentesDeAccionIniciarSesion() {
        ActionListener tocarBotonAceptarIS = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Al tocar boton Aceptar en el menu de iniciar sesion
   
                if (!(ingresarUsuario.getText().equals("")) && !(ingresarContra.getText().equals(""))) {
                    for (int control = 1; control <= controlJugadorCreado; control++) {
                        if (jugadores.get(control - 1).nombreUsuario.equals(ingresarUsuario.getText())
                                && jugadores.get(control - 1).contraseña.equals(ingresarContra.getText())) {
                            panel.setVisible(false);
                            panel.removeAll();
                            menuPrincipal();
                            break;
                        } else {
                            if (control == controlJugadorCreado) {
                                panel.setVisible(false);
                                panel.removeAll();
                                menuInicio();
                            }
                        }
                    }
                } else {
                    panel.setVisible(false);
                    panel.removeAll();
                    menuInicio();
                }
            }
        };

        ingresarContra.addActionListener(tocarBotonAceptarIS);
        botonAceptarIniciarSesion.addActionListener(tocarBotonAceptarIS);
    }

    private void oyentesDeAccionCrearUsuario() {
        //Al tocar boton Crear en el menu de crear usuario
        ActionListener tocarBotonCrear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean usuarioUsado = false;
                if(!(ingresarUsuario.getText().equals("")) && !(ingresarContra.getText().equals(""))){
                    if(ingresarUsuario.getText().contains(" ") || ingresarContra.getText().contains(" ")) {
                        
                    }else {
                        for (int usuario = 0; usuario < jugadores.size(); usuario++) {
                            if (jugadores.get(usuario).nombreUsuario != null) {
                                if (jugadores.get(usuario).nombreUsuario.equals(ingresarUsuario.getText())) {
                                    usuarioUsado = true;
                                }
                            }
                        }
                        if (!usuarioUsado) {
                            jugadores.add(new Player(ingresarUsuario.getText(), ingresarContra.getText()));
                            //System.out.println(jugadores[controlJugador].nombreUsuario+"\n"+jugadores[controlJugador].contraseña);
                            //System.out.println(jugadores.get(0).nombreUsuario);
                            usuarioCreado++;
                            controlJugadorCreado++;
                        }
                        usuarioUsado = false;
                        
                    }
                }

                panel.setVisible(false);
                panel.removeAll();
                menuInicio();
            }
        };

        ingresarContra.addActionListener(tocarBotonCrear);
        botonAceptarCrearUsuario.addActionListener(tocarBotonCrear);
    }

}
