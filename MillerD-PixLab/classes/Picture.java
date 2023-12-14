package classes;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }

    public void keepOnlyBlue() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(0);
                pixelObj.setGreen(0);
            }
        }
    }

    public void negate() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(255-pixelObj.getBlue());
                pixelObj.setRed(255-pixelObj.getRed());
                pixelObj.setGreen(255-pixelObj.getGreen());
            }
        }
    }

    public void grayscale() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                int avgColor = (pixelObj.getBlue() + pixelObj.getRed() + pixelObj.getGreen()) / 3;
                pixelObj.setBlue(avgColor);
                pixelObj.setRed(avgColor);
                pixelObj.setGreen(avgColor);
            }
        }
    }

    public void fixUnderwater() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                if (pixelObj.getBlue() > 160) {
                    pixelObj.setBlue(pixelObj.getBlue() - 5);
                } else {
                    pixelObj.setBlue(pixelObj.getBlue() - 30);
                }

            }
        }
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    public void mirrorVerticalRightToLeft()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = (width / 2) - 1; col < width; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    public void mirrorHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length/2; row++)
        {
            for (int col = 0; col < width; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[pixels.length-1-row][col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    public void mirrorHorizontalBotToTop()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = (pixels.length/2)-1; row < pixels.length; row++)
        {
            for (int col = 0; col < width; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[pixels.length-1-row][col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    public void mirrorOpposite() {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length/2; row++)
        {
            for (int col = 0; col < width; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[(pixels.length)-1-row][width-1-col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    public void mirrorDiagonal() {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int length = pixels.length;
        int width = pixels[0].length;
        int diagonalLineLength = Math.min(length, width);
        for (int row = diagonalLineLength - 1; row >= 0; row--) 
        {
            for (int col = 0; col < diagonalLineLength; col++) 
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[col][row];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++)
            {

                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    public void mirrorArms()
    {
        int[][] loops = new int[2][4];
        loops[0][0] = 156; loops[0][1] = 195; loops[0][2] = 100; loops[0][3] = 170;
        loops[1][0] = 171; loops[1][1] = 195; loops[1][2] = 234; loops[1][3] = 294;
        for (int i = 0; i < 2; i++) {
            int mirrorPoint = loops[i][1];
            Pixel upPixel = null;
            Pixel downPixel = null;
            Pixel[][] pixels = this.getPixels2D();

            for (int row = loops[i][0]; row < mirrorPoint; row++)
            {
                for (int col = loops[i][2]; col < loops[i][3]; col++)
                {

                    upPixel = pixels[row][col];      
                    downPixel = pixels[mirrorPoint+mirrorPoint-row][col];
                    downPixel.setColor(upPixel.getColor());
                }
            }
        }
    }

    public void mirrorGull() {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        int mirrorPoint = 344;
        for (int row = 230; row < 325; row++) {
            for (int col = 235; col < mirrorPoint; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row+70][mirrorPoint-col+mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, 
    int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < toPixels.length; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            toCol < toPixels[0].length;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }
    
    public void copy2(Picture fromPic, 
    int startRow, int startCol, 
    int fromPicStartRow, int fromPicEndRow, 
    int fromPicStartCol, int fromPicEndCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = fromPicStartRow, toRow = startRow; 
        fromRow < fromPixels.length &&
        fromRow <= fromPicEndRow &&
        toRow < toPixels.length; 
        fromRow++, toRow++)
        {
            for (int fromCol = fromPicStartCol, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            fromCol <= fromPicEndCol &&
            toCol < toPixels[0].length;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    /** Method to create a collage of several pictures */
    public void createCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        this.copy(flower1,0,0);
        this.copy(flower2,100,0);
        this.copy(flower1,200,0);
        Picture flowerNoBlue = new Picture(flower2);
        flowerNoBlue.zeroBlue();
        this.copy(flowerNoBlue,300,0);
        this.copy(flower1,400,0);
        this.copy(flower2,500,0);
        this.mirrorVertical();
        this.write("collage.jpg");
    }
    
    public void myCollage() {
        Picture seagull = new Picture("seagull.jpg");
        Picture caterpillar = new Picture("caterpillar.jpg");
        Picture wall = new Picture("wall.jpg");
        Picture smallMyPicture = new Picture("blue-mark.jpg");
        caterpillar.mirrorOpposite();
        this.copy(caterpillar, 0, 0);
        wall.mirrorDiagonal();
        this.copy2(wall, 180, 0, 160, 440, 160, 440);
        smallMyPicture.mirrorHorizontal();
        smallMyPicture.mirrorVertical();
        smallMyPicture.mirrorDiagonal();
        smallMyPicture.negate();
        smallMyPicture.mirrorTemple();
        smallMyPicture.mirrorGull();
        smallMyPicture.mirrorVerticalRightToLeft();
        smallMyPicture.mirrorHorizontalBotToTop();
        this.copy2(smallMyPicture, 0, 350, 0, 600, 0, 600);
        this.copy2(seagull, 200, 400, 230, 325, 235, 344);
    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; 
            col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }
    
    public void edgeDetectionUpDown(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length-1; row++)
        {
            for (int col = 0; 
            col < pixels[0].length; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row+1][col];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture snowman = new Picture("snowman.jpg");
        snowman.explore();
    }

} // this } is the end of class Picture, put all new methods before this
