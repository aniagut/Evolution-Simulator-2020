package Classes;

import java.util.Arrays;
import java.lang.Math;

public class Genes {

    private final int[] genesList;
    private final int amount;
    private final Vector2D range;


    public Genes(Vector2D range,int amount){
        this.range=range;
        this.amount=amount;
        genesList=new int[amount];
        for(int i=0;i<amount;i++){
            int gen=(int)(Math.random()*(range.y-range.x+1)+range.x);
            genesList[i]=gen;
        }
        Arrays.sort(genesList);
    }

    public Genes(Genes genes1, Genes genes2){
        if(genes1.getAmount()!=genes2.getAmount()) throw new IllegalArgumentException("Classes.Genes have different sizes");
        if(!genes1.getRange().equals(genes2.getRange())) throw new IllegalArgumentException("Classes.Genes have different ranges");

        this.range=genes1.range;
        this.amount=genes1.amount;
        int a=(int)(Math.random()*(this.amount-1));
        int b=a;
        while (a==b){
            b=(int)(Math.random()*(this.amount-1));
        }
        if(a>b){
            int tmp=a;
            a=b;
            b=tmp;
        }
        int[] genes3=new int[this.amount];

        for(int i=0;i<=a;i++){
            genes3[i]=genes1.getGenesList()[i];
        }
        for(int i=a+1;i<=b;i++){
            genes3[i]=genes2.getGenesList()[i];
        }
        for(int i=b+1;i<this.amount;i++){
            genes3[i]=genes1.getGenesList()[i];
        }
        boolean[] contains=new boolean[range.y-range.x+1];

        boolean flag=true;
        while(flag){
            flag=false;
            for(int i=0;i<range.y-range.x+1;i++){
                contains[i]=false;
            }
            for(int i=0;i<this.amount;i++){
                contains[genes3[i]-range.x]=true;
            }

            for(int i=0;i<range.y-range.x+1;i++){
                if (!contains[i]) {
                    flag = true;
                    break;
                }
            }
            if(flag){
                for(int i=0;i<range.y-range.x+1;i++){
                    if(!contains[i]){
                        genes3[(int)(Math.random()*this.amount)]=i;
                    }
                }
            }
        }
        Arrays.sort(genes3);
        this.genesList=genes3;

    }


    public int getAmount() {
        return amount;
    }

    public int[] getGenesList() {
        return genesList;
    }

    public Vector2D getRange() {
        return range;
    }

    @Override
    public String toString() {
        String result="Genes=( ";
        for(int i=0;i<amount;i++){
            result+=this.genesList[i]+" ";
        }
        result+=")";
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genes genes = (Genes) o;
        return Arrays.equals(genesList, genes.genesList);
    }

}
