/**
 * Marbles.java
 * Description Here
 * @author Ronald Chan
 * @version 1.0
 * @since 1/9/2018
 */
import java.awt.Color;
import java.awt.Font;

public class Marbles
{
    /**    The board object.  1 represents a marble on the board, 0 is an empty space,
     *     and -1 would indicate that this cell is not part of the board.                  */
    private int [][] board;
    
    /**    How long the GUI should pause, before expecting new input.                      */
    private int pause;
    
    /**    Current x and y values of the user's choice.  The x values count the cells
     *     from the lower left to the right, while the y values count the cells from
     *     the bottom left up.                                                             */
    private int xposition, yposition;
    
    private int marbleMode=3;
        
    /**
     *  Creates a Marbles object, with the font to be used, current position initially
     *  pause off the board, pause at 50 milliseconds, and the board values initialized
     *  in a 9 x 9 grid.
     */
    public Marbles ( )
    {
        Font font = new Font("Arial", Font.BOLD, 18);
        StdDraw.setFont(font);
        xposition = yposition = -5;
        pause = 50;
        board = new int[][]{{-1,-1,-1,1,1,1,-1,-1,-1},{-1,-1,-1,1,1,1,-1,-1,-1},{-1,-1,-1,1,1,1,-1,-1,-1},
                        {1,1,1,1,1,1,1,1,1},{1,1,1,1,0,1,1,1,1},{1,1,1,1,1,1,1,1,1},
                        {-1,-1,-1,1,1,1,-1,-1,-1},{-1,-1,-1,1,1,1,-1,-1,-1},{-1,-1,-1,1,1,1,-1,-1,-1}};
    }
    
    /**
     *  Sets up and runs the game of Marbles.
     *  @param  args     An array of String arguments (not used here).
     */
    public static void main(String [] args)   
    {
        Marbles run = new Marbles();
        run.playGame();
    }
    
    /**
     *  Comments.
     */
    public void playGame ( )
    {
        boolean done = false;
        do
        {
            drawBoard();
            if(StdDraw.mousePressed())
            {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                int checkx = (int)(10 * x - 0.5);
                int checky = (int)(10 * y - 0.5);
                if(reset(x,y))
                {
                    xposition = yposition = -5;
                }
                else if(theme(x,y))
                {
                    xposition = yposition = -5;
                }
                else if(possibleMoveSpace(xposition,yposition,checkx,checky))
                {
                    disappear(xposition,yposition);
                    if(xposition>checkx)
                    {
                        board[xposition][yposition]=0;
                        board[xposition-1][yposition]=0;
                        board[checkx][checky]=1;                        
                    }
                    else if(xposition<checkx)
                    {
                        board[xposition+1][yposition]=0;
                        board[checkx][checky]=1;
                        board[xposition][yposition]=0;
                    }               
                    else if(yposition>checky)
                    {
                        board[xposition][yposition-1]=0;
                        board[checkx][checky]=1;
                        board[xposition][yposition]=0;
                    }
                    else
                    {
                        board[xposition][yposition+1]=0;
                        board[checkx][checky]=1;
                        board[xposition][yposition]=0;
                    }
                    reappear(checkx,checky);
                    if(gameIsFinished())
                    {
                        done=true;
                        drawBoard();
                    }
                    xposition=yposition=-5;
                    StdDraw.show(4 * pause);
                }
                else
                {
                    xposition = checkx;
                    yposition = checky;
                    StdDraw.show(pause);
                }
            }
            StdDraw.show(pause);
            
        }
        while(!done);
        System.out.println("im finished");
    }
    
