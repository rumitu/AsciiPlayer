package com.hackbulgaria.pairprogramming;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import jline.ConsoleReader;

public abstract class SequencePlayer extends FilePlayer {
    protected File sequenceFile;
    ConsoleReader reader;
    protected BufferedImage currentFrame;
    protected int consoleWidth;
    protected int speed;
    
    public SequencePlayer(File sequenceFile, int consoleWidth, int speed) throws IOException {
        this.sequenceFile = sequenceFile;
        this.consoleWidth = consoleWidth;
        this.speed = speed;
        this.reader = new ConsoleReader(System.in, new PrintWriter(System.out));
    }
    
    public void play() throws IOException, InterruptedException {
        ASCIIGenerator generator = new ASCIIGenerator(null);
        while ((currentFrame = getNextFrame()) != null) {
            generator.setImage(currentFrame);
            String asciiFrame = generator.getASCIIImage(consoleWidth);
            System.out.println(asciiFrame);
            Thread.sleep(speed);
            reader.clearScreen();
        }
    }

    protected abstract BufferedImage getNextFrame();
}
