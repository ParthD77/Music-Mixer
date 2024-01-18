import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
// test s

public class Instrument {
    private int amount;
    private Draggable[] beats;

    public Instrument(int counter, int amount, String sound, String activity){
    this.amount = amount;
        beats = new Draggable[amount];
        for (int i = 0; i < amount; i++) {
            beats[i] = new Draggable(new Color(0, 0, 200), sound, i);
            beats[i].setBounds(150+i * 100, 70*counter+70, 50, 50);
            System.out.println(amount);
            if (activity.charAt(i) == '1') beats[i].setActivity(true);
        }
    }

    public void setInstrument(String fileName) {
        for (int i = 0; i < amount; i++) {
            beats[i].setNote(fileName);
        }
    }

    public String getInstrument() {
        return beats[0].getNote();
    }

    public Draggable getBeat(int spot) {
        return beats[spot];
    }


    public void addBeats(JPanel panel){
        for (int i = 0; i < amount; i++) {
            panel.add(beats[i]);
        }
    }

    public int getBeatsCount() {
        return amount;
    }


    public static void main (String[] args) {
    }
}
