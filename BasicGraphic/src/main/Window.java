/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Window extends JFrame {
    private JPanel mainPanel;
    
    public Window (String title,FPSAnimator animator ,int width, int height){
        super(title);
        
        setupWindow(animator, width, height);
        mainPanel = new JPanel(new BorderLayout());
        this.getContentPane().add(mainPanel,BorderLayout.CENTER);
    }
    
    private void setupWindow(final FPSAnimator animator,int width, int height){
        this.setLayout(new BorderLayout());
        this.setSize(width, height); 
        this.setLocationRelativeTo(null);
        
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e ){
                if(animator.isStarted()){
                    System.out.println("Stopping Animator");
                    animator.stop();
                    System.out.println("Animator stopped");
                }                
                System.exit(0);
            }
        });       
   
    }
    
    public void setVisibility(boolean isVisible){
        this.setVisible(isVisible);
    }
    
    public void setGLCanvas(GLCanvas canvas, String position){
        mainPanel.add(canvas, position);
    }
}
