package Escenario;

import Entidades.*;
import Obstaculos.*;
import java.awt.*;

import javax.swing.JLabel;

/**
 * Clase para implementar los elementos del juego Hereda de Escenarios
 *
 * @see Escenarios
 * @author Gregorio y Francisco
 */
public class MapaT1 extends Escenarios {

    /**
     * Constructor de la clase
     * 
     * @param Ancho       Ancho del mapa
     * @param Largo       Largo del mapa
     * @param NumEnemigos Numero de enemigos
     * @param NumFuels    Numero de Combustibles
     * @param NumObs      Numero de Obstáculos
     */
    public MapaT1(int Ancho, int Largo, int NumEnemigos, int NumFuels, int NumObs) {
        super(Ancho, Largo, NumEnemigos, NumFuels, NumObs);
    }

    /**
     * Función donde se invocan las demás funciones
     *
     * @see Escenario.Escenarios#IniciarComponents()
     */
    @Override
    public void IniciarComponents() {
        GenerarPuente();
        GenerarObstaculos();
        GenerarEnemigos();
        GenerarFuels();
    }

    /**
     * Genera los componentes del puente y lo agrega al mapa
     */
    private void GenerarPuente() {
        polly = new Puente(getWidth(), 0);
        for (int i = 0; i < polly.getExp().length; i++) {
            add(polly.getExp()[i]);
        }
        for (int i = 0; i < polly.getObs().length; i++) {
            add(polly.getObs()[i]);
        }
    }

    /**
     * Genera los combustibles y los agrega al mapa
     */
    private void GenerarFuels() {
        for (int i = 0; i < Fuels.length; i++) {
            Fuels[i] = new Fuel();
            add(Fuels[i].getCuerpo());
        }
    }

    /**
     * Genera los enemigos haciendo que unos se puedan mover y otros no, solo 1/3 de
     * estos no se moverán que serian barcos y helicópteros
     */
    private void GenerarEnemigos() {
        for (int i = 0; i < Enemigos.length; i++) {
            int T1 = Rd.nextInt(2);
            int T2 = Rd.nextInt(10);
            if (T1 == 0) {
                if (T2 >= 7) {
                    Enemigos[i] = new Barco(false);
                } else {
                    Enemigos[i] = new Barco(true, getWidth(), getHeight());
                }
            } else if (T1 == 1) {
                if (T2 >= 7) {
                    Enemigos[i] = new Helicoptero(false);
                } else {
                    Enemigos[i] = new Helicoptero(true, getWidth(), getHeight());
                }
            }
            add(Enemigos[i].getCuerpo());
        }
    }

    /**
     * Genera los obstáculos del juego y evita que se solapen entre ellos
     */
    private void GenerarObstaculos() {
        for (int j = 0; j < Obstaculos.length; j++) {
            boolean Colision;
            int T = Rd.nextInt(3);
            int Cambio = 10;
            int intento = 0;
            do {
                if (intento > Cambio) {
                    T = Rd.nextInt(3);
                }
                if (T == 0) {
                    Obstaculos[j] = new Canal(getWidth(), Rd.nextInt(getHeight()), Rd.nextInt(getHeight()/10)+300);
                } else if (T == 1) {
                    Obstaculos[j] = new Brecha(getWidth(), Rd.nextInt(getHeight()));
                } else if (T == 2) {
                    Obstaculos[j] = new Embudo(getWidth(), Rd.nextInt(getHeight()));
                }
                Colision = false;

                Colision = Obstaculos[j].getPAltura() - Obstaculos[j].getAlturaT() < polly.getAlturaT()
                        || Obstaculos[j].getPAltura() + Obstaculos[j].getAlturaT() > getHeight();

                if (!Colision) {
                    for (int i = 0; i < j && !Colision; i++) {
                        if (Obstaculos[i].Colision(Obstaculos[j].getObs())) {
                            Colision = true;
                            intento++;
                        }
                    }
                }
               
            } while (Colision);
            for (int i = 0; i < Obstaculos[j].getObs().length; i++) {
                add(Obstaculos[j].getObs()[i]);
            }
        }
    }

