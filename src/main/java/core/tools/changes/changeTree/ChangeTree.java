package core.tools.changes.changeTree;

import core.scene.Scene;
import core.tools.changes.Change;


public class ChangeTree {

    private ChangeNode currentNode;
    private Change lastChange;
    private ChangeNode fork;

    public ChangeTree(Scene scene){
        this.currentNode = new NullNode(scene);
    }
    public void addNode(Change change){
        ChangeNode newNode = new ChangeNode(change,currentNode);
        currentNode.setNextMainNode(newNode);
        currentNode=newNode;
        lastChange=change;
    }
    public Change previousChange(){
        System.out.println("prev");
        currentNode=currentNode.getUpNode();
        return currentNode.getChange();
    }
    public Change nextChange(){
        ChangeNode nextNode = currentNode.getNextMainNode();
        if(nextNode==null){
            return lastChange;
        }
        Change currentChange = nextNode.getChange();

        currentNode=nextNode;
        return currentChange;
    }

}
