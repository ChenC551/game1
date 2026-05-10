package org;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class SoundManager {
    private Clip backgroundMusic;
    private Clip effectClip;
    private boolean isMuted = false;

    public void setMuted(boolean muted) {
        isMuted = muted;

        if (isMuted) {
            stopBackgroundMusic();
            stopEffect();
        }
    }

    public boolean isMuted() {
        return isMuted;
    }

    // טעינת מוזיקת רקע
    public void playBackgroundMusic(String path) {
        if (isMuted) {
            return;
        }

        try {
            stopBackgroundMusic();

            InputStream is = getClass().getResourceAsStream(path);

            if (is == null) {
                System.out.println("Error: Could not find file " + path);
                return;
            }

            InputStream bufferedIn = new BufferedInputStream(is);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);

            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(ais);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundMusic.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // השמעת אפקט קצר
    public void playEffect(String path) {
        if (isMuted) {
            return;
        }

        try {
            InputStream is = getClass().getResourceAsStream(path);

            if (is == null) {
                System.out.println("Error: Could not find file " + path);
                return;
            }

            InputStream bufferedIn = new BufferedInputStream(is);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);

            effectClip = AudioSystem.getClip();
            effectClip.open(ais);
            effectClip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic.close();
            backgroundMusic = null;
        }
    }

    public void stopEffect() {
        if (effectClip != null) {
            effectClip.stop();
            effectClip.close();
            effectClip = null;
        }
    }
}