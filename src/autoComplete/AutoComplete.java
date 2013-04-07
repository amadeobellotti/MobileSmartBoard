package autoComplete;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AutoComplete {
	public final int NUM_SUGGESTIONS = 4;
	private ArrayList<String> latexDictionary;
	private ArrayList<Integer> usageCounter;
	private ArrayList<Integer> tempArray;
	private BufferedReader reader;
	@SuppressWarnings("unused")
	private BufferedWriter writer;
	
	public AutoComplete(){
		latexDictionary = new ArrayList<String>();
		usageCounter = new ArrayList<Integer>();
		tempArray= new ArrayList<Integer>();
		
		setReader("scoredLatexDictionary.txt");
	    String line;
	    try {
			while ((line = reader.readLine()) != null) {	
				for(int i=0;i<line.length();i++){
					if(line.charAt(i)==' '){
						latexDictionary.add(line.substring(0, i));
						usageCounter.add(Integer.parseInt(line.substring(i+1,line.length())));
					}
				}
			}
			reader.close();
		} catch (NumberFormatException | IOException e) {
			//e.printStackTrace();
		}
	}
	public String[] getBestGuess(String subCmd){
		tempArray.clear();
		String[] bestGuess = new String[NUM_SUGGESTIONS];
		for(int i = 0; i<latexDictionary.size();i++)
		{
			if(subCmd.length()<=latexDictionary.get(i).length()){
				if(latexDictionary.get(i).substring(0, subCmd.length()).equals(subCmd)){
					tempArray.add(i);
				}
			}
		}

		int maxIndex = 0;
		for(int i = 0; i<NUM_SUGGESTIONS;i++){
			if(tempArray.size()>0){
				for(int j = 1; j< tempArray.size(); j++){
					if(usageCounter.get(j)>usageCounter.get(tempArray.get(maxIndex))){
						maxIndex=j;
					}
				}
				bestGuess[i]=latexDictionary.get(tempArray.get(maxIndex));
				tempArray.remove(maxIndex);
				maxIndex = 0;
			}
			else{
				bestGuess[i] = "";
			}
		}
		return bestGuess;
	}

	private void setReader(String getFile){
		reader = new BufferedReader(new InputStreamReader(AutoComplete.class.getResourceAsStream(getFile)));
	}
	@SuppressWarnings("unused")
	private void setWriter(String makeFile) throws IOException{
		writer = new BufferedWriter(new FileWriter(makeFile));
	}
	@SuppressWarnings("unused")
	private void updateProbCount(String latexString){
		//increment value of used words
	}

	
}
