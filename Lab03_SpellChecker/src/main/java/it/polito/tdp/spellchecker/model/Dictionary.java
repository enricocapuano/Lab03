package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	List<String> lingue;
	LinkedList<String> dizionario;
	
	public Dictionary() {
		lingue = new LinkedList<String>();
		lingue.add("english");
		lingue.add("italian");
		dizionario = new LinkedList<String>();
	}


	
	public List<String> getLingue() {
		return lingue;
	}



	public void loadDictionary(String language) {
		String l = language.toLowerCase();
		if(l.equals("english")) {
			try {
				FileReader fr = new FileReader("src/main/resources/English.txt");
					BufferedReader br = new BufferedReader(fr);
				String word;
				
				while((word = br.readLine()) != null) {
					dizionario.add(word);
				}
				br.close();
				
			}catch(IOException e){
				System.out.println("Errore nella lettura del file");
			}
		}
		else if(l.equals("italian")) {
			try {
				FileReader fr = new FileReader("src/main/resources/Italian.txt");
					BufferedReader br = new BufferedReader(fr);
				String word;
				
				while((word = br.readLine()) != null) {
					dizionario.add(word);
				}
				br.close();
			}catch(IOException e){
				System.out.println("Errore nella lettura del file");
			}
		}
		
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> lista = new LinkedList<RichWord>();
		for(String word : inputTextList) {
				
			RichWord rw;
			String w = word.toLowerCase();
			if(dizionario.contains(w)) {
				rw = new RichWord(w, true);				
			}
			else {
				rw = new RichWord(w, false);
			}
			lista.add(rw);
			
			}
		return lista;
	}

}
