package Menu;

import javax.swing.*;

public class SettingsMenu {

    private JFrame menu;

    public SettingsMenu() {

        menu = new JFrame("Settings");
        menu.setSize(500, 500);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLocationRelativeTo(null);

    }
    public void startSimulation(Double[] defaultparameters){
        menu.add(new Panel(defaultparameters));
        menu.setVisible(true);
    }
}