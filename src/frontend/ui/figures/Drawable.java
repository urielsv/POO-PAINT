package frontend.ui.figures;

import javafx.scene.canvas.GraphicsContext;

@FunctionalInterface
public interface Drawable {
    void draw(GraphicsContext gc);
}
