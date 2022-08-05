package Entidades;

import java.awt.event.*;

import javax.swing.Timer;

import Misc.Imagenes;

/**
 * 
 * Clase del Cuerpo del Avion asi como varias variables para su funcionamiento
 * 
 * @author Gregorio y Francisco
 */
public class Avion extends Movible implements ActionListener {

    /**
     * Contiene la aceleración del jugador
     */
    private int Aceleracion;
    /**
     * Lleva al jugador hacia la derecha
     */
    private Timer Der = new Timer(10, this);
    /**
     * Lleva al jugador hacia la izquierda
     */
    private Timer Izq = new Timer(10, this);
    /**
     * Marca si se esta moviendo hacia la derecha
     */
    private boolean AceDer = false;
    /**
     * Marca si se esta moviendo hacia la izquierda
     */
    private boolean AceIzq = false;
    /**
     * Marca si se disparo
     */
    private boolean Disparar = false;
    /**
     * Contador de Vidas
     */
    private int Vidas;
    private double Fuel;
    private int N = 0;

    /**
     * @param Ancho
     * @param Alto
     */
    public Avion(int Ancho, int Alto) {
        super(true, Ancho, Alto);
        SetImg();
        Posicionar();
        Fuel = 100;
        Vidas = 3;
        Aceleracion = 1;
        Time = new Timer(100, new Explotar());
    }

    /**
     * Cantidad de pixeles que se mueve
     *
     * @see Entidades.Movible#Movimiento()
     */
    @Override
    public void Movimiento() {
        if (Velocidad < 5) {
            Velocidad += 1;
        }

    }

    /**
     * Agregar la imagen del avion
     * asi como también le da tamaño a la Jlabel
     *
     * @see Entidades.Entidades#SetImg()
     */
    @Override
    public void SetImg() {
        Cuerpo.setIcon(Imagenes.getImg()[Imagenes.ImgAvion]);
        Cuerpo.setSize(Imagenes.getImg()[Imagenes.ImgAvion].getIconWidth(),
                Imagenes.getImg()[Imagenes.ImgAvion].getIconHeight());
    }

    /**
     * @return aceleracion
     */
    public int getAceleracion() {
        return Aceleracion;
    }

    /**
     * set para aceleracion
     *
     * @param aceleracion
     */
    public void setAceleracion(int aceleracion) {
        Aceleracion = aceleracion;
    }

    /**
     * Posiciona el avion en el centro de la ventana y en su posición de juego
     */
    public void Posicionar() {
        Cuerpo.setLocation((int) (Ancho / 2 - Cuerpo.getWidth() * 0.5), 300);
    }

    /**
     * Metodo para el movimiento del avion,solo lateral
     *
     * @see
     *      java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Der) && Moverse) {
            Cuerpo.setLocation((int) (Cuerpo.getX() + Velocidad), Cuerpo.getY());
        } else if (e.getSource().equals(Izq) && Moverse) {
            Cuerpo.setLocation((int) (Cuerpo.getX() - Velocidad), Cuerpo.getY());
        }

    }

    /**
     * @return timer derecho
     */
    public Timer getDer() {
        return Der;
    }

    /**
     * @return timer izquierdo
     */
    public Timer getIzq() {
        return Izq;
    }

    /**
     * @return boolean para identificar aceleracion
     */
    public boolean isAceDer() {
        return AceDer;
    }

    /**
     * @return boolean para identificar aceleracion
     */
    public boolean isAceIzq() {
        return AceIzq;
    }

    /**
     * set para cambiar el valor de aceleracion
     *
     * @param aceDer
     */
    public void setAceDer(boolean aceDer) {
        AceDer = aceDer;
    }

    /**
     * set para cambiar el valor de aceleracion
     *
     * @param aceIzq
     */
    public void setAceIzq(boolean aceIzq) {
        AceIzq = aceIzq;
    }

    /**
     * @return boolean para identificar disparo
     */
    public boolean isDisparar() {
        return Disparar;
    }

    /**
     * set para cambiar el valor del identificador disparo
     *
     * @param disparar
     */
    public void setDisparar(boolean disparar) {
        Disparar = disparar;
    }

    /**
     *
     * @return cantidad de vidas
     */
    public int getVidas() {
        return Vidas;
    }

    /**
     * set para cambiar la cantidad de vidas
     *
     * @param vidas
     */
    public void setVidas(int vidas) {
        Vidas = vidas;
    }

    /**
     * set para cambiar el valor del combustible
     *
     * @param fuel
     */
    public void setFuel(double fuel) {
        Fuel = fuel;
    }

    /**
     * @return double para el valor del combustible
     */
    public double getFuel() {
        return Fuel;
    }

    /**
     * Metodo para la secuencia de explosiones
     */
    public class Explotar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Moverse = false;
            Cuerpo.setIcon(Imagenes.getExp()[N]);
            N++;
            if (N > 6) {
                Time.stop();
                N = 0;

            }
        }
    }

}
