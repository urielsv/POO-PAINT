package frontend.ui.figures;

import backend.model.Circle;
import backend.model.Figure;
import backend.model.Movable;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

import java.util.EnumSet;

public class DrawableCircle extends Circle implements Drawable {

    private static final String FIGURE_NAME = "Circulo";


    public DrawableCircle(Point start, Point end) {
        super(start, Math.abs(end.getX() - start.getX()));
    }

    @Override
    public void draw(GraphicsContext gc) {
        double diameter = getRadius() * 2;
        gc.fillOval(getCenterPoint().getX() - getRadius(),
                    getCenterPoint().getY() - getRadius(),
                    diameter,
                    diameter);
        gc.strokeOval(getCenterPoint().getX() - getRadius(),
                    getCenterPoint().getY() - getRadius(),
                    diameter,
                    diameter);
    }
}
