package frontend.ui.buttons;

import backend.CanvasState;
import backend.model.Figure;
import backend.model.Point;
import frontend.ui.MouseActions;
import frontend.ui.figures.FigureFactory;
import javafx.scene.control.ToggleButton;

public class FigureButton<F extends Figure> extends ToggleButton implements MouseActions {
    private final FigureFactory<F> factory;
    private Point start;
    private final CanvasState canvasState;

    // new FigureButton("Cuadrado", canvasState, (start, end) -> new Square(start, end.getX() - start.getX())
    // Square::new
    public FigureButton(String name, CanvasState canvasState, FigureFactory<F> factory) {
        super(name);
        this.canvasState = canvasState;
        this.factory = factory;
    }

    @Override
    public void onMousePressed(Point start){
        this.start = start;
    }

    @Override
    public void onMouseReleased(Point end) {
//        figure.update(start, end);
        F figure = factory.create(start, end);
        canvasState.addFigure(figure);
    }
}
