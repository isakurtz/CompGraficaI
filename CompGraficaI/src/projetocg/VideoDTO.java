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
 * @author PC
 */
public class VideoDTO {
    private int proportion;
    private int pessoas;
    private ArrayList<Frame> frames;
    private List<List<Point>> paths;

    public VideoDTO(int proportion, ArrayList<Frame> frames, int pessoas, List<List<Point>> paths) {
        this.proportion = proportion;
        this.frames = frames;
        this.pessoas = pessoas;
        this.paths = paths;
    }

    public int getProportion() {
        return proportion;
    }

    public void setProportion(int proportion) {
        this.proportion = proportion;
    }

    public int getPessoas() {
        return pessoas;
    }

    public ArrayList<Frame> getPositions() {
        return frames;
    }

    public void setPositions(ArrayList<Frame> frames) {
        this.frames = frames;
    }    

    public List<List<Point>> getPaths() {
        return paths;
    }

    public void setPaths(List<List<Point>> paths) {
        this.paths = paths;
    }
    
}
