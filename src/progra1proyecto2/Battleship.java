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
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Battleship extends JFrame implements ActionListener {

    //Declarar variables
    private Timer _timer;
    private JLayeredPane panel;
    private JComboBox selDificultad, selModo;
    private JButton[][] tablero;
    private Casillas[][] matriz1 = new Casillas[8][8], matriz2 = new Casillas[8][8];
    private JButton botonIniciarSesion, botonCrearUsuario, botonSalirMI, botonSalirMP, botonJugar2,
            botonAceptarIniciarSesion, botonAceptarCrearUsuario, botonJugar, botonConfig,
            botonReportes, botonPerfil, botonSalirC, botonRanking, botonHistorial, botonVolver, botonVerDatos, 
            botonModificarDatos, botonEliminarCuenta, botonModificarNombre, botonModificarContra, botonSi, botonNo;
    private JLabel cuadradoMI, cuadradoMI2, cuadradoMI3, etiquetaFondo1, tituloUsuario, tituloContra, dificultad,
            gamemode, errorEspacios1, errorEspacios2, etiquetaTitulo, etiquetaFondo2, errMessage, label, tituloPerfil, 
            etiquetaNombre, etiquetaContra, etiquetaPuntos, etiquetaConfirmar, tituloRanking;
    private JTextField ingresarUsuario, ingresarContra;
    ArrayList<Player> jugadores = new ArrayList<>();
    ArrayList<Player> historial = new ArrayList<>();
    Player ranking[];
    String nombreUsuario, contraseña;
    String[] dificultades = {"EASY", "NORMAL", "EXPERT", "GENIUS"};
    String[] modos = {"TUTORIAL", "ARCADE"};
    int puntos, idIniciado, id2,
            opac = 230, modo = 255, hundidos = 0, hundidos2 = 0;
    byte dif = 0, jugador = 1, barcos = 1, barcos2 = 1, maxBarcos = 0, info;
    boolean error = false, inicio = false, random = false;
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
        //menuRanking();
    }

    private void colocarPanel() {
        panel = new JLayeredPane();
        panel.setLayout(null);
        this.add(panel);
    }

    private void menuInicio() {
        panel.setVisible(true);

        botonIniciarSesion = new JButton("INICIAR SESIÓN");
        botonIniciarSesion.setBounds(260, 240, 400, 80);
        botonIniciarSesion.setFont(fuente.deriveFont(30f));
        panel.add(botonIniciarSesion);

        if (jugadores.size() < 2) {
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

        barcos = 1;
        for (int casilla = 0; casilla < 8; casilla++) {
            for (int borrador = 0; borrador < 8; borrador++) {
                if (matriz1[casilla][borrador] != null) {
                    matriz1[casilla][borrador].reiniciar();
                }

                if (matriz2[casilla][borrador] != null) {
                    matriz2[casilla][borrador].reiniciar();
                }
            }
        }

        botonJugar = new JButton("JUGAR");
        botonJugar.setBounds(230, 350, 450, 80);
        botonJugar.setFont(fuente.deriveFont(30f));
        panel.add(botonJugar);

        botonConfig = new JButton("CONFIGURACIÓN");
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

        selModo = new JComboBox(modos);
        selModo.setBounds(550, 515, 250, 65);
        selModo.setFont(fuente.deriveFont(30f));
        panel.add(selModo);

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

        tituloRanking = new JLabel("RANKING: ", SwingConstants.CENTER);
        tituloRanking.setBounds(100, 50, 710, 100);
        tituloRanking.setForeground(Color.WHITE);
        tituloRanking.setFont(fuente.deriveFont(50f));
        panel.add(tituloRanking);
        
        botonVolver = new JButton("VOLVER");
        botonVolver.setFont(fuente.deriveFont(30f));
        botonVolver.setBounds(230, 720, 450, 80);
        panel.add(botonVolver);
        
        for(int control=0, x=130, y=170; control<jugadores.size(); control++){
            actualizarRanking();
            JLabel printRanking[] = new JLabel[jugadores.size()];
            
            printRanking[control] = new JLabel((control+1)+"- "+ranking[control].getNombreUsuario()+" / "+ranking[control].getPuntos());
            printRanking[control].setBounds(x, y, 355, 100);
            printRanking[control].setForeground(Color.WHITE);
            printRanking[control].setFont(fuente.deriveFont(35f));
            panel.add(printRanking[control]);
            y+=100;
            
            if(control==4) {
                x+=350;
                y=170;
            }
            
            if(control==9)
                break;
            
        }
        
        cuadradoMI = new JLabel();
        cuadradoMI.setBounds(100, 160, 710, 680);
        cuadradoMI.setOpaque(true);
        cuadradoMI.setBackground(new Color(0, 0, 0, 170));
        panel.add(cuadradoMI);
        
        cuadradoMI2 = new JLabel();
        cuadradoMI2.setBounds(100, 50, 710, 100);
        cuadradoMI2.setOpaque(true);
        cuadradoMI2.setBackground(new Color(0, 0, 0, 170));
        panel.add(cuadradoMI2);

        ImageIcon fondo2 = new ImageIcon("fondo2.jpg");
        etiquetaFondo2 = new JLabel();
        etiquetaFondo2.setBounds(0, 0, 925, 950);
        etiquetaFondo2.setIcon(new ImageIcon(fondo2.getImage().getScaledInstance(etiquetaFondo2.getWidth(), etiquetaFondo2.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaFondo2);

        oyentesDeAccionMRanking();
    }

    private void menuHistorial() {
        panel.setVisible(true);

    
        botonVolver = new JButton("VOLVER");
        botonVolver.setBounds(0, 0, 350, 80);
        botonVolver.setFont(fuente.deriveFont(30f));
        panel.add(botonVolver);
        
        for (Player historial1 : historial) {
            if(historial.size()<1){
                
                botonVolver = new JButton("VOLVER");
                botonVolver.setBounds(0, 0, 350, 80);
                botonVolver.setFont(fuente.deriveFont(30f));
                panel.add(botonVolver);
            }
            else{
                botonVolver = new JButton("VOLVER");
                botonVolver.setBounds(0, 0, 350, 80);
                botonVolver.setFont(fuente.deriveFont(30f));
                panel.add(botonVolver);        
                
                cuadradoMI = new JLabel(jugadores.get(idIniciado).historial.get(0));
                cuadradoMI.setBounds(150, 260, 420, 380);
                cuadradoMI.setOpaque(true);
                cuadradoMI.setBackground(new Color(0, 0, 0, 170));
                panel.add(cuadradoMI);

            }
        }

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

        oyentesDeAccionMHistorial();
    }

    private void menuPerfil() {
        panel.setVisible(true);

        tituloPerfil = new JLabel("PERFIL", SwingConstants.CENTER);
        tituloPerfil.setBounds(200, 150, 520, 100);
        tituloPerfil.setForeground(Color.WHITE);
        tituloPerfil.setFont(fuente.deriveFont(50f));
        panel.add(tituloPerfil);

        botonVerDatos = new JButton("VER MIS DATOS");
        botonVerDatos.setFont(fuente.deriveFont(30f));
        botonVerDatos.setBounds(230, 295, 450, 80);
        panel.add(botonVerDatos);

        botonModificarDatos = new JButton("MODIFICAR MIS DATOS");
        botonModificarDatos.setFont(fuente.deriveFont(30f));
        botonModificarDatos.setBounds(230, 405, 450, 80);
        panel.add(botonModificarDatos);

        botonEliminarCuenta = new JButton("ELIMINAR MI CUENTA");
        botonEliminarCuenta.setFont(fuente.deriveFont(30f));
        botonEliminarCuenta.setBounds(230, 515, 450, 80);
        panel.add(botonEliminarCuenta);

        botonVolver = new JButton("VOLVER");
        botonVolver.setFont(fuente.deriveFont(30f));
        botonVolver.setBounds(230, 625, 450, 80);
        panel.add(botonVolver);

        cuadradoMI = new JLabel();
        cuadradoMI.setBounds(200, 260, 520, 480);
        cuadradoMI.setOpaque(true);
        cuadradoMI.setBackground(new Color(0, 0, 0, 170));
        panel.add(cuadradoMI);

        cuadradoMI2 = new JLabel();
        cuadradoMI2.setBounds(200, 150, 520, 100);
        cuadradoMI2.setOpaque(true);
        cuadradoMI2.setBackground(new Color(0, 0, 0, 170));
        panel.add(cuadradoMI2);

        cuadradoMI3 = new JLabel();
        cuadradoMI3.setBounds(200, 770, 520, 140);
        cuadradoMI3.setOpaque(true);
        cuadradoMI3.setBackground(new Color(0, 0, 0, 170));
        panel.add(cuadradoMI3);
        cuadradoMI3.setVisible(false);

        ImageIcon fondo1 = new ImageIcon("fondo2.jpg");
        etiquetaFondo1 = new JLabel();
        etiquetaFondo1.setBounds(0, 0, 925, 950);
        etiquetaFondo1.setIcon(new ImageIcon(fondo1.getImage().getScaledInstance(etiquetaFondo1.getWidth(), etiquetaFondo1.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaFondo1);

        oyentesDeAccionMenuPerfil();
    }

    private void menuMisDatos() {
        panel.setVisible(true);

        tituloPerfil = new JLabel("MIS DATOS", SwingConstants.CENTER);
        tituloPerfil.setBounds(200, 150, 520, 100);
        tituloPerfil.setForeground(Color.WHITE);
        tituloPerfil.setFont(fuente.deriveFont(50f));
        panel.add(tituloPerfil);

        etiquetaNombre = new JLabel("NOMBRE: " + jugadores.get(idIniciado).getNombreUsuario(), SwingConstants.CENTER);
        etiquetaNombre.setBounds(230, 295, 450, 80);
        etiquetaNombre.setForeground(Color.WHITE);
        etiquetaNombre.setFont(fuente.deriveFont(35f));
        panel.add(etiquetaNombre);

        etiquetaContra = new JLabel("CONTRASEÑA: " + jugadores.get(idIniciado).getContraseña(), SwingConstants.CENTER);
        etiquetaContra.setBounds(230, 405, 450, 80);
        etiquetaContra.setForeground(Color.WHITE);
        etiquetaContra.setFont(fuente.deriveFont(35f));
        panel.add(etiquetaContra);

        etiquetaPuntos = new JLabel("PUNTOS: " + jugadores.get(idIniciado).getPuntos(), SwingConstants.CENTER);
        etiquetaPuntos.setBounds(230, 515, 450, 80);
        etiquetaPuntos.setForeground(Color.WHITE);
        etiquetaPuntos.setFont(fuente.deriveFont(35f));
        panel.add(etiquetaPuntos);

        botonVolver = new JButton("VOLVER");
        botonVolver.setFont(fuente.deriveFont(30f));
        botonVolver.setBounds(230, 625, 450, 80);
        panel.add(botonVolver);

        cuadradoMI = new JLabel();
        cuadradoMI.setBounds(200, 260, 520, 480);
        cuadradoMI.setOpaque(true);
        cuadradoMI.setBackground(new Color(0, 0, 0, 170));
        panel.add(cuadradoMI);

        cuadradoMI2 = new JLabel();
        cuadradoMI2.setBounds(200, 150, 520, 100);
        cuadradoMI2.setOpaque(true);
        cuadradoMI2.setBackground(new Color(0, 0, 0, 170));
        panel.add(cuadradoMI2);

        ImageIcon fondo1 = new ImageIcon("fondo2.jpg");
        etiquetaFondo1 = new JLabel();
        etiquetaFondo1.setBounds(0, 0, 925, 950);
        etiquetaFondo1.setIcon(new ImageIcon(fondo1.getImage().getScaledInstance(etiquetaFondo1.getWidth(), etiquetaFondo1.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaFondo1);

        oyentesDeAccionMenuMisDatos();
    }

    private void menuModificarDatos() {
        panel.setVisible(true);

        tituloPerfil = new JLabel("MIS DATOS", SwingConstants.CENTER);
        tituloPerfil.setBounds(200, 150, 520, 100);
        tituloPerfil.setForeground(Color.WHITE);
        tituloPerfil.setFont(fuente.deriveFont(50f));
        panel.add(tituloPerfil);

        ingresarUsuario = new JTextField();
        ingresarUsuario.setText("INGRESAR NUEVO NOMBRE");
        ingresarUsuario.setBounds(220, 300, 480, 50);
        ingresarUsuario.setFont(fuente.deriveFont(30f));
        panel.add(ingresarUsuario);

        botonModificarNombre = new JButton("MODIFICAR NOMBRE");
        botonModificarNombre.setFont(fuente.deriveFont(30f));
        botonModificarNombre.setBounds(260, 375, 400, 60);
        panel.add(botonModificarNombre);

        ingresarContra = new JTextField();
        ingresarContra.setText("INGRESAR NUEVA CONTRA");
        ingresarContra.setBounds(220, 460, 480, 50);
        ingresarContra.setFont(fuente.deriveFont(30f));
        panel.add(ingresarContra);

        botonModificarContra = new JButton("MODIFICAR CONTRA");
        botonModificarContra.setFont(fuente.deriveFont(30f));
        botonModificarContra.setBounds(260, 535, 400, 60);
        panel.add(botonModificarContra);

        botonVolver = new JButton("VOLVER");
        botonVolver.setFont(fuente.deriveFont(30f));
        botonVolver.setBounds(230, 625, 450, 80);
        panel.add(botonVolver);

        cuadradoMI = new JLabel();
        cuadradoMI.setBounds(200, 260, 520, 480);
        cuadradoMI.setOpaque(true);
        cuadradoMI.setBackground(new Color(0, 0, 0, 170));
        panel.add(cuadradoMI);

        cuadradoMI2 = new JLabel();
        cuadradoMI2.setBounds(200, 150, 520, 100);
        cuadradoMI2.setOpaque(true);
        cuadradoMI2.setBackground(new Color(0, 0, 0, 170));
        panel.add(cuadradoMI2);

        ImageIcon fondo1 = new ImageIcon("fondo2.jpg");
        etiquetaFondo1 = new JLabel();
        etiquetaFondo1.setBounds(0, 0, 925, 950);
        etiquetaFondo1.setIcon(new ImageIcon(fondo1.getImage().getScaledInstance(etiquetaFondo1.getWidth(), etiquetaFondo1.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaFondo1);

        oyentesDeAccionModificarDatos();
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
        tituloUsuario.setLocation(228, 330);

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

    public void jugador1() {
        ActionListener tempo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.remove(label);
                label.setForeground(new Color(255, 255, 255, 255));
                label.setBackground(new Color(0, 0, 0, 255));
                panel.add(label, JLayeredPane.DRAG_LAYER);
                panel.setVisible(true);
                opac -= 2;
                for (JButton[] fila : tablero) {
                    for (int columna = 0; columna < fila.length; columna++) {
                        fila[columna].setEnabled(true);
                    }
                }

                _timer.stop();
                panel.removeAll();
                panel.setVisible(false);
                Tablero();

            }
        };

        _timer = new Timer(750, tempo);
        _timer.start();

    }

    private void Juego() {
        Tablero();
        label = new JLabel();
        if (!inicio) {
            panel.setVisible(false);
            label.setBounds(100, 300, 700, 200);
            label.setOpaque(true);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(fuente.deriveFont(30f));
            label.setForeground(new Color(255, 255, 255, 255));
            label.setBackground(new Color(0, 0, 0, 255));
            label.setText("Jugador " + jugador + ": Ordene los barcos");
            panel.add(label, JLayeredPane.DRAG_LAYER);
            panel.setVisible(true);
            for (JButton[] fila : tablero) {
                for (int columna = 0; columna < fila.length; columna++) {
                    fila[columna].setEnabled(false);
                }
            }
            jugador1();
        } else {

            panel.setVisible(false);
            label.setOpaque(true);
            label.setBounds(100, 300, 700, 200);
            label.setFont(fuente.deriveFont(30f));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setForeground(new Color(255, 255, 255, 255));
            label.setBackground(new Color(0, 0, 0, 255));
            label.setText("Jugador " + jugador + ": Ataca");
            panel.add(label, JLayeredPane.DRAG_LAYER);
            panel.setVisible(true);
            for (JButton[] fila : tablero) {
                for (int columna = 0; columna < fila.length; columna++) {
                    fila[columna].setEnabled(false);
                }
            }
            jugador1();

        }

        switch (dif) {
            case 0:
                maxBarcos = 5;
                break;
            case 1:
                maxBarcos = 4;
                break;
            case 2:
                maxBarcos = 2;
                break;
            case 3:
                maxBarcos = 1;
                break;
        }

    }

    public void Tablero() {

        tablero = new JButton[8][8];
        panel.setVisible(true);

        ImageIcon fondoJuego = new ImageIcon("FondosJuego/Fondo.jpg");
        etiquetaFondo2 = new JLabel();
        etiquetaFondo2.setBounds(0, 0, 925, 950);
        etiquetaFondo2.setIcon(new ImageIcon(fondoJuego.getImage().getScaledInstance(etiquetaFondo2.getWidth(), etiquetaFondo2.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaFondo2, Integer.valueOf(0));
        int x = 20, y = 20;
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero[fila].length; columna++) {
                tablero[fila][columna] = new JButton();
                tablero[fila][columna].setOpaque(true);
                tablero[fila][columna].setBounds(x, y, 110, 110);
                tablero[fila][columna].setForeground(new Color(250, 250, 250, modo));
                tablero[fila][columna].setBackground(new Color(0, 0, 255, 230));
                String filas = Integer.toString(fila);
                String columnas = Integer.toString(columna);
                tablero[fila][columna].setText(filas + columnas);
                tablero[fila][columna].setName(filas + columnas);
                tablero[fila][columna].addActionListener(this);
                panel.add(tablero[fila][columna], Integer.valueOf(1));
                x += 110;
            }
            x = 20;
            y += 110;
        }
        y = 20;

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

        ActionListener tocarPerfil = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                menuPerfil();
            }
        };

        botonPerfil.addActionListener(tocarPerfil);
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
                dif = (byte) selDificultad.getSelectedIndex();
                switch (selModo.getSelectedIndex()) {
                    case 0:
                        modo = 255;
                        break;
                    case 1:
                        modo = 0;
                        break;
                }
                panel.setVisible(false);
                panel.removeAll();
                menuPrincipal();
            }
        };

        botonSalirC.addActionListener(tocarSalirC);
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
                                id2 = id;
                                break;
                            }
                        }
                    }
                }
                if (match) {
                    panel.setVisible(false);
                    panel.removeAll();
                    Juego();
                } else {
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
        ActionListener tocarHistorial = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                menuHistorial();
            }
        };

        botonSalirMP.addActionListener(tocarSalirC);
        botonRanking.addActionListener(tocarRanking);
        botonHistorial.addActionListener(tocarHistorial);
    }

    private void oyentesDeAccionMRanking() {

        ActionListener tocarVolverR = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                Reportes();
            }
        };

        botonVolver.addActionListener(tocarVolverR);
      
    }

    private void oyentesDeAccionMHistorial() {

        ActionListener tocarVolverR = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                Reportes();
            }
        };

        botonVolver.addActionListener(tocarVolverR);
      
    }

    private void oyentesDeAccionIniciarSesion() {
        ActionListener tocarBotonAceptarIS = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Al tocar boton Aceptar en el menu de iniciar sesion

                if (!(ingresarUsuario.getText().equals("")) && !(ingresarContra.getText().equals(""))) {
                    for (int control = 0; control < jugadores.size(); control++) {
                        if (jugadores.get(control).nombreUsuario.equals(ingresarUsuario.getText())
                                && jugadores.get(control).contraseña.equals(ingresarContra.getText())) {
                            panel.setVisible(false);
                            panel.removeAll();
                            menuPrincipal();
                            idIniciado = control;
                            break;
                        } else {
                            if (control == jugadores.size()) {
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

    private void oyentesDeAccionMenuPerfil() {
        ActionListener tocarBotonVerMisDatos = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                menuMisDatos();
            }
        };

        ActionListener tocarBotonModificarDatos = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                menuModificarDatos();
            }
        };

        ActionListener tocarBotonEliminarCuenta = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                menuPerfil();

                etiquetaConfirmar = new JLabel("CONFIRMAR", SwingConstants.CENTER);
                etiquetaConfirmar.setBounds(345, 750, 230, 100);
                etiquetaConfirmar.setForeground(Color.WHITE);
                etiquetaConfirmar.setFont(fuente.deriveFont(35f));
                panel.add(etiquetaConfirmar, 2);

                botonSi = new JButton("SI");
                botonSi.setFont(fuente.deriveFont(30f));
                botonSi.setBounds(260, 840, 150, 50);
                panel.add(botonSi, 2);

                botonNo = new JButton("NO");
                botonNo.setFont(fuente.deriveFont(30f));
                botonNo.setBounds(510, 840, 150, 50);
                panel.add(botonNo, 2);

                cuadradoMI3.setVisible(true);
                botonEliminarCuenta.setEnabled(false);

                oyentesDeAccionSiyNo();
            }
        };

        ActionListener tocarBotonVolver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                menuPrincipal();
            }
        };

        botonVerDatos.addActionListener(tocarBotonVerMisDatos);
        botonModificarDatos.addActionListener(tocarBotonModificarDatos);
        botonEliminarCuenta.addActionListener(tocarBotonEliminarCuenta);
        botonVolver.addActionListener(tocarBotonVolver);

    }

    private void oyentesDeAccionSiyNo() {
        ActionListener tocarSi = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jugadores.remove(idIniciado);
                panel.setVisible(false);
                panel.removeAll();
                menuInicio();
            }
        };

        ActionListener tocarNo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                menuPerfil();
            }
        };

        botonSi.addActionListener(tocarSi);
        botonNo.addActionListener(tocarNo);
    }

    private void oyentesDeAccionMenuMisDatos() {
        ActionListener tocarVolver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                menuPerfil();
            }
        };

        botonVolver.addActionListener(tocarVolver);
    }

    private void oyentesDeAccionModificarDatos() {
        ActionListener tocarModificarNombre = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int verificarNombre = 0;

                if (!(ingresarUsuario.getText().equals(""))) {
                    if (!(ingresarUsuario.getText().contains(" "))) {
                        for (int control = 0; control < jugadores.size(); control++) {
                            if (jugadores.get(control).getNombreUsuario() != null) {
                                if (jugadores.get(control).getNombreUsuario().equals(ingresarUsuario.getText())) {
                                    verificarNombre++;
                                }
                            }
                        }
                        if (verificarNombre == 0) {
                            jugadores.get(idIniciado).setNombreUsuario(ingresarUsuario.getText());
                        }
                    }
                }
            }
        };

        ActionListener tocarModificarContra = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!(ingresarContra.getText().equals(""))) {
                    if (!(ingresarContra.getText().contains(" "))) {
                        jugadores.get(idIniciado).setContraseña(ingresarContra.getText());
                    }
                }
            }
        };

        ActionListener tocarVolver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.setVisible(false);
                panel.removeAll();
                menuPerfil();
            }
        };

        botonModificarNombre.addActionListener(tocarModificarNombre);
        botonModificarContra.addActionListener(tocarModificarContra);
        botonVolver.addActionListener(tocarVolver);
    }

    public void actualizarRanking() {
        Player orden;
        ranking = new Player[jugadores.size()];

        for (int control = 0; control < jugadores.size(); control++) {
            ranking[control] = jugadores.get(control);
        }

        for (int control = 1; control < jugadores.size(); control++) {
            for (int control2 = 0; control2 < jugadores.size() - control; control2++) {
                if (jugadores.get(control2).getPuntos() > jugadores.get(control2 + 1).getPuntos()) {
                    orden = ranking[control2];
                    ranking[control2] = ranking[control2 + 1];
                    ranking[control2 + 1] = orden;
                }
            }
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String temporales = Character.toString(ae.getActionCommand().charAt(0));
        int fila = Integer.valueOf(temporales);
        temporales = Character.toString(ae.getActionCommand().charAt(1));
        int columna = Integer.valueOf(temporales);

        if (!inicio) {
            if (jugador == 1) {
                if (matriz1[fila][columna] == null && barcos <= maxBarcos) {
                    matriz1[fila][columna] = new Casillas(barcos, random);
                    barcos++;
                }
                if (barcos > maxBarcos) {

                    jugador = 2;
                    panel.setVisible(false);
                    panel.removeAll();
                    Juego();

                }
            } else {

                if (matriz2[fila][columna] == null && barcos2 <= maxBarcos) {
                    matriz2[fila][columna] = new Casillas(barcos2, random);
                    barcos2++;
                }
                if (barcos2 > maxBarcos) {

                    inicio = true;
                    jugador = 1;
                    Juego();

                }

            }

        } else {

            if (matriz2[fila][columna] != null && jugador == 1) {

                if (matriz2[fila][columna].getVida() == 1) {
                    tablero[fila][columna].setBackground(Color.red);
                    switch (matriz2[fila][columna].getBarco()) {
                        case 1:
                        case 5:
                            tablero[fila][columna].setText("DT");
                            break;
                        case 2:
                            tablero[fila][columna].setText("SM");
                            break;
                        case 3:
                            tablero[fila][columna].setText("AZ");
                            break;
                        case 4:
                            tablero[fila][columna].setText("PA");
                            break;
                    }
                } else if (matriz2[fila][columna].getVida() > 1) {
                    tablero[fila][columna].setBackground(Color.yellow);
                    tablero[fila][columna].setForeground(Color.red);
                    switch (matriz2[fila][columna].getBarco()) {
                        case 1:
                        case 5:
                            tablero[fila][columna].setText("DT");
                            break;
                        case 2:
                            tablero[fila][columna].setText("SM");
                            break;
                        case 3:
                            tablero[fila][columna].setText("AZ");
                            break;
                        case 4:
                            tablero[fila][columna].setText("PA");
                            break;
                    }
                }
                hundidos = matriz2[fila][columna].bomba(hundidos);
                jugador = 2;
                Juego();

            } else if (matriz2[fila][columna] == null && jugador == 1) {

                System.out.println("fallaste");
                jugador = 2;
                Juego();

            } else if (matriz1[fila][columna] != null && jugador == 2) {

                if (matriz1[fila][columna].getVida() == 1) {
                    tablero[fila][columna].setBackground(Color.red);
                    switch (matriz1[fila][columna].getBarco()) {
                        case 1:
                        case 5:
                            tablero[fila][columna].setText("DT");
                            break;
                        case 2:
                            tablero[fila][columna].setText("SM");
                            break;
                        case 3:
                            tablero[fila][columna].setText("AZ");
                            break;
                        case 4:
                            tablero[fila][columna].setText("PA");
                            break;
                    }
                } else if (matriz1[fila][columna].getVida() > 1) {
                    tablero[fila][columna].setBackground(Color.yellow);
                    tablero[fila][columna].setForeground(Color.red);
                    switch (matriz1[fila][columna].getBarco()) {
                        case 1:
                        case 5:
                            tablero[fila][columna].setText("DT");
                            break;
                        case 2:
                            tablero[fila][columna].setText("SM");
                            break;
                        case 3:
                            tablero[fila][columna].setText("AZ");
                            break;
                        case 4:
                            tablero[fila][columna].setText("PA");
                            break;
                    }
                }
                hundidos2 = matriz1[fila][columna].bomba(hundidos2);
                jugador = 1;
                Juego();

            } else if (matriz1[fila][columna] == null && jugador == 2) {

                System.out.println("fallaste");
                jugador = 1;
                Juego();

            }

            if (hundidos == maxBarcos || hundidos2 == maxBarcos) {
                if (hundidos == maxBarcos) {
                    jugadores.get(idIniciado).setPuntos(3);
                    label.setText(jugadores.get(idIniciado).setOracionHistorial((byte) 0, jugadores.get(id2).getNombreUsuario()));
                    jugadores.get(id2).setOracionHistorial((byte) 1, jugadores.get(idIniciado).getNombreUsuario());
                }
                if (hundidos2 == maxBarcos) {
                    jugadores.get(id2).setPuntos(3);
                    label.setText(jugadores.get(idIniciado).setOracionHistorial((byte) 1, jugadores.get(id2).getNombreUsuario()));
                    panel.add(label, JLayeredPane.DRAG_LAYER);
                    panel.setVisible(true);
                    for (JButton[] filas : tablero) {
                        for (int columnas = 0; columnas < filas.length; columnas++) {
                            filas[columnas].setEnabled(false);
                        }
                    }
                    _timer.start();
                    jugadores.get(id2).setOracionHistorial((byte) 0, jugadores.get(idIniciado).getNombreUsuario());
                }
                
                panel.setVisible(false);
                panel.removeAll();
                menuPrincipal();
                hundidos = hundidos2 = 0;
                _timer.stop();
                inicio = false;

            }

        }

    }

}
