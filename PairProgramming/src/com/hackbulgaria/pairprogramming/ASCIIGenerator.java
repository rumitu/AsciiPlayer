package com.hackbulgaria.pairprogramming;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ASCIIGenerator {
    private BufferedImage image;
    
    public ASCIIGenerator(BufferedImage image) {
        this.image = image;
    }
    
    protected void setImage(BufferedImage newImage) {
        this.image = newImage;
    }

    public String getASCIIImage(int consoleWidth) {
        int timesToRescale = getResclaeQuotient(consoleWidth);
        StringBuilder asciiImage = new StringBuilder();
   
        for (int i = 0; i < image.getHeight(); i += timesToRescale) {
            
            StringBuilder row = new StringBuilder();            
            for (int j = 0; j < image.getWidth(); j += timesToRescale) {
                
                int blockIntensity = getBlockIntensity(i, j, timesToRescale);
                String symbol = translateToASCII(blockIntensity);
                row.append(symbol);
            }

            asciiImage.append(row.toString());
            asciiImage.append("\n");
        }
        
        return asciiImage.toString();
    }
    
    protected String translateToASCII(int pixelIntensity) {
        String symbol = null;
        
        if (pixelIntensity > 240) {
            symbol = "@";
        }
        
        else if (pixelIntensity > 200 && pixelIntensity <= 220) {
            symbol = "%";
        }
        
        else if (pixelIntensity > 180 && pixelIntensity <= 200) {
            symbol = "#";
        }
        
        else if (pixelIntensity > 160 && pixelIntensity <= 180) {
            symbol = "*";
        }
        
        else if (pixelIntensity > 140 && pixelIntensity <= 160) {
            symbol = "+";
        }
        
        else if (pixelIntensity > 120 && pixelIntensity <= 140) {
            symbol = "=";
        }
        
        else if (pixelIntensity > 100 && pixelIntensity <= 120) {
            symbol = "<";
        }
        
        
        else if (pixelIntensity > 80 && pixelIntensity <= 100) {
            symbol = ";";
        }
        
        else if (pixelIntensity > 60 && pixelIntensity <= 80) {
            symbol = ":";
        }
        
        else if (pixelIntensity > 40 && pixelIntensity <= 60) {
            symbol = "'";
        }
        
        else if (pixelIntensity > 20 && pixelIntensity <= 40) {
            symbol = ".";
        }
        
        else {
            symbol = " ";
        }
        
        return symbol;
    }
    
    
    protected int getBlockIntensity(int i, int j, int timesToRescale) {
        int average = 0;
        for (int x = i; x < i + timesToRescale; x++) {
            for (int y = j; y < j + timesToRescale; y++) {
                if (inBounds(x, y)) {
                    Color color = new Color(image.getRGB(y, x));
                    average += getPixelIntensity(color);
                }
            }
        }
        
        return average / (timesToRescale * timesToRescale);
    }

    protected int getPixelIntensity(Color color) {
        return ( color.getBlue() + color.getGreen() + color.getRed() ) / 3;
    }

    protected boolean inBounds(int x, int y) {
        boolean xIn = x >= 0 && x < image.getHeight();
        boolean yIn = y >= 0 && y < image.getWidth();
        return xIn && yIn;
    }
    
    protected int getResclaeQuotient(int consolePixelSize) {
        int quotient = image.getWidth() / consolePixelSize;
        if (quotient < 1) {
            return 1;
        }
        
        return quotient;
    }
}
