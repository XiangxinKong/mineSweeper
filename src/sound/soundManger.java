package sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

/**
 * play the sound effect
 */
    public class soundManger extends Thread {
        /////
        private File soundFile;
        private AudioInputStream audioStream;


        public soundManger(String soundFilePath) {
            try {
                soundFile = new File(soundFilePath);
                audioStream = AudioSystem.getAudioInputStream(soundFile);
            } catch (Exception e) {
            }
            run();
        }

    protected void play() throws LineUnavailableException, IOException {
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        audioStream.close();
    }

    @Override
    public void run() {
		try {
			this.play();
		} catch (Exception e) {
		}
	}

}
