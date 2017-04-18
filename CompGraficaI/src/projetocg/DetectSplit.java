/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocg;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author 11104974
 */
public class DetectSplit {
    
    double range;
    ArrayList<Frame> frames;
    int prop;
    int pessoas;

    public DetectSplit(VideoDTO video) {
        frames = video.getPositions();
        prop = video.getProportion();
        pessoas = video.getPessoas();
    }

    public void analize() {
        range = 0.8 * prop; // quantos metros eu considero dentro do range de duas pessoas proximas
        boolean pass = false;
        int time = 0;
        double dist = 0;
        double dist2 = 0;

        for (int i = 1; i < pessoas - 1; i++) {
            for (int j = i + 1; j < pessoas; j++) {
                for (int k = 0; k < frames.size(); k++) {
                    Frame currentFrame = frames.get(k);
                    if (pass && time > 96) { // ficaram perto mais de 4 segundos
                        pass = false;                        
                        break;
                    }
                    if (currentFrame.hasPessoa(i) && currentFrame.hasPessoa(j)) {
                        if(pass && calcDistancia(currentFrame.getPos(i), currentFrame.getPos(j)) > dist){
                            dist2 += calcDistancia(currentFrame.getPos(i), currentFrame.getPos(j));
                        }
                        else if (calcDistancia(currentFrame.getPos(i), currentFrame.getPos(j)) <= range ) {
                            pass = true;
                            dist = calcDistancia(currentFrame.getPos(i), currentFrame.getPos(j));
                            dist2= dist;
                            time++;
                        }
                        
                    }
                }
               
                if (pass) {
                    System.out.println("A distancia entre Pessoa "+ i + " e "+ j + " aumentou: " + (int) dist2/prop + " metros");
                }
                 time = 0;
                 pass = false;
            }

        }
    }

    public double calcDistancia(Point p1, Point p2) {
        return p1.distance(p2);

    }
    
}
