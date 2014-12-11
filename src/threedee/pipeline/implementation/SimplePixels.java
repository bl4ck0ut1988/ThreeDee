package threedee.pipeline.implementation;

import threedee.geometry.Fragment;
import threedee.geometry.Pixel;
import threedee.pipeline.PipelineListener;
import threedee.pipeline.Pixels;
import threedee.pipeline.Rasterization;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SimplePixels implements Pixels {
    private Rasterization rasterization;
    private BufferedImage screenBuffer;
    private Color backgroundColor;

    private ArrayList<PipelineListener> listeners;

    public SimplePixels(Rasterization rasterization) {
        this.rasterization = rasterization;
        rasterization.addPipelineListener(new PipelineListener() {
            public void pipelineChanged() {
                createScreenBuffer();
            }
        });

        backgroundColor = Color.black;

        listeners = new ArrayList<PipelineListener>();
    }

    public BufferedImage getPixels() {
        return screenBuffer;
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        createScreenBuffer();
    }

    public void setDepthCueingEnabled(boolean enabled) {
    }

    public void setTransparencyEnabled(boolean enabled) {
    }

    public void addPipelineListener(PipelineListener listener) {
        listeners.add(listener);
    }

    private void firePipelineChanged() {
        for (PipelineListener listener : listeners) {
            listener.pipelineChanged();
        }
    }

    private void createScreenBuffer() {
        int screenWidth = rasterization.getScreenWidth();
        int screenHeight = rasterization.getScreenHeight();
        screenBuffer = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);

        paintBackground();
        paintFragments();

        firePipelineChanged();
    }

    private void paintBackground() {
        for (int i=0; i<screenBuffer.getHeight(); i++) {
            for (int j=0; j<screenBuffer.getWidth(); j++) {
                screenBuffer.setRGB(j, i, backgroundColor.getRGB());
            }
        }
    }

    private void paintFragments() {
        int screenHeight = rasterization.getScreenHeight();
        ArrayList<Fragment> fragments = rasterization.getFragments();
        for (Fragment fragment : fragments) {
            for (int i=0; i<fragment.getPixelCount(); i++) {
                Pixel pixel = fragment.getPixel(i);
                screenBuffer.setRGB(pixel.x, (screenHeight-1) - pixel.y, pixel.color.getRGB());
            }
        }
    }

}
