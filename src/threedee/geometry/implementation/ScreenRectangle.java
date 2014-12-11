package threedee.geometry.implementation;

import threedee.geometry.ScreenPolygon;
import threedee.geometry.ScreenVertex;

import java.awt.*;

public class ScreenRectangle implements ScreenPolygon {
    private ScreenVertex[] vertices;
    public Color color;
    public double transparency;

    public ScreenRectangle(ScreenVertex upperLeftVertex, ScreenVertex lowerRightVertex, Color color, double transparency) {
        vertices = new ScreenVertex[4];
        vertices[0] = upperLeftVertex;
        vertices[1] = new ScreenVertex(lowerRightVertex.x, upperLeftVertex.y, lowerRightVertex.depth);
        vertices[2] = new ScreenVertex(upperLeftVertex.x, lowerRightVertex.y, upperLeftVertex.depth);
        vertices[3] = lowerRightVertex;
        this.color = color;
        this.transparency = transparency;
    }

    public int getVertexCount() {
        return vertices.length;
    }

    public ScreenVertex getVertex(int index) {
        return vertices[index];
    }

    public Color getColor() {
        return color;
    }

    public double getTransparency() {
        return transparency;
    }

}
