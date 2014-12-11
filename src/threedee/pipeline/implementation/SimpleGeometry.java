package threedee.pipeline.implementation;

import threedee.geometry.ScreenPolygon;
import threedee.geometry.ScreenVertex;
import threedee.geometry.WorldPolygon;
import threedee.geometry.WorldVertex;
import threedee.geometry.implementation.ScreenRectangle;
import threedee.pipeline.Geometry;
import threedee.pipeline.PipelineListener;
import threedee.scene.Scene;
import threedee.scene.SceneListener;

import java.util.ArrayList;

public class SimpleGeometry implements Geometry {
    private Scene scene;
    private ArrayList<ScreenPolygon> screenPolygons;
    private ArrayList<PipelineListener> listeners;

    double xWorldMin;
    double xWorldMax;
    double yWorldMin;
    double yWorldMax;
    double cameraPosition;
    int screenWidth;
    int screenHeight;

    public SimpleGeometry(Scene scene,
                          double xWorldMin, double xWorldMax, double yWorldMin, double yWorldMax,
                          double cameraPosition,
                          int screenWidth, int screenHeight) {

        this.scene = scene;
        this.xWorldMin = xWorldMin;
        this.xWorldMax = xWorldMax;
        this.yWorldMin = yWorldMin;
        this.yWorldMax = yWorldMax;
        this.cameraPosition = cameraPosition;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        scene.addSceneListener(new SceneListener() {
            public void sceneChanged() {
                transformWorldToScreen();
            }
        });

        screenPolygons = new ArrayList<ScreenPolygon>();
        listeners = new ArrayList<PipelineListener>();

        transformWorldToScreen();
    }

    public ArrayList<ScreenPolygon> getScreenObjects() {
        return screenPolygons;
    }

    public void setScreenSize(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
        transformWorldToScreen();
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setFieldOfView(double xMin, double xMax, double yMin, double yMax) {
        xWorldMin = xMin;
        xWorldMax = xMax;
        yWorldMin = yMin;
        yWorldMax = yMax;
        transformWorldToScreen();
    }

    public void setCameraPosition(double z) {
        this.cameraPosition = z;
        transformWorldToScreen();
    }

    public void addPipelineListener(PipelineListener listener) {
        listeners.add(listener);
    }

    private void firePipelineChanged() {
        for (PipelineListener listener : listeners) {
            listener.pipelineChanged();
        }
    }

    private void transformWorldToScreen() {
        screenPolygons.clear();

        for (int i=0; i < scene.getPolygonCount(); i++) {
            WorldPolygon worldPolygon = scene.getPolygon(i);

            WorldVertex wv1 = worldPolygon.getVertex(0);
            int sv1x = computeScreenCoordinate(screenWidth, xWorldMin, xWorldMax, wv1.x);
            int sv1y = computeScreenCoordinate(screenHeight, yWorldMin, yWorldMax, wv1.y);
            ScreenVertex sv1 = new ScreenVertex(sv1x, sv1y, cameraPosition - wv1.z);

            WorldVertex wv2 = worldPolygon.getVertex(3);
            int sv2x = computeScreenCoordinate(screenWidth, xWorldMin, xWorldMax, wv2.x);
            int sv2y = computeScreenCoordinate(screenHeight, yWorldMin, yWorldMax, wv2.y);
            ScreenVertex sv2 = new ScreenVertex(sv2x, sv2y, cameraPosition - wv2.z);

            ScreenPolygon screenPolygon = new ScreenRectangle(sv1, sv2, worldPolygon.getColor(), worldPolygon.getTransparency());
            screenPolygons.add(screenPolygon);
        }

        firePipelineChanged();
    }

    private int computeScreenCoordinate(int screenSize, double worldMin, double worldMax, double worldCoordinate) {
        return (int) ((worldCoordinate - worldMin) / (worldMax - worldMin) * (screenSize-1));
    }
}
