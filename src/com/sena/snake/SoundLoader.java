package com.sena.snake;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundLoader {

    private static final ThreadLocal<AudioInputStream> audioStream = new ThreadLocal<>();

    public static void play(String fileName) {
        try {
            File file = new File(fileName);
            audioStream.set(AudioSystem.getAudioInputStream(file));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream.get());
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ignored) {
        }
    }
}
