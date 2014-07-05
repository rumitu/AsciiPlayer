package com.hackbulgaria.pairprogramming;

import java.io.File;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException,
			InterruptedException {
        File imageFile = new File(args[0]);
        int consolePixels = Integer.parseInt(args[1]);
        FilePlayer player;
        if (imageFile.isFile()) {
            if (imageFile.toString().endsWith(".gif")) {
				int speed = Integer.parseInt(args[2]);
				player = new GifPlayer(imageFile, consolePixels, speed);
			}

			else {
				player = new PicturePlayer(imageFile, consolePixels);
            }
            
            player.play();
        }
    }
}