package World;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;



public class ParametersLoader {
    private int width;
    private int height;
    private int startEnergy;
    private int moveEnergy;
    private int plantEnergy;
    private int copulationEnergy;
    private double jungleRatio;
    private int delay;
    private int startAnimalsNum;
    private int startPlantsNum;

    static public ParametersLoader loadFromFile() throws FileNotFoundException,IllegalArgumentException{
        Gson gson = new Gson();
        File f = new File("");
        System.out.println(f.getAbsolutePath());
        ParametersLoader parameters =  (ParametersLoader) gson.fromJson(new FileReader("src\\World\\parameters.json"), ParametersLoader.class);
        parameters.validate();
        return parameters;
    }
    public void validate() throws IllegalArgumentException {
        if (this.width <= 0) {
            throw new IllegalArgumentException("Invalid map width");
        }
        if (this.height <= 0) {
            throw new IllegalArgumentException("Invalid map height");
        }
        if (this.jungleRatio <= 0) {
            throw new IllegalArgumentException("Invalid jungle width");
        }
        if (this.copulationEnergy < 0) {
            throw new IllegalArgumentException("Invalid copulationMinimumEnergy");
        }
        if (this.startEnergy < 0) {
            throw new IllegalArgumentException("Invalid animalsStartEnergy");
        }
        if (this.moveEnergy < 0) {
            throw new IllegalArgumentException("Invalid numOfSpawnedAnimals");
        }
        if (this.plantEnergy < 0) {
            throw new IllegalArgumentException("Invalid grassSpawnedInEachDay");
        }
        if(this.delay<0){
            throw new IllegalArgumentException("Invalid delay");
        }
        if(this.startAnimalsNum<=0){
            throw new IllegalArgumentException("Invalid number of animals");
        }
        if(this.startPlantsNum<=0){
            throw new IllegalArgumentException("Invalid number of plants");
        }
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public int getStartEnergy(){
        return startEnergy;
    }
    public int getMoveEnergy(){
        return moveEnergy;
    }
    public int getPlantEnergy(){
        return plantEnergy;
    }
    public int getCopulationEnergy(){
        return copulationEnergy;
    }
    public double getJungleRatio(){ return jungleRatio; }
    public int getDelay(){
        return delay;
    }
    public int getStartAnimalsNum(){
        return startAnimalsNum;
    }
    public int getStartPlantsNum(){
        return startPlantsNum;
    }
}
