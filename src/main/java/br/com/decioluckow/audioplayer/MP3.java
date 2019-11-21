package br.com.decioluckow.audioplayer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MP3 {
    private String filename;
    private Player player; 

    // constructor that takes the name of an MP3 file
    public MP3(String filename) {
        this.filename = filename;
    }

    public void close() { 
        if (player != null) player.close(); 
    }

    public int getPosition() {
        return player.getPosition(); 
    }

    // play the MP3 file to the sound card
    public void play() {
        try {
            FileInputStream fis     = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }

        // run in new thread to play in background
        new Thread() {
            public void run() {
                try { player.play(); }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();
    }


    // test client
    public static void main(String[] args) {
        String filename = ".\\src\\main\\resources\\test-music\\country\\Country - faixa 1.mp3";
        MP3 mp3 = new MP3(filename);
        mp3.play();

        // enquanto a musica toca - você pode fazer algum procesamento
        for (int i = 0; i < 10; i++) {
            try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            System.out.println(mp3.getPosition());
        }
        // when the computation is done, stop playing it
        mp3.close();

        // play from the beginning
        mp3 = new MP3(".\\src\\main\\resources\\test-music\\country\\Country - faixa 2.mp3");
        mp3.play();

    }

}
