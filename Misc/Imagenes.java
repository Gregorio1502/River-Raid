package Misc;

import java.net.URL;

import javax.swing.ImageIcon;

/**
 * En esta clase estan todas las direcciones de las imagenes, facilitando su
 * implementacion en las otras clases
 *
 * @author Gregorio y Francisco
 */
public class Imagenes {

    private URL ImgURL[] = new URL[17];
    private static ImageIcon Img[];
    private static ImageIcon Exp[];

    /**
     *Imagen de la vida
     */
    public final static int ImgLlaves = 0;

    /**
     *Imagen del combustible 
     */
    public final static int ImgFuel = 1;

    /**
     *Imagen del fondo del menu
     */
    public final static int ImgFondo = 2;

    /**
     *Imagen del Avion
     */
    public final static int ImgAvion = 3;

    /**
     *Imagen del Helicóptero que se mueve a la Derecha
     */
    public final static int ImgHelicopteroDer = 4;

    /**
     *Imagen del Helicóptero que se mueve a la Izquierda
     */
    public final static int ImgHelicopteroIzq = 5;

    /**
     *Imagen del Barco que se mueve a la Izquierda
     */
    public final static int ImgBarcoDer = 6;

    /**
     *Imagen del Barco que se mueve a la Izquierda
     */
    public final static int ImgBarcoIzq = 7;

    /**
     *Imagen del combustible
     */
    public final static int ImgFuelR = 8;

    /**
     *Imagen del fondo de agua del juego
     */
    public final static int ImgAgua = 9;

    /**
     *Imagen del lateral izquierdo
     */
    public final static int LateralIzq = 10;

    /**
     *Imagen del lateral derecho
     */
    public final static int LateralDer = 11;

    /**
     *Imagen de la parte Destructible del puente
     */
    public final static int Puente = 12;

    /**
     *Imagen del los laterales del puente 
     */
    public final static int LateralPuente = 13;


    /**
     *Imagen de un tipo de obstáculo
     */
    public final static int ObsP = 14;

    /**
     *Imagen Para los de obstáculo Grandes
     */
    public final static int SeaStone = 15;

    /**
     *Imagen para el fondo de los indicadores
     */
    public final static int FondoInd = 16;

    /**
     *Constructor de la Clase
     */
    public Imagenes() {
        DireccionImg();
        CargarImgs();
    }

    /**
     * En esta función se cargan las direcciones de las imágenes y se enlazan con
     * valores estáticos para facilitar la implementación
     */
    public void DireccionImg() {
        ImgURL[ImgLlaves] = getClass().getResource("/Img/Llavez.png");
        ImgURL[ImgFuel] = getClass().getResource("/Img/Fuel.png");
        ImgURL[ImgFondo] = getClass().getResource("/Img/Fondo1.jpg");
        ImgURL[ImgAvion] = getClass().getResource("/Img/Avion.png");
        ImgURL[ImgHelicopteroDer] = getClass().getResource("/Img/HelicopteroDer.png");
        ImgURL[ImgHelicopteroIzq] = getClass().getResource("/Img/HelicopteroIzq.png");
        ImgURL[ImgBarcoDer] = getClass().getResource("/Img/BarcoDer.png");
        ImgURL[ImgBarcoIzq] = getClass().getResource("/Img/BarcoIzq.png");
        ImgURL[ImgFuelR] = getClass().getResource("/Img/FuelR.png");
        ImgURL[ImgAgua] = getClass().getResource("/Img/FondoAgua.jpg");
        ImgURL[SeaStone] = getClass().getResource("/Img/StoneSea.png");
        ImgURL[FondoInd] = getClass().getResource("/Img/FondoInd.png");
        ImgURL[Puente] = getClass().getResource("/Img/PuenteD.png");
        ImgURL[LateralPuente] = getClass().getResource("/Img/LateralesPuenteD.png");
        ImgURL[LateralIzq] = getClass().getResource("/Img/LateralIzq.png");
        ImgURL[LateralDer] = getClass().getResource("/Img/LateralDer.png");
        ImgURL[ObsP] = getClass().getResource("/Img/ObstaculoP.png");

    }

    /**
     * Función para cargar las imágenes y también para la secuencia de imágenes
     * de las explosiones
     */
    public void CargarImgs() {
        Img = new ImageIcon[ImgURL.length];
        for (int i = 0; i < Img.length; i++) {
            Img[i] = new ImageIcon(ImgURL[i]);
        }
        Exp = new ImageIcon[7];
        for (int i = 0; i < Exp.length; i++) {
            Exp[i] = new ImageIcon(getClass().getResource("/Img/Explociones/" + (i + 1) + ".png"));
        }
    }

    /**
     * Devuelve el vector de imágenes para ser implementado
     * @return una imagen
     */
    public static ImageIcon[] getImg() {
        return Img;
    }

    /**
     * Devuelve el vector de imágenes para ser implementado en las explosiones 
     * @return una explosion
     */
    public static ImageIcon[] getExp() {
        return Exp;
    }

}
