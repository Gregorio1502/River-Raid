package Prueba1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import Misc.Sound;
import Misc.Imagenes;

/**
 * Clase para el menu antes de empezar el juego en si
 *
 * @author Gregorio y Francisco
 */
public class Menu extends Container {

    private JButton Bt[][] = new JButton[5][];
    private JButton Iniciar = new JButton("Empezar");
    private JPanel Pantallas[] = new JPanel[5];
    private JLabel Titulo;
    private JLabel Fondo;
    private JLabel Error;
    private JTextField JText;
    private int Ancho, Alto;
    private boolean Pant[] = new boolean[4];

    /**
     * Constructor de la clase
     */
    public Menu() {
        super();
        setLayout(null);
    }

    /**
     * En esta Funcion estan los componentes de la ventana inicial del menu
     *
     * @param Ancho Ancho de la ventana a ocupar
     * @param Alto  Alto de la ventana a ocupar
     */
    public void MenuInicial(int Ancho, int Alto) {
        this.Alto = Alto;
        this.Ancho = Ancho;
        for (int i = 0; i < Pantallas.length; i++) {
            Pantallas[i] = new JPanel();
            Pantallas[i].setSize(Ancho, Alto);
            Pantallas[i].setOpaque(false);
            Pantallas[i].setLayout(null);
            Pantallas[i].setVisible(false);
            add(Pantallas[i]);
        }

        Titulo = new JLabel();
        Fondo = new JLabel();
        Bt[0] = new JButton[4];

        Titulo.setText("River Raid");
        Titulo.setSize(Ancho, Alto / 10);
        Titulo.setHorizontalAlignment(SwingConstants.CENTER);
        Titulo.setFont(new Font("", Font.BOLD, 25));
        Titulo.setOpaque(true);
        Titulo.setBackground(Color.ORANGE);

        Fondo.setSize(Ancho, Alto - Titulo.getHeight());
        Fondo.setLocation(0, Titulo.getHeight());
        Fondo.setIcon(Imagenes.getImg()[Imagenes.ImgFondo]);
        Fondo.setOpaque(true);
        Fondo.setBackground(Color.darkGray);

        for (int i = 0; i < Bt[0].length; i++) {
            Bt[0][i] = new JButton();
            Bt[0][i].setSize(200, 40);
            Bt[0][i].setLocation(Ancho / 2 - Bt[0][i].getWidth() / 2,
                    (Titulo.getHeight() + 100) + (Bt[0][i].getHeight() + 40) * i);
            Bt[0][i].setBackground(Color.WHITE);
            Bt[0][i].addActionListener(new EventosBotones());
            Pantallas[0].add(Bt[0][i]);
        }

        Bt[0][0].setText("Jugar");
        Bt[0][1].setText("Instrucciones");
        Bt[0][2].setText("Créditos");
        Bt[0][3].setText("Salir");
        Pantallas[0].setVisible(true);
        add(Fondo);
        add(Titulo);
        Ventana.sound.play(Sound.SoundMenu);
    }

    /**
     * En esta funcion esta la ventana para las instrucciones del juego
     */
    public void Instrucciones() {
        if (!Pant[0]) {
            JLabel SubTitulo = new JLabel("Instrucciones", SwingConstants.CENTER);
            JLabel Texto = new JLabel();

            Bt[1] = new JButton[1];
            Bt[1][0] = new JButton("Atrás");

            SubTitulo.setFont(new Font("", Font.BOLD, 20));
            SubTitulo.setSize(Ancho, Alto / 15);
            SubTitulo.setLocation(0, Titulo.getHeight());
            SubTitulo.setOpaque(true);
            SubTitulo.setBackground(Color.red);

            Bt[1][0].setSize(150, 30);
            Bt[1][0].setLocation(Ancho - Bt[1][0].getWidth() - 30, Alto - Bt[1][0].getHeight() - 50);
            Bt[1][0].setBackground(Color.WHITE);

            Texto.setBounds(0, SubTitulo.getY() + SubTitulo.getHeight(), Ancho,
                    Alto - SubTitulo.getHeight() - Titulo.getHeight());
            Texto.setFont(new Font("", Font.BOLD, 17));
            Texto.setOpaque(true);
            Texto.setBackground(new Color(255, 255, 255, 150));
            Texto.setVerticalAlignment(SwingConstants.TOP);

            String text = "<html><body style=\"margin-left: 20px;\">"
                    + "<em>-Para empezar el juego presione la barra espaciadora</em> <br><br>"
                    + "<span style=\"text-decoration: underline;\">-Movimiento</span>:<br>"
                    + "*Derecha:fecha direccional derecha  <br>"
                    + "*Izquierda:flecha direccional izquierda  <br>"
                    + "*Acelerar:flecha hacia arriba <br> "
                    + "-Disparar:barra espaciadora <br><br>"
                    + "<span style=\"text-decoration: underline;\">Teclas especiales</span>:<br>"
                    + "Esc:Modo de Juego Inmortal <br> "
                    + "J:Marcha atras <br>"
                    + "H:Detiene el Avion <br> "
                    + "K:Estrecha el mapa <br> "
                    + "M:Amplia el mapa<br> "
                    + "V:Amplia el mapa <br>"
                    + "P: Generar Nuevo mapa <br>"
                    + "</body></html>";

            Texto.setText(text);

            Bt[1][0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Pantallas[1].setVisible(false);
                    Pantallas[0].setVisible(true);
                }
            });

