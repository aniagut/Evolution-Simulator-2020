package Visualisation;

import Classes.Animal;
import Classes.Vector2D;
import World.GameMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MapPanel extends JPanel implements MouseListener{

    private GameMap map;
    private final Vector2D location;
    private BufferedImage image;
    private BufferedImage en1;
    private BufferedImage en2;
    private BufferedImage en3;
    private BufferedImage en4;
    private BufferedImage en5;
    private BufferedImage en6;
    private BufferedImage en7;
    private BufferedImage en8;
    private BufferedImage en9;
    private BufferedImage en10;
    private BufferedImage en11;
    private BufferedImage en12;


    private InfoPanel infopanel;
    private Animal animalstofollow;
    protected Timer timer;


    public MapPanel(GameMap map, Vector2D location, InfoPanel infopanel,Timer timer) {
        this.map = map;
        this.location=location;
        this.infopanel=infopanel;
        this.timer=timer;

        try {
            image = ImageIO.read(new File("src\\kwiatek.png"));
            en1=ImageIO.read(new File("src\\1mucha.png"));
            en2=ImageIO.read(new File("src\\2mysz.png"));
            en3=ImageIO.read(new File("src\\3ptak.png"));
            en4=ImageIO.read(new File("src\\4kot.png"));
            en5=ImageIO.read(new File("src\\5pies.png"));
            en6=ImageIO.read(new File("src\\6lis.png"));
            en7=ImageIO.read(new File("src\\7krowa.png"));
            en8=ImageIO.read(new File("src\\8jelen.png"));
            en9=ImageIO.read(new File("src\\9kon.png"));
            en10=ImageIO.read(new File("src\\10tygrys.png"));
            en11=ImageIO.read(new File("src\\12slon.png"));
            en12=ImageIO.read(new File("src\\13lew.png"));

        } catch (IOException ex) {
            throw new IllegalArgumentException("Invalid Image");
        }

        addMouseListener(this);
        animalstofollow=null;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(map.getWidth()>map.getHeight()){
            this.setSize(745,745/map.getWidth()*map.getHeight());
        }
        else{
            this.setSize(745/map.getHeight()*map.getWidth(),745);
        }
        this.setLocation(location.x,location.y);
        int width = this.getWidth();
        int height = this.getHeight();
        int widthScale = Math.round(width / map.getWidth());
        int heightScale = height / map.getHeight();


            g.setColor(new Color(140, 200, 100));
            g.fillRect(0, 0, width, height);

            g.setColor(new Color(0, 110, 0));
            g.fillRect(map.getLowerleftJungle().x*widthScale,
                    map.getLowerleftJungle().y*widthScale,
                    (map.getUpperrightJungle().x-map.getLowerleftJungle().x) * widthScale,
                    (map.getUpperrightJungle().y-map.getLowerleftJungle().y) * heightScale);

            map.getOccupiedplant().forEach((key,value)->{
                int x=map.toRightPosition(key).x * widthScale;
                int y=map.toRightPosition(key).y*heightScale;
                g.drawImage(image, x, y,widthScale,heightScale,  this);
            });

            for (LinkedList<Animal> a : map.getAnimals().values()) {
                for (Animal animal : a) {
                    int y = map.toRightPosition(animal.getPosition()).y * heightScale;
                    int x = map.toRightPosition(animal.getPosition()).x * widthScale;
                    if (Arrays.equals(animal.getGenes().getGenesList(),map.mostfrequentgenes())) {
                        g.setColor(Color.RED);
                        g.fillRect(x, y, widthScale, heightScale);
                        g.setColor(Color.BLACK);
                    }
                    int energy = animal.getEnergy();
                    if (energy <= 0) g.drawImage(en1, x, y, widthScale, heightScale, this);
                    else if (energy < 0.2 * map.getStartEnergy()) g.drawImage(en2, x, y, widthScale, heightScale, this);
                    else if (energy < 0.4 * map.getStartEnergy()) g.drawImage(en3, x, y, widthScale, heightScale, this);
                    else if (energy < 0.6 * map.getStartEnergy()) g.drawImage(en4, x, y, widthScale, heightScale, this);
                    else if (energy < 0.8 * map.getStartEnergy()) g.drawImage(en5, x, y, widthScale, heightScale, this);
                    else if (energy < map.getStartEnergy()) g.drawImage(en6, x, y, widthScale, heightScale, this);
                    else if (energy < 2 * map.getStartEnergy()) g.drawImage(en7, x, y, widthScale, heightScale, this);
                    else if (energy < 4 * map.getStartEnergy()) g.drawImage(en8, x, y, widthScale, heightScale, this);
                    else if (energy < 6 * map.getStartEnergy()) g.drawImage(en9, x, y, widthScale, heightScale, this);
                    else if (energy < 8 * map.getStartEnergy()) g.drawImage(en10, x, y, widthScale, heightScale, this);
                    else if (energy < 10 * map.getStartEnergy()) g.drawImage(en11, x, y, widthScale, heightScale, this);
                    else g.drawImage(en12, x, y, widthScale, heightScale, this);
                }
            }
        }

    @Override
    public void mousePressed(MouseEvent e){
    }
    @Override
    public void mouseClicked(MouseEvent e){
        int widthScale = Math.round(this.getWidth() / map.getWidth());
        int heightScale = this.getHeight()/ map.getHeight();
        int x=((e.getLocationOnScreen().x-getLocation().x-14)/widthScale);
        int y=((e.getLocationOnScreen().y-getLocation().y-28)/heightScale);
        Object object=this.map.getObject(new Vector2D(x,y));
        if(object!=null && object.getClass()==LinkedList.class){
            LinkedList<Animal> animals=(LinkedList<Animal>)object;
            Animal animalwithmax=animals.get(0);
            int max1=0;
            for(Animal animal:animals){
                if(animal.getEnergy()>max1){
                    animalwithmax=animal;
                    max1=animal.getEnergy();
                }
            }
            infopanel.animaltofollow=animalwithmax;
            infopanel.repaint();
        }


    }

    @Override
    public void mouseReleased(MouseEvent e){
    }
    @Override
    public void mouseEntered(MouseEvent e){
    }
    @Override
    public void mouseExited(MouseEvent e){
    }


}
