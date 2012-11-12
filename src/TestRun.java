import frame.MainFrame;
import frameListener.DefaultFrameListener;
import frameListener.FrameListener;


public class TestRun {
	public static void main(String[] args){
		System.out.println("Welcome");
		new MainFrame();
		voce.SpeechInterface.init("voce-0.9.1/lib", true, true, "./gram", "digits");
		System.out.println("speak digits or quit");
		boolean quit = false;
		while (!quit) {
			try {
				Thread.sleep(200);
			}
			catch (InterruptedException e) {}
			while (voce.SpeechInterface.getRecognizerQueueSize() > 0) {
				String s = voce.SpeechInterface.popRecognizedString();
				if (-1 != s.indexOf("quit")) {
					quit = true;
				}
				System.out.println("You said: " + s);
				voce.SpeechInterface.synthesize(s);
			}
		}
		voce.SpeechInterface.destroy();
	}

}
