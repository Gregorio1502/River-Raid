package Prueba1;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Entidades.Avion;
import Entidades.Balas;
import Escenario.Escenarios;
import Escenario.Fuel_Indicador;
import Escenario.MapaT1;
import Misc.Imagenes;
import Misc.Sound;

/**
 * Clase central para el juego, Aquí se juntan la gran mayoría de las clases
 * 
 * @author Gregorio y Francisco
 */
public class Prueba extends JPanel {

    private Avion Jugador;
    private Escenarios Esc;
    private Balas bala = new Balas();
    private JLabel MuroIzq, MuroDer, PuntosLabel, Vidas, Agua1, Agua2, Hierba[] = new JLabel[4];
    private Timer Ace = new Timer(200, new Aceleracion()), AceBajar = new Timer(200, new Aceleracion()),
            Bajar = new Timer(15, new Bajar()), TimerMuros = new Timer(10, new Muros());
    private boolean Acele = false;
    private boolean GOD = true;
    private int inicio;
    private int Puntos = 0;
    private int DirMuros = 0;
    private int TamañoMuros = 0;

    /**
     * Constructor de la clase en donde se inicializan los componentes
     *
     * @param Ancho Ancho del panel contenedor del juego
     * @param Largo largo del panel contenedor del juego
     */
    public Prueba(int Ancho, int Largo) {
        setSize(Ancho, Largo);
        setVisible(true);
        setLayout(null);
        iniciar();
        requestFocus();
    }

    /**
     * Inicia el timer bajar y por lo tanto el juego
     */
    public void Start() {
        Bajar.start();
    }

    /**
     * detiene el timer bajar y por lo tanto el juego
     */
    public void Stop() {
        Bajar.stop();
    }

    /**
     * @return la cantidad de puntos obtenidos
     */
    public int getPuntos() {
        return Puntos;
    }

    /**
     * @return el label donde estan los puntos
     */
    public JLabel getPuntosLabel() {
        return PuntosLabel;
    }

    /**
     * @return el label donde estan las vidas
     */
    public JLabel getVidas() {
        return Vidas;
    }

    /**
     * @return el timer Bajar
     */
    public Timer getBajar() {
        return Bajar;
    }

    /**
     * En esta función se implementan los componentes principales del juego
     */
    public void iniciar() {
        setBackground(Color.CYAN);
        MuroIzq = new JLabel();
        MuroDer = new JLabel();
        Agua1 = new JLabel();
        Agua2 = new JLabel();

        Jugador = new Avion(getWidth(), getHeight());
        int MuAncho = (getWidth() / 5);
        int MuLargo = getHeight();

        PuntosLabel = new JLabel("Punto: " + Puntos);

        add(Jugador.getCuerpo());
        Jugador.setVidas(2);

        Vidas = new JLabel();
        Vidas.setText(": " + (1 + Jugador.getVidas()));

        Ventana.sound.play(Sound.SoundFondo);

        MuroIzq.setSize(MuAncho, MuLargo);
        MuroIzq.setLocation(0, 0);
        MuroIzq.setVisible(false);
        add(MuroIzq);

        MuroDer.setSize(MuAncho, MuLargo);
        MuroDer.setVisible(false);
        MuroDer.setLocation((int) (getWidth() - MuroDer.getWidth()), 0);
        add(MuroDer);

        for (int i = 0; i < Hierba.length; i++) {
            Hierba[i] = new JLabel();
            if (i % 2 == 0) {
                Hierba[i].setSize(MuAncho, Imagenes.getImg()[Imagenes.LateralIzq].getIconHeight());
                Hierba[i].setIcon(Imagenes.getImg()[Imagenes.LateralIzq]);
            } else {
                Hierba[i].setSize(MuAncho, Imagenes.getImg()[Imagenes.LateralDer].getIconHeight());
                Hierba[i].setIcon(Imagenes.getImg()[Imagenes.LateralDer]);
            }
            add(Hierba[i]);
        }

        Hierba[0].setLocation(0, -Hierba[0].getHeight() + getHeight());
        Hierba[1].setLocation(MuroDer.getX(), -Hierba[1].getHeight() + getHeight());
        Hierba[2].setLocation(0, -Hierba[2].getY() - Hierba[2].getHeight());
        Hierba[3].setLocation(MuroDer.getX(), Hierba[1].getY() - Hierba[3].getHeight());

        Esc = new MapaT1(getWidth() - MuroDer.getWidth() - MuroIzq.getWidth(), 10000, 40, 10, 7);
        Esc.setLocation(MuroIzq.getWidth(), -Esc.getHeight() - 20);

        inicio = Esc.getY();

        bala.getCuerpo().setVisible(false);

        add(bala.getCuerpo());
        add(Esc);

        Agua1.setIcon(Imagenes.getImg()[Imagenes.ImgAgua]);
        Agua1.setSize(Imagenes.getImg()[Imagenes.ImgAgua].getIconWidth(),
                Imagenes.getImg()[Imagenes.ImgAgua].getIconHeight());

        Agua2.setIcon(Imagenes.getImg()[Imagenes.ImgAgua]);
        Agua2.setSize(Imagenes.getImg()[Imagenes.ImgAgua].getIconWidth(),
                Imagenes.getImg()[Imagenes.ImgAgua].getIconHeight());

        Agua1.setLocation(0, -Agua1.getHeight() / 2);
        Agua2.setLocation(0, Agua1.getY() - Agua2.getHeight());
        add(Agua1);
        add(Agua2);

    }

