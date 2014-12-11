package threedee.geometry;

import java.awt.*;

public interface ScreenPolygon {
    int getVertexCount();
    ScreenVertex getVertex(int index);
    Color getColor();
    double getTransparency();
}
