package Escenario;

import java.awt.Color;
/**
 * Clase para el indicador del combustible
 *
 * @author Gregorio y Francisco
 */
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase que sirve de base para el indicador de combustible
 * 
 * @author Gregorio
 */
public class Fuel_Indicador extends JPanel {

    /**
     * Cuerpo del indicador
     */
    public static JLabel Ind;

    /**
     * Constructor de la clase
     * 
     * @param width  Ancho del indicador
     * @param height Alto del indicador
     */
    public Fuel_Indicador(int width, int height) {
        setSize(width + 15, height);
        setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
        setOpaque(false);
        setLayout(null);

        Ind = new JLabel();
        Ind.setOpaque(true);
        Ind.setBackground(Color.RED);
        Ind.setSize(getWidth() - 15, getHeight() - 10);
        Ind.setLocation(5, 5);
        add(Ind);
    }

}
