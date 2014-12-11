package threedee.geometry.implementation;

import threedee.geometry.Fragment;
import threedee.geometry.Pixel;

import java.util.ArrayList;

public class SimpleFragment implements Fragment {
    private ArrayList<Pixel> pixels;

    public SimpleFragment() {
        pixels = new ArrayList<Pixel>();
    }

    public int getPixelCount() {
        return pixels.size();
    }

    public Pixel getPixel(int index) {
        return pixels.get(index);
    }

    public void addPixel(Pixel pixel) {
        pixels.add(pixel);
    }
}
