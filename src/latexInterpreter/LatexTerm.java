package latexInterpreter;

public class LatexTerm {
	private String term, voice;
	
	public LatexTerm(String term, String voice) {
		this.term = term;
		this.voice = voice;
	}
	
	public String getTerm() {
		return term;
	}
	
	public String getVoice() {
		return voice;
	}
	
}
