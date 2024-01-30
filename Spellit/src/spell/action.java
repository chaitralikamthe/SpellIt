package spell;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class action extends JPanel implements ActionListener, KeyListener {
	
	String loc;
	 private ImageIcon icon ;
	 private Image img;

    
	protected class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
	int num;
	int boardWidth;
	int boardHeight;
	int tileSize = 30;
	int gc=0;
	ArrayList<String> word1;
	spellit spell= new spellit();
		    
	    //word
	    Tile box0;
	    ArrayList<Tile> word;
	    ArrayList<Tile> correctWord=new ArrayList<>();

	    //letter
	    Tile letter;
		letters l;		
		ArrayList<Integer> posx= new ArrayList<>();
		ArrayList<Integer> posy= new ArrayList<>();

	    //game logic
	    int velocityX;
	    int velocityY;
	    Timer gameLoop;

	    boolean gameOver = false;
	//    int letterDone =0;
	    boolean wrongLetter;
	    
	    
	
	   //Constructor
	    action(int boardWidth, int boardHeight,letters l,int num) {
	        this.boardWidth = boardWidth-300;
	        this.boardHeight = boardHeight;       
	        this.l=l;
	        this.posx=l.posx;
	        this.posy=l.posy;
	        this.word1=l.word1;
	        this.num=num;
	        this.loc=l.loc;
	        
	        icon = new ImageIcon(loc);
	        img = icon.getImage();
	        
	        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
	        setBackground(Color.black);
	        addKeyListener(this);
	        setFocusable(true);

	        box0 = new Tile(5, 5);
	        word = new ArrayList<Tile>();

	        velocityX = 1;
	        velocityY = 0;
	        
	        for(int i=0;i<posx.size();i++) {        	       	

	        	letter = new Tile(10, 10);	        	
	        	letter.x = posx.get(i);
	        	letter.y = posy.get(i);	
	        	correctWord.add(letter);

	        }
	        
	        
			//game timer
			gameLoop = new Timer(200, this); //how long it takes to start timer, milliseconds gone between frames 
	        gameLoop.start();
		}	
	    
	    
	    @Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
	        // System.out.println("KeyEvent: " + e.getKeyCode());
	        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
	            velocityX = 0;
	            velocityY = -1;
	        }
	        else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
	            velocityX = 0;
	            velocityY = 1;
	        }
	        else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
	            velocityX = -1;
	            velocityY = 0;
	        }
	        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
	            velocityX = 1;
	            velocityY = 0;
	        }
		}

		@Override
		public void keyReleased(KeyEvent e) {	
			}

		@Override
		public void actionPerformed(ActionEvent e) {
	        try {
				move();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        repaint();
	        if (gameOver) {
	            gameLoop.stop();
	        }
	    
		}   
	    
	    //Paint
	    public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 700, 110, this);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.PLAIN, 75));
			g.drawString(""+word1.get(0),720,400);
			draw(g);
		}

	    
		public void draw(Graphics g) {
			
			if(wrongLetter)
				g.drawString("OOPS! WRONG LETTER.. TRY AGAIN", 60, 60);

	        //Grid Lines
	        for(int i = 0; i < boardHeight/tileSize; i++) {
	            //(x1, y1, x2, y2)
	            g.drawLine(i*tileSize, 0, i*tileSize, boardHeight);
	            g.drawLine(0, i*tileSize, boardHeight, i*tileSize); 
	        }
	        
	        //box0
	        g.setColor(Color.green);
	        g.fill3DRect(box0.x*tileSize, box0.y*tileSize, tileSize, tileSize, true);
        	
	               
	        //Letter	        
	        for(int i=gc;i<posx.size();i++) {        	       	

		        	letter = new Tile(10, 10);	        	
		        	letter.x = posx.get(i);
		        	letter.y = posy.get(i);	

		        	g.setColor(Color.red);
		        	g.fill3DRect(letter.x*tileSize, letter.y*tileSize, tileSize, tileSize, true);	        
		        	g.setColor(Color.white);
		        	g.setFont(new Font("Arial", Font.PLAIN, 25));
		        	g.drawString(""+l.word1.get(i), letter.x*tileSize+8, letter.y*tileSize+26);		        	
	       }
	        		                
	        //word
	        for (int j =  gc-1;j>=0 ;j--) {	        	
	            Tile inLetter = word.get(j);
	            g.setColor(Color.red);
	            g.fill3DRect(inLetter.x*tileSize, inLetter.y*tileSize, tileSize, tileSize, true);
    	        g.setColor(Color.white);
    	        g.setFont(new Font("Arial", Font.PLAIN, 25));
    	        g.drawString(""+l.word1.get(gc-1-j), inLetter.x*tileSize+12, inLetter.y*tileSize+26);
	        }  
	        

	        //Score
	        g.setFont(new Font("Arial", Font.PLAIN, 36));
	        if (gameOver) {
	            g.setColor(Color.red);
	            g.drawString("Game Over: " + String.valueOf(word.size()), boardHeight+60, boardHeight-60);
	        }
	        else {
	            g.drawString("Score: " + String.valueOf(word.size()), boardHeight+60, boardHeight-60);
	        	 }
	       }


		
	    public void move() throws Exception {
	        //insert letter
	    	System.out.println(gc+"   "+correctWord.size()+"  "+l.posx.size()+" "+word1.size());
	    	for(int i=gc;i<correctWord.size();i++) {
	    		if (collision(box0,correctWord.get(i))){	 	        
	        			if(i==gc) {
	        				gc++;	        	
	        	        	word.add(0,new Tile(correctWord.get(i).x, correctWord.get(i).y));	
	        	        	break;
	        			}
	        			
	        			else if(i<gc) {
	        				break;
	        			}
	        			else {
	        				num--;
	        				letterdone();
	        				//break;
	        				//gameOver=true;
	        			}
	        			
	    		}
	        }
    	
 	
       	        	
	        //Word move
		        for ( int i = word.size()-1;i>=0;i--) {
		            Tile inLetter = word.get(i);
		            if(i==0) {
		            	inLetter.x=box0.x;
		            	inLetter.y=box0.y;
		            }
		            
		            else {
		                Tile prevLetter = word.get(i-1);
		                inLetter.x=prevLetter.x;
		                inLetter.y=prevLetter.y;
		            }
     	      }
	        	
       	              
	        box0.x += velocityX;
			box0.y += velocityY;
			 
			//letterdone conditions
			if(gc==word1.size())
			letterdone();
  
	        //game over conditions
				
	    	
	        for (int i = 0; i < word.size(); i++) {
	            Tile inLetter = word.get(i);
	            //collide with box0
	            if (collision(box0, inLetter)) {
	                gameOver = true;
	            }
	        }

	        if (box0.x*tileSize < 0 || box0.x*tileSize > boardHeight || //passed left border or right border
	            box0.y*tileSize < 0 || box0.y*tileSize > boardHeight ) { //passed top border or bottom border
	            gameOver = true;
	        }
}
	    

	    public boolean collision(Tile tile1, Tile tile2) {
	        return tile1.x == tile2.x && tile1.y == tile2.y;
	    }
	    
	    public void letterdone() throws Exception {
	    	//if(gc==word1.size())
	    	 {
	    		gc=0;
	    		num++;
	    		Thread.sleep(1000);
	    		storage sg1 =new storage(num);
	    		letters l1=new letters(boardHeight,sg1);
	    		spellit.oneLetter(sg1, l1,boardWidth+300, boardHeight,num);	
	    	}
	    }
	    
	    
}
