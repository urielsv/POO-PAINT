package frontend.ui.bars;

import backend.model.Circle;
import backend.model.Point;
import frontend.CanvasState;
import frontend.ui.buttons.FigureButton;
import frontend.ui.figures.DrawableCircle;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class SideBar extends VBox {

    // Botones Barra Izquierda
    private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
    private final ToggleButton rectangleButton = new ToggleButton("Rectángulo");
    //private final ToggleButton circleButton = new ToggleButton("Círculo");
    private final ToggleButton squareButton = new ToggleButton("Cuadrado");
    private final ToggleButton ellipseButton = new ToggleButton("Elipse");
    private final ToggleButton deleteButton = new ToggleButton("Borrar");
    private final CanvasState canvasState;

    private ToggleGroup tools = new ToggleGroup();

    public SideBar(CanvasState canvasState) {
            super(10);
            this.canvasState = canvasState;
            setPadding(new Insets(5));
            setStyle("-fx-background-color: #999;");
            setPrefWidth(100);

//            ToggleButton rectButton = new RectangleButton("Seleccionar");


        FigureButton<Circle> circleButton = new FigureButton<>("Círculo", canvasState,
                (start, end) -> new DrawableCircle<>(new Circle(start, end.getX() - start.getX())));

        ToggleButton[] toolsArr = {circleButton};

        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }

        //		buttonsBox.getChildren().addAll(toolsArr);
//		buttonsBox.getChildren().add(fillColorPicker);
//		buttonsBox.setPadding(new Insets(5));
//		buttonsBox.setStyle("-fx-background-color: #999");
//		buttonsBox.setPrefWidth(100);

        getChildren().addAll(toolsArr);
        setPadding(new Insets(5));
        setStyle("-fx-background-color: #999");
        setPrefWidth(100);

    }

    public ToggleGroup getTools() {
        return tools;
    }
}
