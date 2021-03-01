package Visualisation;

import Classes.Animal;
import Classes.Plant;
import Classes.Vector2D;
import World.GameMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapSimulation implements ActionListener {

    private GameMap firstMap;
    private GameMap secondMap;

    private final int startAnimalsNum;
    private final int startPlantsNum;
    private final int delay;
    private JFrame frame;
    private MapPanel firstMapPanel;
    private MapPanel secondMapPanel;
    private InfoPanel firstInfoPanel;
    private InfoPanel secondInfoPanel;
    private MainInfo mainInfo;
    private ButtonPanel firstButtonPanel;
    private ButtonPanel secondButtonPanel;
    protected Timer firstTimer;
    protected Timer secondTimer;

    public MapSimulation(GameMap firstMap,GameMap secondMap,int delay,int startAnimalsNum,int startPlantsNum) {
        this.delay=delay;
        this.firstMap = firstMap;
        this.secondMap=secondMap;
        this.startAnimalsNum=startAnimalsNum;
        this.startPlantsNum=startPlantsNum;

        firstTimer = new Timer(delay, this);
        secondTimer=new Timer(delay,this);

        frame = new JFrame("Evolution Simulator");
        frame.setSize(1600, 1000);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        firstInfoPanel = new InfoPanel(firstMap,  new Vector2D(20,50));
        firstInfoPanel.setSize(1, 1);

        secondInfoPanel=new InfoPanel(secondMap,new Vector2D(790,50));
        secondInfoPanel.setSize(1,1);

        firstMapPanel = new MapPanel(firstMap, new Vector2D(15,250),firstInfoPanel,firstTimer);
        firstMapPanel.setSize(new Dimension(1, 1));

        secondMapPanel=new MapPanel(secondMap,new Vector2D(780,250),secondInfoPanel,secondTimer);
        secondMapPanel.setSize(new Dimension(1, 1));


        mainInfo=new MainInfo();
        mainInfo.setSize(1,1);

        firstButtonPanel=new ButtonPanel(new Vector2D(20,200),firstInfoPanel,firstMap,firstMapPanel);
        firstButtonPanel.setSize(new Dimension(1,1));

        secondButtonPanel=new ButtonPanel(new Vector2D(790,200),secondInfoPanel,secondMap,secondMapPanel);
        secondButtonPanel.setSize(new Dimension(1,1));


        frame.add(firstMapPanel);
        frame.add(secondMapPanel);
        frame.add(firstInfoPanel);
        frame.add(secondInfoPanel);
        frame.add(mainInfo);
        frame.add(firstButtonPanel);
        frame.add(secondButtonPanel);

    }
    public void startSimulation() {
        for (int i = 0; i < startAnimalsNum; i++) {
            new Animal(this.firstMap);
            new Animal(this.secondMap);
        }
        for(int i=0;i<startPlantsNum;i++){
            new Plant(this.firstMap);
            new Plant(this.secondMap);
        }
        firstTimer.start();
        secondTimer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(firstTimer.isRunning()){
            firstInfoPanel.repaint();
            firstMapPanel.repaint();
            firstMap.removeDead();
            firstMap.refreshstats();
            firstMap.moveanimals();
            firstMap.eating();
            firstMap.copulation();
            firstMap.nextDay();
        }
        if(secondTimer.isRunning()){
            secondInfoPanel.repaint();
            secondMapPanel.repaint();
            secondMap.removeDead();
            secondMap.refreshstats();
            secondMap.moveanimals();
            secondMap.eating();
            secondMap.copulation();
            secondMap.nextDay();
        }

    }

}
