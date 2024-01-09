package backend;
//
//import backend.model.Figure;
//import backend.model.Point;
//import frontend.ui.figures.DrawableFigure;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CanvasState {
//
//    //
//    private final List<Figure> list = new ArrayList<>();
//
//    //
//    private DrawableFigure selectedFigure;
//
//    //
//    public void addFigure(Figure figure) {
//        list.add(figure);
//    }
//
//    //
//    public void deleteFigure(Figure figure) {
//        list.remove(figure);
//    }
//
//
//    public Iterable<Figure> figures() {
//        return new ArrayList<>(list);
//    }
//
//    public Figure getSelectedFigure() {
//        return selectedFigure;
//    }
//
//    public void setSelectedFigure(Figure selectedFigure) {
//        this.selectedFigure = selectedFigure;
//    }
//
//    public boolean figureBelongs(Figure figure, Point eventPoint) {
//            return figure.isReachable(eventPoint);
//    }
//
//    // update selected figures
//    public Figure updateFigure(double diffX, double diffY) {
//        // get selected and modify
//        selectedFigure.move(diffX, diffY);
//        return selectedFigure;
//    }
//}
