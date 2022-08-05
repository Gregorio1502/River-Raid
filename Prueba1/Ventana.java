package Prueba1;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Escenario.Fuel_Indicador;
import Misc.Cronometro;
import Misc.Imagenes;
import Misc.Sound;

import java.awt.*;
import java.awt.event.*;

/**
 * Clase para las ventanas del juego en las cuales están los indicadores
 *
 * @author Gregorio y Francisco
 */
public class Ventana extends JFrame {

    /**
     * Sonidos del juego
     */
    static public Sound sound = new Sound();
    /**
     * Contenedor del juego
     */
    Container Juego;
    /**
     * Contenedor del menu
     */
    Menu menu = new Menu();
    /**
     * Clase con todas las imágenes del juego
     */
    Imagenes Img = new Imagenes();
    Insets ins;
    /**
     * El motor del juego
     */
    Prueba Pb;
    JLabel Points, Vidas, ImgVidas, ImgFuel, MsInicio, JNombre, Jcrono, FondoInd;
    String Nombre;
    /**
     * Cronometro del juego
     */
    Cronometro Crom = new Cronometro();
    Fuel_Indicador Fuel;
    /**
     * Comprueba el numero de vidas y si es menor a 0 vuelve a colocar el contenedor
     * de menu
     */
    Timer Volver = new Timer(10, new Volver());
    /**
     * Aumenta el cronometro
     */
    Timer cronoTimer = new Timer(1000, new CronometroT());

    /**
     * Constructor de la clase en donde se inicializan las funciones
     */
    public Ventana() {
        super("River Raid");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setContentPane(menu);
        addKeyListener(new Teclas());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                ins = getInsets();
                setSize(600 + ins.left + ins.right, 500 + ins.top + ins.bottom);
                setLocationRelativeTo(null);
                menu.MenuInicial(getWidth(), getHeight());
                menu.getIniciar().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Nombre = menu.getNombre();
                        if (!Nombre.isEmpty()) {
                            requestFocus();
                            sound.stop(Sound.SoundMenu);
                            IniJuego();
                        } else {
                            menu.Error();
                        }
                    }
                });
            }
        });
        repaint();
    }

    /**
     * Funcion para los componentes del juego, tanto inferiores como
     * superiores
     */
    public void IniJuego() {
        setSize(getWidth(), getHeight() + 20);
        Juego = new Container();
        setContentPane(Juego);
        Juego.setSize(getWidth(), getHeight());
        Juego.setLayout(null);

        Pb = new Prueba(getWidth(), getHeight() - 120);
        Pb.setLocation(0, 20);

        Points = Pb.getPuntosLabel();
        Vidas = Pb.getVidas();

        JNombre = new JLabel("Usuario:" + Nombre + "        Tiempo:" + Crom.getCrono(), SwingConstants.CENTER);
        JNombre.setSize(getWidth(), 20);
        JNombre.setLocation(0, 0);
        JNombre.setOpaque(true);
        JNombre.setBackground(Color.orange);

        MsInicio = new JLabel("Presiona Espacio Para Empezar", SwingConstants.CENTER);
        MsInicio.setBounds(0, Pb.getHeight() + Pb.getY() - 100, getWidth(), 30);
        MsInicio.setFont(new Font("", Font.BOLD, 20));
        MsInicio.setForeground(Color.BLACK);

        Points.setBounds(20, Pb.getHeight() + Pb.getY() + 20, 150, 30);
        Points.setOpaque(true);
        Points.setBackground(Color.ORANGE);
        Points.setFont(new Font("AZONIX", Font.BOLD, 15));

        Vidas.setSize(50, 30);
        Vidas.setLocation((int) (getWidth() / 2 - Vidas.getWidth() * 0.5), Pb.getHeight() + Pb.getY() + 20);
        Vidas.setOpaque(true);
        Vidas.setBackground(Color.LIGHT_GRAY);
        Vidas.setFont(new Font("", Font.BOLD, 20));

        ImgVidas = new JLabel();
        ImgVidas.setSize(Imagenes.getImg()[0].getIconWidth(), Imagenes.getImg()[0].getIconHeight());
        ImgVidas.setIcon(Imagenes.getImg()[0]);
        ImgVidas.setLocation(Vidas.getX() - ImgVidas.getWidth() - 5, Vidas.getY());

        Fuel = new Fuel_Indicador(100, 30);
        Fuel.setLocation(getWidth() - Fuel.getWidth() - 60, Pb.getHeight() + Pb.getY() + 20);
        ImgFuel = new JLabel();
        ImgFuel.setSize(Imagenes.getImg()[1].getIconWidth(), Imagenes.getImg()[1].getIconHeight());
        ImgFuel.setIcon(Imagenes.getImg()[1]);
        ImgFuel.setLocation(Fuel.getX() - ImgFuel.getWidth() - 5, Vidas.getY() - 3);

        FondoInd = new JLabel();
        FondoInd.setSize(getWidth(), getHeight() - Pb.getHeight() - JNombre.getHeight());
        FondoInd.setLocation(0, Pb.getHeight());
        FondoInd.setIcon(Imagenes.getImg()[Imagenes.FondoInd]);

        Juego.add(MsInicio);
        Juego.add(JNombre);
        Juego.add(Pb);
        Juego.add(Fuel);
        Juego.add(Points);
        Juego.add(Vidas);
        Juego.add(ImgVidas);
        Juego.add(ImgFuel);
        Juego.add(FondoInd);

    }

    /**
     * Clase privada para el Eventos de las teclas
     */
    private class Teclas extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (Pb != null) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    Pb.Right(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    Pb.Left(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    Pb.Up(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    MsInicio.setVisible(false);
                    Volver.start();
                    cronoTimer.start();
                    Pb.Space();
                } else {
                    Pb.TeclasEspeciales(e);
                }
            }
        }

        public void keyReleased(KeyEvent e) {
            if (Pb != null) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    Pb.Right(false);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    Pb.Left(false);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    Pb.Up(false);
                } else if (e.getKeyCode() == KeyEvent.VK_R) {
                    System.out.println("W:" + getWidth() + "\t H:" + getHeight());
                }
            }

        }

    }

    /**
     * Funcion para el GameOver
     */
    public class Volver implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!Pb.getBajar().isRunning()) {
                setSize(getWidth(), getHeight() - 20);
                menu.PantallaFinal(Nombre, Crom.getCrono(), Pb.getPuntos());
                sound.play(Sound.SoundMenu);
                setContentPane(menu);
                cronoTimer.stop();
                Crom.reset();
                Volver.stop();
            }

        }

    }

    /**
     * Funcion para implementar los datos de la ventana superior del juego
     */
    public class CronometroT implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Crom.segUp();
            JNombre.setText("Usuario:" + Nombre + "        Tiempo:" + Crom.getCrono());
        }

    }

}
