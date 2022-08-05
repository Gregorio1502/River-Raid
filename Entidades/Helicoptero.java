package Entidades;

import Misc.Imagenes;

/**
 * Clase con las Propiedades y Modelo de uno de losEnemigo del juego,
 * Se mueve mas rápido que el Barco
 * 
 * @author Gregorio y Francisco
 */
public class Helicoptero extends Movible {

    /**
     * Constructor si el Enemigo no se va a mover
     * 
     * @param Moverse
     */
    public Helicoptero(boolean Moverse) {
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
    public Helicoptero(boolean Moverse, int Ancho, int Alto) {
        super(Moverse, Ancho, Alto);
        Velocidad = 3;
        Movimiento();
        SetImg();
    }

    /**
     * Funcion para el mivimiento del helicoptero
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
                if (Cuerpo.getX() < (Ancho - Cuerpo.getWidth())) {
                    Cuerpo.setLocation(Cuerpo.getX() + (int) Velocidad, Cuerpo.getY());
                } else {
                    CambiarDireccion();
                }
            } else if (Direccion.equals("Izq")) {
                if (Cuerpo.getX() > 0) {
                    Cuerpo.setLocation(Cuerpo.getX() - (int) Velocidad, Cuerpo.getY());
                } else {
                    CambiarDireccion();
                }
            }
        }

    }

    /**
     * Agrega imagen al helicoptero, valora la direccion y elije la imagen
     *
     * @see Entidades.Entidades#SetImg()
     */
    @Override
    public void SetImg() {
        if (Direccion.equals("Der")) {
            Cuerpo.setIcon(Imagenes.getImg()[Imagenes.ImgHelicopteroDer]);
            Cuerpo.setSize(Imagenes.getImg()[Imagenes.ImgHelicopteroDer].getIconWidth(),
                    Imagenes.getImg()[Imagenes.ImgHelicopteroDer].getIconHeight());
        } else if (Direccion.equals("Izq")) {
            Cuerpo.setIcon(Imagenes.getImg()[Imagenes.ImgHelicopteroIzq]);
            Cuerpo.setSize(Imagenes.getImg()[Imagenes.ImgHelicopteroIzq].getIconWidth(),
                    Imagenes.getImg()[Imagenes.ImgHelicopteroIzq].getIconHeight());
        }

    }

    /**
     * Sobreescibe el metodo CambiarDireccion y le agrega el cambio de imagen
     *
     * @see Entidades.Movible#CambiarDireccion()
     */
    @Override
    public void CambiarDireccion() {
        super.CambiarDireccion();
        if (isVivo() && isMoverse()) {
            if (Direccion.equals("Der")) {
                Cuerpo.setIcon(Imagenes.getImg()[Imagenes.ImgHelicopteroDer]);
            } else if (Direccion.equals("Izq")) {
                Cuerpo.setIcon(Imagenes.getImg()[Imagenes.ImgHelicopteroIzq]);
            }
        }
    }

}
