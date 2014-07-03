package com.hackbulgaria.pairprogramming;

import java.io.File;
import java.io.IOException;

import jline.ConsoleReader;

public class ASCIIGifViewer extends ASCIIViewer {
    private int frameCount;
    private GifDecoder decoder;

    public ASCIIGifViewer(File imageFile, int consolePixelSize) throws IOException {
        super(imageFile, consolePixelSize);
        decoder = new GifDecoder();
        decoder.read(imageFile.toString());
        frameCount = decoder.getFrameCount();
    }
    
    @Override
    protected void printASCIIArt() throws IOException, InterruptedException {
        ConsoleReader reader = new ConsoleReader();
        reader.clearScreen();
        for (int i = 0; i < frameCount; i++) {
            image = decoder.getFrame(i);
            String asciiImage = getASCIIImage();
            System.out.println(asciiImage);
            Thread.sleep(50);
            reader.clearScreen();
        }
    }
}
