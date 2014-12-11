package threedee.scene.implementation;

import threedee.geometry.WorldPolygon;
import threedee.scene.Scene;
import threedee.scene.SceneListener;

import java.util.ArrayList;

public class SimpleScene implements Scene {
    private ArrayList<WorldPolygon> polygons;
    private ArrayList<SceneListener> listeners;

    public SimpleScene() {
        polygons = new ArrayList<WorldPolygon>();
        listeners = new ArrayList<SceneListener>();
    }

    public void addPolygon(WorldPolygon polygon) {
        polygons.add(polygon);
        fireSceneChanged();
    }

    public void addPolygons(ArrayList<WorldPolygon> newPolygons) {
        for (WorldPolygon newPolygon : newPolygons) {
            polygons.add(newPolygon);
        }
        fireSceneChanged();
    }

    public void replacePolygons(ArrayList<WorldPolygon> newPolygons) {
        polygons.clear();
        addPolygons(newPolygons);
    }

    public int getPolygonCount() {
        return polygons.size();
    }

    public WorldPolygon getPolygon(int index) {
        return polygons.get(index);
    }

    public void addSceneListener(SceneListener listener) {
        listeners.add(listener);
    }

    private void fireSceneChanged() {
        for (SceneListener listener : listeners) {
            listener.sceneChanged();
        }
    }
}
