package main.java.app.util;

import java.io.InputStream;
import java.util.Map;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public class MP3Player implements BasicPlayerListener {
	private final BasicPlayer player = new BasicPlayer();
	private String resourcePath;
	private boolean looping = false;

	public MP3Player() {
		player.addBasicPlayerListener(this);
	}

	public void play(String resourcePath) {
		play(resourcePath, true);
	}
	
	public void play(String resourcePath, boolean loop) {
		stop();
		this.resourcePath = resourcePath;
		this.looping = loop;
		playOnce();
	}

	private void playOnce() {
		try {
			InputStream is = getClass().getResourceAsStream(resourcePath);
			if (is == null)
				throw new RuntimeException("Resource not found: " + resourcePath);
			player.open(is);
			player.play(); // non-blocking, returns immediately
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		looping = false;
		try {
			player.stop();
		} catch (Exception ignored) {
		}
	}

	@Override
	public void stateUpdated(BasicPlayerEvent event) {
		if (event.getCode() == BasicPlayerEvent.EOM && looping) {
			playOnce();
		}
	}

	@Override
	public void opened(Object stream, Map properties) {}

	@Override
	public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties) {}

	@Override
	public void setController(BasicController controller) {}
}
