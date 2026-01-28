package fr.univrennes.istic.l2gen.geometrie.model.formes;

import fr.univrennes.istic.l2gen.geometrie.model.Point;

public final class Triangle implements IShape {
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

    public Point getCenter(){
        double x = (point1.getX() + point2.getX() + point3.getX()) / 3;
        double y = (point1.getY() + point2.getY() + point3.getY()) / 3;
        return new Point(x,y);
    }

    public double getWidth(){
        double minX = Math.min(point1.getX(), Math.min(point2.getX(), point3.getX()));
        double maxX = Math.max(point1.getX(), Math.max(point2.getX(), point3.getX()));
        return maxX - minX;
    }

    public double getHeight(){
        double minY = Math.min(point1.getY(), Math.min(point2.getY(), point3.getY()));
        double maxY = Math.max(point1.getY(), Math.max(point2.getY(), point3.getY()));
        return maxY - minY;
    }

    public String getDescription(int indentation){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < indentation*2; i++){
            sb.append(" ");
        }
        sb.append("Triangle ");
        sb.append((int)point1.getX() + "," + (int)point1.getY() + " ");
        sb.append((int)point2.getX() + "," + (int)point2.getY() + " ");
        sb.append((int)point3.getX() + "," + (int)point3.getY());
        return sb.toString();
    }

    public void move(double dx, double dy){
        Point newP1 = point1.add(dx, dy);
        Point newP2 = point2.add(dx, dy);
        Point newP3 = point3.add(dx, dy);
        point1 = newP1;
        point2 = newP2;
        point3 = newP3;
    }

    public IShape copy(){
        return new Triangle(new Point(point1.getX(), point1.getY()),
                            new Point(point2.getX(), point2.getY()),
                            new Point(point3.getX(), point3.getY()));
    }

    public void resize(double px, double py){
        Point centre = getCenter();
        point1 = new Point(centre.getX() + (point1.getX() - centre.getX()) * px, 
                          centre.getY() + (point1.getY() - centre.getY()) * py);
        point2 = new Point(centre.getX() + (point2.getX() - centre.getX()) * px, 
                          centre.getY() + (point2.getY() - centre.getY()) * py);
        point3 = new Point(centre.getX() + (point3.getX() - centre.getX()) * px, 
                          centre.getY() + (point3.getY() - centre.getY()) * py);
    }

    @Override
    public String toSVG() {
        StringBuilder sb = new StringBuilder();
        sb.append("<polygon points=\"");
        sb.append(point1.getX()).append(",").append(point1.getY()).append(" ");
        sb.append(point2.getX()).append(",").append(point2.getY()).append(" ");
        sb.append(point3.getX()).append(",").append(point3.getY()).append("\" ");
        sb.append("fill=\"white\" stroke=\"black\" ");
        sb.append("/>");
        return sb.toString();
    }
}
