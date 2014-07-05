package com.hackbulgaria.pairprogramming;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.FileChannelWrapper;


public class VideoPlayer extends SequencePlayer {

	private final FrameGrab frameGrab;
	private final FileInputStream fileInputStream;

	public VideoPlayer(File sequenceFile, int consoleWidth) throws IOException,
			JCodecException {
		super(sequenceFile, consoleWidth);
		fileInputStream = new FileInputStream(sequenceFile);
		FileChannelWrapper wrapper = new FileChannelWrapper(
				fileInputStream.getChannel());
		this.frameGrab = new FrameGrab(wrapper);
	}

	@Override
	protected BufferedImage getNextFrame() {
		BufferedImage frame = null;
		try {
			frame = frameGrab.getFrame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return frame;
	}

}
