package threedee.scene.implementation;

import threedee.geometry.WorldPolygon;
import threedee.geometry.WorldVertex;
import threedee.geometry.implementation.WorldRectangle;
import threedee.scene.Scene;
import threedee.scene.SceneLoader;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FileSceneLoader implements SceneLoader {

    private String fileName;

    public FileSceneLoader(String fileName) {
        this.fileName = fileName;
    }

    public Scene loadScene() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            return null;
        }

        SimpleScene scene = new SimpleScene();

        try {
            String line = reader.readLine();
            while (line != null) {
                StringTokenizer lineTokenizer = new StringTokenizer(line, ",");

                double vertex1x = Double.parseDouble(lineTokenizer.nextToken().trim());
                double vertex1y = Double.parseDouble(lineTokenizer.nextToken().trim());
                double vertex1z = Double.parseDouble(lineTokenizer.nextToken().trim());

                double vertex2x = Double.parseDouble(lineTokenizer.nextToken().trim());
                double vertex2y = Double.parseDouble(lineTokenizer.nextToken().trim());
                double vertex2z = Double.parseDouble(lineTokenizer.nextToken().trim());

                int red = Integer.parseInt(lineTokenizer.nextToken().trim());
                int green = Integer.parseInt(lineTokenizer.nextToken().trim());
                int blue = Integer.parseInt(lineTokenizer.nextToken().trim());

                double transparency = Double.parseDouble(lineTokenizer.nextToken().trim());

                WorldPolygon polygon = new WorldRectangle(new WorldVertex(vertex1x, vertex1y, vertex1z), new WorldVertex(vertex2x, vertex2y, vertex2z), new Color(red, green, blue), transparency);
                scene.addPolygon(polygon);

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file.");
            return null;
        }

        return scene;
    }
}
