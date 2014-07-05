package com.hackbulgaria.pairprogramming;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PicturePlayer extends FilePlayer {
    BufferedImage image;
    int consoleWidth;
    
    public PicturePlayer(File imageFile, int consoleWidth) throws IOException {
        this.image = ImageIO.read(imageFile);
        this.consoleWidth = consoleWidth;
    }

    @Override
    public void play() {
        ASCIIGenerator generator = new ASCIIGenerator(image);
        String asciiPicture = generator.getASCIIImage(consoleWidth);
        System.out.println(asciiPicture);
    }
    

}