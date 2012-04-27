import pFaceDetect.*;
import JMyron.*;
import processing.core.*;

public class FaceDetect extends PApplet {

	PFaceDetect face;
	JMyron m;
	PImage img;
	PImage my_img;

	public void setup() {
		size(320, 240);
		m = new JMyron();
		m.start(width, height);
		m.findGlobs(0);
		face = new PFaceDetect(this, width, height,
				"C:/Users/roshani/workspace/SimpleProject/haarcascade_frontalface_default.xml");
		frameRate(15);
		my_img = loadImage("C:/Users/roshani/workspace/SimpleProject/src/heart.png");
		img = createImage(width, height, ARGB);
		rectMode(CORNER);		
		noFill();
		stroke(255, 0, 0);
		smooth();
	}

	public void draw() {
		background(0);
		m.update();
		arraycopy(m.cameraImage(), img.pixels);
		img.updatePixels();
		face.findFaces(img);
		image(img, 0, 0);
		drawFace();
	}

	void drawFace() {
		int[][] res = face.getFaces();
		if (res.length > 0) {
			for (int i = 0; i < res.length; i++) {
				int x = res[i][0];
				int y = res[i][1];
				int w = res[i][2];
				int h = res[i][3];
				//ellipse(x+w/2, y+h/2, w, h);
				//rect(x, y, w, h);
				image(my_img, x+w/5, y+h/4,w/4,h/4);
				image(my_img, x+3*w/5, y+h/4,w/4,h/4);
			}
		}
	}

	public void stop() {
		m.stop();
		super.stop();
	}

}
