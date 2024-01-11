import javax.swing.*;
import java.awt.*;

/*
Author: Parth
Date:
Description;
 */
public class BeatMaker extends JFrame{
    private Draggable[] draggables;
    JPanel panel;
    public BeatMaker() {
        setLayout(null);
        panel = new JPanel();
        panel.setBounds(0, 0, 200, 200);

        draggables = new Draggable[10];
        for (int i = 0; i < 10; i++) {
            draggables[i] = new Draggable(50, 50, new Color(20 * i, 30, 30), "ceeday-huh-sound-effect.wav");
            panel.add(draggables[i]);
            draggables[i].setBounds(i * 50, i * 50, 50, 50);
        }


        add(panel);
        revalidate();
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BeatMaker();
    }
}
