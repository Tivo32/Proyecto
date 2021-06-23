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
import javax.swing.Timer;

public class Battleship extends JFrame implements ActionListener {

    //Declarar variables
    private Timer _timer;
    private JPanel panel;
    private JComboBox selDificultad;
    private JButton[][] tablero;
    private int[][] casillas1 = new int[8][8], casillas2 = new int[8][8];
    private JButton botonIniciarSesion, botonCrearUsuario, botonSalirMI, botonSalirMP, botonJugar2,
            botonAceptarIniciarSesion, botonAceptarCrearUsuario, botonJugar, botonConfig,
            botonReportes, botonPerfil, botonSalirC, botonRanking, botonHistorial, botonVolver;
    private JLabel cuadradoMI, cuadradoMI2, etiquetaFondo1, tituloUsuario, tituloContra, dificultad,
            gamemode, errorEspacios1, errorEspacios2, etiquetaTitulo, etiquetaFondo2, errMessage, label;
    private JTextField ingresarUsuario, ingresarContra;
    ArrayList<Player> jugadores = new ArrayList<>();
    ArrayList<Player> historial = new ArrayList<>();
    String nombreUsuario, contraseña;
    String[] dificultades = {"EASY", "NORMAL", "EXPERT", "GENIUS"};
    int controlJugadorCreado = 0, puntos, usuarioCreado = 0, idIniciado, id2, maxBarcos = 0,
            barcos = 1, barcos2 = 1, opac = 255, jugador = 1;
    byte dif;
    boolean error = false, inicio = false;
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

        barcos = 1;
        for (int[] casilla : casillas1) {
            for (int borrador = 0; borrador < casilla.length; borrador++) {
                casilla[borrador] = 0;
            }
        }

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
                label.setText("Jugador 1\nOrdene los barcos");
                if (opac > 0) {
                    panel.setVisible(false);
                    panel.remove(label);
                    label.setForeground(new Color(255, 255, 255, opac));
                    label.setBackground(new Color(0, 0, 0, opac));
                    panel.add(label);
                    panel.setVisible(true);
                    opac -= 5;
                }

            }
        };

        _timer = new Timer(3000, tempo);
        _timer.start();

        if (opac < 5) {
            _timer.stop();
        }

    }

    private void Juego() {
        label = new JLabel();
        label.setBounds(100, 425, 700, 200);
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(fuente.deriveFont(30f));
        label.setForeground(new Color(255, 255, 255, opac));
        label.setBackground(new Color(0, 0, 0, 170));
        label.setText("Jugador " + jugador + ": Ordene los barcos");
        panel.add(label);
        jugador1();
        Tablero();
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

        int x = 20, y = 20;
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero[fila].length; columna++) {
                tablero[fila][columna] = new JButton();
                tablero[fila][columna].setBounds(x, y, 110, 110);
                tablero[fila][columna].setBackground(Color.BLUE);
                tablero[fila][columna].setForeground(new Color(0, 0, 0, 0));
                String filas = Integer.toString(fila);
                String columnas = Integer.toString(columna);
                tablero[fila][columna].setText(filas + columnas);
                tablero[fila][columna].addActionListener(this);
                panel.add(tablero[fila][columna]);
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

    public void imprimirRanking() {
        String orden;
        String ranking[] = new String[jugadores.size()];

        for (int control = 0; control < jugadores.size(); control++) {
            ranking[control] = jugadores.get(control).getNombreUsuario();
        }

        for (int control = 1; control < jugadores.size(); control++) {
            for (int control2 = 0; control2 < jugadores.size(); control++) {
                if (jugadores.get(control).getPuntos() > jugadores.get(control + 1).getPuntos()) {
                    orden = ranking[control];
                    ranking[control] = ranking[control + 1];
                    ranking[control + 1] = orden;
                }
            }
        }

        for (int control1 = 1, control2 = ranking.length; control2 > -1; control2--, control1++) {
            System.out.println(control1 + ". " + ranking[control2]);
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
                if (casillas1[fila][columna] == 0 && barcos <= maxBarcos) {
                    casillas1[fila][columna] = barcos;
                    barcos++;
                }
                if (barcos > maxBarcos) {

                    jugador = 2;
                    panel.setVisible(false);
                    panel.removeAll();
                    Juego();

                }
            } else {

                if (casillas2[fila][columna] == 0 && barcos2 <= maxBarcos) {
                    casillas2[fila][columna] = barcos2;
                    barcos2++;
                }
                if (barcos2 > maxBarcos) {

                    inicio = true;
                    jugador = 1;

                }

            }
            
        } else {
            
            
            
        }

    }

}
