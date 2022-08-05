package Obstaculos;

import javax.swing.JLabel;

import Misc.Imagenes;

/**
 * Obstáculo de forma cerrada y corta
 * @author Gregorio y Francisco
 */
public class Embudo extends Obstaculos {

    /**
     * Constructor de la clase
     * @param Ancho Limite de que tan ancho puede ser
     * @param largo EL punto en y a partir de donde se empieza a generar el obstáculo
     */
    public Embudo(int Ancho, int largo) {
        super(Ancho, largo);
        IniciarComponentes();
        Posicionar();
        Tipo = 0;
    }

    /**
     * En esta función se agregan los componentes que conforman los obstáculos,
     * en este caso varios JLabels
     *
     * @see Obstaculos.Obstaculos#IniciarComponentes()
     */
    @Override
    public void IniciarComponentes() {
        Obs = new JLabel[14];
        for (int i = 0; i < Obs.length; i++) {
            Obs[i] = new JLabel();
            Obs[i].setIcon(Imagenes.getImg()[Imagenes.ObsP]);
            Obs[i].setSize(((Ancho / (Obs.length + 5))) * i / 2, 30);
        }
    }

    /**
     * Funcion para posicionar el obstaculo
     *
     * @see Obstaculos.Obstaculos#Posicionar()
     */
    @Override
    public void Posicionar() {
        for (int i = 0, j = 1; i < Obs.length; i += 2, j++) {
            Obs[i].setLocation(0, Altura + Obs[i].getHeight() * j);
            Obs[i + 1].setLocation(Ancho - Obs[i + 1].getWidth(), Altura + Obs[i + 1].getHeight() * j);
        }
    }

}
