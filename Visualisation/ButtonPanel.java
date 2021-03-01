package Visualisation;

import Classes.Animal;
import Classes.Vector2D;
import World.GameMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ButtonPanel extends JPanel implements ActionListener, KeyListener {
    private JButton pauseButton;
    private JButton continueButton;
    private JButton followButton;
    private JButton writetofileButton;

    private JTextField textfield;

    private Vector2D location;

    private InfoPanel infoPanel;
    private MapPanel mapPanel;
    private GameMap map;

    public ButtonPanel(Vector2D location,InfoPanel infoPanel,GameMap map,MapPanel mapPanel){
        textfield=new JTextField("Enter number");
        pauseButton=new JButton("Pause simulation");
        pauseButton.addActionListener(this);
        continueButton=new JButton("Continue simulation");
        continueButton.addActionListener(this);
        followButton=new JButton("Follow animal");
        followButton.addActionListener(this);
        writetofileButton=new JButton("Save statistics");
        writetofileButton.addActionListener(this);
        textfield.addKeyListener(this);

        this.location=location;
        this.infoPanel=infoPanel;
        this.mapPanel=mapPanel;
        this.map=map;
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setSize(760, 50);
        this.setLocation(location.x, location.y);
        textfield.setLocation(0,15);
        followButton.setLocation(100,15);
        followButton.setSize(150,20);
        pauseButton.setLocation(260,15);
        pauseButton.setSize(150,20);
        continueButton.setLocation(420,15);
        continueButton.setSize(150,20);
        writetofileButton.setLocation(580,15);
        writetofileButton.setSize(150,20);
        add(textfield);
        add(pauseButton);
        add(continueButton);
        add(followButton);
        add(writetofileButton);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        Object source=e.getSource();
        if(source==pauseButton){
            mapPanel.timer.stop();
        }
        if(source==continueButton){
            mapPanel.timer.start();
        }
        if(source==followButton){
            try {
                infoPanel.howlongfollow = Integer.parseInt(textfield.getText());
                infoPanel.stopfollow= infoPanel.howlongfollow+infoPanel.map.getEra();
                infoPanel.showstatistics=true;
                if(infoPanel.animaltofollow!=null){
                    Object obj=infoPanel.animaltofollow.getChildren().clone();
                    infoPanel.childrentofollowstart=(ArrayList<Animal>)obj;
                }
                infoPanel.repaint();
            }
            catch (IllegalArgumentException d){
                System.out.println("Enter correct number");
            }


        }
        if(source==writetofileButton){
            try{
                File myObj = new File("statistics.txt");
                myObj.createNewFile();
                FileWriter myWriter=new FileWriter(myObj,false);
                int[] dominating=new int[32];
                int max1=1;
                for(int[] key:map.allgenes.keySet()){
                    if(map.allgenes.get(key)>max1){
                        max1=map.allgenes.get(key);
                        dominating=key;
                    }
                }
                myWriter.write("Average statistics after: "+map.getEra()+" eras\nAverage number of animals: "+map.sumanimals/map.getEra()+"\nAverage number of plants: "+map.sumplants/map.getEra()+"\n" +
                        "Average energy level: "+map.sumenergy/map.sumanimals+"\nAverage lifetime for dead animals: "+map.deathage/map.deadnum+"\n" +
                        "Average amount of children: "+map.sumchildren/map.sumanimals+"\nDominating genotype: "+Arrays.toString(dominating));
                myWriter.close();
                System.out.println("File created: " + myObj.getName());
            }
            catch (IOException a) {
                System.out.println("An error occurred.");
                a.printStackTrace();
            }
        }

    }
    @Override
    public void keyReleased(KeyEvent e){

    }
    @Override
    public void keyPressed(KeyEvent e){

    }
    @Override
    public void keyTyped(KeyEvent e){

    }
}
