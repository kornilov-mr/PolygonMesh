package core.scene;

import primitive.face.Face;

import java.util.ArrayList;

public class Scene {

    private ArrayList<Face> faces =new ArrayList<>();

    public Scene(){

    }
    public void addFace(Face face){
        faces.add(face);
    }

    public ArrayList<Face> getFaces() {
        return faces;
    }
}
