package com.hackbulgaria.pairprogramming;

import java.awt.image.BufferedImage;
import java.io.File;

public abstract class SequencePlayer extends FilePlayer {
    protected File sequenceFile;
    protected BufferedImage currentFrame;
    protected int consoleWidth;
    
    public SequencePlayer(File sequenceFile, int consoleWidth) {
        this.sequenceFile = sequenceFile;
        this.consoleWidth = consoleWidth;        
    }
    
    public void play() {
        ASCIIGenerator generator = new ASCIIGenerator(null);
        while ((currentFrame = getNextFrame()) != null) {
            generator.setImage(currentFrame);
            String asciiFrame = generator.getASCIIImage(consoleWidth);
            System.out.println(asciiFrame);
        }
    }

    protected abstract BufferedImage getNextFrame();
}
