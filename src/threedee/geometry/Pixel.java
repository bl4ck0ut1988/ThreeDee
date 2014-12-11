package threedee.geometry;

import java.awt.*;

public class Pixel {
    public int x;
    public int y;
    public Color color;
    public double transparency;
    public double depth;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;

        this.color = Color.black;
        this.transparency = 0;
        this.depth = 0;
    }
}
