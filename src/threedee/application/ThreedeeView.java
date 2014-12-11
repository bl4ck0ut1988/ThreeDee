package threedee.application;

import threedee.pipeline.Geometry;
import threedee.pipeline.PipelineListener;
import threedee.pipeline.Pixels;
import threedee.pipeline.Rasterization;
import threedee.pipeline.implementation.SimpleGeometry;
import threedee.pipeline.implementation.SimplePixels;
import threedee.pipeline.implementation.SimpleRasterization;
import threedee.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class ThreedeeView extends JPanel {
    private Scene scene;
    private Geometry geometry;
    private Rasterization rasterization;
    private Pixels pixels;

    public ThreedeeView(Scene scene) {
        this.scene = scene;

        geometry = new SimpleGeometry(scene, 0, 100, 0, 100, 100, 100, 100);
        rasterization = new SimpleRasterization(geometry);
        pixels = new SimplePixels(rasterization);

        pixels.addPipelineListener(new PipelineListener() {
            public void pipelineChanged() {
                repaint();
            }
        });

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                Dimension d = getSize();
                geometry.setScreenSize(d.width, d.height);
            }
        });
    }

    protected void paintComponent(Graphics g) {
        BufferedImage screenBuffer = pixels.getPixels();
        g.drawImage(screenBuffer, 0, 0, null);
    }
}
