package World;

import Classes.Animal;
import Classes.Plant;
import Classes.Vector2D;
import Interfaces.IGameMap;
import Interfaces.IMapElement;
import Interfaces.PositionObserver;


import java.lang.Math;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GameMap implements PositionObserver, IGameMap {

    private final int width;
    private final int height;
    private final double jungleRatio;
    private final Vector2D lowerleftMap;
    private final Vector2D upperrightMap;
    private final Vector2D lowerleftJungle;
    private final Vector2D upperrightJungle;

    private int era;
    private final int plantEnergy;
    private final int moveEnergy;
    private final int copulationEnergy;
    private final int startEnergy;


    private  ArrayList<Vector2D> freeanimal;
    private ArrayList<Vector2D> freeplant;
    private Map<Vector2D, IMapElement> occupiedplant;
    private Map<Animal,LinkedList<Vector2D>> positionhistory;
    private Map<Vector2D,LinkedList<Animal>> animals;
    private Map<Animal,Integer> deadanimals;
    public int sumanimals;
    public int sumplants;
    public Map<int[],Integer> allgenes=new HashMap<>();
    public int sumenergy;
    public int deathage;
    public int deadnum;
    public int sumchildren;


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Vector2D getLowerleftJungle() {
        return lowerleftJungle;
    }

    public Vector2D getLowerleftMap() {
        return lowerleftMap;
    }

    public Vector2D getUpperrightJungle() {
        return upperrightJungle;
    }

    public Vector2D getUpperrightMap() {
        return upperrightMap;
    }

    public int getStartEnergy(){
        return startEnergy;
    }

    public ArrayList<Vector2D> getFreeAnimal() {
        return freeanimal;
    }

    public Map<Animal,Integer> getDeadanimals(){
        return deadanimals;
    }

    public Map<Vector2D,LinkedList<Animal>> getAnimals() {
        return animals;
    }

    public ArrayList<Vector2D> getFreeplant() {
        return freeplant;
    }

    public Map<Vector2D, IMapElement> getOccupiedplant() {
        return occupiedplant;
    }

    public int getEra(){
        return era;
    }
    public void setEra(int era){
        this.era=era;
    }

    @Override
    public void update(Vector2D newPos, IMapElement element) {
        element.setPosition(newPos);
    }

    public GameMap(int width,int height,double jungleRatio, int plantEnergy,int moveEnergy, int copulationEnergy,int startEnergy){
        this.era=1;
        this.width=width;
        this.height=height;
        this.jungleRatio=jungleRatio;
        this.lowerleftMap=new Vector2D(0,0);
        this.upperrightMap=new Vector2D(width,height);

        double junglearea=this.width*this.height*this.jungleRatio;
        double wsk=(double)width/(double)height;
        int jungleheight=(int)Math.sqrt(junglearea/wsk);
        int junglewidth=(int)(wsk*jungleheight);

        int lowerleftx=0;
        int lowerlefty=0;
        int upperrightx=this.width;
        int upperrighty=this.height;
        for(int i=0;i<(this.width-junglewidth);i++){
            if(i%2==0){
                lowerleftx+=1;
            }
            else{
                upperrightx-=1;
            }
        }
        for(int i=0;i<(this.height-jungleheight);i++){
            if(i%2==0){
                lowerlefty+=1;
            }
            else{
                upperrighty-=1;
            }
        }
        this.lowerleftJungle=new Vector2D(lowerleftx,lowerlefty);
        this.upperrightJungle=new Vector2D(upperrightx,upperrighty);

        this.plantEnergy=plantEnergy;
        this.moveEnergy=moveEnergy;
        this.copulationEnergy=copulationEnergy;
        this.startEnergy=startEnergy;

        ArrayList<Vector2D> freepositionanimal=new ArrayList<>();
        ArrayList<Vector2D> freepositionplant=new ArrayList<>();
        Map<Vector2D,LinkedList<Animal>> animals=new HashMap<>();
        Map<Vector2D, IMapElement> occupiedpositionplant=new HashMap<>();
        Map<Animal,Integer> deadanimals=new HashMap<>();
        for(int i=lowerleftMap.x;i<upperrightMap.x;i++){
            for(int j=lowerleftMap.y;j<upperrightMap.y;j++){
                freepositionanimal.add(new Vector2D(i,j));
                freepositionplant.add(new Vector2D(i,j));
            }
        }

        this.animals=animals;
        this.freeanimal=freepositionanimal;
        this.freeplant=freepositionplant;
        this.occupiedplant=occupiedpositionplant;
        Map<Animal,LinkedList<Vector2D>> positionhistory=new HashMap<>();
        this.positionhistory=positionhistory;
        this.deadanimals=deadanimals;
        sumanimals=0;
        sumplants=0;
        this.allgenes=new HashMap<int[],Integer>();
        sumenergy=0;
        deathage=0;
        deadnum=0;
        sumchildren=0;
    }

    @Override
    public boolean isOccupied(Vector2D position) {
        if(this.occupiedplant.containsKey(position)||this.animals.containsKey(position)) return true;

        return false;
    }

    @Override
    public boolean place(IMapElement element) {
        if(element.isMovable()) {
            int n = this.freeanimal.size();
            if (n > 0) {
                int i = (int) (Math.random() * n);
                Vector2D position = this.freeanimal.get(i);
                this.freeanimal.remove(position);
                this.freeplant.remove(position);
                Animal animal=(Animal)element;
                animal.notifyObservers(position);
                animal.setPosition(position);
                LinkedList<Animal> animals=new LinkedList<>();
                animals.add(animal);
                this.animals.put(position,animals);
                return true;
            } else {
                int m = this.animals.size();
                Object pos[]=this.animals.keySet().toArray();
                if (m > 0) {
                    int j = (int) (Math.random() * m);
                    Vector2D position=(Vector2D)pos[j];
                    Vector2D newpos=position;
                    Animal animal1=(Animal)element;
                    animal1.setPosition(newpos);
                    animal1.notifyObservers(newpos);
                    animals.get(position).add(animal1);
                    return true;
                }
            }
        }
        else{
            int n=this.freeanimal.size();
            if(n>0){
                Object copy1=this.freeanimal.clone();
                ArrayList<Vector2D> copy=(ArrayList<Vector2D>)copy1;
                while (copy.size()>0){
                    int j=(int)(Math.random()* copy.size());
                    Vector2D pos=copy.get(j);
                    copy.remove(pos);
                    if(this.freeplant.contains(pos)){
                        element.setPosition(pos);
                        this.freeplant.remove(pos);
                        this.occupiedplant.put(pos,element);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    @Override
    public Object getObject(Vector2D position){
        if(this.animals.containsKey(position)){
            return this.animals.get(position);
        }
        if(this.occupiedplant.containsKey(position)){
            return this.occupiedplant.get(position);
        }
        return null;
    }
    public Vector2D toRightPosition(Vector2D where){
        if(where.x<this.getLowerleftMap().x){
            if(where.y<this.getLowerleftMap().y){
                where=new Vector2D(this.getUpperrightMap().x-1,this.getUpperrightMap().y-1);
            }
            else if(where.y>this.getUpperrightMap().y){
                where=new Vector2D(this.getUpperrightMap().x-1,this.getLowerleftMap().y);
            }
            else{
                where=new Vector2D(this.getUpperrightMap().x-1, where.y);
            }
        }
        else if(where.x>this.getUpperrightMap().x-1){
            if(where.y>this.getUpperrightMap().y-1){
                where= this.getLowerleftMap();
            }
            else if(where.y<this.getLowerleftMap().y){
                where=new Vector2D(this.getLowerleftMap().x,this.getUpperrightMap().y-1);
            }
            else{
                where=new Vector2D(this.getLowerleftMap().x,where.y);
            }
        }
        else if(where.y>this.getUpperrightMap().y-1){
            where=new Vector2D(where.x,this.getLowerleftMap().y);
        }
        else if(where.y<this.getLowerleftMap().y){
            where=new Vector2D(where.x,this.getUpperrightMap().y-1);
        }
        return where;
    }
    @Override
    public void eating(){
        Object plants[]=this.occupiedplant.keySet().toArray();
        int n=plants.length;
        for(int i=0;i<n;i++){
            Vector2D vec=(Vector2D)plants[i];
            if(animals.containsKey(vec)){
                LinkedList<Animal> animalsonthesamepos=new LinkedList<>();
                for(Animal animal:animals.get(vec)){
                        animalsonthesamepos.add(animal);
                }
                int maxenergy=0;
                for(Animal animal1:animalsonthesamepos){
                    if(animal1.getEnergy()>maxenergy){
                        maxenergy= animal1.getEnergy();
                    }
                }
                LinkedList<Animal> animalswithmax=new LinkedList<>();
                for(Animal animal1:animalsonthesamepos){
                    if(animal1.getEnergy()==maxenergy){
                        animalswithmax.add(animal1);
                    }
                }
                int size=animalswithmax.size();
                for(Animal animal:animalswithmax){
                    animal.changeEnergy(plantEnergy/size);
                }
                this.occupiedplant.remove(vec);

            }

        }

    }
    @Override
    public void copulation(){
        HashMap<Vector2D,LinkedList<Animal>> animalstoadd=new HashMap<>();
        animals.forEach((key,value)->{
            if (value.size() >= 2) {
                Object animals[] = value.toArray();
                int maxenergy1 = -1;
                int maxenergy2 = -1;
                for (Object animal : animals) {
                    Animal animal1 = (Animal) animal;
                    if (animal1.getEnergy() > maxenergy1 && animal1.getEnergy() >= this.copulationEnergy) {
                        maxenergy1 = animal1.getEnergy();
                    } else if (animal1.getEnergy() < maxenergy1 && animal1.getEnergy() > maxenergy2 && animal1.getEnergy()>=this.copulationEnergy) {
                        maxenergy2 = animal1.getEnergy();
                    }
                }
                LinkedList<Animal> animalswithmax1 = new LinkedList<>();
                LinkedList<Animal> animalswithmax2 = new LinkedList<>();
                for (Object animal : animals) {
                    Animal animal1 = (Animal) animal;
                    if (animal1.getEnergy() == maxenergy1) {
                        animalswithmax1.add(animal1);
                    }
                    if (animal1.getEnergy() == maxenergy2) {
                        animalswithmax2.add(animal1);
                    }
                }
                if(animalswithmax1.size()>1){
                    int a=(int)(Math.random()*animalswithmax1.size());
                    int b=a;
                    while(b==a){
                        b=(int)(Math.random()*animalswithmax1.size());
                    }
                    Animal child=animalswithmax1.get(a).copulation(animalswithmax1.get(b));
                    if(animalstoadd.containsKey(child.getPosition())){
                        animalstoadd.get(child.getPosition()).add(child);
                    }
                    else{
                        LinkedList<Animal> animals1=new LinkedList<>();
                        animals1.add(child);
                        animalstoadd.put(child.getPosition(),animals1);
                    }
                }
                else if (animalswithmax1.size()==1 && animalswithmax2.size()>0){
                    int c=(int)(Math.random()*animalswithmax2.size());
                    Animal child=animalswithmax1.get(0).copulation(animalswithmax2.get(c));
                    if(animalstoadd.containsKey(child.getPosition())){
                        animalstoadd.get(child.getPosition()).add(child);
                    }
                    else{
                        LinkedList<Animal> animals1=new LinkedList<>();
                        animals1.add(child);
                        animalstoadd.put(child.getPosition(),animals1);
                    }
                }
            }
        });
        animalstoadd.forEach((key,value)->{
            for(Animal animal:value){
                if(animals.containsKey(key)){
                    animals.get(key).add(animal);
                }
                else{
                    LinkedList<Animal> newlist=new LinkedList<>();
                    newlist.add(animal);
                    animals.put(animal.getPosition(),newlist);
                    this.getFreeAnimal().remove(animal.getPosition());
                }
            }
        });
    }
    @Override
    public void moveanimals(){
        Map<Vector2D,LinkedList<Animal>> newanimals=new HashMap<>();
        animals.forEach((key,value)->{
            for(Animal animal:value){
                animal.move();
                if (newanimals.containsKey(animal.getPosition())) {
                    newanimals.get(animal.getPosition()).add(animal);
                } else {
                    LinkedList<Animal> newlist = new LinkedList<>();
                    newlist.add(animal);
                    newanimals.put(animal.getPosition(), newlist);
                }
            }
            this.getFreeAnimal().add(key);
            this.getFreeplant().add(key);
        });
        newanimals.forEach((key,value)->{
            this.getFreeAnimal().remove(key);
            this.getFreeplant().remove(key);
        });
        this.animals=newanimals;
        animals.forEach((key,value)->{
            for(Animal animal:value){
                animal.changeEnergy(-this.moveEnergy);
            }
        });
    }
    @Override
    public void removeDead(){
        Map<Vector2D,LinkedList<Animal>> toremove=new HashMap<>();
        animals.forEach((key,value)-> {
            for (Animal animal : value) {
                if(animal.isDead()){
                    deadnum+=1;
                    animal.setDeathepoque(this.era);
                    deathage+=animal.getDeathepoque()-animal.getBornepoque();
                    this.deadanimals.put(animal,this.era-animal.getBornepoque());
                    if(toremove.containsKey(key)){
                        toremove.get(key).add(animal);
                    }
                    else{
                        LinkedList<Animal> newlist=new LinkedList<>();
                        newlist.add(animal);
                        toremove.put(key,newlist);
                    }
                }
            }
        });
        toremove.forEach((key,value)->{
            for(Animal animal:value){
                animals.get(key).remove(animal);
                if(animals.get(key).size()==0){
                    animals.remove(key);
                    this.getFreeAnimal().add(key);
                    this.getFreeplant().add(key);
                }
            }
        });
    }
    @Override
    public void nextDay(){
        this.era+=1;
        Object copy1=this.freeplant.clone();
        ArrayList<Vector2D> copy=(ArrayList<Vector2D>)copy1;
        boolean injungle=false;
        boolean instep=false;
        while((!injungle || !instep) && copy.size()>0){
            int j=(int)(Math.random()*copy.size());
            Vector2D position=copy.get(j);
            copy.remove(position);
            if(position.x>=lowerleftJungle.x && position.x<=upperrightJungle.x-1 && position.y>=lowerleftJungle.y && position.y<=upperrightJungle.y-1){
                if(!injungle){
                    Plant newplant=new Plant(this,position);
                    this.occupiedplant.put(position,newplant);
                    injungle=true;
                    this.freeplant.remove(position);
                }
            }
            else{
                if(!instep){
                    Plant newplant=new Plant(this,position);
                    this.occupiedplant.put(position,newplant);
                    instep=true;
                    this.freeplant.remove(position);
                }
            }
        }
    }

    public int[] mostfrequentgenes(){
        Map<int[],Integer> allgenes=new HashMap<>();
        for(LinkedList<Animal> animals: this.animals.values()){
            for(Animal animal:animals){
                if(allgenes.containsKey(animal.getGenes().getGenesList())){
                    allgenes.replace(animal.getGenes().getGenesList(),allgenes.get(animal.getGenes())+1);
                }
                else{
                    allgenes.put(animal.getGenes().getGenesList(),1);
                }
            }
        }
        int max=0;
        int mostfrequent[]=new int[32];
        for(int[] genes:allgenes.keySet()){
            if (allgenes.get(genes)>max){
                max=allgenes.get(genes);
                mostfrequent=genes;
            }
        }
        return mostfrequent;
    }

    public int averageenergylevel(){
        int sumofenergy=0;
        int numofanimals=0;
        for(LinkedList<Animal> animals: this.animals.values()) {
            for (Animal animal : animals) {
                sumofenergy += animal.getEnergy();
                numofanimals+=1;
            }
        }
        return sumofenergy/numofanimals;
    }
    public int averageagefordead(){
        AtomicInteger sum= new AtomicInteger();
        deadanimals. forEach((key,value)->{
            sum.addAndGet(value);
        });
        return sum.get()/deadanimals.size();
    }
    public int averagechildrennumber(){
        AtomicInteger sum=new AtomicInteger();
        AtomicInteger animalsnum=new AtomicInteger();
        animals.forEach((key,value)->{
            value.forEach((animal)->{
                sum.addAndGet(animal.getChildren().size());
                animalsnum.addAndGet(1);
            });
        });
        return sum.get()/animalsnum.get();
    }
    public void refreshstats(){
        sumanimals+=animals.size();
        sumplants+=occupiedplant.size();
        animals.forEach((key,value)->{
            value.forEach((animal)->{
                 sumenergy+=animal.getEnergy();
                 sumchildren+=animal.getChildren().size();
                 if(allgenes.containsKey(animal.getGenes().getGenesList())){
                     int c=allgenes.get(animal.getGenes().getGenesList());
                     allgenes.replace(animal.getGenes().getGenesList(),c+1);
                 }
                 else{
                     allgenes.put(animal.getGenes().getGenesList(),1);
                 }
            });
        });
    }

}


