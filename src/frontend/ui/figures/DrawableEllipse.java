package frontend.ui.figures;

import backend.model.Ellipse;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableEllipse<E extends Ellipse> extends DrawableFigure<E> {

    public DrawableEllipse(E figure, Color color) { super(figure, color); }
    @Override
    public void draw(GraphicsContext gc) {

        E ellipse = getFigure();

        handleSelection(gc);

        gc.fillOval(ellipse.getCenterPoint().getX() - (ellipse.getsMayorAxis() / 2),
                    ellipse.getCenterPoint().getY() - (ellipse.getsMinorAxis() / 2),
                    ellipse.getsMayorAxis(),
                    ellipse.getsMinorAxis());
        gc.strokeOval(ellipse.getCenterPoint().getX() - (ellipse.getsMayorAxis() / 2),
                ellipse.getCenterPoint().getY() - (ellipse.getsMinorAxis() / 2),
                ellipse.getsMayorAxis(),
                ellipse.getsMinorAxis());
    }
}
