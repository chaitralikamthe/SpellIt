package spell;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class statFrame extends JPanel {
	
	statFrame(int w, int b){
		setPreferredSize(new Dimension(w, b));
        setBackground(Color.black);
        setFocusable(true);
	}

}
