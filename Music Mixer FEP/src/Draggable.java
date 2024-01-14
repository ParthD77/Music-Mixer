import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 */

/**
 * @author parth
 *
 *
 * // you can add a set width and height method if we want to allow adjustable boxes
 */
public class Draggable extends JPanel implements MouseListener, MouseMotionListener{
	private int  x, y;
	private String note;
	private Color c;

	public Draggable(int width, int height, Color color, String note) {
		setColor(color);
		setNote(note);
		setBounds(0, 0, width, height);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void setNote(String fileName) {
		note = fileName;
	}
	public String getNote() {
		return note;
	}

	public void setColor(Color c) {
		setBackground(c);
	}
	public Color getColor() {
		return getBackground();
	}


	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)) { // check if its a right click to drag
			setLocation(e.getX() - x + getLocation().x, e.getY() - y + getLocation().y);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) makeSound(note);  // play  music if clicked
	}


	// makes a sound
	public void makeSound(String name){
		try{
			File lol = new File("Audios/"+name);
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(lol));
			clip.start();
		} catch (Exception e){
			e.printStackTrace();
		}
	}



	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {}


	public static void main(String[] args) {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame("Draggable Components");
		frame.setLayout(null);
		panel.setLayout(null);

		for (int i = 0; i < 10; i++) {
			Draggable component = new Draggable(50, 50, new  Color(20*i, 30, 30), "ceeday-huh-sound-effect.wav");
			panel.add(component);
			component.setBounds(i * 50, i * 50, 50, 50);
		}

		panel.setBounds(0, 0, 500, 500);
		frame.add(panel);
		frame.revalidate();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}
