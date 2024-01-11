package frontend.ui.buttons;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.CanvasState;
import frontend.ui.MouseActions;
import frontend.ui.figures.DrawableFigure;
import javafx.scene.control.ToggleButton;

import java.util.List;
import java.util.function.Predicate;

public class SelectionButton extends ToggleButton implements MouseActions {

    private static final String BUTTON_NAME = "Seleccionar";

    private Rectangle selectionArea;

    private Point end;
    private final CanvasState canvasState;

    public SelectionButton(CanvasState canvasState) {
        super(BUTTON_NAME);
        this.canvasState = canvasState;
    }


    @Override
    public void onMouseReleased(Point point) {
        // reset the selection area every time the mouse is released for the next selection

        canvasState.clearSelectedFigures();
        end = point;
        selectionArea = new Rectangle(point, end);

        // and clear any selected figures within it
        for (DrawableFigure<? extends Figure> figure : canvasState.figures()) {
            if (figure.getFigure().isContained(selectionArea)) {
                // add to seleceted list from canvasstate
                canvasState.addSelectedFigures(figure);

            }
        }
    }

    @Override
    public void onMouseDragged(Point point) {
				double diffX = (point.getX() - selectionArea.getTopLeft().getX());
				double diffY = (point.getY() - selectionArea.getTopLeft().getY());


                // loop through the selected list and modify them (move, change color, effects, etc)

//				selectedFigure = canvasState.updateFigure(diffX, diffY);

                // modify the starting point of the imaginary rectangle
				end.move(diffX, diffY);
    }
}
