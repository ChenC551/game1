package org;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;


public class SoundManager {
    private Clip backgroundMusic;
    private Clip effectClip;
    private boolean isMuted = false;

    // טעינת מוזיקת רקע
    public void playBackgroundMusic(String path) {
        try {
            // שימוש ב-getResourceAsStream כדי להתאים לדרך שבה טענת תמונות
            InputStream is = getClass().getResourceAsStream(path);
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

    // השמעת אפקט קצר (פצצה או סוכריה)
    public void playEffect(String path) {
        if (isMuted) return;
        try {
            InputStream is = getClass().getResourceAsStream(path);
            InputStream bufferedIn = new BufferedInputStream(is);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
            effectClip = AudioSystem.getClip();
            effectClip.open(ais);
            effectClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toggleMute() {
        if (backgroundMusic == null) return;
        isMuted = !isMuted;
        if (isMuted) {
            backgroundMusic.stop();
        } else {
            backgroundMusic.start();
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    public void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic.close();
        }
    }
    public void stopEffect() {

        if (effectClip != null && effectClip.isRunning()) {

            effectClip.stop();
            effectClip.close();
        }
    }

}

