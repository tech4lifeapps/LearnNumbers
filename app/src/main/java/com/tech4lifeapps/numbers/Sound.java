package com.tech4lifeapps.numbers;

//Store information about a sound
public class Sound {
	private String soundFileName;
	
	/**
	 * Constructor
	*/
	public Sound(String soundFileName) {
		super();
		this.soundFileName = soundFileName;
	}
	
	public String getSoundFileName() {
		return soundFileName;
	}

}
