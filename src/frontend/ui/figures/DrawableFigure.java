package frontend.ui.figures;

import backend.model.Figure;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

import java.util.EnumSet;

public abstract class DrawableFigure {

    private final String name;

    protected Figure figure;

    // hay que pasarle los efectos como parametro
    public DrawableFigure(String name, Figure figure, EnumSet effects) {
        this.name = name;
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public Figure getFigure() {
    	return figure;
    }

    public abstract Figure create(Point start, Point end);

    public abstract void draw(GraphicsContext gc);
}
