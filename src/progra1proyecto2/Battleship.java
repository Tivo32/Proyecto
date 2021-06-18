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
    private JButton botonIniciarSesion, botonCrearUsuario, botonSalirMI, botonSalirMP, botonJugar2,
            botonAceptarIniciarSesion, botonAceptarCrearUsuario, botonJugar, botonConfig,
            botonReportes, botonPerfil, botonSalirC, botonRanking, botonHistorial, botonVolver;
    private JButton boton1a1, boton1a2, boton1a3, boton1a4, boton1a5, boton1a6, boton1a7, boton1a8,
            boton2a1, boton2a2, boton2a3, boton2a4, boton2a5, boton2a6, boton2a7, boton2a8,
            boton3a1, boton3a2, boton3a3, boton3a4, boton3a5, boton3a6, boton3a7, boton3a8,
            boton4a1, boton4a2, boton4a3, boton4a4, boton4a5, boton4a6, boton4a7, boton4a8,
            boton5a1, boton5a2, boton5a3, boton5a4, boton5a5, boton5a6, boton5a7, boton5a8,
            boton6a1, boton6a2, boton6a3, boton6a4, boton6a5, boton6a6, boton6a7, boton6a8,
            boton7a1, boton7a2, boton7a3, boton7a4, boton7a5, boton7a6, boton7a7, boton7a8,
            boton8a1, boton8a2, boton8a3, boton8a4, boton8a5, boton8a6, boton8a7, boton8a8;
    private JLabel cuadradoMI, cuadradoMI2, etiquetaFondo1, tituloUsuario, tituloContra, dificultad,
            gamemode, errorEspacios1, errorEspacios2, etiquetaTitulo, etiquetaFondo2, errMessage;
    private JTextField ingresarUsuario, ingresarContra;
    ArrayList<Player> jugadores = new ArrayList<>();
    ArrayList<Player> historial = new ArrayList<>();
    String nombreUsuario, contraseña;
    String[] dificultades = {"EASY", "NORMAL", "EXPERT", "GENIUS"};
    int controlJugadorCreado = 0, puntos, usuarioCreado = 0, idIniciado;
    boolean error = false;
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

        botonIniciarSesion = new JButton("INICIAR SESIÓN");
        botonIniciarSesion.setBounds(260, 240, 400, 80);
        botonIniciarSesion.setFont(fuente.deriveFont(30f));
        panel.add(botonIniciarSesion);

        if (usuarioCreado < 2) {
            botonIniciarSesion.setEnabled(false);
        }

        botonCrearUsuario = new JButton("CREAR USUARIO");
        botonCrearUsuario.setBounds(260, 390, 400, 80);
        botonCrearUsuario.setFont(fuente.deriveFont(30f));
        panel.add(botonCrearUsuario);

        botonSalirMI = new JButton("SALIR");
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

        botonConfig = new JButton("CONDIGURACIÓN");
        botonConfig.setBounds(230, 450, 450, 80);
        botonConfig.setFont(fuente.deriveFont(30f));
        panel.add(botonConfig);

        botonReportes = new JButton("REPORTES");
        botonReportes.setBounds(230, 550, 450, 80);
        botonReportes.setFont(fuente.deriveFont(30f));
        panel.add(botonReportes);

        botonPerfil = new JButton("MI PERFIL");
        botonPerfil.setBounds(230, 650, 450, 80);
        botonPerfil.setFont(fuente.deriveFont(30f));
        panel.add(botonPerfil);

        botonSalirMP = new JButton("SALIR");
        botonSalirMP.setBounds(20, 840, 120, 60);
        botonSalirMP.setFont(fuente.deriveFont(20f));
        panel.add(botonSalirMP);

        ImageIcon titulo = new ImageIcon("titulo.png");
        etiquetaTitulo = new JLabel();
        etiquetaTitulo.setBounds(160, -20, 600, 300);
        etiquetaTitulo.setIcon(new ImageIcon(titulo.getImage().getScaledInstance(etiquetaTitulo.getWidth(), etiquetaTitulo.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaTitulo);

        ImageIcon fondo2 = new ImageIcon("fondo2.jpg");
        etiquetaFondo2 = new JLabel();
        etiquetaFondo2.setBounds(0, 0, 925, 950);
        etiquetaFondo2.setIcon(new ImageIcon(fondo2.getImage().getScaledInstance(etiquetaFondo2.getWidth(), etiquetaFondo2.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaFondo2);

        oyentesDeAccionMP();

    }

    private void menuConfig() {
        panel.setVisible(true);

        dificultad = new JLabel("DIFICULTAD");
        dificultad.setBounds(110, 400, 450, 80);
        dificultad.setForeground(Color.white);
        dificultad.setFont(fuente.deriveFont(30f));
        panel.add(dificultad);

        selDificultad = new JComboBox(dificultades);
        selDificultad.setBounds(550, 415, 250, 65);
        selDificultad.setFont(fuente.deriveFont(30f));
        panel.add(selDificultad);

        gamemode = new JLabel("MODO DE JUEGO");
        gamemode.setBounds(110, 500, 550, 80);
        gamemode.setForeground(Color.white);
        gamemode.setFont(fuente.deriveFont(30f));
        panel.add(gamemode);

        botonSalirC = new JButton("VOLVER");
        botonSalirC.setBounds(230, 650, 450, 80);
        botonSalirC.setFont(fuente.deriveFont(30f));
        panel.add(botonSalirC);

        cuadradoMI = new JLabel();
        cuadradoMI.setBounds(80, 380, 765, 220);
        cuadradoMI.setOpaque(true);
        cuadradoMI.setBackground(new Color(0, 0, 0, 170));
        panel.add(cuadradoMI);

        ImageIcon titulo = new ImageIcon("titulo.png");
        etiquetaTitulo = new JLabel();
        etiquetaTitulo.setBounds(160, -20, 600, 300);
        etiquetaTitulo.setIcon(new ImageIcon(titulo.getImage().getScaledInstance(etiquetaTitulo.getWidth(), etiquetaTitulo.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaTitulo);

        ImageIcon fondo2 = new ImageIcon("fondo2.jpg");
        etiquetaFondo2 = new JLabel();
        etiquetaFondo2.setBounds(0, 0, 925, 950);
        etiquetaFondo2.setIcon(new ImageIcon(fondo2.getImage().getScaledInstance(etiquetaFondo2.getWidth(), etiquetaFondo2.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaFondo2);

        oyentesDeAccionMC();

    }

    //menu de inicio de sesion
    private void inicioDeSesion() {
        panel.setVisible(true);

        tituloUsuario = new JLabel("NOMBRE DE USUARIO: ", SwingConstants.LEFT);
        tituloUsuario.setBounds(228, 230, 500, 100);
        tituloUsuario.setForeground(Color.WHITE);
        tituloUsuario.setFont(fuente.deriveFont(30f));
        panel.add(tituloUsuario);

        ingresarUsuario = new JTextField();
        ingresarUsuario.setBounds(220, 320, 480, 50);
        ingresarUsuario.setFont(fuente.deriveFont(30f));
        panel.add(ingresarUsuario);

        tituloContra = new JLabel("CONTRASEÑA: ", SwingConstants.LEFT);
        tituloContra.setBounds(226, 400, 320, 100);
        tituloContra.setForeground(Color.WHITE);
        tituloContra.setFont(fuente.deriveFont(30f));
        panel.add(tituloContra);

        ingresarContra = new JTextField();
        ingresarContra.setBounds(220, 490, 480, 50);
        ingresarContra.setFont(fuente.deriveFont(30f));
        panel.add(ingresarContra);

        botonAceptarIniciarSesion = new JButton("INICIAR");
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

        tituloUsuario = new JLabel("NOMBRE DE USUARIO: ", SwingConstants.LEFT);
        tituloUsuario.setBounds(228, 230, 500, 110);
        tituloUsuario.setForeground(Color.WHITE);
        tituloUsuario.setFont(fuente.deriveFont(30f));
        panel.add(tituloUsuario);

        ingresarUsuario = new JTextField();
        ingresarUsuario.setBounds(220, 320, 480, 50);
        ingresarUsuario.setFont(fuente.deriveFont(30f));
        panel.add(ingresarUsuario);

        tituloContra = new JLabel("CONTRASEÑA: ", SwingConstants.LEFT);
        tituloContra.setBounds(226, 400, 320, 105);
        tituloContra.setForeground(Color.WHITE);
        tituloContra.setFont(fuente.deriveFont(30f));
        panel.add(tituloContra);

        ingresarContra = new JTextField();
        ingresarContra.setBounds(220, 490, 480, 50);
        ingresarContra.setFont(fuente.deriveFont(30f));
        panel.add(ingresarContra);

        botonAceptarCrearUsuario = new JButton("CREAR");
        botonAceptarCrearUsuario.setFont(fuente.deriveFont(30f));
        botonAceptarCrearUsuario.setBounds(310, 590, 300, 50);
        panel.add(botonAceptarCrearUsuario);

        errorEspacios1 = new JLabel("El nombre y la contraseña", SwingConstants.CENTER);
        errorEspacios2 = new JLabel("no pueden contener espacios", SwingConstants.CENTER);
        errorEspacios1.setBounds(160, 670, 600, 100);
        errorEspacios2.setBounds(160, 700, 600, 100);
        errorEspacios1.setFont(fuente.deriveFont(25f));
        errorEspacios2.setFont(fuente.deriveFont(25f));
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

    private void Reportes() {
        panel.setVisible(true);
        botonRanking = new JButton("RANKING");
        botonRanking.setBounds(230, 350, 450, 80);
        botonRanking.setFont(fuente.deriveFont(30f));
        panel.add(botonRanking);

        botonHistorial = new JButton("HISTORIAL");
        botonHistorial.setBounds(230, 450, 450, 80);
        botonHistorial.setFont(fuente.deriveFont(30f));
        panel.add(botonHistorial);

        botonSalirMP = new JButton("VOLVER");
        botonSalirMP.setBounds(230, 550, 450, 80);
        botonSalirMP.setFont(fuente.deriveFont(30f));
        panel.add(botonSalirMP);

        ImageIcon titulo = new ImageIcon("titulo.png");
        etiquetaTitulo = new JLabel();
        etiquetaTitulo.setBounds(160, -20, 600, 300);
        etiquetaTitulo.setIcon(new ImageIcon(titulo.getImage().getScaledInstance(etiquetaTitulo.getWidth(), etiquetaTitulo.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaTitulo);

        ImageIcon fondo2 = new ImageIcon("fondo2.jpg");
        etiquetaFondo2 = new JLabel();
        etiquetaFondo2.setBounds(0, 0, 925, 950);
        etiquetaFondo2.setIcon(new ImageIcon(fondo2.getImage().getScaledInstance(etiquetaFondo2.getWidth(), etiquetaFondo2.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaFondo2);

        oyentesDeAccionMR();
    }

    private void menuRanking() {
        panel.setVisible(true);
        panel.setBounds(0, 0, 725, 650);
        panel.setBackground(Color.green);

        oyentesDeAccionMR();
    }

    private void menuHistorial() {
        panel.setVisible(true);

    }
    
    private void retador() {
        panel.setVisible(true);
        
        errMessage = new JLabel();
        errMessage.setHorizontalAlignment(SwingConstants.CENTER);
        errMessage.setForeground(Color.RED);
        errMessage.setText("Jugador no encontrado");
        errMessage.setBounds(230, 500, 450, 80);
        errMessage.setFont(fuente.deriveFont(30f));
        errMessage.setOpaque(true);
        errMessage.setBackground(new Color(0, 0, 0, 170));
        
        ingresarUsuario.setLocation(220, 420);
        ingresarUsuario.setText("Ingrese el Jugador 2");
        tituloUsuario.setLocation(228,330);
        
        botonVolver = new JButton();
        botonVolver.setText("VOLVER");
        botonVolver.setBounds(230, 700, 450, 80);
        botonVolver.setFont(fuente.deriveFont(30f));
        
        botonJugar2 = new JButton("JUGAR");
        botonJugar2.setBounds(230, 600, 450, 80);
        botonJugar2.setFont(fuente.deriveFont(30f));
        
        panel.add(botonJugar2);
        panel.add(botonVolver);
        panel.add(tituloUsuario);
        panel.add(ingresarUsuario);
        if (error) {
            panel.add(errMessage);
        }
        panel.add(etiquetaTitulo);
        panel.add(etiquetaFondo2);
        
        oyentesRetador();
        
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
                retador();
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
    
    private void oyentesRetador() {
        
        ActionListener tocarVolver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                menuPrincipal();
            }
        };
        
        ActionListener tocarJugar2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean match = false;
                for (int id = 0; id < jugadores.size(); id++) {
                    if (jugadores.get(id) != null) {
                        if (jugadores.get(id).nombreUsuario.equals(ingresarUsuario.getText())) {
                            if (id != idIniciado) {
                                match = true;
                            }
                        }
                    }
                }
                if (match) {
                    panel.setVisible(false);
                    panel.removeAll();
                    imprimirTablero();
                }
                else {
                    error = true;
                    panel.setVisible(false);
                    panel.removeAll();
                    retador();
                }
                error = false;
                match = false;
            }
        };
        
        botonJugar2.addActionListener(tocarJugar2);
        botonVolver.addActionListener(tocarVolver);
        
    }

    private void oyentesDeAccionMR() {

        ActionListener tocarSalirC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                menuPrincipal();
            }
        };

        ActionListener tocarRanking = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                menuRanking();
            }
        };

        botonSalirMP.addActionListener(tocarSalirC);
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
                            idIniciado = control - 1;
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
                if (!(ingresarUsuario.getText().equals("")) && !(ingresarContra.getText().equals(""))) {
                    if (ingresarUsuario.getText().contains(" ") || ingresarContra.getText().contains(" ")) {

                    } else {
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
