package enael.eafc.appli;

import enael.eafc.Design.WindowFrame;

import javax.swing.SwingUtilities;


/*
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html
 * tout en bas la #Swing's Threading Policy
 * j'ai fait comme il ont dit, puis eclipse soulignait en rouje la methode invokeLater et precisait le Runnable dans sa java doc que j'ai implementer, le bg persiste.
 */
public class MainoutApp {
	static Runnable doRun = new Runnable() {
		public void run() {
		    WindowFrame window = new WindowFrame();	
	    }
		
	};

	public static void main(String[] args) {
			SwingUtilities.invokeLater(doRun);
	}
}
