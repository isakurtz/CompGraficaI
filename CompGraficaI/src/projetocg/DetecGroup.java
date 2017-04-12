/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocg;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Marina
 */
public class DetecGroup {

    public double range;
    public ArrayList<Frame> frames;
    public int prop;
    public int pessoas;

    public DetecGroup(VideoDTO video) {
        frames = video.getPositions();
        prop = video.getProportion();
        pessoas = video.getPessoas();
    }

    public void analize() {
        range = 0.8 * prop; // quantos metros eu considero dentro do range de duas pessoas proximas       
        int framesi =0;
        int framesj =0;
        int time = 0;
        HashSet<Integer> grupo;
        ArrayList<HashSet<Integer>> listaG = new ArrayList<>();
        
        for (int i = 1; i < pessoas - 1; i++) {            
            grupo =  new HashSet<>();
            
            for ( int l = 0; l < listaG.size(); l++) {
                if(listaG.get(l).contains(i)){                    
                    grupo = listaG.get(l);    
                    listaG.remove(l);
                }
            }
                        
            for (int j = i + 1; j < pessoas; j++) {
                
                for (int k = 0; k < frames.size(); k++) {
                    Frame currentFrame = frames.get(k);
                    
                    if (currentFrame.hasPessoa(i)){framesi++;}
                    if (currentFrame.hasPessoa(j)){framesj++;}
                    

//                    if (time > 192) { // ficaram perto mais de 8 segundos                       
//                        if (grupo.isEmpty()) {                            
//                            grupo.add(i);
//                        }                        
//                        grupo.add(j);
//                        break;
//                    }  
                    if (currentFrame.hasPessoa(i) && currentFrame.hasPessoa(j)) {                        
                        if (calcDistancia(currentFrame.getPos(i), currentFrame.getPos(j)) <= range) {
                            time++;
                        }
                    }
                }  
               
               
                 if (time > 0 &&(time/(double)(Math.min(framesi, framesj)/100)) > 80) { // ficaram perto mais de 8 segundos                       
                        if (grupo.isEmpty()) {                            
                            grupo.add(i);
                        }                        
                        grupo.add(j);                        
                    }                 
                framesj=0;
                time = 0;                
            }
            
           
            
            if(!grupo.isEmpty()){
                 listaG.add(grupo);
            }
            framesi=0;
        }
        
        if (!listaG.isEmpty()) {
            System.out.println("\n\nGRUPOS \n\n");
            int i =1;
            for (HashSet<Integer> hashSet : listaG) {
                System.out.println("\tGrupo " + i);
                for (Integer integer : hashSet) {
                    System.out.println("\t\tPessoa" + integer);
                }
               i++; 
            }
            //System.out.println("Pessoa " + i + " se agrupou com " + j + "e: " + " " + " por " + (double) time / 24 + " segundos");
                }
    }

    public double calcDistancia(Point p1, Point p2) {
        return p1.distance(p2);
    }

}
