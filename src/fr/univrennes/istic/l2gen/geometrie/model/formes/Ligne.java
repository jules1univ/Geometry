package fr.univrennes.istic.l2gen.geometrie.model.formes;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.model.Point;

public class Ligne implements IForme {

    private List<Point> sommets;

    public Ligne(double... n){
        this.sommets = new ArrayList<Point>();
        for(int i = 0; i<n.length; i+=2){
            this.sommets.add(new Point(n[i], n[i+1]));
        }
    }

    public void ajouterSommet(Point pt){
        this.sommets.add(pt);
    }

    public void ajouterSommet(double x, double y){
        Point pt = new Point(x,y);
        this.sommets.add(pt);
    }

    @Override
    public Point centre() {
        double x = 0;
        double y = 0;
        for(int i = 0; i<this.sommets.size(); i++){
            x += this.sommets.get(i).x();
            y += this.sommets.get(i).y();
        }
        x /= this.sommets.size();
        y /= this.sommets.size();
        return new Point(x,y);
    }

    @Override
    public double hauteur() {
        double min = this.sommets.get(0).y();
        double max = this.sommets.get(0).y();
        for(int i = 1; i<this.sommets.size(); i++){
            if(this.sommets.get(i).y() < min){
                min = this.sommets.get(i).y();
            }
            if(this.sommets.get(i).y() > max){
                max = this.sommets.get(i).y();
            }
        }
        return max - min;
    }

    @Override
    public double largeur() {
        double min = this.sommets.get(0).x();
        double max = this.sommets.get(0).x();
        for(int i = 1; i<this.sommets.size(); i++){
            if(this.sommets.get(i).x() < min){
                min = this.sommets.get(i).x();
            }
            if(this.sommets.get(i).x() > max){
                max = this.sommets.get(i).x();
            }
        }
        return max - min;
    }

    @Override
    public String description(int desc) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat((desc*2)));
        sb.append("Ligne ");
        for(Point p: sommets){
            sb.append((int)p.x()).append(",").append((int)p.y()).append(" ");
        }
        return sb.toString();
    }
    
    public List<Point> getSommets(){
        return this.sommets;
    }

    @Override
    public void deplacer(double x, double y){
        for(Point p: this.sommets){
            p=p.plus(x, y);
        }
    }

    @Override
    public IForme dupliquer(){
        double[] points = new double[this.sommets.size() * 2];
        int index = 0;
        for (Point p : this.sommets) {
            points[index++] = p.x();
            points[index++] = p.y();
        }
        Ligne clone = new Ligne(points);
        return clone;
    }

    
    public void redimensionner(double x, double y){
        for(Point p: this.sommets){
            p=p.plus(x, y);
        }
    }

    @Override
    public String enSVG() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enSVG'");
    }

}
