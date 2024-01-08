package backend.model;

public class Rectangle extends Figure {

    private Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        super(new Point[]{topLeft, bottomRight});
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }


    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    public boolean isReachable(Point selection) {
        return selection.getX() > this.getTopLeft().getX() && selection.getX() < this.getBottomRight().getX() &&
                selection.getY() > this.getTopLeft().getY() && selection.getY() < this.getBottomRight().getY();
    }

    //para hacer dsp en la query 1
    @Override
    public boolean isReachable(Rectangle selection){
        return true;
    }


}
