package Obstaculos;

import javax.swing.JLabel;

import Misc.Imagenes;

/**
 * Obstáculo de forma cerrada y alargada
 * 
 * @author Gregorio y Francisco
 */
public class Canal extends Obstaculos {

    /**
     * Dicta que tan largo es el JLabel mas Largo
     */
    private int Largo;

    /**
     * Constructor de la clase
     * 
     * @param Ancho  Limite de que tan ancho puede ser
     * @param Altura EL punto en y a partir de donde se empieza a generar el
     *               obstáculo
     * @param largo  Dicta que tan largo es el JLabel mas Largo
     */
    public Canal(int Ancho, int Altura, int largo) {
        super(Ancho, Altura);
        Largo = largo;
        IniciarComponentes();
        Posicionar();
    }

    /**
     * En esta funcion se agregan los componentes que conforman los obstaculos,
     * en este caso varios rectangulos
     *
     * @see Obstaculos.Obstaculos#IniciarComponentes()
     */
    @Override
    public void IniciarComponentes() {
        Obs = new JLabel[24];
        for (int i = 0; i < Obs.length; i++) {
            Obs[i] = new JLabel();
            Obs[i].setIcon(Imagenes.getImg()[Imagenes.ObsP]);
        }

            for (int i = 0, j = 1; i < Obs.length - 2; i += 2) {
                Obs[i].setSize(((int) ((Ancho / (Obs.length)) * j)), 30);
                Obs[i + 1].setSize(((int) ((Ancho / (Obs.length)) * j)), 30);

                if (i <= (Obs.length - 2) / 2) {
                    j++;
                } else {
                    j--;
                }
            }
            for (int i = Obs.length - 2; i < Obs.length; i++) {

                Obs[i].setSize(((Ancho / (Obs.length + 10)) * Obs.length / 2), Largo);
                Obs[i].setIcon(Imagenes.getImg()[Imagenes.SeaStone]);
            }
    }

    /**
     * Posiciona los componentes del obstáculo
     *
     * @see Obstaculos.Obstaculos#Posicionar()
     */
    @Override
    public void Posicionar() {
            for (int i = 0, j = 0, k = -1; i < Obs.length; i += 2) {
                if ((i) <= (Obs.length - 2) / 2) {
                    Obs[i].setLocation(0, Altura + Obs[i].getHeight() * j);
                    Obs[i + 1].setLocation(Ancho - Obs[i + 1].getWidth(), Altura
                            + Obs[i].getHeight() * j);
                    j++;
                } else {
                    if (k == -1) {
                        Obs[Obs.length - 2].setLocation(0, Obs[i - 1].getY() + Obs[i - 1].getHeight());
                        Obs[Obs.length - 1].setLocation(Ancho - Obs[Obs.length - 1].getWidth(),
                                Obs[i - 1].getY() + Obs[i - 1].getHeight());
                        k++;
                    } else {
                        Obs[i - 2].setLocation(0,
                                Obs[Obs.length - 1].getY() + Obs[Obs.length - 1].getHeight() + Obs[i].getHeight() * k);
                        Obs[i + 1 - 2].setLocation(Ancho - Obs[i + 1 - 2].getWidth(),
                                Obs[Obs.length - 1].getY() + Obs[Obs.length - 1].getHeight() + Obs[i].getHeight() * k);
                        k++;
                    }

                }
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
