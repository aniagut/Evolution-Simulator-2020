package Menu;
import World.GameMap;
import Visualisation.MapSimulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Panel extends JPanel implements ActionListener {

    public static final int HEIGHT=600;
    public static final int  WIDTH =600;

    private final JTextField delay;
    private final JTextField startAnimalsNum;
    private final JTextField width;
    private final JTextField height;
    private final JTextField startEnergy;
    private final JTextField moveEnergy;
    private final JTextField plantEnergy;
    private final JTextField copulationEnergy;
    private final JTextField jungleRatio;
    private final JTextField startPlantsNum;

    private final JLabel delayLabel;
    private final JLabel startAnimalsNumLabel;
    private final JLabel widthLabel;
    private final JLabel heightLabel;
    private final JLabel startEnergyLabel;
    private final JLabel moveEnergyLabel;
    private final JLabel plantEnergyLabel;
    private final JLabel copulationEnergyLabel;
    private final JLabel jungleRatioLabel;
    private final JLabel startPlantsNumLabel;

    private final JButton startButton;

    public Panel(Double[] defaultParameters) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        startButton = new JButton("Start Simulation");
        startButton.addActionListener(this);

        startEnergyLabel = new JLabel("Animal start energy:                  ");
        copulationEnergyLabel = new JLabel("Minimum energy to copulate:  ");
        plantEnergyLabel = new JLabel("Plant energy profit:                   ");
        heightLabel = new JLabel("Map height:                  ");
        widthLabel = new JLabel("Map width:                   ");
        jungleRatioLabel = new JLabel("Jungle ratio:                ");
        moveEnergyLabel = new JLabel("Daily move cost:                        ");
        delayLabel = new JLabel("Refresh time(ms):                            ");
        startAnimalsNumLabel=new JLabel("Number of animals at the start:      ");
        startPlantsNumLabel=new JLabel("Number of plants at the start:        ");

        int x = 10;

        startEnergy = new JTextField();
        startEnergy.setColumns(x);
        startEnergy.setText(defaultParameters[6].toString());

        plantEnergy = new JTextField();
        plantEnergy.setColumns(x);
        plantEnergy.setText(defaultParameters[5].toString());

        copulationEnergy = new JTextField();
        copulationEnergy.setColumns(x);
        copulationEnergy.setText(defaultParameters[3].toString());

        height = new JTextField();
        height.setColumns(x);
        height.setText(defaultParameters[1].toString());

        width = new JTextField();
        width.setColumns(x);
        width.setText(defaultParameters[0].toString());

        moveEnergy = new JTextField();
        moveEnergy.setColumns(x);
        moveEnergy.setText(defaultParameters[4].toString());

        jungleRatio = new JTextField();
        jungleRatio.setColumns(x);
        jungleRatio.setText(defaultParameters[2].toString());

        delay=new JTextField();
        delay.setColumns(x);
        delay.setText(defaultParameters[7].toString());

        startAnimalsNum=new JTextField();
        startAnimalsNum.setColumns(x);
        startAnimalsNum.setText(defaultParameters[8].toString());

        startPlantsNum=new JTextField();
        startPlantsNum.setColumns(x);
        startPlantsNum.setText(defaultParameters[9].toString());


        startEnergyLabel.setLabelFor(startEnergy);
        copulationEnergyLabel.setLabelFor(copulationEnergy);
        plantEnergyLabel.setLabelFor(plantEnergy);
        heightLabel.setLabelFor(height);
        widthLabel.setLabelFor(width);
        moveEnergyLabel.setLabelFor(moveEnergy);
        jungleRatioLabel.setLabelFor(jungleRatio);
        delayLabel.setLabelFor(delay);
        startAnimalsNumLabel.setLabelFor(startAnimalsNum);
        startPlantsNumLabel.setLabelFor(startPlantsNum);

        JPanel l1 = new JPanel();
        JPanel l2 = new JPanel();
        JPanel l3 = new JPanel();
        JPanel l4 = new JPanel();
        JPanel l5 = new JPanel();
        JPanel l6 = new JPanel();
        JPanel l7 = new JPanel();
        JPanel l8=new JPanel();
        JPanel l9=new JPanel();
        JPanel l10=new JPanel();


        l1.add(startEnergyLabel);
        l2.add(copulationEnergyLabel);
        l3.add(plantEnergyLabel);
        l4.add(widthLabel);
        l5.add(heightLabel);
        l6.add(moveEnergyLabel);
        l7.add(jungleRatioLabel);
        l8.add(delayLabel);
        l9.add(startAnimalsNumLabel);
        l10.add(startPlantsNumLabel);


        l1.add(startEnergy);
        l2.add(copulationEnergy);
        l3.add(plantEnergy);
        l4.add(width);
        l5.add(height);
        l6.add(moveEnergy);
        l7.add(jungleRatio);
        l8.add(delay);
        l9.add(startAnimalsNum);
        l10.add(startPlantsNum);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);

        add(new JLabel("Map"));
        add(l4);
        add(l5);
        add(l7);
        add(new JLabel("Energy"));
        add(l1);
        add(l2);
        add(l3);
        add(l6);
        add(new JLabel("Other"));
        add(l8);
        add(l9);
        add(l10);

        add(buttonPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        GameMap firstMap = new GameMap(

                Integer.parseInt(width.getText().substring(0,width.getText().length()-2)),
                Integer.parseInt(height.getText().substring(0,height.getText().length()-2)),
                Double.parseDouble(jungleRatio.getText()),
                Integer.parseInt(plantEnergy.getText().substring(0,plantEnergy.getText().length()-2)),
                Integer.parseInt(moveEnergy.getText().substring(0,moveEnergy.getText().length()-2)),
                Integer.parseInt(copulationEnergy.getText().substring(0,copulationEnergy.getText().length()-2)),
                Integer.parseInt(startEnergy.getText().substring(0,startEnergy.getText().length()-2))

        );
        GameMap secondMap=new GameMap(
                Integer.parseInt(width.getText().substring(0,width.getText().length()-2)),
                Integer.parseInt(height.getText().substring(0,height.getText().length()-2)),
                Double.parseDouble(jungleRatio.getText()),
                Integer.parseInt(plantEnergy.getText().substring(0,plantEnergy.getText().length()-2)),
                Integer.parseInt(moveEnergy.getText().substring(0,moveEnergy.getText().length()-2)),
                Integer.parseInt(copulationEnergy.getText().substring(0,copulationEnergy.getText().length()-2)),
                Integer.parseInt(startEnergy.getText().substring(0,startEnergy.getText().length()-2))
        );

        MapSimulation simulation=new MapSimulation(
                firstMap,secondMap,Integer.parseInt(delay.getText().substring(0,delay.getText().length()-2)),
                Integer.parseInt(startAnimalsNum.getText().substring(0,startAnimalsNum.getText().length()-2)),
                Integer.parseInt(startPlantsNum.getText().substring(0,startPlantsNum.getText().length()-2)));
        simulation.startSimulation();

    }
}

