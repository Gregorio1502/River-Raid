package Obstaculos;

import javax.swing.JLabel;
import javax.swing.*;
import Misc.Imagenes;
import java.awt.event.*;
import java.util.Arrays;

/**
 * Obstáculo final del mapa
 * 
 * @author Gregorio y Francisco
 */
public class Puente extends Obstaculos {

    /**
     * Contienen la animación de explosion al destruir el puente
     */
    JLabel Exp[] = new JLabel[5];

    /**
     * Permite la animación al ser destruido
     */
    Timer Time;
    /**
     * Dicta si el puente esta o no destruido y transitable
     */
    boolean Destruido;
    /**
     * Marcador de secuencia de imágenes de explosiones
     */
    int N[] = new int[Exp.length];

    /**
     * Marcador de etiquetas que seguirán la secuencia de imágenes de explosiones
     */
    int E = 1;

    /**
     * @return boolean para identificar el estado del puente
     */
    public boolean isDestruido() {
        return Destruido;
    }

    /**
     * @param vivo set apra modificar el estado del puente
     */
    public void setDestruido(boolean vivo) {
        Destruido = vivo;
    }

    /**
     * Constructor de la clase
     * 
     * @param Ancho  Limite de que tan ancho puede ser
     * @param Altura EL punto en y a partir de donde se empieza a generar el
     *               obstáculo
     */
    public Puente(int Ancho, int Altura) {
        super(Ancho, Altura);
        Destruido = false;
        Obs = new JLabel[11];
        Time = new Timer(50, new Explotar());
        IniciarComponentes();
        Posicionar();
    }

    /**
     * En esta función se agregan los componentes que conforman los obstáculos,
     * en este caso varios rectángulos En este caso esta el puente que es
     * destruible el cual es un Rectangulo y están sus laterales que son varios
     * rectángulos
     *
     * @see Obstaculos.Obstaculos#IniciarComponentes()
     */
    @Override
    public void IniciarComponentes() {

        for (int i = 0; i < Obs.length; i++) {
            Obs[i] = new JLabel();
            Obs[i].setOpaque(true);
            Obs[i].setIcon(Imagenes.getImg()[Imagenes.LateralPuente]);
        }
        for (int i = 0, j = (Obs.length - 1) / 2; i < Obs.length - 1; i += 2) {
            Obs[i].setSize(((int) ((Ancho / (1.7 * Obs.length)) * j)), 20);
            Obs[i + 1].setSize(((int) ((Ancho / (1.7 * Obs.length)) * j)), 20);
            j--;
        }

        Obs[Obs.length - 1].setSize(Ancho - (2 * (Obs[Obs.length - 2].getWidth())), 20);
        Obs[Obs.length - 1].setIcon(Imagenes.getImg()[Imagenes.Puente]);

        for (int i = 0; i < Exp.length; i++) {
            Exp[i] = new JLabel();
            Exp[i].setSize(20, 20);
            Exp[i].setIcon(Imagenes.getExp()[0]);
            Exp[i].setVisible(false);
        }
    }

    /**
     * Posiciona el puente
     *
     * @see Obstaculos.Obstaculos#Posicionar()
     */
    @Override
    public void Posicionar() {

        for (int i = 0, j = 0; i < Obs.length - 1; i += 2) {
            Obs[i].setLocation(0, Altura + Obs[i].getHeight() * j);
            Obs[i + 1].setLocation(Ancho - Obs[i + 1].getWidth(), Altura
                    + Obs[i].getHeight() * j);
            j++;
        }
        Obs[Obs.length - 1].setLocation(Obs[0].getWidth(), 0);

        for (int i = 0; i < Exp.length; i++) {
            Exp[i].setLocation(getObs()[Obs.length - 1].getX() + 35 * i + 20, getObs()[Obs.length - 1].getY());
        }
    }

    /**
     * Función para cambiar el boolean que indica si el puente ha sido destruido
     * e inicia la animación de destrucción
     */
    public void Destruir() {
        Destruido = true;
        Arrays.fill(N, 0);
        Time.start();
    }

    /**
     * @return un label para las explosiones del puente
     */
    public JLabel[] getExp() {
        return Exp;
    }

    /**
     * Funcion para las explosiones
     */
    public class Explotar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Destruido) {
                for (int i = 0; i < E; i++) {
                    if (!Exp[i].isVisible()) {
                        Exp[i].setVisible(true);
                        Exp[i].setIcon(Imagenes.getExp()[N[i]]);
                    } else {
                        Exp[i].setIcon(Imagenes.getExp()[N[i]]);
                    }
                }
                for (int i = 0; i < E; i++) {
                    if (N[i] < Imagenes.getExp().length - 1) {
                        N[i]++;
                    }

                }
                if (E < N.length) {
                    E++;
                }
                if (N[N.length - 1] > 5) {
                    for (int i = 0; i < Exp.length; i++) {
                        Exp[i].setVisible(false);
                    }
                    Obs[Obs.length - 1].setVisible(false);
                    Time.stop();
                }
            }

        }
    }
}
