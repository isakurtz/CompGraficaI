/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocg;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marina
 */
public class DetecGroup {
    
  public  double range;
   public ArrayList<Frame> frames;
   public int prop;
   public int pessoas;

    public DetecGroup(VideoDTO video) {
       frames = video.getPositions();
       prop = video.getProportion();
       pessoas = video.getPessoas();
    }
    
    public void analize()
    {
       range = 0.8 * prop; // quantos metros eu considero dentro do range de duas pessoas proximas
       boolean pass = false;
       int time = 0;
     List<Integer> listaG = new ArrayList<Integer>();

       for (int i = 1; i < pessoas - 1; i++) {
           for (int j = i + 1; j < pessoas; j++) {
               for (int k = 0; k < frames.size(); k++) {
                   Frame currentFrame = frames.get(k);
                   
                   if (pass && time > 172) { // ficaram perto mais de 4 segundos
                       pass = false;
                       break;
                   }
                   
                   if (currentFrame.hasPessoa(i) && currentFrame.hasPessoa(j)) {
                       if (calcDistancia(currentFrame.getPos(i), currentFrame.getPos(j)) <= range) {
                           pass = true;
                           time++;
                       //    listaG.add(i,j);                                                  
                       }
                       
                   
                   }
               }
              
               if (pass) {
              
    System.out.println("Pessoa "+ i + " se agrupou com "+ j + "e: " + " "+ " por " + (double) time / 24 + " segundos");
               }
                time = 0;
                pass = false;
           }
       }
    }   
         public double calcDistancia(Point p1, Point p2){
          return p1.distance(p2);
   }
        
    }