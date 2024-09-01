package core.scene.resentProjects;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ResentProjectManager {
    //class to store ResentProjectData and saving it in Json file
    private final File jsonFile = new File("src/main/java/core/data/resentProjects.json");
    private final ArrayList<ResentProjectData> projects = new ArrayList<>();

    public ResentProjectManager() {
        checkFile();
        readResentProjects();
    }
    private void checkFile(){
        if(!jsonFile.exists()){
            try {
                jsonFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            PrintWriter printWriter=null;
            try {
                printWriter = new PrintWriter(jsonFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            printWriter.print("[]");
            printWriter.flush();
        }
    }
    private void readResentProjects(){
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(jsonFile.toURI())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONArray jsonArray = new JSONArray(content);
        for(int i =0;i<jsonArray.length();i++){
            JSONObject data = jsonArray.getJSONObject(i);
            ResentProjectData resentProject= new ResentProjectData(data);
            projects.add(resentProject);
        }
    }
    public void addNewProject(ResentProjectData resentProjectData){
        projects.add(resentProjectData);
    }
    public void saveResentData(){
        checkFile();
        try {
            PrintWriter printWriter = new PrintWriter(jsonFile);
            JSONArray jsonArray = new JSONArray();
            for(ResentProjectData project: projects){
                jsonArray.put(project.createJson());
            }
            printWriter.print(jsonArray);
            printWriter.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<ResentProjectData> getSortedResentProjects(){
        projects.sort(new ResentProjectComparator());
        return projects;
    }

}
