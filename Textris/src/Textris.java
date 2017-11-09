import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Textris extends JPanel implements KeyListener
{
	private static final long serialVersionUID = 1L;
	int numBlocks = 9;
	static int[][] grid = new int[20][10];
	JFrame f;
	
	public static void main(String[] args) 
	{
		grid[1][3] = 4;
	    grid[1][4] = 4;
	    grid[1][5] = 4;
	    grid[2][3] = 3;
	    grid[2][4] = 4;
	    grid[2][5] = 3;
	    grid[3][3] = 3;
	    grid[3][4] = 3; 
	    grid[3][5] = 3;
	    grid[18][5] = 1;
      
	    new Textris();
	 }
  	
	public Textris()
	{
		setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setFocusable(true);
		
		f = new JFrame("Textris");
		f.add(this);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.addKeyListener(this);		
		f.pack();
		f.setSize(500, 650);
		f.setLocation(200, 100);
		f.setVisible(true);
		
		Go();
	}
	
	public void Go()
	{
		new java.util.Timer().schedule(
	  	        new java.util.TimerTask()
	  	        {
	  	            public void run()
	  	            {
	  	            	if(cantMoveDown(grid))
	  	            	{
	  	            		grid = moveDown(grid);
		  	            	print(grid);
		  	            	replace(grid);
		  	            	print(grid);
	  	            	}
	  	            	grid = moveDown(grid);
	  	            	//print(grid);
	  	            	repaint();
	  	            }
	  	        }, 500, 500);		
	}
	
	public void paint(Graphics g)
    {
		final Color c1 = Color.RED;
		final Color c3 = Color.GRAY;
		final Color c4 = Color.BLUE;
		int xStart = 30;
		int xEnd = 250;
		int yStart = 30;
		int yEnd = 430;
		
		print(grid);
        super.paint(g);
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setColor(Color.WHITE);
        int CounterX = 0; 
        for(int x = xStart; x <= xEnd; x += 20)
        {
        	if(x != xEnd)
        		g2D.drawString("" + CounterX, x + 5, 20);
        	g2D.drawLine(x, yStart, x, yEnd);
        	CounterX++;
        }
        int CounterY = 0;
        for(int y = yStart; y <= yEnd; y += 20)
        {
        	if(y != yEnd)
        		g2D.drawString("" + CounterY, 10, y + 15);
        	g2D.drawLine(xStart, y, xEnd, y);
        	CounterY++;
        }

        for(int i = 0; i < grid.length; i++)
        {
        	for(int j = 0; j < grid[i].length; j++)
        	{
        		if(grid[i][j] == 1)
        		{
        			g2D.setColor(c1);
        			//System.out.println("Data for 1: (" + i + ", " + j + ")");
        			g2D.fillRect((j * 20) + 31, (i * 20) + 31, 19, 19);
        		}
        		if(grid[i][j] == 3)
        		{
        			g2D.setColor(c3);
        			//System.out.println("Data for 3: (" + i + ", " + j + ")");
        			g2D.fillRect((j * 20) + 31, (i * 20) + 31, 19, 19);
        		}
        		if(grid[i][j] == 4)
        		{
        			g2D.setColor(c4);
        			//System.out.println("Data for 4: (" + i + ", " + j + ")");
        			g2D.fillRect((j * 20) + 31, (i * 20) + 31, 19, 19);
        		}
        	}
        }
        
        g2D.dispose();
    }
	
	
	public static void replace(int[][] grid)
	{
		for (int row = grid.length - 1; row >= 0; row--)
	   {
		   for (int col = 0; col < grid[row].length; col++)
		   {
			   	 if(grid[row][col] > 2)
				 {
				 	 if(grid[row][col] == 3)
					 {
						 grid[row][col] = 0;
					 }
					 if(grid[row][col] == 4)
					 {
						 grid[row][col] = 1;
					 }
				 }
		   }
	   }
	   grid[1][3] = 4;
	   grid[1][4] = 4;
	   grid[1][5] = 4;
	   grid[2][3] = 3;
	   grid[2][4] = 4;
	   grid[2][5] = 3;
	   grid[3][3] = 3;
	   grid[3][4] = 3; 
	   grid[3][5] = 3;
    }
  
    
  
  public static void set(int[][] x)
  {
   	    grid = x;
  } 
  
    public static boolean cantMoveDown(int[][] grid)
	{
		for (int row = grid.length - 2; row >= 0; row--)
		{
			 for (int col = grid[row].length - 1; col >= 0; col--)
			 {
				 if(grid[row][col] > 2)
				 {
					 if(grid[row + 1][col] == 1 || row == grid.length - 2) 
					 {
						 return true;
					 }
				 }
			 }
		}
		return false;
	}
  
  
  
  
   public static int[][] rotate(int[][] grid)
   {
	    //CLOCKWISE
	    int[][] matrix = new int[3][3];
   		int[][] array = new int [3][3];
   		int x = 0;
		int y = 0;
   	   	for (int row = grid.length - 2; row >= 0; row--)
		{
			 for (int col = 1; col < grid[row].length; col++)
			 {  
				 if(grid[row][col] >= 2)
				 {
					 x = row;
					 y = col;
					 break;
				 }					 
			 }
		}
   
   	   int counter = 0;
	   for (int row = grid.length - 2; row >= 0; row--)
	   {
			 for (int col = 1; col < grid[row].length; col++)
			 {  
				 if(grid[row][col] >= 2 && counter < 3 )
				 {
					 matrix[0][counter] = grid[row][col];    					 
					 counter++;     
				 }    	
				 else if(grid[row][col] >= 2 && counter < 6 )
				 {
					 matrix[1][counter-3] = grid[row][col];    					 
					 counter++;     
				 }   
				 else if(grid[row][col] >= 2 && counter < 9 )
				 {
					 matrix[2][counter-6] = grid[row][col];    					 
					 counter++;     
				 }   
			 }   
	   }

		   
		   
		for(int i = 0; i < matrix[0].length; i++)
		{
	        for(int j=matrix.length-1; j>=0; j--)
	        {
	            array[i][j] = matrix[j][i];
	        }
	    }
		   
    	   	
    	for(int i = x; i < grid.length; i++)
        {
          for(int j = y; j < grid[i].length; j++)
          {
            if(grid[i][j] >= 2)
            {
             	grid[i][j] = array[i - x][j - y];
            }
          }
        }
    	
    	return grid;
    }
	   
	  
	  
	
    public static int[][] moveDown(int[][] grid)
    {
		int test = 0;
		for (int row = grid.length - 2; row >= 0; row--)
		{
		 for (int col = 0; col < grid[row].length; col++)
		 {
		    if(grid[row][col] >= 2 && test <= 9)
		    {
		          grid[row + 1][col] = grid[row][col];
		          grid[row][col] =  0;
		          test++;
		    }
		  }
		}
		return grid;
    }

    public static int[][] moveLeft(int[][] grid)
    {
	    int test = 0;
		    for (int row = grid.length - 1; row >= 0; row--)
		    {
			    for(int col = 1; col < grid[row].length; col++)
			    {
				    if(grid[row][col] >= 2 && test <= 9)
				    {
					    grid[row][col - 1] = grid[row][col];
					    test++;
					    grid[row][col] =  0;
				    }
			    }
		    }
	    return grid;
    }
		
    public static int[][] moveRight(int[][] grid)
    {
    	int test = 0;
    	for (int row = grid.length - 1; row  >= 0; row--)
    	{
    		for (int col = grid[row].length - 2; col >= 0; col--)
    		{
    			if(grid[row][col] >= 2 && test <= 9)
    			{
    				grid[row][col + 1] = grid[row][col];
    				grid[row][col] =  0;
    				test++;
    			}
    		}
    	}
    	return grid;
    }
    
	public static void print(int[][] grid)
	{
		for (int row = 0; row < grid.length; row++)
		{
			for (int col=0; col<grid[row].length; col++)
			{	// Display contents of cell
				System.out.print(grid[row][col] + " ");
			}
			System.out.println("");
		}
	    System.out.println("-----------------");
	}


	public void keyPressed(KeyEvent e) 
	{
		System.out.println("Pressed");
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_UP)
		{
			grid = rotate(grid);
			print(grid);
		}
		if(k == KeyEvent.VK_LEFT)
		{
			grid = moveLeft(grid);
			print(grid);
		}
		if(k == KeyEvent.VK_RIGHT)
		{
			grid = moveRight(grid);
			print(grid);
		}
		if(k == KeyEvent.VK_DOWN)
		{
			grid = moveDown(grid);
			print(grid);
		}
	}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}
}