package threedee.application;

import threedee.scene.Scene;
import threedee.scene.SceneLoader;
import threedee.scene.implementation.FileSceneLoader;

import javax.swing.*;
import java.awt.*;

public class ThreedeeApplication {

    public ThreedeeApplication(String sceneFileName) {

        SceneLoader sceneLoader = new FileSceneLoader(sceneFileName);
        Scene scene = sceneLoader.loadScene();
        if (scene == null) {
            System.err.println("Error loading the scene description.");
            System.exit(1);
        }

        ThreedeeView threedeeView = new ThreedeeView(scene);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(threedeeView);

        final JFrame frame = new JFrame();
        frame.setContentPane(mainPanel);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);

    }

    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("Usage: ThreedeeApplication sceneFileName");
            System.exit(1);
        }

        new ThreedeeApplication(args[0]);

    }
}
