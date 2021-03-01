package Visualisation;

import Classes.Animal;
import Classes.Vector2D;
import World.GameMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class InfoPanel extends JPanel {
    protected GameMap map;
    private final Vector2D location;
    protected Animal animaltofollow;
    protected boolean showstatistics;
    protected int howlongfollow;
    protected int stopfollow;
    private ArrayList<Integer> AnimalPopulation;
    private ArrayList<Integer> PlantPopulation;
    protected ArrayList<Animal> childrentofollowstart;
    private ArrayList<Animal> childrentofollowstop;
    private int descedants;

    public InfoPanel(GameMap map, Vector2D location) {

        this.map = map;
        this.location=location;
        showstatistics=false;
        animaltofollow=null;
        childrentofollowstart=null;
        childrentofollowstop=null;
        descedants=0;
        AnimalPopulation=new ArrayList<Integer>();
        PlantPopulation=new ArrayList<Integer>();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize(760, 160);
        this.setLocation(location.x, location.y);
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Calibri", Font.ITALIC, 14));
        g.drawString("We're living in era : " + map.getEra(), 0, 10);

        AtomicInteger currpopulation= new AtomicInteger(0);
        map.getAnimals().forEach((key,value)->{
            currpopulation.addAndGet(value.size());
        });
        AnimalPopulation.add(currpopulation.get());
        g.drawString("Total number of animals: " + AnimalPopulation.get(AnimalPopulation.size()-1),0,30);
        PlantPopulation.add(map.getOccupiedplant().size());
        g.drawString("Total number of plants: " + PlantPopulation.get(PlantPopulation.size()-1),0,50);

        g.setColor(Color.BLACK);
        g.drawString("Most frequent genotype:",0,70);
        String str1="";
        for(int i=0;i< map.mostfrequentgenes().length;i++){
            str1+=" "+map.mostfrequentgenes()[i];
        }
        g.drawString(str1,0,90);
        if(AnimalPopulation.get(AnimalPopulation.size()-1)>0){
            g.drawString("Average energy level:   "+ map.averageenergylevel(),0,110);
        }
        else{
            g.drawString("Average energy level:  -  ",0,110);
        }

        if(map.getDeadanimals().size()>0){
            g.drawString("Average age for dead animals:    "+map.averageagefordead(),0,130);
        }
        else{
            g.drawString("Average age for dead animals:  - ",0,130);
        }
        if(AnimalPopulation.get(AnimalPopulation.size()-1)>0){
            g.drawString("Average children amount: "+map.averagechildrennumber(),0,150);
        }
        else{
            g.drawString("Average children amount:   -  ",0,150);
        }

        g.drawString("Click on the animal to show its genotype:", 350,10);
        if(animaltofollow!=null){
            g.drawString(animaltofollow.getGenes().toString(),350,30);
        }
        g.drawString("Click on the animal, enter number and click 'Follow animal' to see statistics:",250,50);
        if(showstatistics) {
            g.drawString("Number of children after " + howlongfollow + " eras: ", 350, 70);
            g.drawString("Number of descedants after " + howlongfollow + " eras: ", 350, 90);
            g.drawString("Era of death: ", 350, 110);
            if (animaltofollow != null && childrentofollowstart!=null) {
                if (animaltofollow.isDead()) {
                    g.drawString("" + animaltofollow.getDeathepoque(), 550, 110);
                }

                if (map.getEra() == stopfollow) {
                    Object obj = animaltofollow.getChildren().clone();
                    childrentofollowstop = (ArrayList<Animal>) obj;
                    childrentofollowstop.subList(childrentofollowstart.size(), childrentofollowstop.size());
                    descedants = childrentofollowstop.size();
                    for (Animal animal : childrentofollowstop) {
                        descedants += animal.getChildren().size();
                    }
                }
                if (map.getEra() >= stopfollow) {
                    g.drawString("" + childrentofollowstop.size(), 620, 70);
                    g.drawString("" + descedants, 620, 90);
                }
            }
        }

    }

}