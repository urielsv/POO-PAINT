package frontend.ui.bars;

import backend.model.*;
import frontend.CanvasState;
import frontend.ui.buttons.*;
import frontend.ui.figures.DrawableEllipse;
import frontend.ui.figures.DrawableRectangle;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javax.swing.*;

public class SideBar extends VBox {

    // Botones Barra Izquierda
    private final ToggleButton deleteButton = new ToggleButton("Borrar");
    private final CanvasState canvasState;

    private ToggleGroup tools = new ToggleGroup();

    private final ColorPicker fillColorPicker = new ColorPicker(Color.YELLOW);

    private final ChoiceBox shadowButton = new ShadowButton();

    public SideBar(CanvasState canvasState) {
            super(10);
            this.canvasState = canvasState;
            setPadding(new Insets(5));
            setStyle("-fx-background-color: #999;");
            setPrefWidth(100);


        ToggleButton[] toolsArr = getToggleButtons(canvasState);

        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }


        getChildren().addAll(toolsArr);
        getChildren().add(shadowButton);
		getChildren().add(fillColorPicker);
        setPadding(new Insets(5));
        setStyle("-fx-background-color: #282828");
        setPrefWidth(100);

    }

    private ToggleButton[] getToggleButtons(CanvasState canvasState) {
        FigureButton<Rectangle> rectangleButton = new FigureButton<>("Rectángulo", canvasState,
                (start, end) -> new DrawableRectangle<>(new Rectangle(start, end),fillColorPicker.getValue(), shadowButton.getValue().toString()));

        FigureButton<Ellipse> ellipseButton = new FigureButton<>("Elipse", canvasState,
                (start, end) -> new DrawableEllipse<>(new Ellipse(
                    new Point(Math.abs(end.getX() + start.getX()) / 2, (Math.abs((end.getY() + start.getY())) / 2)),
                    Math.abs(end.getX() - start.getX()),
                    Math.abs(end.getY() - start.getY())), fillColorPicker.getValue(), shadowButton.getValue().toString())
                    );

        FigureButton<Square> squareButton = new FigureButton<>("Cuadrado", canvasState,
                (start, end) -> new DrawableRectangle<>(new Square(start, end.getX() - start.getX()),
                fillColorPicker.getValue(), shadowButton.getValue().toString()));

        FigureButton<Circle> circleButton = new FigureButton<>("Círculo", canvasState,
                (start, end) -> new DrawableEllipse<>(new Circle(start, end.getX() - start.getX()),
                        fillColorPicker.getValue(), shadowButton.getValue().toString())
        );

        // Other buttons
        SelectionButton selectionButton = new SelectionButton(canvasState);


        ToggleButton[] toolsArr = {selectionButton, circleButton, rectangleButton, ellipseButton, squareButton, deleteButton};
        return toolsArr;
    }

    public ChoiceBox getShadowButton(){
        return this.shadowButton;
    }

    public ToggleGroup getTools() {
        return tools;
    }

    public Color getColorPicker() {
        return fillColorPicker.getValue();
    }

    public ColorPicker getColorPickerButton() {
        return fillColorPicker;
    }

    public ToggleButton getDeleteButton() {
        return deleteButton;
    }

}
