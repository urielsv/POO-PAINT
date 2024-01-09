package frontend.ui.figures;

import backend.model.Figure;
import backend.model.Point;

@FunctionalInterface
public interface FigureFactory<E extends Figure> {

    E create(Point start, Point end);

}
