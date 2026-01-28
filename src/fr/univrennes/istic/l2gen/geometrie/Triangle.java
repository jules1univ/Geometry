package fr.univrennes.istic.l2gen.geometrie;

public class Triangle implements IForme {
    private Point point1;
    private Point point2;
    private Point point3;
    
    public Triangle(double x1,double y1, double x2,double y2,double x3,double y3){
        this.point1 = new Point(x1,y1);
        this.point2 = new Point(x2,y2);
        this.point3 = new Point(x3,y3);
    }

    public Triangle(Point p1, Point p2, Point p3){
        point1  = p1;
        point2  = p2;
        point3  = p3;
    }

    public Point centre(){
        double x = (point1.x() + point2.x() + point3.x()) / 3;
        double y = (point1.y() + point2.y() + point3.y()) / 3;
        return new Point(x,y);
    }

    public double largeur(){
        double minX = Math.min(point1.x(), Math.min(point2.x(), point3.x()));
        double maxX = Math.max(point1.x(), Math.max(point2.x(), point3.x()));
        return maxX - minX;
    }

    public double hauteur(){
        double minY = Math.min(point1.y(), Math.min(point2.y(), point3.y()));
        double maxY = Math.max(point1.y(), Math.max(point2.y(), point3.y()));
        return maxY - minY;
    }

    public String description(int indentation){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < indentation*2; i++){
            sb.append(" ");
        }
        sb.append("Triangle ");
        sb.append((int)point1.x() + "," + (int)point1.y() + " ");
        sb.append((int)point2.x() + "," + (int)point2.y() + " ");
        sb.append((int)point3.x() + "," + (int)point3.y());
        return sb.toString();
    }

    public void deplacer(double dx, double dy){
        Point newP1 = point1.plus(dx, dy);
        Point newP2 = point2.plus(dx, dy);
        Point newP3 = point3.plus(dx, dy);
        point1 = newP1;
        point2 = newP2;
        point3 = newP3;
    }

    public IForme dupliquer(){
        return new Triangle(new Point(point1.x(), point1.y()),
                            new Point(point2.x(), point2.y()),
                            new Point(point3.x(), point3.y()));
    }

    public void redimensionner(double px, double py){
        Point centre = centre();
        point1 = new Point(centre.x() + (point1.x() - centre.x()) * px, 
                          centre.y() + (point1.y() - centre.y()) * py);
        point2 = new Point(centre.x() + (point2.x() - centre.x()) * px, 
                          centre.y() + (point2.y() - centre.y()) * py);
        point3 = new Point(centre.x() + (point3.x() - centre.x()) * px, 
                          centre.y() + (point3.y() - centre.y()) * py);
    }

    @Override
    public String enSVG() {
        throw new UnsupportedOperationException("Unimplemented method 'enSVG'");
    }
}
