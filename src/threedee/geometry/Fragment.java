package threedee.geometry;

public interface Fragment {
    int getPixelCount();
    Pixel getPixel(int index);
    void addPixel(Pixel pixel);
}
