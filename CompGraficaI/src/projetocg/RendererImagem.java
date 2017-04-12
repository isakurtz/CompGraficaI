/**
 
 * @author Marcelo Cohen, Isabel H. Manssour 
 * @version 1.0
 */
package projetocg;

import java.awt.event.*; 
import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import com.sun.opengl.util.*;
import java.nio.FloatBuffer;

public class RendererImagem extends MouseAdapter implements GLEventListener, KeyListener
{
	// Atributos
	private GL gl;
	private GLU glu;
	private GLUT glut;
	private GLAutoDrawable glDrawable;
	private double fAspect;
	private Imagem imgs[], nova;
	private int sel;
	
	
	public RendererImagem(Imagem imgs[])
	{
		// Inicializa o valor para corre�ão de aspecto   
		fAspect = 1;

		// Imagem carregada do arquivo
		this.imgs = imgs;
		nova = null;
		sel = 0;	// selecionada = primeira imagem
	}

	   
	public void init(GLAutoDrawable drawable)
	{
		glDrawable = drawable;
		gl = drawable.getGL();
		// glu = drawable.getGLU();       
		glu = new GLU();
		glut = new GLUT();

		drawable.setGL(new DebugGL(gl));        

		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		// Define a janela de visualiza�ão 2D
		gl.glMatrixMode(GL.GL_PROJECTION);
		glu.gluOrtho2D(0,1,0,1);
		gl.glMatrixMode(GL.GL_MODELVIEW);
	}
    public void display(GLAutoDrawable drawable)
	{
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );
		gl.glLoadIdentity();    

		gl.glColor3f(0.0f, 0.0f, 1.0f);

		// Desenha a imagem original
		gl.glRasterPos2f(0,0);
		gl.glDrawPixels(imgs[sel].getWidth(), imgs[sel].getHeight(),
			       GL.GL_BGR, GL.GL_UNSIGNED_BYTE, imgs[sel].getData());

		// Desenha a imagem resultante
		if(nova!=null) {
			gl.glRasterPos2f(0.5f,0);
			gl.glDrawPixels(nova.getWidth(), nova.getHeight(),
			       GL.GL_BGR, GL.GL_UNSIGNED_BYTE, nova.getData());
		}
	}

	  
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{
		gl.glViewport(0, 0, width, height);
		fAspect = (float)width/(float)height;      
	}

	 
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) { }

 
	public void mouseClicked(MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON1) // botão 1
		{ }
		if (e.getButton() == MouseEvent.BUTTON3) // botão 2
		{ }
		glDrawable.display();
	}

	   
	public void keyPressed(KeyEvent e)
	{
		// F1 para próxima imagem
		if(e.getKeyCode()==KeyEvent.VK_F1)
		{
			if(++sel>imgs.length-1) sel=imgs.length-1;
		}
		// F2 para imagem anterior
		else if(e.getKeyCode()==KeyEvent.VK_F2)
		{
			if(--sel<0) sel = 0;
		}

		// Cria a imagem resultante
		nova = (Imagem) imgs[sel].clone();

//		switch (e.getKeyCode())
//		{
//			case KeyEvent.VK_1:		// Para exibir a imagem "original": não faz nada
//										System.out.println("Original");
//										break;
//			case KeyEvent.VK_2:		// Para converter a imagem para tons de cinza
//										System.out.println("Grayscale");
//										convertToGrayScale();
//										break;     
//			case KeyEvent.VK_3:		// Para converter a imagem para preto e branco  
//										System.out.println("B&W");
//										convertToGrayScale();
//										convertToBlackAndWhite();
//										break;
//			case KeyEvent.VK_4:		// Para aplicar um filtro passa-alta (realce de bordas)
//										System.out.println("High pass");
//										convertToGrayScale();
//										highPass();
//										break;
//			case KeyEvent.VK_5:		// Para aplicar um filtro passa-baixa (suavização)
//										System.out.println("Low pass");
//										convertToGrayScale();
//										lowPass();
//										break;
//			case KeyEvent.VK_6:		// Para gerar a imagem atrav�s da t�cnica de posteriza�ão
//										System.out.println("Posterize");
//										posterize();
//										break; 	
//			case KeyEvent.VK_7:		// Para converter a imagem para tons de s�pia  
//										System.out.println("Sepia");
//										convertToGrayScale();
//										sepia();
//										break; 
//			case KeyEvent.VK_ESCAPE:	System.exit(0);
//										break;
//		}  
		glDrawable.display();
	}

	   
	public void keyTyped(KeyEvent e) { }

	     
	public void keyReleased(KeyEvent e) { }
    
	   


	
	public void applyKernel(float [][]kernel)
	{
		for(int x=0; x<imgs[sel].getWidth()-2; x++)
		{
			for(int y=0; y<imgs[sel].getHeight()-2; y++)
			{
				float soma = 0;
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<3; j++)
					{
						soma += imgs[sel].getR(x+i,y+j) * kernel[i][j];
					}
				}
				if(soma>255) soma = 255;
				if(soma<0) soma = 0;
				nova.setPixel(x+1,y+1,(int)soma,(int)soma,(int)soma);
			}
		}
        }

	
	
			
}


