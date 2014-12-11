package threedee.scene.implementation;

import threedee.geometry.WorldVertex;
import threedee.geometry.implementation.WorldRectangle;
import threedee.scene.Scene;
import threedee.scene.SceneLoader;

import java.awt.*;

public class MemorySceneLoader implements SceneLoader {
    public Scene loadScene() {
        SimpleScene scene = new SimpleScene();

        scene.addPolygon(new WorldRectangle(new WorldVertex(30, 70, 50), new WorldVertex(90, 30, 50), Color.yellow, 0.1));
        scene.addPolygon(new WorldRectangle(new WorldVertex(10, 90, 40), new WorldVertex(40, 50, 10), Color.blue, 0));
        scene.addPolygon(new WorldRectangle(new WorldVertex(60, 80, 20), new WorldVertex(80, 20, 40), Color.red, 0));
        scene.addPolygon(new WorldRectangle(new WorldVertex( 5, 60, 10), new WorldVertex(70, 10, 70), Color.green, 0.5));

        return scene;
    }
}
