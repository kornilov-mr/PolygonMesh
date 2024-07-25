package core.tools.changes;

import core.scene.IDManager;
import core.scene.Scene;
import core.tools.changes.changeTree.ChangeTree;


public class ChangeManager {

    private final ChangeReverser changeReverser;
    private final ChangeTree changeTree;

    public ChangeManager(IDManager idManager, Scene scene) {
        this.changeReverser = new ChangeReverser(idManager);
        this.changeTree=new ChangeTree(scene);
    }
    public void addChangeInStack(Change change){
        System.out.println("add");
        changeTree.addNode(change);
    }
    public void reversePreviousChange(){
        changeReverser.ReverseChange(changeTree.previousChange());
    }
    public void reverseNextChange(){
        changeReverser.ReverseChange(changeTree.nextChange());
    }
}