    /**
     * Funcion para reposicionar los objetos y enemigos en caso de que se
     * generen superpuestos
     *
     * @param Ent Entidad a Reposicionar
     */
    private void RePos(Entidades Ent) {
        boolean Colicion;
        do {
            Colicion = false;
            int x = Rd.nextInt(getWidth() - Ent.getCuerpo().getWidth());
            int y = Rd.nextInt(getHeight() - Ent.getCuerpo().getHeight() - 10);
            Ent.getCuerpo().setLocation(x, y);
            if (Ent.getCuerpo().getY() + Ent.getCuerpo().getHeight() * 2 > getHeight()) {
                Colicion = true;
            }
            for (int i = 0; i < Enemigos.length && !Colicion; i++) {
                if (Ent.getCuerpo().getBounds().intersects(Enemigos[i].getCuerpo().getBounds())
                        && !Ent.equals(Enemigos[i])) {
                    Colicion = true;
                }
                if (i < Fuels.length) {
                    if (Ent.getCuerpo().getBounds().intersects(Fuels[i].getCuerpo().getBounds())
                            && !Ent.equals(Fuels[i])) {
                        Colicion = true;
                    }
                }
            }
            for (int j = 0; j < Obstaculos.length; j++) {
                if (Obstaculos[j].ColisionEnemigo(Ent.getCuerpo())) {
                    Colicion = true;
                }
            }
        } while (Colicion);

    }

    /**
     * Funcion para posicionar los objetos y enemigos
     *
     * @see Escenario.Escenarios#Posicionar()
     */
    @Override
    public void Posicionar() {
        for (int i = 0; i < Enemigos.length; i++) {
            int x = Rd.nextInt(getWidth() - Enemigos[i].getCuerpo().getWidth());
            int y = Rd.nextInt(getHeight()) + polly.getAlturaT() + 5;
            Enemigos[i].getCuerpo().setLocation(x, y);
        }

        for (int i = 0; i < Fuels.length; i++) {
            int x = Rd.nextInt(getWidth() - Fuels[i].getCuerpo().getWidth() - 5);
            int y = Rd.nextInt(getHeight() - Fuels[i].getCuerpo().getHeight()) + polly.getAlturaT() + 5;
            Fuels[i].getCuerpo().setLocation(x, y);
        }

        for (int i = 0; i < Enemigos.length; i++) {
            if (Enemigos[i].getCuerpo().getY() + Enemigos[i].getCuerpo().getHeight() * 2 > getHeight()) {
                RePos(Enemigos[i]);
            }
            for (int j = 0; j < Enemigos.length; j++) {
                if (i != j) {
                    if (Enemigos[i].getCuerpo().getBounds().intersects(Enemigos[j].getCuerpo().getBounds())) {
                        RePos(Enemigos[j]);
                    }
                }
            }
            for (int j = 0; j < Fuels.length; j++) {
                if (Enemigos[i].getCuerpo().getBounds().intersects(Fuels[j].getCuerpo().getBounds())) {
                    RePos(Fuels[j]);
                }
            }
            for (int j = 0; j < Obstaculos.length; j++) {
                for (int j2 = 0; j2 < Obstaculos[j].getObs().length; j2++) {
                    if (Enemigos[i].getCuerpo().getBounds().intersects(Obstaculos[j].getObs()[j2].getBounds())) {
                        RePos(Enemigos[i]);
                    }
                }
            }
        }
        for (int i = 0; i < Fuels.length; i++) {
            if (Fuels[i].getCuerpo().getY() + Fuels[i].getCuerpo().getHeight() * 2 > getHeight()) {
                RePos(Fuels[i]);
            }
            for (int j = 0; j < Obstaculos.length; j++) {
                if (Obstaculos[j].ColisionEnemigo(Fuels[i].getCuerpo())) {
                    RePos(Fuels[i]);
                }
            }
        }
    }

}
