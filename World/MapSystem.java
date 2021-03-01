package World;
import Menu.SettingsMenu;

import java.io.FileNotFoundException;
import javax.swing.*;
public class MapSystem {


    public static void main(String args[]){
        try {

            ParametersLoader parameters = ParametersLoader.loadFromFile();

            Double[] defaultMapProperties = {
                    parameters.getWidth()*1.0,
                    parameters.getHeight()*1.0,
                    parameters.getJungleRatio()*1.0,
                    parameters.getCopulationEnergy()*1.0,
                    parameters.getMoveEnergy()*1.0,
                    parameters.getPlantEnergy()*1.0,
                    parameters.getStartEnergy()*1.0,
                    parameters.getDelay()*1.0,
                    parameters.getStartAnimalsNum()*1.0,
                    parameters.getStartPlantsNum()*1.0
            };

            //SettingsMenu constructor start animation
            SettingsMenu menu = new SettingsMenu();
            menu.startSimulation(defaultMapProperties);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
            return;
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex);
            return;
        }
    }
}

