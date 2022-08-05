package Entidades;

import Misc.Imagenes;
import java.awt.event.*;

import javax.swing.Timer;

/**
 * Clase abstracta para los objetos que se mueve (Avion y Enemigos) Hereda de
 * Entidades
 *
 * @see Entidades
 * @author Gregorio y Francisco
 */
public abstract class Movible extends Entidades {

    /**
     *Contiene la cantidad de movimiento
     */
    protected float Velocidad;

    /**
     *Ancho del mapa, permitirá cambiar de dirección 
     */
    protected int Ancho,
            /**
             *
             */
            Alto;

    /**
     *Marca hacia donde se mueve el objeto
     */
    protected String Direccion;

    /**
     *Dice si el objeto se mueve o no
     */
    protected boolean Moverse;

    /**
     *Dice si el objeto ya escogió dirección 
     */
    protected boolean EscogerDir = false;
    /**
     * Marcador de secuencia de imágenes de explosiones al morir
     */
    private int N = 0;

    /**
     *
     */
    public Movible() {
        super();
    }

    /**
     * @param Moverse boolean para saber si se esta moviendo
     * @param Ancho
     * @param Alto
     */
    public Movible(boolean Moverse, int Ancho, int Alto) {
        super();
        this.Moverse = Moverse;
        this.Ancho = Ancho;
        this.Alto = Alto;
        Time = new Timer(100, new Explotar());
    }

    /**
     * Funcion para cambiar la dirrecion de los enemigos
     */
    public void CambiarDireccion() {
        if (EscogerDir) {
            if (Direccion.equals("Der")) {
                Direccion = "Izq";
            } else if (Direccion.equals("Izq")) {
                Direccion = "Der";
            }
        }
    }

    /**
     * identifica la colision entre enemigos y llama a la funcion
     * CambiarDireccion para cambiar su direccion
     *
     * @param Ene
     */
    public void ColisionEneg(Movible Ene[]) {
        boolean A = false;
        for (int i = 0; i < Ene.length && !A; i++) {
            if (Cuerpo.getBounds().intersects(Ene[i].getCuerpo().getBounds()) && !Cuerpo.equals(Ene[i].getCuerpo())
                    && Ene[i].isVivo() && isVivo()) {
                CambiarDireccion();
                A = true;
            }
        }
    }

    /**
     * @return el valor de la velocidad
     */
    public float getVelocidad() {
        return Velocidad;
    }

    /**
     * set para modificar el valor de la velocidad
     *
     * @param velocidad
     */
    public void setVelocidad(float velocidad) {
        Velocidad = velocidad;
    }

    /**
     * @return boolean para identificar el movimiento
     */
    public boolean isMoverse() {
        return Moverse;
    }

    /**
     * set para modificar el identificador de movimiento
     *
     * @param moverse
     */
    public void setMoverse(boolean moverse) {
        Moverse = moverse;
    }

    /**
     * Metodo abstracto que se implementara en las clases hijas
     */
    public abstract void Movimiento();

    /**
     * @return el ancho del label
     */
    public int getAncho() {
        return Ancho;
    }

    /**
     * @return el alto del label
     */
    public int getAlto() {
        return Alto;
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
                Cuerpo.setVisible(false);
            }
        }
    }
}
