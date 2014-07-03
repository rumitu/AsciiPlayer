package com.hackbulgaria.pairprogramming;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;

public class ASCIIViewer {
    protected BufferedImage image;
    protected final int timesToRescale;
    protected final LinkedHashMap<Integer, Character> asciiIntensityTable;
    
    public ASCIIViewer(File imageFile, int consolePixelSize) throws IOException {
        image = ImageIO.read(imageFile);
        timesToRescale = getResclaeQuotient(consolePixelSize);
        asciiIntensityTable = getASCIIIntensityTable();
    }

//    public static void main (String[] args) throws IOException, InterruptedException {
//        File imageFile = new File(args[0]);
//        int consolePixels = Integer.parseInt(args[1]);
//        ASCIIViewer viewer;
//        if (imageFile.isFile()) {
//            if (imageFile.toString().endsWith(".gif")) {
//                viewer = new ASCIIGifViewer(imageFile, consolePixels);
//            }
//            
//            else {
//                viewer = new ASCIIViewer(imageFile, consolePixels);
//            }
//            
//            viewer.printASCIIArt();
//        }
//    }

    protected void printASCIIArt() throws IOException, InterruptedException {
        String asciiImage = getASCIIImage();
        System.out.println(asciiImage);
    }

    protected String getASCIIImage() {
        StringBuilder asciiImage = new StringBuilder();
   
        for (int i = 0; i < image.getHeight(); i += timesToRescale) {
            
            StringBuilder row = new StringBuilder();            
            for (int j = 0; j < image.getWidth(); j += timesToRescale) {
                
                int blockIntensity = getBlockIntensity(i, j);
                String symbol = translateToASCII(blockIntensity);
                row.append(symbol);
            }

            asciiImage.append(row.toString());
            asciiImage.append("\n");
        }
        
        return asciiImage.toString();
    }
    
    protected int getBlockIntensity(int i, int j) {
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
    
    
    protected LinkedHashMap<Integer, Character> getASCIIIntensityTable() {
        LinkedHashMap<Integer, Character> intensityTable = new LinkedHashMap<>();
        String asciiSymbols = "@%#*+=<>;:'. ";
        int step = (255 / asciiSymbols.length());
        int upperBound = step;
        for (int i = 0; i < asciiSymbols.length(); i++) {
            intensityTable.put(upperBound, asciiSymbols.charAt(i));
            upperBound += step;
        }
        return intensityTable;
    }

    
    protected String translateToASCII(int pixelIntensity) {
        String symbol = null;
        
        for (Integer uppedBound: asciiIntensityTable.keySet()) {                      
            if (pixelIntensity <= uppedBound) {
                symbol = asciiIntensityTable.get(uppedBound).toString();
                break;
            }
        }
        
        return symbol.toString();
    }

    
    protected int getResclaeQuotient(int consolePixelSize) {
        int quotient = image.getWidth() / consolePixelSize;
        if (quotient < 1) {
            return 1;
        }
        
        return quotient;
    }
}
