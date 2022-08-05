package Obstaculos;

import javax.swing.JLabel;

/**
 * Clase abstracta cuyos atributos y métodos se utilizaran en la generación de
 * obstáculos
 *
 * @author Gregorio y Francisco
 */
public abstract class Obstaculos {
    /**
     * Componentes base de los obstáculos
     */
    protected JLabel Obs[];

    /**
     * Limite de que tan ancho puede ser
     */
    protected int Ancho,
            /**
             * EL punto en y a partir de donde se empieza a generar el obstáculo
             */
            Altura;

    /**
     * @param Ancho  Limite de que tan ancho puede ser
     * @param Altura EL punto en y a partir de donde se empieza a generar el
     *               obstáculo
     */
    public Obstaculos(int Ancho, int Altura) {
        this.Ancho = Ancho;
        this.Altura = Altura;
    }

    /**
     * Método abstracto que iniciara los JLabel
     */
    public abstract void IniciarComponentes();

    /**
     * Método abstracto que Posicionara los JLabels 
     */
    public abstract void Posicionar();

    /**
     * @return el vector de etiquetas los cuales componen al obstáculo
     */
    public JLabel[] getObs() {
        return Obs;
    }

    /**
     * @return la altura baje desde donde se colocaron los JLabels
     */
    public int getPAltura() {
        return Altura;
    }

    /**
     * @return T que es el espacio que ocupa en Y el obstaculo conformado por
     *         varios rectangulos
     */
    public int getAlturaT() {
        int T = 0;
        for (int i = 0; i < Obs.length; i++) {
            T += Obs[i].getHeight();
        }
        return T;
    }

    /**
     * Función para calcular la colisión entre obstáculos para evitar
     * superposición
     *
     * @param Obs1 Componentes de otros obstáculos
     * @return boolean que indica si colisionan los obstáculos
     */
    public boolean Colision(JLabel Obs1[]) {
        for (int i = 0; i < Obs.length; i++) {
            for (int j = 0; j < Obs1.length; j++) {
                if (Obs[i].getBounds().intersects(Obs1[j].getBounds())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Función para evitar superposición con los enemigos al ser generados
     *
     * @param Obs1 Cuerpo de entidad a comparar
     * @return boolean que indica superposición con los enemigos
     */
    public boolean ColisionEnemigo(JLabel Obs1) {
        for (int i = 0; i < Obs.length; i++) {
            if (Obs[i].getBounds().intersects(Obs1.getBounds())) {
                return true;
            }
        }
        return false;
    }

}
