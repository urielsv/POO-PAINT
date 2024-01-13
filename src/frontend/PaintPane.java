package frontend;

import frontend.CanvasState;
import backend.model.*;
import frontend.ui.MouseActions;
import frontend.ui.bars.SideBar;
import frontend.ui.buttons.ActionButton;
import frontend.ui.buttons.FigureButton;
import frontend.ui.figures.DrawableFigure;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaintPane extends BorderPane {

    // BackEnd
    private final CanvasState canvasState;

    // Canvas y relacionados
    private final Canvas canvas = new Canvas(800, 600);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();


    // Selector de color de relleno
    private final ColorPicker fillColorPicker = new ColorPicker(defaultFillColor);

    // Dibujar una figura
    private Point startPoint;

    // Seleccionar una figura
    private DrawableFigure selectedFigure;

    // StatusBar
    private final StatusPane statusPane;

    // Colores de relleno de cada figura
    private final Map<DrawableFigure, Color> figureColorMap = new HashMap<>();


    public PaintPane(CanvasState canvasState, StatusPane statusPane) {
        this.canvasState = canvasState;
        this.statusPane = statusPane;

        SideBar sideBar = new SideBar(canvasState);

        gc.setLineWidth(1);

        canvas.setOnMousePressed(event -> {
            startPoint = new Point(event.getX(), event.getY());


            ActionButton button = (ActionButton) sideBar.getTools().getSelectedToggle();
            if (button != null) {
                button.onMousePressed(startPoint);
            }


        });


        canvas.setOnMouseReleased(event -> {
            Point endPoint = new Point(event.getX(), event.getY());
            if (startPoint == null) {
                return;
            }
            if (endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()) {
                return;
            }

            ActionButton button = (ActionButton) sideBar.getTools().getSelectedToggle();
            if (button != null) {
                button.onMouseReleased(endPoint);
            }

            redrawCanvas();
        });

        canvas.setOnMouseMoved(event -> {
            Point eventPoint = new Point(event.getX(), event.getY());
            boolean found = false;
            StringBuilder label = new StringBuilder();
            for (DrawableFigure<? extends Figure> figure : canvasState.figures()) {
                if (canvasState.figureBelongs(figure, eventPoint)) {
                    found = true;
                    label.append(figure.getFigure().toString());
                }
            }
            if (found) {
                statusPane.updateStatus(label.toString());
            } else {
                statusPane.updateStatus(eventPoint.toString());
            }
        });

        canvas.setOnMouseClicked(event -> {
//			if(selectionButton.isSelected()) {
            Point eventPoint = new Point(event.getX(), event.getY());
            boolean found = false;
            StringBuilder label = new StringBuilder("Se seleccionó: ");
//				for (DrawableFigure<? extends Figure> figure : canvasState.figures()) {
//					if(canvasState.figureBelongs(figure, eventPoint)) {
//						found = true;
//						selectedFigure = figure;
//						canvasState.setSelectedFigure(selectedFigure);
//						label.append(figure.toString());
//					}
////				}

            // Clicked but moved
            if (startPoint.getX() != eventPoint.getX() && startPoint.getY() != eventPoint.getY()) {
                return;
            }

            ActionButton button = (ActionButton) sideBar.getTools().getSelectedToggle();
            if (button != null) {
                button.onMouseClicked(eventPoint);
            }

            if (found) {
                statusPane.updateStatus(label.toString());
            } else {
                selectedFigure = null;
                statusPane.updateStatus("Ninguna figura encontrada");
            }
            redrawCanvas();

        });

        canvas.setOnMouseDragged(event -> {
//			if(selectionButton.isSelected()) {
            Point eventPoint = new Point(event.getX(), event.getY());
//				double diffX = (eventPoint.getX() - startPoint.getX());
//				double diffY = (eventPoint.getY() - startPoint.getY());
//
//				selectedFigure = canvasState.updateFigure(diffX, diffY);
//				startPoint.move(diffX, diffY);
            ActionButton button = (ActionButton) sideBar.getTools().getSelectedToggle();
            if (button != null) {
                button.onMouseDragged(eventPoint);
            }

            redrawCanvas();
        });


        sideBar.getDeleteButton().setOnAction(event -> {

            if(canvasState.noSelection()) {
                alertInfo("No hay figuras seleccionadas");
                return;
            }

                canvasState.deleteFigure();
                redrawCanvas();
        });


        setLeft(sideBar);
        setRight(canvas);
    }

    void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (DrawableFigure<? extends Figure> figure : canvasState.figures()) {

            figure.draw(gc);

        }
    }

    private void alertInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText(message);
        alert.setContentText("TPE Final POO Diciembre 2023");
        alert.showAndWait();
    }


}

