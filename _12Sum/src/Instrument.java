import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
// test s

public class Instrument {
    private int amount;
    private Draggable[] beats;

    public Instrument(int counter, int amount, String sound){
    this.amount = amount;
        beats = new Draggable[amount];
        for (int i = 0; i < amount; i++) {
            beats[i] = new Draggable(new Color(200, 10*i, 30), sound);
            beats[i].setBounds(150+i * 90, 70*counter+50, 50, 50);
            beats[i].changeMoveable(false);
        }
    }

    public void setInstrument(String fileName) {
        for (int i = 0; i < amount; i++) {
            beats[i].setNote(fileName);
        }
    }

    public void setVisible(boolean visible){
        for (int i = 0; i < amount; i++) {
            beats[i].setEnabled(visible);
        }
    }

    public JPanel addBeats(JPanel panel){
        for (int i = 0; i < amount; i++) {
            panel.add(beats[i]);
        }
        return panel;
    }

    public int getBeatsCount() {
        return amount;
    }

    public void forceSound(int spot) {
        beats[spot].forceSound();
    }

    public static void main (String[] args) {
    }
}
