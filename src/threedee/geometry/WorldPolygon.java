package threedee.geometry;

import java.awt.*;

public interface WorldPolygon {
    int getVertexCount();
    WorldVertex getVertex(int index);
    Color getColor();
    double getTransparency();
}
