package festivalSimulatie.Object;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.Timer;

public class DigitalClock extends JLabel {

	private static final long serialVersionUID = 1L;
	private int hours = 0, minutes = 0, seconds = 0, frames = 0, maxFrames = 20;
	private Timer timer;
	/**
	 * @param delay: The delay is the frames per second of the timer in de simulation
	 */
	public DigitalClock(Timer timer) {
		this.timer = timer;		
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setPreferredSize(new Dimension(100, 20));
		setDigitalClock();
	}

	public void setDigitalClock() {
		String hour = "";
		String minute = "";
		String second = "";

		if (hours <= 9) {
			hour = "0" + hours;
		} else {
			hour = "" + hours;
		}

		if (minutes <= 9) {
			minute = "0" + minutes;
		} else {
			minute = "" + minutes;
		}

		if (seconds <= 9) {
			second = "0" + seconds;
		} else {
			second = "" + seconds;
		}

		setText(hour + ":" + minute + ":" + second);
	}

	public void tick() {
		
		if (frames != maxFrames) {
			frames++;
		} else {
			frames = 0;
			seconds++;
			if (seconds == 60) {
				seconds = 0;
				minutes++;
				if (minutes == 60) {
					minutes = 0;
					hours++;
					if (hours == 24) {
						hours = 0;
						minutes = 0;
						seconds = 0;
					}
				}
			}
		}
		setDigitalClock();
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getFrames() {
		return frames;
	}

	public void setFrames(int frames) {		
			this.frames = frames;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public int getMaxFrames() {
		return maxFrames;
	}

	public void setMaxFrames(int maxFrames) {		
		this.maxFrames = maxFrames;		
	}
	
	

}
