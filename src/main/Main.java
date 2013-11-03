package main;


import java.awt.Component;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.FPSAnimator;

public class Main implements GLEventListener, KeyListener{
	
	private double theta = 0;
	private double s = 0;
	private double c = 0;
	
    public static void main(String[] args) {
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);

        Frame frame = new Frame("AWT Window Test");
        frame.setSize(1200, 800);
        frame.add(canvas);
        frame.setVisible(true);
        
        // by default, an AWT Frame doesn't do anything when you click
        // the close button; this bit of code will terminate the program when
        // the window is asked to close
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        canvas.addGLEventListener(new Main());
                
        FPSAnimator animator = new FPSAnimator(canvas, 60);
        //animator.add(canvas);
        animator.start();
    }

	@Override
	public void display(GLAutoDrawable drawable) {
		update();
		render(drawable);
	}

	private void update() {
	    theta += 0.01;
	    s = Math.sin(theta);
	    c = Math.cos(theta);
	}
	
	private void render(GLAutoDrawable drawable){
		GL2 gl = drawable.getGL().getGL2();
				
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	    // draw a triangle filling the window
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(1, 0, 0);
		gl.glVertex3d(0.0, 0.0, -c);
		gl.glColor3f(0, 1, 0);
		gl.glVertex3d(0.0, 1.0, s);
		gl.glColor3f(0, 0, 1);
		gl.glVertex3d(1.0, 1.0, c);
		gl.glColor3f(0, 0, 1);
		gl.glVertex3d(1.0, 0.0, -s);
		gl.glEnd();
		
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
	    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	      
	    ((Component) drawable).addKeyListener(this);
	    
	    gl.glLoadIdentity();		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e)
    {
      if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
    	System.exit(0);
    }

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}