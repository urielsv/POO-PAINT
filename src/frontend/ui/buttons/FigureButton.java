package frontend.ui.buttons;

import backend.CanvasState;
import backend.model.Figure;
import backend.model.Point;
import frontend.ui.MouseActions;
import frontend.ui.figures.DrawableFigure;
import javafx.scene.control.ToggleButton;

import java.util.List;

    public class FigureButton extends ToggleButton implements MouseActions {
    private Point start;
    private final CanvasState canvasState;
    private DrawableFigure drawable;
    private List<DrawableFigure> drawables;

    public FigureButton(String name, DrawableFigure drawable, CanvasState canvasState, List<DrawableFigure> drawables) {
        super(name);
        this.drawable = drawable;
        this.canvasState = canvasState;
        this.drawables = drawables;
    }

    @Override
    public void onMousePressed(Point start){
        this.start = start;
    }

    @Override
    public void onMouseReleased(Point end) {
        Figure figure = drawable.create(start, end);
        canvasState.addFigure(figure);
        drawables.add(drawable);
    }
}
