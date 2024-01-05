package frontend.ui.figures;

import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

import java.util.EnumSet;

public class DrawableCircle extends DrawableFigure {

    private static final String FIGURE_NAME = "Circulo";


    public DrawableCircle(EnumSet effects) {
        super(FIGURE_NAME, null, effects);
    }

    public Figure create(Point start, Point end) {
        figure = new Circle(start, Math.abs(end.getX() - start.getX()));
        return figure;
    }

    public void draw(GraphicsContext gc) {
        Circle circle = (Circle) getFigure();
        double diameter = ((Circle) getFigure()).getRadius() * 2;
        gc.fillOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
        gc.strokeOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
    }
}
