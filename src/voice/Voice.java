package voice;

import frame.TextMaker;
import renderableObject.Text;

public class Voice extends Thread {
	public boolean quit = true;
	TextMaker text;
	boolean dead = true;

	public Voice(TextMaker tm) {
		voce.SpeechInterface.init("voce-0.9.1/lib", true, true, "./gram",
				"digits");
		text = tm;
		dead = true;
	}

	@Override
	public void run() {
		while (dead) {
			while (!quit) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					quit = true;
					voce.SpeechInterface.destroy();
					dead = false;
				}
				while (voce.SpeechInterface.getRecognizerQueueSize() > 0) {
					String s = voce.SpeechInterface.popRecognizedString();
					if (-1 != s.indexOf("quit")) {
						quit = true;
					}
					if (s != null)
						text.appendText(s);
					System.out.println("You said: " + s);
					voce.SpeechInterface.synthesize(s);
				}
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				quit = true;
				voce.SpeechInterface.destroy();
				dead = false;
			}
		}
		// voce.SpeechInterface.destroy();
	}

}
