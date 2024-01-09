package frontend.ui.figures;

import backend.model.Circle;
import backend.model.Figure;
import backend.model.Movable;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

import java.util.EnumSet;

public class DrawableCircle<C extends Circle> extends DrawableFigure<C> {

    // new FigureButton("Circle", canvasState, (start, end) -> new Circle(start, end.getX() - start.getX())

    public DrawableCircle(C figure) {
        super(figure);
    }

    @Override
    public void draw(GraphicsContext gc) {

        C circle = getFigure();

        handleSelection(gc);

        double diameter = circle.getRadius() * 2;
        gc.fillOval(circle.getCenterPoint().getX() - circle.getRadius(),
                    circle.getCenterPoint().getY() - circle.getRadius(),
                    diameter,
                    diameter);
        gc.strokeOval(circle.getCenterPoint().getX() - circle.getRadius(),
                    circle.getCenterPoint().getY() - circle.getRadius(),
                    diameter,
                    diameter);
    }
}
