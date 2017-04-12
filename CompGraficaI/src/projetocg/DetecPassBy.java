/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocg;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class DetecPassBy {

    double range;
    ArrayList<Frame> frames;
    int prop;
    int pessoas;

    public DetecPassBy(VideoDTO video) {
        frames = video.getPositions();
        prop = video.getProportion();
        pessoas = video.getPessoas();
    }

    public void analize() {
        range = 0.8 * prop; // quantos metros eu considero dentro do range de duas pessoas proximas
        boolean pass = false;
        int time = 0;

        for (int i = 1; i < pessoas - 1; i++) {
            for (int j = i + 1; j < pessoas; j++) {
                for (int k = 0; k < frames.size(); k++) {
                    Frame currentFrame = frames.get(k);
                    if (pass && time > 96) { // ficaram perto mais de 4 segundos
                        pass = false;                        
                        break;
                    }
                    if (currentFrame.hasPessoa(i) && currentFrame.hasPessoa(j)) {
                        if (calcDistancia(currentFrame.getPos(i), currentFrame.getPos(j)) <= range) {
                            pass = true;
                            time++;
                        }
                    }
                }
               
                if (pass) {
                    System.out.println("Pessoa "+ i + " passou por pessoa "+ j + " por " + (double) time / 24 + " segundos");
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
