import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

import javax.imageio.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 *
 */

/**
 * @author parth
 *asdasd
 *
 * // you can add a set width and height method if we want to allow adjustable boxes
 */
public class Draggable extends JPanel implements MouseListener, MouseMotionListener {
	private int x, y, count;
	private String note;
	private Color c;
	private boolean moveable;
	private JLabel icon;
	private boolean active;

	public Draggable() {
		changeMoveable(false);
		addMouseListener(this);
		addMouseMotionListener(this);
		active = true;

	}

	public Draggable(Color color, String note, int count) {
		changeMoveable(false);
		setColor(color);
		setNote(note);
		addMouseListener(this);
		addMouseMotionListener(this);
		this.count = count;
		active = false;
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



	public void setActivity(boolean active) {
		this.active = active;
		if (active) {
			setColor(new Color(200, 0, 0));
		} else {
			setColor(new Color(0, 0, 200));
		}
		repaint();
	}
	public boolean getActivity() {
		return active;
	}


	public void setIcon(String fileName) {
		try {
			icon = new JLabel();
			icon.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("imgs/" + fileName))));
			add(icon);
			setSize(icon.getPreferredSize());
		} catch (Exception e) {
		}
	}
	public void removeIcon() {remove(icon);}



	public void changeMoveable(Boolean valid){moveable = valid;}


	public void mousePressed(MouseEvent e) {
		// play sound if its a left click
		if(SwingUtilities.isLeftMouseButton(e)) {
			makeSound();  // play  music if clicked
			setActivity(!active); // swtich active
		}

		// to make it so that the component stick to where the mouse pressed it,
		// instead of the corner of the component when moving
		x = e.getX();
	}

	@Override // move the component
	public void mouseDragged(MouseEvent e) {
		// check if its a right click to drag and moveable
		if(SwingUtilities.isRightMouseButton(e) && moveable) {
			// move to the mouse
			int newX = e.getX() - x + getLocation().x;

			// Round to the nearest multiple of 50
			newX = Math.round(newX / 100) * 100;

			setLocation(newX, getLocation().y);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}


	// makes a sound
	public void makeSound(){
		try{
			//File inputFile = new File(getClass().getResource("/sounds/" + name).toURI());
		//	String jarPath = Draggable.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		//	File jarFile = new File(jarPath).getParentFile().getParentFile();

			File inputFile = new File("sounds/" + note);


			AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputFile);

			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();												// try to see if the clip is done playing
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
			Draggable component = new Draggable(new  Color(20*i, 30, 30), "mic.wav", i);
			component.setBounds(i * 50, i * 50, 50, 50);
			component.changeMoveable(true);
			panel.add(component);
		}


		panel.setBounds(0, 0, 500, 500);
		frame.add(panel);
		frame.revalidate();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}
