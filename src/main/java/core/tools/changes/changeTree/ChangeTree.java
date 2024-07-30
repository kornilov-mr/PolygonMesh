package core.tools.changes.changeTree;

import core.scene.Scene;
import core.tools.changes.Change;

import java.util.ArrayList;


public class ChangeTree {

    private ChangeNode currentNode;

    public ChangeTree(Scene scene){
        this.currentNode = new NullNode(scene);
    }
    public void addNode(Change change){
        ChangeNode newNode = new ChangeNode(change,currentNode);
        currentNode.setNextMainNode(newNode);
        currentNode=newNode;
    }
    public Change previousChange(){
        currentNode=currentNode.getUpNode();
        return currentNode.getChange();
    }
    public Change nextChange(){
        ChangeNode nextNode = currentNode.getNextMainNode();
        if(nextNode==null){
            return new Change(new ArrayList<>(), new ArrayList<>());
        }
        Change currentChange = nextNode.getChange();

        currentNode=nextNode;
        return currentChange;
    }

}
