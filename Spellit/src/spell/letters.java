package spell;

import java.util.*;

class letters{
	
	String s;
	String loc;
	int boardHeight;
	Random random=new Random();
	int x;
	int y;
	ArrayList<Integer> posx= new ArrayList<>();
	ArrayList<Integer> posy= new ArrayList<>();
	ArrayList<String> word1;
    
    
    //Constructor
	letters(int boardHeight, storage sg){
		loc=sg.loc;
		ArrayList<String> repeat= new ArrayList<>();		
		this.s=sg.ds;
		String[] strsplit=s.split("");	

	    word1= new ArrayList<String>(Arrays.asList(strsplit));
		
	
	this.boardHeight=boardHeight;
    Iterator it= word1.iterator();	
		
	while(it.hasNext()) {
		
		x=random.nextInt(20);
		y=random.nextInt(20);
		posx.add(x);
		posy.add(y);
		it.next();
	}
		
    	for(int i=0;i<s.length();i++) {
    		for(int j=i+1;j<s.length();j++) {
    			if(s.charAt(i)==s.charAt(j)) {
    				posx.set(j,posx.get(i));
    				posy.set(j,posy.get(i));
    				   }
    				}  		
    			}  			
	}
}

