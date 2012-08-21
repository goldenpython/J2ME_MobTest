/*******************************************************************************
*    Cristian Alexandrescu                                                     *
*    2163013577ba2bc237f22b3f4d006856                                          *
*    11a4bb2c77aca6a9927b85f259d9af10db791ce5cf884bb31e7f7a889d4fb385          *
*    bc9a53289baf23d369484f5343ed5d6c                                          *
*******************************************************************************/


import javax.microedition.midlet.*;

public class cMIDlet extends MIDlet {

	static cMain _MainCanvas;
	public static cMIDlet _MIDlet;

	public cMIDlet() {
		_MIDlet = this;
		_MainCanvas = new cMain(_MIDlet);
	}

	public void startApp() {
		_MainCanvas.start();
	}

	public void pauseApp() {
	}

	public void destroyApp(boolean unconditional) {
	}
}
