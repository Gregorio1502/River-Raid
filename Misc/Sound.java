package Misc;

import java.net.URL;

import javax.sound.sampled.*;

/**
 * En esta clase están todos los sonidos necesarios en el proyecto
 *
 * @author Gregorio y Francisco
 */
public class Sound {

    Clip clipEfects;
    Clip clipMusicaFondo;
    Clip clipMusicaMenu;
    AudioInputStream AudioFondo;
    AudioInputStream AudioMenu;
    AudioInputStream AudioEfects;
    URL soundURL[] = new URL[10];
    int e;

    /**
     *Música de fondo para el juego
     */
    public static final int SoundFondo = 0;

    /**
     *Efecto de sonido al morir
     */
    public static final int SoundMorir = 1;

    /**
     *Efecto de sonido al Destruir un enemigo
     */
    public static final int SoundDestruir = 2;

    /**
     *Música de fondo para el menu
     */
    public static final int SoundMenu = 3;

    /**
     * En esta función se enlazan los atributos estáticos con las direcciones de
     * los archivos de sonido
     */
    public Sound() {
        soundURL[SoundFondo] = getClass().getResource("/Sounds/fondo.wav");
        soundURL[SoundMorir] = getClass().getResource("/Sounds/GameOver.wav");
        soundURL[SoundDestruir] = getClass().getResource("/Sounds/Disparar.wav");
        soundURL[SoundMenu] = getClass().getResource("/Sounds/menu.wav");
        try {
            AudioFondo = AudioSystem.getAudioInputStream(soundURL[SoundFondo]);
            clipMusicaFondo = AudioSystem.getClip();
            clipMusicaFondo.open(AudioFondo);

            AudioMenu = AudioSystem.getAudioInputStream(soundURL[SoundMenu]);
            clipMusicaMenu = AudioSystem.getClip();
            clipMusicaMenu.open(AudioMenu);

            AudioEfects = AudioSystem.getAudioInputStream(soundURL[SoundMorir]);
            clipEfects = AudioSystem.getClip();
            clipEfects.open(AudioEfects);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Función para implementar archivos de audio en las demás clases
     *y comenzar a reproducir
     * @param e auxiliar para identificar el archivo de audio
     */
    public void play(int e) {
        if (e == SoundFondo) {
            clipMusicaFondo.start();
            clipMusicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
        } else if (e == SoundMenu) {
            clipMusicaMenu.start();
            clipMusicaMenu.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            try {
                AudioEfects = AudioSystem.getAudioInputStream(soundURL[e]);
                clipEfects = AudioSystem.getClip();
                clipEfects.open(AudioEfects);
                clipEfects.start();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Función para detener la música
     *
     * @param e auxiliar para identificar el archivo de audio
     */
    public void stop(int e) {
        if (e == SoundFondo) {
            clipMusicaFondo.stop();
        } else if (e == SoundMenu) {
            clipMusicaMenu.stop();
        }

    }
}
