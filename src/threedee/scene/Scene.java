package threedee.scene;

import threedee.geometry.WorldPolygon;

public interface Scene {
    int getPolygonCount();
    WorldPolygon getPolygon(int index);
    void addSceneListener(SceneListener listener);
}
