package Entidades;

import Misc.Imagenes;

/**
 * Clase con las Propiedades y Modelo de uno de los enemigos
 * este se mueve mas lento que el helicóptero
 * 
 * @author Gregorio y Francisco
 */
public class Barco extends Movible {

    /**
     * Constructor si el Enemigo no se va a mover
     * 
     * @param Moverse
     */
    public Barco(boolean Moverse) {
        super(Moverse, 0, 0);
        Velocidad = 0;
        Movimiento();
        SetImg();
    }

    /**
     * Constructor De un enemigo que si se va a mover
     * 
     * @param Moverse
     * @param Ancho
     * @param Alto
     */
    public Barco(boolean Moverse, int Ancho, int Alto) {
        super(Moverse, Ancho, Alto);
        Velocidad = 1;
        Movimiento();
        SetImg();

    }

    /**
     * Funcion para el movimiento del barco, derecha e izquierda
     *
     * @see Entidades.Movible#Movimiento()
     */
    @Override
    public void Movimiento() {
        if (!EscogerDir) {
            if (Cuerpo.getX() <= Ancho / 2) {
                Direccion = "Der";
            } else {
                Direccion = "Izq";
            }
            EscogerDir = true;
        }
        if (Moverse) {
            if (Direccion.equals("Der")) {
                if (Cuerpo.getX() <= (Ancho - Cuerpo.getWidth())) {
                    Cuerpo.setLocation(Cuerpo.getX() + (int) Velocidad, Cuerpo.getY());
                } else {
                    CambiarDireccion();
                }
            } else if (Direccion.equals("Izq")) {
                if (Cuerpo.getX() >= 0) {
                    Cuerpo.setLocation(Cuerpo.getX() - (int) Velocidad, Cuerpo.getY());
                } else {
                    CambiarDireccion();
                }
            }
        }

    }

    /**
     * Agrega la imagen dependiendo de la direccion
     *
     * @see Entidades.Entidades#SetImg()
     */
    @Override
    public void SetImg() {
        if (Direccion.equals("Der")) {
            Cuerpo.setIcon(Imagenes.getImg()[Imagenes.ImgBarcoDer]);
            Cuerpo.setSize(Imagenes.getImg()[Imagenes.ImgBarcoDer].getIconWidth(),
                    Imagenes.getImg()[Imagenes.ImgBarcoDer].getIconHeight());
        } else if (Direccion.equals("Izq")) {
            Cuerpo.setIcon(Imagenes.getImg()[Imagenes.ImgBarcoIzq]);
            Cuerpo.setSize(Imagenes.getImg()[Imagenes.ImgBarcoIzq].getIconWidth(),
                    Imagenes.getImg()[Imagenes.ImgBarcoIzq].getIconHeight());
        }
    }

    /**
     * Funcion para cambiar la imagen de acuerdo a la direccion
     *
     * @see Entidades.Movible#CambiarDireccion()
     */
    @Override
    public void CambiarDireccion() {
        super.CambiarDireccion();
        if (Direccion.equals("Der")) {
            Cuerpo.setIcon(Imagenes.getImg()[Imagenes.ImgBarcoDer]);
        } else if (Direccion.equals("Izq")) {
            Cuerpo.setIcon(Imagenes.getImg()[Imagenes.ImgBarcoIzq]);
        }
    }

}
