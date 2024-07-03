package frontend;



import backend.model.Figure;
import backend.model.Point;
import frontend.ui.SelectedSet;
import frontend.ui.figures.DrawableFigure;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CanvasState {


    // TODO remove selected


    private final List<DrawableFigure<? extends Figure>> list = new ArrayList<>();

    private Set<DrawableFigure<? extends Figure>> selectedList = new SelectedSet<>();

    public void addFigure(DrawableFigure figure) {
        list.add(figure);
    }

    public Iterable<DrawableFigure<? extends Figure>> figures() {
        return new ArrayList<>(list);
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

    public void updateSelectedFigures(Color color) {
        for (DrawableFigure<?extends Figure> figure : figures()){
            if (selectedList.contains(figure)) {
                figure.changeColor(color);
            }
        }
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


    public void updateShadow(String shadow) {
        for (DrawableFigure<?extends Figure> figure : figures()) {
            if (selectedList.contains(figure)) {
                figure.updateShadow(shadow);
            }
        }
    }

}