            Pantallas[1].add(SubTitulo);
            Pantallas[1].add(Bt[1][0]);
            Pantallas[1].add(Texto);
            Pantallas[1].setVisible(true);
            Pant[0] = true;
        } else {
            Pantallas[1].setVisible(true);
        }
    }

    /**
     * Funcion para la ventana de los creditos
     */
    public void Creditos() {
        if (!Pant[1]) {
            JLabel SubTitulo = new JLabel("Créditos", SwingConstants.CENTER);
            JLabel Texto = new JLabel("");

            Bt[2] = new JButton[1];
            Bt[2][0] = new JButton("Atrás");

            SubTitulo.setFont(new Font("", Font.BOLD, 20));
            SubTitulo.setSize(Ancho, Alto / 15);
            SubTitulo.setLocation(0, Titulo.getHeight());
            SubTitulo.setOpaque(true);
            SubTitulo.setBackground(Color.red);

            Texto.setText(
                    "<html><body><br><br><br><br><br>@Gregorio Briceño <br><br><br>@Francisco Sanchez</body></html>");
            Texto.setVerticalAlignment(SwingConstants.TOP);
            Texto.setHorizontalAlignment(SwingConstants.CENTER);
            Texto.setFont(new Font("", Font.BOLD, 20));
            Texto.setSize(Ancho, Alto - SubTitulo.getHeight() - Titulo.getHeight());
            Texto.setLocation(0, SubTitulo.getY() + SubTitulo.getHeight());
            Texto.setOpaque(true);
            Texto.setBackground(new Color(255, 255, 255, 150));

            Bt[2][0].setSize(150, 40);
            Bt[2][0].setLocation(10, Alto - Bt[2][0].getHeight() - 70);
            Bt[2][0].setBackground(Color.WHITE);

            Bt[2][0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Pantallas[2].setVisible(false);
                    Pantallas[0].setVisible(true);
                }
            });

            Pantallas[2].add(SubTitulo);
            Pantallas[2].add(Bt[2][0]);
            Pantallas[2].add(Texto);
            Pantallas[2].setVisible(true);
            Pant[1] = true;
        } else {
            Pantallas[2].setVisible(true);
        }
    }

    /**
     * Funcion para la ventana de ingresar el nombre antes de empezar a jugar
     */
    public void Nombre() {
        if (!Pant[2]) {

            JLabel SubTitulo = new JLabel("Ingresa Tu Nombre", SwingConstants.CENTER);
            Error = new JLabel("Ingresa un Nombre de Usuario", SwingConstants.CENTER);
            JText = new JTextField();
            Bt[3] = new JButton[1];
            Bt[3][0] = new JButton("Atrás");

            SubTitulo.setFont(new Font("", Font.BOLD, 22));
            SubTitulo.setSize(Ancho, Alto / 15);
            SubTitulo.setLocation(0, Titulo.getHeight());
            SubTitulo.setOpaque(true);
            SubTitulo.setBackground(Color.red);

            Bt[3][0].setSize(150, 40);
            Bt[3][0].setLocation(10, Alto - Bt[3][0].getHeight() - 70);
            Bt[3][0].setBackground(Color.WHITE);

            Bt[3][0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Pantallas[3].setVisible(false);
                    Pantallas[0].setVisible(true);
                }
            });

            JText.setSize(300, 35);
            JText.setHorizontalAlignment(SwingConstants.CENTER);
            JText.setFont(new Font("", Font.BOLD, 20));
            JText.setLocation((int) (Ancho / 2 - JText.getWidth() * 0.5), Alto / 2 - JText.getHeight() - 20);
            JText.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (JText.getText().length() == 8) {
                        e.consume();
                    }
                }
            });
            Iniciar.setSize(150, 40);
            Iniciar.setLocation((int) (Ancho / 2 - Iniciar.getWidth() * 0.5), JText.getY() + Iniciar.getHeight() + 10);
            Iniciar.setBackground(Color.WHITE);

            Error.setSize(Ancho, 30);
            Error.setLocation(0, JText.getY() - Error.getHeight() - 10);
            Error.setFont(new Font("", Font.BOLD, 15));
            Error.setForeground(Color.red);
            Error.setVisible(false);

            Pantallas[3].add(Error);
            Pantallas[3].add(SubTitulo);
            Pantallas[3].add(Bt[3][0]);
            Pantallas[3].add(Iniciar);
            Pantallas[3].add(JText);
            Pantallas[3].setVisible(true);
            Pant[2] = true;
        } else {
            Pantallas[3].setVisible(true);
        }
    }

    /**
     * Ventana de GameOver
     *
     * @param Nombre Usuario que Juego
     * @param Crono  Tiempo de Juego
     * @param Puntos Puntos alcanzados
     */
    public void PantallaFinal(String Nombre, String Crono, int Puntos) {
        Pantallas[3].setVisible(false);
        Pantallas[4].removeAll();
        JLabel SubTitulo = new JLabel("Pantalla Final", SwingConstants.CENTER);
        JLabel Texto = new JLabel("", SwingConstants.CENTER);

        Bt[4] = new JButton[2];
        Bt[4][0] = new JButton("Atrás");
        Bt[4][1] = new JButton("Salir");

        SubTitulo.setFont(new Font("", Font.BOLD, 20));
        SubTitulo.setSize(Ancho, Alto / 15);
        SubTitulo.setLocation(0, Titulo.getHeight());
        SubTitulo.setOpaque(true);
        SubTitulo.setBackground(Color.red);

        Texto.setSize(Ancho, Alto - SubTitulo.getHeight() - Titulo.getHeight());
        Texto.setLocation(0, SubTitulo.getY() + SubTitulo.getHeight());
        Texto.setVerticalAlignment(SwingConstants.TOP);
        Texto.setFont(new Font("", Font.BOLD, 20));
        Texto.setOpaque(true);
        Texto.setBackground(new Color(255, 255, 255, 150));

        Texto.setText("<html><body>"
                + "<Br><Br>Usuario: " + Nombre + "<Br><Br>"
                + "Tiempo de Juego: " + Crono + "<Br><Br>"
                + "Puntaje Alcanzado: " + Puntos + "<Br><Br>"
                + "</body></html>");

        Bt[4][0].setSize(150, 40);
        Bt[4][0].setLocation(50, Alto - Bt[4][0].getHeight() - 100);
        Bt[4][0].setBackground(Color.WHITE);

        Bt[4][1].setSize(150, 40);
        Bt[4][1].setLocation(Ancho - Bt[4][1].getWidth() - 50, Bt[4][0].getY());
        Bt[4][1].setBackground(Color.WHITE);

        Bt[4][0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pantallas[4].setVisible(false);
                Pantallas[0].setVisible(true);
            }
        });

        Bt[4][1].addActionListener(new EventosBotones());

        Pantallas[4].add(SubTitulo);
        Pantallas[4].add(Bt[4][0]);
        Pantallas[4].add(Bt[4][1]);
        Pantallas[4].add(Texto);
        Pantallas[4].setVisible(true);

    }

    /**
     * @return el nombre guardado en el textfield Jtext
     */
    public String getNombre() {
        return JText.getText();
    }

    /**
     * Cambia la visibilidad a true si no hay ningún usuario escrito
     */
    public void Error() {
        Error.setVisible(true);
    }

    /**
     * @return el JButton iniciar
     */
    public JButton getIniciar() {
        return Iniciar;
    }

    /**
     * Eventos para los botones que redirigirán a las ventanas correspondientes
     */
    public class EventosBotones implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Jugar")) {

                Pantallas[0].setVisible(false);

                Nombre();
            } else if (e.getActionCommand().equals("Instrucciones")) {
                Pantallas[0].setVisible(false);
                Instrucciones();
            } else if (e.getActionCommand().equals("Créditos")) {
                Pantallas[0].setVisible(false);
                Creditos();
            } else if (e.getActionCommand().equals("Salir")) {
                System.exit(0);
            }

        }

    }

}
