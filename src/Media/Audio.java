package Media;

import javafx.scene.media.AudioClip;

public class Audio {
    private String audioUrl;

    public Audio(String audioUrl){
        setAudioUrl(audioUrl);
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public void playAudio(String audioUrl){
        AudioClip audioClip = new AudioClip(audioUrl);
        audioClip.play();
    }

    public void stopAudio(String audioUrl){
        AudioClip audioClip = new AudioClip(audioUrl);
        audioClip.stop();
    }
}
