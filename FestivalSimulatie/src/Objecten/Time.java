package Objecten;

import java.io.Serializable;

public class Time implements Serializable {

	private static final long serialVersionUID = -5317692881299169116L;
	private String beginTime;
	private String endTime;

	public Time(String begin, String end) {
		setBeginTime(begin);
		setEndTime(end);
	}

	// setters
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String toString() {
		return "Time [Begin=" + beginTime + ", End=" + endTime + "]";
	}

}
