package com.hackbulgaria.pairprogramming;

import java.io.IOException;


public abstract class FilePlayer implements Player {
    public abstract void play() throws IOException, InterruptedException;
}
