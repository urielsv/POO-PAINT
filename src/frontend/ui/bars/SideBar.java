package frontend.ui.bars;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class SideBar extends VBox {

    // Botones Barra Izquierda
    private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
    private final ToggleButton rectangleButton = new ToggleButton("Rectángulo");
    private final ToggleButton circleButton = new ToggleButton("Círculo");
    private final ToggleButton squareButton = new ToggleButton("Cuadrado");
    private final ToggleButton ellipseButton = new ToggleButton("Elipse");
    private final ToggleButton deleteButton = new ToggleButton("Borrar");

        public SideBar() {
            super(10);
            setPadding(new Insets(5));
            setStyle("-fx-background-color: #999;");
            setPrefWidth(100);

//            ToggleButton rectButton = new RectangleButton("Seleccionar");

            ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton, deleteButton};
            ToggleGroup tools = new ToggleGroup();
            for (ToggleButton tool : toolsArr) {
                tool.setMinWidth(90);
                tool.setToggleGroup(tools);
                tool.setCursor(Cursor.HAND);
            }


        }
}
