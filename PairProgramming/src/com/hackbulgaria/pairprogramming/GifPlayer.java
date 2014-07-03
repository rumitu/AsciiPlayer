package com.hackbulgaria.pairprogramming;

import java.awt.image.BufferedImage;
import java.io.File;

public final class GifPlayer extends SequencePlayer {
    private GifDecoder decoder;
    private int lastFrameIndex;

    public GifPlayer(File sequenceFile, int consoleWidth) {
        super(sequenceFile, consoleWidth);
        this.decoder = new GifDecoder();
        this.lastFrameIndex = 0;
    }

    @Override
    protected BufferedImage getNextFrame() {
        BufferedImage frame = decoder.getFrame(lastFrameIndex);
        lastFrameIndex++;
        return frame;
    }

}