    /**
     * Funcion para el funcionamiento de las teclas especiales del juego
     *
     * @param e auxiliar para identificar la letra ingresada
     */
    public void TeclasEspeciales(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_K) {
            MuroIzq.setSize(MuroDer.getWidth() + 10, MuroDer.getHeight());
            MuroDer.setSize(MuroDer.getWidth() + 10, MuroIzq.getHeight());
            MuroDer.setLocation(MuroDer.getX() - 10, MuroDer.getY());

            Hierba[0].setBounds(MuroIzq.getX(), Hierba[0].getY(), MuroIzq.getWidth(), Hierba[0].getHeight());
            Hierba[2].setBounds(MuroIzq.getX(), Hierba[2].getY(), MuroIzq.getWidth(), Hierba[2].getHeight());
            Hierba[1].setBounds(MuroDer.getX(), Hierba[1].getY(), MuroDer.getWidth(), Hierba[1].getHeight());
            Hierba[3].setBounds(MuroDer.getX(), Hierba[3].getY(), MuroDer.getWidth(), Hierba[3].getHeight());
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            MuroIzq.setSize(MuroDer.getWidth() - 10, MuroDer.getHeight());
            MuroDer.setSize(MuroDer.getWidth() - 10, MuroIzq.getHeight());
            MuroDer.setLocation(MuroDer.getX() + 10, MuroDer.getY());

            Hierba[0].setBounds(MuroIzq.getX(), Hierba[0].getY(), MuroIzq.getWidth(), Hierba[0].getHeight());
            Hierba[2].setBounds(MuroIzq.getX(), Hierba[2].getY(), MuroIzq.getWidth(), Hierba[2].getHeight());
            Hierba[1].setBounds(MuroDer.getX(), Hierba[1].getY(), MuroDer.getWidth(), Hierba[1].getHeight());
            Hierba[3].setBounds(MuroDer.getX(), Hierba[3].getY(), MuroDer.getWidth(), Hierba[3].getHeight());
        }

