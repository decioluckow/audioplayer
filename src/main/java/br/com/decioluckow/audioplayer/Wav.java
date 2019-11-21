package br.com.decioluckow.audioplayer;

import java.io.*;
import javax.sound.sampled.*;

/**
 * Wav
 */
public class Wav {

    public static void main(String[] args) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(".\\src\\main\\resources\\real-music\\How_We_Like_It.wav"));
            AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
            Thread.sleep(10000);
        }
        catch (Exception e) {
            //whatevers
        }
    }
}