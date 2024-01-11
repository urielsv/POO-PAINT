package frontend;

import frontend.CanvasState;
import backend.model.*;
import frontend.ui.MouseActions;
import frontend.ui.bars.SideBar;
import frontend.ui.buttons.FigureButton;
import frontend.ui.figures.DrawableFigure;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
	private final Color lineColor = Color.BLACK;
	private final Color defaultFillColor = Color.YELLOW;

	// Botones Barra Izquierda
	private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
//	private final ToggleButton rectangleButton = new ToggleButton("Rectángulo");
//	private final ToggleButton circleButton = new ToggleButton("Círculo");
//	private final ToggleButton squareButton = new ToggleButton("Cuadrado");
//	private final ToggleButton ellipseButton = new ToggleButton("Elipse");
	private final ToggleButton deleteButton = new ToggleButton("Borrar");

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
			// temp
			FigureButton button = (FigureButton) sideBar.getTools().getSelectedToggle();
			if(button != null) {
				button.onMousePressed(startPoint);
			}
		});

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null) {
				return ;
			}
			if(endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()) {
				return ;
			}

			FigureButton button = (FigureButton) sideBar.getTools().getSelectedToggle();
			if(button != null) {
				button.onMouseReleased(endPoint);
			}

			redrawCanvas();
		});

		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(DrawableFigure<? extends Figure> figure : canvasState.figures()) {
				if(canvasState.figureBelongs(figure, eventPoint)) {
					found = true;
					label.append(figure.getFigure().toString());
				}
			}
			if(found) {
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
				for (DrawableFigure<? extends Figure> figure : canvasState.figures()) {
					if(canvasState.figureBelongs(figure, eventPoint)) {
						found = true;
						selectedFigure = figure;
						figure.setSelected(true);
						canvasState.setSelectedFigure(selectedFigure);
						label.append(figure.toString());
					}
//				}
				if (found) {
					statusPane.updateStatus(label.toString());
				} else {
					selectedFigure = null;
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
			}
		});

//		canvas.setOnMouseDragged(event -> {
//			if(selectionButton.isSelected()) {
//				Point eventPoint = new Point(event.getX(), event.getY());
//				double diffX = (eventPoint.getX() - startPoint.getX());
//				double diffY = (eventPoint.getY() - startPoint.getY());
//
//				selectedFigure = canvasState.updateFigure(diffX, diffY);
//				startPoint.move(diffX, diffY);
//				redrawCanvas();
//			}
//		});
//
//		deleteButton.setOnAction(event -> {
//			if (selectedFigure != null) {
//				canvasState.deleteFigure(selectedFigure);
//				selectedFigure = null;
//				redrawCanvas();
//			}
//		});

		setLeft(sideBar);
		setRight(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		for(DrawableFigure<? extends  Figure> figure: canvasState.figures()) {

			// if selected
//			if(figure == selectedFigure) {
//				gc.setStroke(Color.RED);
//			} else {
//				gc.setStroke(lineColor);
//			}
//			gc.setFill(figureColorMap.get(figure));

			figure.draw(gc);
			//drawableFigure.applyEffects(gc);


//			drawableFigure.draw(gc);

//			// @TODO: Make generic (front)
//			if(figure instanceof Rectangle) {
//				Rectangle rectangle = (Rectangle) figure;
//				gc.fillRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
//						Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
//				gc.strokeRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
//						Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
//			} else if(figure instanceof Circle) {
//				Circle circle = (Circle) figure;
//				double diameter = circle.getRadius() * 2;
//				gc.fillOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
//				gc.strokeOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
//			} else if(figure instanceof Square) {
//				Square square = (Square) figure;
//				gc.fillRect(square.getTopLeft().getX(), square.getTopLeft().getY(),
//						Math.abs(square.getTopLeft().getX() - square.getBottomRight().getX()), Math.abs(square.getTopLeft().getY() - square.getBottomRight().getY()));
//				gc.strokeRect(square.getTopLeft().getX(), square.getTopLeft().getY(),
//						Math.abs(square.getTopLeft().getX() - square.getBottomRight().getX()), Math.abs(square.getTopLeft().getY() - square.getBottomRight().getY()));
//			} else if(figure instanceof Ellipse) {
//				Ellipse ellipse = (Ellipse) figure;
//				gc.strokeOval(ellipse.getCenterPoint().getX() - (ellipse.getsMayorAxis() / 2), ellipse.getCenterPoint().getY() - (ellipse.getsMinorAxis() / 2), ellipse.getsMayorAxis(), ellipse.getsMinorAxis());
//				gc.fillOval(ellipse.getCenterPoint().getX() - (ellipse.getsMayorAxis() / 2), ellipse.getCenterPoint().getY() - (ellipse.getsMinorAxis() / 2), ellipse.getsMayorAxis(), ellipse.getsMinorAxis());
//			}
		}
	}

}
