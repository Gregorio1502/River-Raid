package Entidades;

import java.awt.*;

/**
 * Clase para el cuerpo y propiedades de la bola
 * 
 * @author Gregorio y Francisco
 */
public class Balas extends Movible {

    /**
     * Constructor de la clase
     */
    public Balas() {
        SetImg();
    }

    /**
     * En este metodo se le dan valores al objeto para ingresarlo al juego
     *
     * @see Entidades.Entidades#SetImg()
     */
    @Override
    public void SetImg() {
        Cuerpo.setSize(7, 7);
        Cuerpo.setOpaque(true);
        Cuerpo.setBackground(Color.yellow);
        Velocidad = 15;
    }

    /**
     * Funcion para el movimiento de la bala, solo se modifica la Y
     *
     * @see Entidades.Movible#Movimiento()
     */
    @Override
    public void Movimiento() {
        Cuerpo.setLocation(Cuerpo.getX(), Cuerpo.getY() - (int) Velocidad);
    }

}
