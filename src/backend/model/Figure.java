package backend.model;

public abstract class Figure implements Movable, Reachable {

    private final Point[] points;

    public Figure(Point[] points) {
        this.points = points;
    }

    @Override
    public void move(double diffX, double diffY) {
        // figure tiene lista de points
        for (Point p : points) {
            p.move(diffX, diffY);
        }
    }

}
