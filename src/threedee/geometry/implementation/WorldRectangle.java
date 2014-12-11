package threedee.geometry.implementation;

import threedee.geometry.WorldPolygon;
import threedee.geometry.WorldVertex;

import java.awt.*;

public class WorldRectangle implements WorldPolygon {
    private WorldVertex[] vertices;
    private Color color;
    private double transparency;

    public WorldRectangle(WorldVertex upperLeftVertex, WorldVertex lowerRightVertex, Color color, double transparency) {
        vertices = new WorldVertex[4];

        vertices[0] = upperLeftVertex;
        vertices[1] = new WorldVertex(lowerRightVertex.x, upperLeftVertex.y, lowerRightVertex.z);
        vertices[2] = new WorldVertex(upperLeftVertex.x, lowerRightVertex.y, upperLeftVertex.z);
        vertices[3] = lowerRightVertex;

        this.color = color;
        this.transparency = transparency;
    }

    public int getVertexCount() {
        return vertices.length;
    }

    public WorldVertex getVertex(int index) {
        return vertices[index];
    }

    public Color getColor() {
        return color;
    }

    public double getTransparency() {
        return transparency;
    }
}
