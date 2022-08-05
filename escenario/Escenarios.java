package escenario;

import javax.swing.JPanel;
import java.util.Random;
import Entidades.*;
import Obstaculos.Obstaculos;
import Obstaculos.Puente;

/**
 * Clase abstracta con las bases en donde se agregan los objetos que conforman el juego
 *
 * @author Gregorio y Francisco
 */
public abstract class Escenarios extends JPanel {

    /**
     *Vector de Objetos movibles que serán Enemigos
     */
    protected Movible Enemigos[];

    /**
     *Vector de Combustibles
     */
    protected Fuel Fuels[];

    /**
     *Vector de obstaculos
     */
    protected Obstaculos Obstaculos[];

    /**
     *Puente para el final del mapa
     */
    protected Puente polly;

    /**
     *Atributo que ayudara a diversificar mapas
     */
    protected Random Rd = new Random();

    /**
     * Constructor de la clase
     * @param Ancho Ancho del mapa
     * @param Largo Largo del mapa
     * @param NumEnemigos Numero de enemigos
     * @param NumFuels Numero de Combustibles
     * @param Obstaculos Numero de Obstaculos
     */
    public Escenarios(int Ancho, int Largo, int NumEnemigos, int NumFuels, int Obstaculos) {
        setOpaque(false);
        setSize(Ancho, Largo);
        Enemigos = new Movible[NumEnemigos];
        Fuels = new Fuel[NumFuels];
        this.Obstaculos = new Obstaculos[Obstaculos];
        setLayout(null);
        IniciarComponents();
        Posicionar();
    }

    /**
     * Metodo abstracto que se implementara en las clases hijas
     */
    public abstract void IniciarComponents();

    /**
     * Metodo abstracto que se implementara en las clases hijas
     */
    public abstract void Posicionar();

    /**
     * @return los enemigos de la clase movible
     * @see Movible
     */
    public Movible[] getEnemigos() {
        return Enemigos;
    }

    /**
     * @return Objetos de la clase Fuel
     * @see Fuel
     */
    public Fuel[] getFuels() {
        return Fuels;
    }

    /**
     * @return objetos de la clase obstaculos
     * @see Obstaculos
     */
    public Obstaculos[] getObstaculos() {
        return Obstaculos;
    }

    /**
     * @return objetos de la clase puente hija de obstaculos
     * @see Obstaculos.Puente
     */
    public Puente getPolly() {
        return polly;
    }

}
