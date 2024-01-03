package backend.model;

public class Circle extends Ellipse {

    protected final Point centerPoint;
    protected final double radius;

    public Circle(Point centerPoint, double radius) {
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, radius);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean isReachable(Point selection) {
        return Math.sqrt(Math.pow(this.getCenterPoint().getX() - selection.getX(), 2) +
                Math.pow(this.getCenterPoint().getY() - selection.getY(), 2)) < this.getRadius();
    }

    @Override
    public boolean isReachable(Rectangle selection){
        return true;
    }
}
