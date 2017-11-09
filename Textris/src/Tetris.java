import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tetris extends JPanel implements KeyListener
{
    private static final long serialVersionUID = 1L;
	
    static int numBlocks = 9;
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
        grid[19][5] = 1;

        new Tetris();
    }
    
    

    public Tetris()
    {
    	//now that Tetris is "extend"ing JPanel, I need to change its attributes and
    	//add a key listener to pick up the keystrokes when the frame is selected
    	addKeyListener(this);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setFocusable(true);
		
		f = new JFrame("Textris");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		f.addKeyListener(this);
		f.pack();
		f.setSize(450, 600);
		f.setLocation(850, 100);
		f.setVisible(true);
		
		Go();
    }

    public void Go()
	{
    	//moved the timer to a non-static method so that I could call it when the actual Tetris object is created.
		new java.util.Timer().schedule
		(
	  	        new java.util.TimerTask()
	  	        {
	  	            public void run()
	  	            {
	  	            	if(cantMoveDown(grid))
	  	            	{
	  	            		grid = moveDown(grid);
		  	            	repaint();
		  	            	replace(grid);
		  	            	repaint();
	  	            	}
	  	            	grid = moveDown(grid);
	  	            	repaint();
	  	            }
	  	        }, 1000, 1000
	  	 );		
	}
    
    public void checkRemove(int[][] grid)
    {
    	for(int i = 0; i < grid.length; i++)
    	{
    		boolean remove = false;
    		for(int j = 0; j < grid[i].length; j++)
    		{
    			remove = grid[i][j] == 1;
    		}
    		if(remove)
    		{
    			rowRemove(grid, i);
    		}
    	}
    }
    
    
    public void rowRemove(int[][] grid, int x)
    {
    	for(int i = 0; i < grid.length; i++)
    	{
    		
    	}
    }
    
    
    public void paint(Graphics g)
    {
    	//I added these values to make it easier to see and explain what I am doing
		final Color c1 = Color.RED;//Color corresponding to a "1"
		final Color c3 = Color.BLACK;//Color corresponding to a "3"
		final Color c4 = Color.BLUE;//etc.
		
		int xStart = 125;//starting x-value for the grid
		int xStep = 20;
		int xEnd = (grid[0].length * xStep) + xStart;//which is equal to 250
		
		int yStart = 100;
		int yStep = 20;
		int yEnd = (grid.length * yStep) + yStart;//which is equal to 430
		
		
		
        //using the given graphics "g" element to create a Graphics2D object to create 2D figures
		super.paint(g);
        Graphics2D g2D = (Graphics2D)g.create();
        Font f = new Font("Impact", Font.BOLD , 50);
        g2D.setFont(f);
        g2D.setColor(Color.YELLOW);
        g2D.drawString("Tetris", 150, 60);
        
        //Creates grid of lines based on the "Start", "Step", and "End" variables
        g2D.setFont(new Font("Arial", Font.PLAIN , 15));
        g2D.setColor(Color.WHITE);
        
        int CounterX = 1; 
        for(int x = xStart; x <= xEnd; x += xStep)
        {
        	if(x != xEnd)
        	{
        		g2D.drawString("" + CounterX, x + 4, yStart - 10);
        	}
        	g2D.drawLine(x, yStart, x, yEnd);
        	CounterX++;
        }
        int CounterY = 1;
        for(int y = yStart; y <= yEnd; y += yStep)
        {
        	if(y != yEnd)
        	{
        		g2D.drawString("" + CounterY, xStart - 25, y + 15);
        	}
        	g2D.drawLine(xStart, y, xEnd, y);
        	CounterY++;
        }
        
        //Adding squares to the grid while setting each number value to a certain color(set above)
        for(int i = 0; i < grid.length; i++)
        {
        	for(int j = 0; j < grid[i].length; j++)
        	{
        		if(grid[i][j] == 1)
        		{
        			g2D.setColor(c1);
        			g2D.fillRect((j * xStep) + xStart + 1, (i * yStep) + yStart + 1, xStep - 1, yStep - 1);
        		}
        		if(grid[i][j] == 3)
        		{
        			g2D.setColor(c3);
        			g2D.fillRect((j * xStep) + xStart + 1, (i * yStep) + yStart + 1, xStep - 1, yStep - 1);
        		}
        		if(grid[i][j] == 4)
        		{
        			g2D.setColor(c4);
        			g2D.fillRect((j * xStep) + xStart + 1, (i * yStep) + yStart + 1, xStep - 1, yStep - 1);
        		}
        	}
        }
        
        g2D.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g2D.setColor(Color.WHITE);
        //g2D.drawString("Created by Kartik Nagpal and Nathan Read", 25, 535);
        g2D.drawString("Created by Kartik Nagpal", 105, 535);
        
        g2D.dispose();
    }
    
    
    public static void replace(int[][] grid)
    {
        for (int row = grid.length - 1; row >= 0; row--)
        {
            for (int col = 0; col < grid[row].length; col++)
            {
                if (grid[row][col] > 2)
                {
                    if (grid[row][col] == 3)
                    {
                        grid[row][col] = 0;
                    }
                    if (grid[row][col] == 4)
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
    

    public static int[][] rotate(int[][] grid)
    {
    	System.out.println("Checkpoint 1===========================");
    	printText(grid);
    	
        int[][] matrix = new int[3][3];
        int[][] array = new int[3][3];
        int x = 0;
        int y = 0;
        for (int row = grid.length - 2; row >= 0; row--)
        {
            for (int col = 1; col < grid[row].length; col++)
            {
                if (grid[row][col] >= 2)
                {
                    x = row;
                    y = col;
                    break;
                }
            }
        }
        System.out.println("(" + x + ", " + y + ")");
        
        int counter = 0;
        for (int row = grid.length - 2; row >= 0; row--)
        {
            for (int col = 1; col < grid[row].length; col++)
            {
                if(grid[row][col] >= 2)
            	{
            		matrix[(int)(counter/3)][counter % 3] = grid[row][col];
            		counter++;
            	}
            }
        }
        
        System.out.println("Checkpoint 2===========================");
        printText(matrix);
        for (int i = 0; i < matrix[0].length; i++)
        {
            for (int j = matrix.length - 1; j >= 0; j--)
            {
                array[i][j] = matrix[j][i];
            }
        }
        
        System.out.println("Checkpoint 3===========================");
        printText(array);
        for (int i = x; i < grid.length; i++)
        {
            for (int j = y; j < grid[i].length; j++)
            {
                if (grid[i][j] >= 2)
                {
                    grid[i][j] = array[i - x][j - y];
                }
            }
        }
        System.out.println("\n\nEND---------------------");
        
        return grid;
    }
    
    
    
    public static boolean cantMoveDown(int[][] grid)
    {
        for (int row = grid.length - 2; row >= 0; row--)
        {
            for (int col = grid[row].length - 1; col >= 0; col--)
            {
                if (grid[row][col] > 3)
                {
                    if (row == grid.length - 2 || grid[row + 2][col] == 1)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean canMove(String dir, int[][] grid)
    {
    	if(dir.equalsIgnoreCase("left"))
    	{
    		for(int row = 0; row < grid.length; row++)
    		{
    			if(grid[row][0] == 4)
    			{
    				return false;
    			}
    		}
    		for (int row = 0; row < grid.length; row++)
            {
                for (int col = grid[row].length - 1; col > 0; col--)
                {
                	if(grid[row][col] > 2 && grid[row][col - 1] == 1)
                	{
                		return false;
                	}
                }
            }
    	}
    	else if(dir.equalsIgnoreCase("right"))
    	{
    		for(int row = 0; row < grid.length; row++)
    		{
    			if(grid[row][grid[row].length - 1] == 4)
    			{
    				return false;
    			}    				
    		}
    		for (int row = 0; row < grid.length; row++)
            {
                for (int col = grid[row].length - 2; col >= 0; col--)
                {
                	if(grid[row][col] > 2 && grid[row][col + 1] == 1)
                	{
                		return false;
                	}
                }
            }
    	}
    	return true;
    }

    public static int[][] moveDown(int[][] grid)
    {
        int counter = 0;
        for (int row = grid.length - 2; row >= 0; row--)
        {
            for (int col = 0; col < grid[row].length; col++)
            {
                if (grid[row][col] >= 2 && counter <= numBlocks)
                {
                	boolean x = false;
                	if(grid[row + 1][col] == 1)
                	{
                		x = true;
                	}
                    grid[row + 1][col] = grid[row][col];
                    grid[row][col] = 0;
                    if(x)
                    {
                    	grid[row + 1][col] = 1;
                    }
                    counter++;
                }
            }
        }
        return grid;
    }

    public static int[][] moveLeft(int[][] grid)
    {
    	if(!canMove("left", grid))
    		return grid;
        int counter = 0;
        for (int row = grid.length - 1; row >= 0; row--)
        {
            for (int col = 1; col < grid[row].length; col++)
            {
                if (grid[row][col] >= 2 && counter <= numBlocks)
                {
                    grid[row][col - 1] = grid[row][col];
                    counter++;
                    grid[row][col] = 0;
                }
            }
        }
        return grid;
    }

    public static int[][] moveRight(int[][] grid)
    {
    	if(!canMove("right", grid))
    	{
    		return grid;
    	}
        int counter = 0;
        for (int row = grid.length - 1; row >= 0; row--)
        {
            for (int col = grid[row].length - 2; col >= 0; col--)
            {
                if (grid[row][col] >= 2 && counter <= numBlocks)
                {
                    grid[row][col + 1] = grid[row][col];
                    grid[row][col] = 0;
                    counter++;
                }
            }
        }
        return grid;
    }
    
    public static void printText(int[][] grid)
    {
        /*for (int row = 0; row < grid.length; row++)
        {
            for (int col = 0; col < grid[row].length; col++)
            { // Display contents of cell
                System.out.print(grid[row][col] + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------------");*/
    }


    public void keyPressed(KeyEvent e)
    {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_UP)
        {
        	grid = rotate(grid);
            repaint();
        }
        if (k == KeyEvent.VK_LEFT)
        {
            grid = moveLeft(grid);
            repaint();
        }
        if (k == KeyEvent.VK_RIGHT)
        {
            grid = moveRight(grid);
            repaint();
        }
        if (k == KeyEvent.VK_DOWN)
        {
        	if(cantMoveDown(grid))
          	{
          		grid = moveDown(grid);
	            repaint();
	            replace(grid);
	            repaint();
          	}
          	grid = moveDown(grid);
          	repaint();
        }
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}
}