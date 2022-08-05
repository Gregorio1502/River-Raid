package Entidades;

import Misc.Imagenes;
import java.awt.event.*;

import javax.swing.Timer;

/**
 * Objeto que al pasar sobre el rellenara el combustible del avion
 * 
 * @author Gregorio y Francisco
 */
public class Fuel extends Entidades {

    /**
     * Marcador de secuencia de imágenes de explosiones al morir
     */
    private int N = 0;

    /**
     * Constructor de la clase
     */
    public Fuel() {
        Time = new Timer(100, new Explotar());
        SetImg();
    }

    /**
     * Agrega la imagen del combustible
     *
     * @see Entidades.Entidades#SetImg()
     */
    @Override
    public void SetImg() {
        Cuerpo.setIcon(Imagenes.getImg()[Imagenes.ImgFuelR]);
        Cuerpo.setSize(Imagenes.getImg()[Imagenes.ImgFuelR].getIconWidth(),
                Imagenes.getImg()[Imagenes.ImgFuelR].getIconHeight());

    }

    /**
     * Secuencia de explosiones
     */
    public class Explotar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

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
