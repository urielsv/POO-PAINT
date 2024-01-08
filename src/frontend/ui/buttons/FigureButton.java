package frontend.ui.buttons;

import backend.CanvasState;
import backend.model.Figure;
import backend.model.Point;
import frontend.ui.MouseActions;
import javafx.scene.control.ToggleButton;

public class FigureButton<F extends Figure> extends ToggleButton implements MouseActions {
    private Point start;
    private final CanvasState canvasState;
    private F figure;

    // new FigureButton("Rectangle", canvasState, Rectangle::new)
    public FigureButton(String name, CanvasState canvasState) {
        super(name);
        this.canvasState = canvasState;
    }

    @Override
    public void onMousePressed(Point start){
        this.start = start;
    }

    @Override
    public void onMouseReleased(Point end) {
//        figure.update(start, end);
        canvasState.addFigure(figure);
    }
}
