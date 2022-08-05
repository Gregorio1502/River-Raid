package Obstaculos;

import javax.swing.JLabel;

import Misc.Imagenes;

/**
 * Obstáculo en forma oblicuo
 * 
 * @author Gregorio y Francisco
 */
public class Brecha extends Obstaculos {

    /**
     * Construtor de la clase
     * 
     * @param Ancho  Limite de que tan ancho puede ser
     * @param Altura EL punto en y a partir de donde se empieza a generar el
     *               obstáculo
     */
    public Brecha(int Ancho, int Altura) {
        super(Ancho, Altura);
        IniciarComponentes();
        Posicionar();

    }

    /**
     * En esta función se agregan los componentes que conforman los obstáculos,
     * en este caso varios JLabels
     *
     * @see Obstaculos.Obstaculos#IniciarComponentes()
     */
    @Override
    public void IniciarComponentes() {
        Obs = new JLabel[20];
        for (int i = 0; i < Obs.length; i++) {
            Obs[i] = new JLabel();
            Obs[i].setIcon(Imagenes.getImg()[Imagenes.ObsP]);
        }
        for (int i = 0, j = 1, k = Obs.length; i < Obs.length; i += 2, j++, k--) {
            Obs[i].setSize(((Ancho / Obs.length + 10)) * j / 2, 30);
            Obs[i + 1].setSize(((Ancho / Obs.length + 10)) * k / 3, 30);
        }
    }

    /**
     * Posiciona los componentes del obstáculo
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

    /*
     * (non-Javadoc)
     * Sobre escribe la altura total para adaptarse a dos laterales ocupados
     * 
     * @see Obstaculos.Obstaculos#getAlturaT()
     */
    @Override
    public int getAlturaT() {
        return super.getAlturaT() / 2;
    }
}
