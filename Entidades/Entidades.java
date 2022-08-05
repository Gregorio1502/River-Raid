package Entidades;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Clase abstracta para todos los objetos que pueden estar vivos
 *
 * @author Gregorio y Francisco
 */
public abstract class Entidades {

    /**
     *Jlabel para el cuerpo de todos las hijas de esta clase
     */
    protected JLabel Cuerpo;
    /**
     *Marca si el objeto esta vivo o no
     */
    private boolean Vivo;

    /**
     *Atributo que las hijas emplearan 
     */
    protected Timer Time;

    /**
     * @return timer
     */
    public Timer getTime() {
        return Time;
    }

    /**
     * Constructor de la clase
     */
    public Entidades() {
        Cuerpo = new JLabel();
        Vivo = true;
    }

    /**
     * @return el label que contiene la entidad
     */
    public JLabel getCuerpo() {
        return Cuerpo;
    }

    /**
     * set para modificar el label que contiene la entidad
     *
     * @param cuerpo
     */
    public void setCuerpo(JLabel cuerpo) {
        Cuerpo = cuerpo;
    }

    /**
     * @return boolean para identificar si la entidad esta viva
     */
    public boolean isVivo() {
        return Vivo;
    }

    /**
     * set para modificar el boolean para identificar si la entidad esta viva
     *
     * @param vivo
     */
    public void setVivo(boolean vivo) {
        Vivo = vivo;
    }

    /**
     *Cada hija tendrá su forma de obtener su imagen, dependiendo del tipo de instancia que esta tenga
     */
    public abstract void SetImg();

}
