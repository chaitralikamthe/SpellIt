package spell;

import javax.swing.JFrame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class spellit {

	
	    public static void main(String[] args) throws Exception {
	        int boardWidth = 1200;
	        int boardHeight =600;
	                
	        int num=1;
	        storage sg= new storage(num);
	    	letters l= new letters(boardHeight,sg);
	    	
	    	spellit sp=new spellit();
	    	sp.oneLetter(sg,l,boardWidth,boardHeight,num);
	    	
	        


	    }
	    
	    public static void oneLetter(storage s,letters l,int b, int h,int num) {
	    	
	        JFrame frame = new JFrame("Spellit");
	        frame.setBackground(Color.red);
	        frame.setVisible(true);
	        frame.setSize(b, h);
	        frame.setLocationRelativeTo(null);
	        frame.setResizable(false);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	action act1 = new action(b, h, l,num);
//	    	statFrame f= new statFrame(30,30);
	        frame.add(act1);
	        frame.pack();
	        act1.requestFocus();
	    }
	    
}
	