        if (e.getKeyCode() == KeyEvent.VK_H) {
            Jugador.setAceleracion(-2);
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            Jugador.setAceleracion(-5);
        }
        if (e.getKeyCode() == KeyEvent.VK_V) {
            Esc.setSize(Esc.getWidth() + 10, Esc.getHeight());
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            New_Mapa();
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && Jugador.isVivo()) {
            GOD = !GOD;
        }
    }

    /**
     * Función para efectuar los disparos
     * O inicia el juego
     */
    public void Space() {
        if (Bajar.isRunning()) {
            if (!Jugador.isDisparar() && Jugador.isVivo()) {
                bala.getCuerpo().setLocation(
                        Jugador.getCuerpo().getX() + Jugador.getCuerpo().getWidth() / 2 + bala.getAncho(),
                        Jugador.getCuerpo().getY());
                bala.getCuerpo().setVisible(true);
                Jugador.setDisparar(true);
            }
        } else {
            Bajar.start();
        }
    }

    /**
     * Funcion para la aceleración al hundir la tecla de flecha hacia arriba
     * si la presiona acelera
     * sino comienza a desacelerar
     * 
     * @param Press
     */
    public void Up(boolean Press) {
        if (Press) {
            Acele = true;
            AceBajar.start();
        } else {
            Acele = false;
        }
    }

    /**
     *
     * Funcion para la aceleracion hacia la derecha
     *
     * @param T
     */
    public void Right(boolean T) {
        if (T) {
            Jugador.setAceDer(true);
            if (!Ace.isRunning()) {
                Ace.start();
            }
            Jugador.getDer().start();
        } else {
            Jugador.setAceDer(false);
            Jugador.getDer().stop();
        }
    }

    /**
     * Funcion para la aceleracion hacia la izquierda
     *
     * @param T
     */
    public void Left(boolean T) {
        if (T) {
            Jugador.setAceIzq(true);
            if (!Ace.isRunning()) {
                Ace.start();
            }
            Jugador.getIzq().start();

        } else {
            Jugador.setAceIzq(false);
            Jugador.getIzq().stop();
        }
    }

    /**
     * Función para al ser destruido el avion vuelva a el principio del nivel y
     * se le reste una vida, ademas de iniciar la animación de destrucción
     */
    public void Morir() {
        if (!GOD) {
            if (!Jugador.getTime().isRunning() && Jugador.isVivo()) {
                Jugador.getTime().start();
                Jugador.setVivo(false);
                Ventana.sound.play(Sound.SoundMorir);
                Ventana.sound.play(Sound.SoundDestruir);
            } else if (!Jugador.getTime().isRunning() && !Jugador.isVivo()) {
                if (Jugador.getVidas() > 0) {
                    Esc.setLocation(Esc.getX(), inicio);
                    Jugador.getCuerpo().setLocation(
                            (int) (Esc.getX() + Esc.getWidth() / 2 - Jugador.getCuerpo().getWidth() * 0.5),
                            300);
                    Jugador.setVidas(Jugador.getVidas() - 1);
                    Vidas.setText(": " + (1 + Jugador.getVidas()));
                    Jugador.getCuerpo().setIcon(Imagenes.getImg()[Imagenes.ImgAvion]);
                    Jugador.setFuel(100);
                    Jugador.setMoverse(true);
                    Jugador.setVivo(true);
                    Jugador.setAceleracion(0);
                } else {
                    Ventana.sound.stop(Sound.SoundFondo);
                    Bajar.stop();
                }
            }
        }
    }

    /**
     * Genera un nuevo nivel después del actual
     * o al presionar la tecla P
     */
    public void New_Mapa() {
        if (!TimerMuros.isRunning()) {
            Random Rd = new Random();
            TamañoMuros = Rd.nextInt(getWidth() / 6) + 50;
            if (TamañoMuros < MuroDer.getWidth()) {
                DirMuros = -1;
            } else {
                DirMuros = +1;
            }
            TimerMuros.start();
        }
    }

    /**
     * Funcion para evitar disparos seguidos
     */
    public void NDisparar() {
        bala.getCuerpo().setVisible(false);
        Jugador.setDisparar(false);
    }

    /**
     * Funcion para que los muros se muevan de acuerdo a la función New_Mapa
     */
    private class Muros implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (DirMuros == 1) {
                MuroIzq.setSize(MuroDer.getWidth() + 1, MuroDer.getHeight());
                MuroDer.setSize(MuroDer.getWidth() + 1, MuroIzq.getHeight());
                MuroDer.setLocation(MuroDer.getX() - 1, MuroDer.getY());

                Hierba[0].setBounds(MuroIzq.getX(), Hierba[0].getY(), MuroIzq.getWidth(), Hierba[0].getHeight());
                Hierba[2].setBounds(MuroIzq.getX(), Hierba[2].getY(), MuroIzq.getWidth(), Hierba[2].getHeight());
                Hierba[1].setBounds(MuroDer.getX(), Hierba[1].getY(), MuroDer.getWidth(), Hierba[1].getHeight());
                Hierba[3].setBounds(MuroDer.getX(), Hierba[3].getY(), MuroDer.getWidth(), Hierba[3].getHeight());
                if (MuroDer.getWidth() >= TamañoMuros) {
                    TimerMuros.stop();
                    Esc.removeAll();
                    Esc = new MapaT1(getWidth() - MuroDer.getWidth() - MuroIzq.getWidth(), Esc.getHeight() + 5000,
                            Esc.getEnemigos().length + 20, Esc.getFuels().length + 5,
                            (int) (Esc.getObstaculos().length * 1.3));
                    add(Esc);
                    add(Agua1);
                    add(Agua2);
                    Esc.setLocation(MuroIzq.getWidth(), -Esc.getHeight() - 20);
                    Esc.setOpaque(false);

                    inicio = Esc.getY();
                }
            }
            if (DirMuros == -1) {
                MuroIzq.setSize(MuroDer.getWidth() - 1, MuroDer.getHeight());
                MuroDer.setSize(MuroDer.getWidth() - 1, MuroIzq.getHeight());
                MuroDer.setLocation(MuroDer.getX() + 1, MuroDer.getY());

                Hierba[0].setBounds(MuroIzq.getX(), Hierba[0].getY(), MuroIzq.getWidth(), Hierba[0].getHeight());
                Hierba[2].setBounds(MuroIzq.getX(), Hierba[2].getY(), MuroIzq.getWidth(), Hierba[2].getHeight());
                Hierba[1].setBounds(MuroDer.getX(), Hierba[1].getY(), MuroDer.getWidth(), Hierba[1].getHeight());
                Hierba[3].setBounds(MuroDer.getX(), Hierba[3].getY(), MuroDer.getWidth(), Hierba[3].getHeight());
                if (MuroDer.getWidth() <= TamañoMuros) {
                    TimerMuros.stop();
                    Esc.removeAll();
                    Esc = new MapaT1(getWidth() - MuroDer.getWidth() - MuroIzq.getWidth(), Esc.getHeight() + 5000,
                            Esc.getEnemigos().length + 20, Esc.getFuels().length + 5,
                            (int) (Esc.getObstaculos().length * 1.3));
                    add(Esc);
                    add(Agua1);
                    add(Agua2);
                    Esc.setLocation(MuroIzq.getWidth(), -Esc.getHeight() - 20);
                    Esc.setOpaque(false);

                    inicio = Esc.getY();
                }
            }

        }

    }

    /**
     * Funcion para la aceleración del mapa y para los laterales
     */
    private class Aceleracion implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(Ace)) {
                if ((Jugador.isAceDer() || Jugador.isAceIzq()) && Jugador.getVelocidad() < 5) {
                    Jugador.setVelocidad(Jugador.getVelocidad() + 1);
                } else if (!Jugador.isAceDer() && !Jugador.isAceIzq()) {
                    Jugador.setVelocidad(Jugador.getVelocidad() - 0.25f);
                    if (Jugador.getVelocidad() <= 1) {
                        Jugador.setVelocidad(1);
                        Ace.stop();
                    }
                }
            } else if (e.getSource().equals(AceBajar)) {
                if (Acele && Jugador.getAceleracion() < 7) {
                    Jugador.setAceleracion(Jugador.getAceleracion() + 1);
                    bala.setVelocidad(bala.getVelocidad() + 1);
                } else if (!Acele) {
                    Jugador.setAceleracion(Jugador.getAceleracion() - 1);
                    bala.setVelocidad(bala.getVelocidad() - 1);
                    if (Jugador.getAceleracion() <= 1) {
                        Jugador.setAceleracion(1);
                        bala.setVelocidad(15);
                        AceBajar.stop();
                    }
                }
            }
        }
    }

    /**
     * En esta clase esta el Timer que mueve el escenario y valora las
     * acciones que hacen que el juego funcione. Todas las colisiones ocasionadas
     * por el jugador o el funcionamiento del juego se valoran aquí Es seguro
     * decir que esta función es la mas importante pues hace que el juego se
     * mueva y funcione
     */
    private class Bajar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (Jugador.isVivo()) {
                Esc.setLocation(Esc.getX(), Esc.getY() + 2 + (int) Jugador.getAceleracion());
                Agua1.setLocation(Agua1.getX(), Agua1.getY() + 2 + (int) Jugador.getAceleracion());
                Agua2.setLocation(Agua2.getX(), Agua2.getY() + 2 + (int) Jugador.getAceleracion());

                for (int j = 0; j < Hierba.length; j++) {
                    Hierba[j].setLocation(Hierba[j].getX(), Hierba[j].getY() + 2 + (int) Jugador.getAceleracion());
                }
                if (Hierba[0].getY() > getHeight()) {
                    Hierba[0].setLocation(0, Hierba[2].getY() - Hierba[0].getHeight());
                }
                if (Hierba[2].getY() > getHeight()) {
                    Hierba[2].setLocation(0, Hierba[0].getY() - Hierba[2].getHeight());
                }
                if (Hierba[1].getY() > getHeight()) {
                    Hierba[1].setLocation(MuroDer.getX(), Hierba[3].getY() - Hierba[1].getHeight());
                }
                if (Hierba[3].getY() > getHeight()) {
                    Hierba[3].setLocation(MuroDer.getX(), Hierba[1].getY() - Hierba[3].getHeight());
                }
            } else {
                Morir();
            }

            if (Agua1.getY() > getHeight()) {
                Agua1.setLocation(0, Agua2.getY() - Agua1.getHeight());
            }
            if (Agua2.getY() > getHeight()) {
                Agua2.setLocation(0, Agua1.getY() - Agua2.getHeight());
            }

            if (Jugador.getFuel() >= 0) {
                Jugador.setFuel(Jugador.getFuel() - (0.03 + Jugador.getAceleracion() / 100));
            }

            if (Jugador.getFuel() < 0) {
                Morir();
            }

            Fuel_Indicador.Ind.setSize((int) Jugador.getFuel(), Fuel_Indicador.Ind.getHeight());

            if (Esc.getY() > getHeight() + 20 && !TimerMuros.isRunning()) {
                New_Mapa();
            }

            if (Jugador.getCuerpo().getBounds().intersects(MuroDer.getBounds())
                    || Jugador.getCuerpo().getBounds().intersects(MuroIzq.getBounds())) {
                Morir();
            }

            for (int i = 0; i < Esc.getObstaculos().length; i++) {
                for (int j = 0; j < Esc.getObstaculos()[i].getObs().length; j++) {
                    Rectangle Obst = Esc.getObstaculos()[i].getObs()[j].getBounds();
                    Obst.setLocation(Obst.x + Esc.getX(), Obst.y + Esc.getY());
                    if (Jugador.getCuerpo().getBounds().intersects(Obst)) {
                        Morir();
                    }
                    if (Jugador.isDisparar()) {
                        if (bala.getCuerpo().getBounds().intersects(Obst)) {
                            NDisparar();
                        }
                    }

                }

            }

            for (int i = 0; i < Esc.getEnemigos().length; i++) {
                Rectangle Com = Esc.getEnemigos()[i].getCuerpo().getBounds();
                Com.setLocation(Com.x + Esc.getX(), Esc.getY() + Com.y);
                if (Com.y > 100 && Com.y < getHeight()) {
                    Esc.getEnemigos()[i].ColisionEneg(Esc.getEnemigos());
                    Esc.getEnemigos()[i].Movimiento();
                    for (int j = 0; j < Esc.getObstaculos().length; j++) {
                        if (Esc.getObstaculos()[j].getPAltura() < Esc.getEnemigos()[i].getCuerpo().getY()
                                && Esc.getObstaculos()[j].getPAltura() + Esc.getObstaculos()[j]
                                        .getAlturaT() > Esc.getEnemigos()[i].getCuerpo().getY()) {
                            if (Esc.getObstaculos()[j].ColisionEnemigo(Esc.getEnemigos()[i].getCuerpo())) {
                                Esc.getEnemigos()[i].CambiarDireccion();
                            }

                        }
                    }
                    if (Esc.getPolly().ColisionEnemigo(Esc.getEnemigos()[i].getCuerpo())) {
                        Esc.getEnemigos()[i].CambiarDireccion();
                    }
                }
                if (Com.y > 0 && Com.y < getHeight()) {
                    if (Jugador.getCuerpo().getBounds().intersects(Com) && Esc.getEnemigos()[i].isVivo()) {
                        Morir();
                    }
                    if (Jugador.isDisparar()) {
                        if (bala.getCuerpo().getBounds().intersects(Com) && Esc.getEnemigos()[i].isVivo()) {
                            Esc.getEnemigos()[i].setVivo(false);
                            Esc.getEnemigos()[i].getTime().start();
                            Ventana.sound.play(Sound.SoundDestruir);
                            NDisparar();
                            Puntos += 100;
                            PuntosLabel.setText("Puntos: " + Puntos);
                        }

                        if (bala.getCuerpo().getBounds().intersects(Esc.getPolly().getObs()[0].getBounds())
                                && !Esc.getPolly().isDestruido()) {
                            Esc.getPolly().Destruir();
                            NDisparar();
                        }
                    }

                }
            }

            for (int i = 0; i < Esc.getFuels().length; i++) {
                Rectangle Com = Esc.getFuels()[i].getCuerpo().getBounds();
                Com.setLocation(Com.x + Esc.getX(), Com.y + Esc.getY());
                if (Com.y > 0 && Com.y < Esc.getHeight()) {
                    if (Jugador.getCuerpo().getBounds().intersects(Com)) {
                        if (Jugador.getFuel() < 100 && Esc.getFuels()[i].isVivo()) {
                            Jugador.setFuel(Jugador.getFuel() + 10);
                        }
                    }
                    if (Jugador.isDisparar()) {
                        if (bala.getCuerpo().getBounds().intersects(Com) && Esc.getFuels()[i].isVivo()) {
                            Esc.getFuels()[i].setVivo(false);

                            Esc.getFuels()[i].getTime().start();
                            Ventana.sound.play(Sound.SoundDestruir);
                            NDisparar();
                            Puntos -= 50;
                            PuntosLabel.setText("Puntos: " + Puntos);
                        }
                    }
                }
            }

            if (Esc.getY() > -10) {
                for (int i = 0; i < Esc.getPolly().getObs().length; i++) {
                    Rectangle polly = Esc.getPolly().getObs()[i].getBounds();
                    polly.setLocation(polly.x + Esc.getX(), polly.y + Esc.getY());
                    if (Jugador.getCuerpo().getBounds().intersects(polly) && i < Esc.getPolly().getObs().length - 2) {
                        Morir();
                    } else if (i == Esc.getPolly().getObs().length - 1 && !Esc.getPolly().isDestruido()
                            && Jugador.getCuerpo().getBounds().intersects(polly)) {
                        Morir();
                    }
                    if (Jugador.isDisparar()) {
                        if (bala.getCuerpo().getBounds().intersects(polly) && i == Esc.getPolly().getObs().length - 1
                                && !Esc.getPolly().isDestruido()) {
                            Esc.getPolly().Destruir();
                            NDisparar();
                        } else if (i != Esc.getPolly().getObs().length - 1
                                && bala.getCuerpo().getBounds().intersects(polly) && !Esc.getPolly().isDestruido()) {
                            NDisparar();
                        }
                    }
                }
            }

            if (Jugador.isDisparar()) {
                if (bala.getCuerpo().getY() > -bala.getCuerpo().getHeight()) {
                    bala.Movimiento();
                } else {
                    NDisparar();
                }
            }

        }

    }

}
