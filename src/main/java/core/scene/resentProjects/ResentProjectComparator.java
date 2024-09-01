package core.scene.resentProjects;

import java.util.Comparator;

public class ResentProjectComparator implements Comparator<ResentProjectData> {
    //Comparator to sort ResentProjectData on time
    @Override
    public int compare(ResentProjectData o1, ResentProjectData o2) {
        if(o1.lastTime>o2.lastTime){
            return -1;
        } else if(o1.lastTime==o2.lastTime){
            return 0;
        } else {
            return 1;
        }
    }
}
