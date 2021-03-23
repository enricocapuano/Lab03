package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	Dictionary dizionario;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtSpell;

    @FXML
    private Button btnSpellCheck;
    
    @FXML
    private ComboBox<String> boxForma;

    @FXML
    private TextArea txtResult;

    @FXML
    private Label labelResult;

    @FXML
    private Button btnClear;

    @FXML
    private Label labelTime;

    @FXML
    void doClearText(ActionEvent event) {
    	boxForma.setValue("");
    	txtSpell.setText("");
    	txtResult.setText("");
    	labelResult.setText("");
    	labelTime.setText("");
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	long inizio = System.nanoTime();
    	dizionario.loadDictionary(boxForma.getValue());
    	LinkedList<String> wrongWords = new LinkedList<String>();
    	List<RichWord> words = new LinkedList<RichWord>();
    	String testo = txtSpell.getText().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_'()\\[\\]\"]","");
    	String campi[] = testo.split(" ");
    	LinkedList<String> wordSpell = new LinkedList<String>();
    	for(int i = 0; i < campi.length; i++) {
    		wordSpell.add(campi[i]);
    	}
    	words = this.dizionario.spellCheckText(wordSpell);
    	for(RichWord rw : words) {
    		if(rw.isCheck() == false) {
    			wrongWords.add(rw.getWord());
    		}
    	}
    	String s = "";
    	if(wrongWords.isEmpty()) {
    		labelResult.setText("Non ci sono errori");
    	}
    	else {
    		for(String w : wrongWords) {
	    		s += w+"\n";
	    		txtResult.setText(s);
	    	}
    		long fine = System.nanoTime();
    		double time = fine - inizio;
    		labelResult.setText("Ci sono "+wrongWords.size()+" errori");
    		labelTime.setText("Tempo impiegato: "+(time/1000000000)+" secondi");
    	}
    	
    	
    }
    
    public void setModel(Dictionary d) {
    	this.dizionario = d;
    	boxForma.getItems().addAll(this.dizionario.getLingue());
    }

    @FXML
    void initialize() {
    	assert boxForma != null : "fx:id=\"boxForma\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSpell != null : "fx:id=\"txtSpell\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelResult != null : "fx:id=\"labelResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelTime != null : "fx:id=\"labelTime\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}


