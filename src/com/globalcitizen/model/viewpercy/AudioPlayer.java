package com.globalcitizen.model.viewpercy;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
	boolean playBackGroundMusic;

	public boolean isPlayBackGroundMusic() {
		return playBackGroundMusic;
	}

	public void setPlayBackGroundMusic(boolean playBackGroundMusic) {
		this.playBackGroundMusic = playBackGroundMusic;
	}

	public void playMusic(String fileName) {
		playBackGroundMusic = true;
		AudioInputStream audioIn;
		try {
			audioIn = AudioSystem.getAudioInputStream(ClassLoader.getSystemResource(fileName));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			while (playBackGroundMusic) {
				while (!clip.isRunning())
					Thread.sleep(10);
				while (clip.isRunning())
					Thread.sleep(10);
				clip.close();
				System.out.println("working");
				Thread.sleep(10);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
