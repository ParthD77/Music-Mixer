import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author parth
 *
 *
 * //https://www.youtube.com/watch?v=WSyTrdjKeqQ
 */
public class Recorder {


	public static void main(String[] args) {
		try {
			AudioFormat af = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);

			DataLine.Info dataInfo  = new  DataLine.Info(TargetDataLine.class, af);

			if (!AudioSystem.isLineSupported(dataInfo)) {
				System.out.println("Not supported");
			}

			TargetDataLine targetLine = (TargetDataLine)AudioSystem.getLine(dataInfo);
			targetLine.open();

			JOptionPane.showMessageDialog(null, "Start recording");

			targetLine.start();

			Thread audioRecorderThread = new Thread() {
				@Override public void run()
				{
					AudioInputStream rS = new AudioInputStream(targetLine);
					File outputFile = new File("record.wav");
					try {
						AudioSystem.write(rS, AudioFileFormat.Type.WAVE, outputFile);
					}
					catch(Exception e1) {

					}
				}
			};
			
			audioRecorderThread.start();
			JOptionPane.showMessageDialog(null, "Stop recording");
			targetLine.stop();
			targetLine.close();
		}
		catch(Exception e){
		}
	}
}
