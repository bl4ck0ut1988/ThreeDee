package threedee.pipeline.implementation;

import threedee.geometry.Fragment;
import threedee.geometry.Pixel;
import threedee.geometry.ScreenPolygon;
import threedee.geometry.ScreenVertex;
import threedee.geometry.implementation.SimpleFragment;
import threedee.pipeline.Geometry;
import threedee.pipeline.PipelineListener;
import threedee.pipeline.Rasterization;

import java.util.ArrayList;

public class SimpleRasterization implements Rasterization {
    private Geometry geometry;
    private ArrayList<Fragment> fragments;
    private ArrayList<PipelineListener> listeners;

    public SimpleRasterization(Geometry geometry) {
        this.geometry = geometry;
        geometry.addPipelineListener(new PipelineListener() {
            public void pipelineChanged() {
                doRasterization();
            }
        });

        fragments = new ArrayList<Fragment>();
        listeners = new ArrayList<PipelineListener>();
    }

    public ArrayList<Fragment> getFragments() {
        return fragments;
    }

    public int getScreenWidth() {
        return geometry.getScreenWidth();
    }

    public int getScreenHeight() {
        return geometry.getScreenHeight();
    }

    public void addPipelineListener(PipelineListener listener) {
        listeners.add(listener);
    }

    private void firePipelineChanged() {
        for (PipelineListener listener : listeners) {
            listener.pipelineChanged();
        }
    }

    private void doRasterization() {
        fragments.clear();

        ArrayList<ScreenPolygon> polygons = geometry.getScreenObjects();
        for (ScreenPolygon polygon : polygons) {
            Fragment fragment = new SimpleFragment();

            ScreenVertex v1 = polygon.getVertex(0);
            ScreenVertex v2 = polygon.getVertex(3);

            for (int row = v2.y; row <= v1.y; row++) {
                for (int column = v1.x; column <= v2.x; column++) {
                    Pixel pixel = new Pixel(column, row);
                    pixel.color = polygon.getColor();
                    pixel.transparency = polygon.getTransparency();
                    pixel.depth = (double)(column - v1.x) / (double)(v2.x - v1.x) * (v2.depth - v1.depth) + v1.depth;
                    fragment.addPixel(pixel);
                }
            }

            fragments.add(fragment);
        }

        firePipelineChanged();
    }
}
