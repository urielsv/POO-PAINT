package backend.model;

public class Square extends Rectangle {

    private final Point topLeft, bottomRight;

    public Square(Point topLeft, double size) {
        this.topLeft = topLeft;
        this.bottomRight = new Point(topLeft.x + size, topLeft.y + size);
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", getTopLeft(), getBottomRight());
    }

}
