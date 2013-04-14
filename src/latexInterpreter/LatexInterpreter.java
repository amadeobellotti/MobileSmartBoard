package latexInterpreter;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import frameListener.*;
import frame.*;

public class LatexInterpreter extends Thread {
	private FrameListener mainFrame;
	public boolean quit;
	private ArrayList<LatexTerm> terms;

	public LatexInterpreter(FrameListener mainFrame) throws IOException {
		voce.SpeechInterface.init("voce-0.9.1/lib", false, true, "./gram",
				"latex");
		this.mainFrame = mainFrame;
		generateGrammar();
		terms = setupTerms();
		quit = false;
	}

	@Override
	public void run() {
		String s;
		s = voce.SpeechInterface.popRecognizedString();
		if (s != null) {
			for (LatexTerm o : terms) {
				if (s.equals(o.getVoice())) {
					insertText(o.getTerm());
				}
			}
		}
	}
	
	private void insertText(String s) {
		//hook into window and 
		//insert string into text box
	}
	
	public void generateGrammar() throws IOException {
		String voice, line;
		File gramOut = new File("gram/latex.gram");
		if (!gramOut.exists()) {
			gramOut.createNewFile();
		}
		FileWriter fw = new FileWriter(gramOut.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("#JSGF V1.0;\ngrammar latex;\npublic <latex> = (");
		Scanner sc = new Scanner(new File("latex_dictionary.txt"));
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			voice = line.substring(line.indexOf('=') + 1);
			bw.write(voice);
			bw.write(" | ");
		}
		bw.write(") *;");
	}
	
	public ArrayList<LatexTerm> setupTerms() throws FileNotFoundException {
		ArrayList<LatexTerm> newTerms = new ArrayList<LatexTerm>();
		String voice, term, line;
		Scanner sc = new Scanner(new File("latex_dictionary.txt"));
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			term = line.substring(0, line.indexOf(':') - 1);
			voice = line.substring(line.indexOf('=') + 1);
			newTerms.add(new LatexTerm(term, voice));
		}
		sc.close();
		return newTerms;
	}

}
