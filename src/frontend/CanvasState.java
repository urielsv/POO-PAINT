package frontend;



import backend.model.Figure;
import backend.model.Point;
import frontend.ui.figures.DrawableFigure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CanvasState {

    private final List<DrawableFigure<? extends Figure>> list = new ArrayList<>();

    // temp
    private DrawableFigure<? extends Figure> selected;

    private Set<DrawableFigure<? extends Figure>> selectedList = new HashSet<>();


    public void addFigure(DrawableFigure figure) {
        list.add(figure);
    }

    public Iterable<DrawableFigure<? extends Figure>> figures() {
        return new ArrayList<>(list);
    }

    public DrawableFigure getSelectedFigure() {
        return selected;
    }

    public void setSelectedFigure(DrawableFigure selectedFigure) {
        this.selected = selectedFigure;
    }

    public void addSelectedFigures(DrawableFigure<? extends Figure> figure) {
        selectedList.add(figure);
    }

    public void clearSelectedFigures() {
        selectedList.clear();
    }

    public boolean figureBelongs(DrawableFigure<? extends Figure> figure, Point eventPoint) {
        // temp
            if (figure.getFigure() == null)
                return false;
            else
                return figure.getFigure().isReachable(eventPoint);
    }


}
