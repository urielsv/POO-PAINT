package frontend;



import backend.model.Figure;
import backend.model.Point;
import frontend.ui.figures.DrawableFigure;

import java.util.ArrayList;
import java.util.List;

public class CanvasState {

    private final List<DrawableFigure<? extends Figure>> list = new ArrayList<>();

    private DrawableFigure<? extends Figure> selectedFigure;


    public void addFigure(DrawableFigure figure) {
        list.add(figure);
    }
    public Iterable<DrawableFigure<? extends Figure>> figures() {
        return new ArrayList<>(list);
    }

    public DrawableFigure getSelectedFigure() {
        return selectedFigure;
    }

    public void setSelectedFigure(DrawableFigure selectedFigure) {
        this.selectedFigure = selectedFigure;
    }

    public boolean figureBelongs(DrawableFigure<? extends Figure> figure, Point eventPoint) {
        // temp
            if (figure.getFigure() == null)
                return false;
            else
                return figure.getFigure().isReachable(eventPoint);
    }
}
