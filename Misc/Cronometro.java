package Misc;

/**
 * Clase para el cronometro del juego
 *
 * @author Gregorio y Francisco
 */
public class Cronometro {

    private int segundo;
    private int minuto;
    private String crono;

    /**
     *Constructor de la clase
     */
    public Cronometro() {
        segundo = 0;
        minuto = 0;
        crono = "00:00";
    }

    /**
     * Función para hacer el reloj diferenciando los minutos y segundos
     */
    public void Time() {
        crono = "";
        if (minuto < 10) {
            crono = "0" + Integer.valueOf(minuto) + ":";
        }
        if (minuto >= 10) {
            crono = Integer.valueOf(minuto) + ":";
        }
        if (segundo < 10) {
            crono += "0" + Integer.valueOf(segundo);
        }
        if (segundo >= 10) {
            crono += Integer.valueOf(segundo);
        }
        if (segundo == 59) {
            minuto++;
            segundo = -1;
        }

    }

    /**
     * Incrementa los segundos
     */
    public void segUp() {
        segundo++;
        Time();
    }

    /**
     * @return la cadena de texto que contiene el reloj
     */
    public String getCrono() {
        return crono;
    }

    /**
     * Resetea el reloj
     */
    public void reset() {
        crono = "00:00";
        segundo = 0;
        minuto = 0;
    }

}
