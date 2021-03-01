package Visualisation;

import Classes.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainInfo extends JPanel {
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

    public MainInfo(){
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
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setSize(1500, 50);
        this.setLocation(0,0);
        g.setFont(new Font("Calibri", Font.ITALIC, 14));

        g.drawString("Jungle Field:", 140, 20);
        g.drawString("Steppe Field:", 20, 20);
        g.setColor(new Color(140, 200, 100));
        g.fillRect(110, 5, 20, 20);

        g.setColor(new Color(0, 110, 0));
        g.fillRect(225, 5, 20, 20);

        g.setColor(new Color(0,0,0));

        g.drawString("Plant:", 260, 20);
        g.drawImage(image,305,5,20,20,this);

        g.drawString("Animals (in increasing order of energy level):" ,340,20);

        g.drawImage(en1, 610, 5,20,20,  this);
        g.drawImage(en2,650,5,20,20,this);
        g.drawImage(en3,690,5,20,20,this);
        g.drawImage(en4,730,5,20,20,this);
        g.drawImage(en5,770,5,20,20,this);
        g.drawImage(en6,810,5,20,20,this);
        g.drawImage(en7,850,5,20,20,this);
        g.drawImage(en8,890,5,20,20,this);
        g.drawImage(en9,930,5,20,20,this);
        g.drawImage(en10,970,5,20,20,this);
        g.drawImage(en11,1010,5,20,20,this);
        g.drawImage(en12,1050,5,20,20,this);

        g.drawString("Animals with most frequent genotype:",1100,20);
        g.setColor(Color.RED);
        g.fillRect(1340,5,20,20);

    }
}
