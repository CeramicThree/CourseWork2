package Media;

import javafx.scene.media.AudioClip;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Audio extends Thread{
    Thread thread;
    private String audioUrl;
    private boolean isPlaying = false;

    public Audio(String audioUrl){
        setAudioUrl(audioUrl);
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public void playAudio(String audioUrl) throws InterruptedException {
        Runnable r = ()->{
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(audioUrl);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Player playMP3 = null;
            try {
                playMP3 = new Player(fileInputStream);
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }

            try {
                playMP3.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        };
        thread = new Thread(r, "Audio Thread");
        thread.start();
        setPlaying(true);
    }

    public void stopPlay() throws InterruptedException {
        thread.join();
    }

}
