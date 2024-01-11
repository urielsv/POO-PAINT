package frontend.ui.figures;

import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public class DrawableRectangle<R extends Rectangle> extends DrawableFigure<R> {

        public DrawableRectangle(R figure) {
            super(figure);
        }

        @Override
        public void draw(GraphicsContext gc) {

            R rectangle = getFigure();

            handleSelection(gc);

            gc.fillRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
						Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
				gc.strokeRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
					Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
        }
}
