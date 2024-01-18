import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.imageio.*;
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
public class Draggable extends JPanel implements MouseListener, MouseMotionListener{
	private int  x, y;
	private String note;
	private Color c;
	private boolean moveable;
	private JLabel icon;

	public Draggable(Color color, String note) {
		changeMoveable(false);
		setColor(color);
		setNote(note);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public Draggable(String note) {
		changeMoveable(false);
		setNote(note);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public Draggable() {
		changeMoveable(false);
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

	public void setIcon(String fileName) {
		try {
			icon = new JLabel();
			icon.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("imgs/" + fileName))));
			add(icon);
			setSize(icon.getPreferredSize());
		}
		catch (Exception e) {}
	}

	public void removeIcon() {remove(icon);}


	public void changeMoveable(Boolean valid){moveable = valid;}

	// to make it so that the component stick to where the mouse pressed it,
	// not the corner of the component
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override // move the component
	public void mouseDragged(MouseEvent e) {
		// check if its a right click to drag and moveable
		if(SwingUtilities.isRightMouseButton(e) && moveable) {
			setLocation(e.getX() - x + getLocation().x, e.getY() - y + getLocation().y);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) makeSound(note);  // play  music if clicked
	}

	public void forceSound() {
		makeSound(note);  // play on timer
	}


	// makes a sound
	public void makeSound(String name){
		try{
			File lol = new File("sounds/"+name);
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(lol));
			clip.start();
		} catch (Exception e){}
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
			Draggable component = new Draggable(new  Color(20*i, 30, 30), "ceeday-huh-sound-effect.wav");
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
