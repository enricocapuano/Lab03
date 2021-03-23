package it.polito.tdp.spellchecker.model;

public class RichWord {
	
	private String word;
	private boolean check;
	
	
	public RichWord(String word, boolean check) {
		super();
		this.word = word;
		this.check = check;
	}
	
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	
	

}
