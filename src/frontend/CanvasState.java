package frontend;



import backend.model.Figure;
import backend.model.Point;
import frontend.ui.SelectedSet;
import frontend.ui.figures.DrawableFigure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CanvasState {


    // TODO remove selected
    private DrawableFigure<? extends Figure> selected;

    private final List<DrawableFigure<? extends Figure>> list = new ArrayList<>();

    private Set<DrawableFigure<? extends Figure>> selectedList = new SelectedSet<>();

    public void addFigure(DrawableFigure figure) {
        list.add(figure);
    }

    public Iterable<DrawableFigure<? extends Figure>> figures() {
        return new ArrayList<>(list);
    }

    public void setSelectedFigure(DrawableFigure selectedFigure) {
        this.selected = selectedFigure;
    }

    public void deleteFigure() {
        for (DrawableFigure<?extends Figure> figure : figures()){
            if (selectedList.contains(figure)) {
                list.remove(figure);
            }
        }
        clearSelectedFigures();
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

    public boolean noSelection() {
        return selectedList.isEmpty();
    }


}