    public void disappear(double x,double y)
    {
        String fileName="";
        if(marbleMode==0)
        {
            fileName="deruiter.png";
        }
        else if(marbleMode==1)
        {
            fileName="chan.png";
        }
        else if(marbleMode==2)
        {
            fileName="kim.png";
        }
        else
        {
            fileName="marble.png";
        }
        for(double i=40;i>=0;i--)
        {
            drawBoard();
            StdDraw.setPenColor(255,(int)(255-((40-i)*6)),(int)(255-((40-i)*6)));
            StdDraw.filledSquare(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.0425);
            StdDraw.picture(0.1 + 0.1 * x, 0.1 + 0.1 * y,fileName,i/1000,i/1000,(40-i)*15);
            StdDraw.show(6);
        }
    }
    public void reappear(double x, double y)
    {
        String fileName="";
        if(marbleMode==0)
        {
            fileName="deruiter.png";
        }
        else if(marbleMode==1)
        {
            fileName="chan.png";
        }
        else if(marbleMode==2)
        {
            fileName="kim.png";
        }
        else
        {
            fileName="marble.png";
        }
        for(double i=0;i<=40;i++)
        {
            StdDraw.show(6);
            drawBoard();
            StdDraw.setPenColor((int)(i*6),255,(int)(i*6));
            StdDraw.filledSquare(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.0425);
            StdDraw.picture(0.1 + 0.1 * x, 0.1 + 0.1 * y,fileName,i/1000,i/1000,(40-i)*15);
            
        }
    }
    /**
     *  Comments.
     */
    public void drawBoard ( )
    {
        StdDraw.setPenColor(new Color(0,0,0));
        StdDraw.filledSquare(0.5,0.5,0.5);
        StdDraw.setPenColor(new Color(5,25,160));
        StdDraw.filledSquare(0.5,0.5,0.475);
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                if(board[i][j] != -1)
                {
                    drawCell(i,j);
                }
            }
        }
        drawResetButtons();
        drawWinOrLoseMessage();

    }
    
    /**
     *  Comments.
     */
    public void drawResetButtons ( )
    {
        StdDraw.setPenColor(new Color(0,0,0));
        StdDraw.filledRectangle(0.8, 0.25, 0.125, 0.05);
        StdDraw.filledRectangle(0.8, 0.125, 0.125, 0.05);
        StdDraw.filledRectangle(0.1, 0.125,0.05,0.05);
        StdDraw.picture(.1, 0.125,"deruiter.png");
        StdDraw.filledRectangle(0.1, 0.25,0.05,0.05);
        StdDraw.picture(0.1,0.25,"chan.png");
        StdDraw.filledRectangle(0.225,0.25,0.05,0.05);
        StdDraw.picture(0.225,0.25,"kim.png");
        StdDraw.filledRectangle(0.225,0.125,0.05,0.05);
        StdDraw.picture(0.225,0.125,"marble.png");
        StdDraw.setPenColor(new Color(255,255,255));
        StdDraw.text(0.8, 0.25, "RESET 7 x 7");
        StdDraw.text(0.8,0.125, "RESET 9 x 9");
        
    }
    
    /**
     *  Comments.
     */
    public void drawWinOrLoseMessage ( )
    {
        if(gameIsFinished())
        {
            String message;
            if(countMarbles()==1)
            {
                message=new String("YOU WIN!");
            }
            else
            {
                message=new String("YOU LOSE!");
            }
            StdDraw.setPenColor(new Color(255,255,255));
            StdDraw.filledRectangle(0.5,0.5,0.3,.055);
            StdDraw.setPenColor(new Color(0,0,0));
            StdDraw.text(0.5,0.5,message);
        }
    }
    
    /**
     *  Comments.
     */
    public boolean reset(double x, double y)
    {
        if(x>=0.675&&x<=.925)
        {
            if(y>=0.075&&y<=0.175)
            {
                board = new int[][]{{-1,-1,-1,1,1,1,-1,-1,-1},
                                    {-1,-1,-1,1,1,1,-1,-1,-1},
                                    {-1,-1,-1,1,1,1,-1,-1,-1},
                                    {1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,0,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1},
                                    {-1,-1,-1,1,1,1,-1,-1,-1},
                                    {-1,-1,-1,1,1,1,-1,-1,-1},
                                    {-1,-1,-1,1,1,1,-1,-1,-1}};
                drawBoard();
                return true;
            }
            else if(y>=0.2&&y<=0.3)
            {
                board = new int[][]{{-1,-1,-1,-1,-1,-1,-1,-1,-1},
                                    {-1,-1,-1,1,1,1,-1,-1,-1},
                                    {-1,-1,-1,1,1,1,-1,-1,-1},
                                    {-1,1,1,1,1,1,1,1,-1},
                                    {-1,1,1,1,0,1,1,1,-1},
                                    {-1,1,1,1,1,1,1,1,-1},
                                    {-1,-1,-1,1,1,1,-1,-1,-1},
                                    {-1,-1,-1,1,1,1,-1,-1,-1},
                                    {-1,-1,-1,-1,-1,-1,-1,-1,-1}};
                drawBoard();
                return true;
            }
        }
        return false;
    }
    
    public boolean theme(double x, double y)
    {
        if(x>=.05&&x<=0.15&&y>=0.2&&y<=0.3)
        {
            marbleMode=1;
            return true;
        }
        else if(x>=.05&&x<=0.15&&y>=0.075&&y<=0.175)
        {
            marbleMode=0;
            return true;
        }
        else if(x>=0.175&&x<=0.275&&y>=0.2&&y<=0.3)
        {
            marbleMode=2;
            return true;
        }
        else if(x>=0.175&&x<=0.275&&y>=0.075&&y<=0.175)
        {
            marbleMode=3;
            return true;
        }
        return false;
    }
    
    /**
     *  Comments.
     */
    public void drawCell(int x, int y)   
    {
        StdDraw.setPenColor(new Color(0,0,0));
        StdDraw.filledSquare(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.055);
        StdDraw.setPenColor(new Color(255,255,255));
        StdDraw.filledSquare(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.0425);
        StdDraw.setPenColor(new Color(200,200,200));
        StdDraw.filledCircle(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.02);
        if(x == xposition && y == yposition && board[x][y] == 1)
        {
            StdDraw.setPenColor(new Color(0,0,0));
            StdDraw.filledSquare(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.05);
            StdDraw.setPenColor(new Color(230,30,30));
            StdDraw.filledCircle(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.04);
        }
        if(possibleMoveSpace(xposition,yposition,x,y))
        {
            StdDraw.setPenColor(new Color(0,0,0));
            StdDraw.filledSquare(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.05);
            StdDraw.setPenColor(new Color(230,30,30));
            StdDraw.filledCircle(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.03);
        }
        if(board[x][y] == 1)
        {
            String file="";
            switch(marbleMode)
            {
                case 0:
                    file="deruiter.png"; break;
                case 1:
                    file="chan.png"; break;
                case 2:
                    file="kim.png"; break;
                case 3:
                    file="marble.png"; break;
            }
            StdDraw.picture(0.1 + 0.1 * x, 0.1 + 0.1 * y,file);
        }
    }
    
    /**
     * Checks if a cell surrounding the user's choice is a valid move, 
     * requiring that it is exactly 1 cell apart from the selected cell, 
     * it is empty, and has another marble separating from the user 
     * selected cell. 
     * 
     * @param           x coordinate of the user selected marble
     *                  y coordinate of the user selected marble
     *                  x coordinate of the cell to check
     *                  y coordinate of the cell to check
     * 
     * @return          true if can move to cell according to rules
     *                  else false
     */
    public boolean possibleMoveSpace(int x, int y, int xval, int yval)
    {
        if(x<0||y<0||board[x][y]!=1||board[xval][yval]!=0)
        {
            return false;
        }
        else if(Math.abs(x-xval)==2&&y==yval)
        {
            if(x>xval)
            {
                if(board[x-1][y]==1)
                {
                    return true;
                }
            }
            else
            {
                if(board[x+1][y]==1)
                {
                    return true;
                }
            }
        }
        else if(Math.abs(y-yval)==2&&x==xval)
        {
            if(y>yval)
            {
                if(board[x][y-1]==1)
                {
                    return true;
                }
            }
            else
            {
                if(board[x][y+1]==1)
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     *  Comments.
     */
    public boolean gameIsFinished()
    {
        if(countMarbles()==1)
        {
            return true;
        }
        else 
        {
            for(int i=0;i<board.length;i++)
            {
                for(int j=0;j<board[i].length;j++)
                {
                    for(int x=0;x<board.length;x++)
                    {
                        for(int y=0;y<board[x].length;y++)
                        {
                            if(board[i][j]==1&&possibleMoveSpace(i,j,x,y))
                            {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    
    /**
     *  Comments.
     */
    public int countMarbles ( )
    {
        int count=0;
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[i].length;j++)
            {
                if(board[i][j]==1)
                {
                    count++;
                }
            }
        }
        return count;
    }
}
