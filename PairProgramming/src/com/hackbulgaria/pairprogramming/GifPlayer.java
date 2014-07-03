package com.hackbulgaria.pairprogramming;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class GifPlayer extends SequencePlayer {
    private GifDecoder decoder;
    private int lastFrameIndex;
    private int framesCount;

    public GifPlayer(File sequenceFile, int consoleWidth, int speed) throws IOException {
        super(sequenceFile, consoleWidth, speed);
        this.decoder = new GifDecoder();
        decoder.read(sequenceFile.toString());
        this.framesCount = decoder.getFrameCount();
        this.lastFrameIndex = 0;
    }

    @Override
    protected BufferedImage getNextFrame() {
        BufferedImage frame = null;
        
        if (lastFrameIndex < framesCount) {
            frame = decoder.getFrame(lastFrameIndex);
            lastFrameIndex++;
        }
        
        return frame;
    }

}
