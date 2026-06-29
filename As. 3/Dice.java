public class Dice{
    private int sides; //number of sides on the dice

    //constructor to set the number of sides
    public Dice(int sides){
        this.sides = sides;
    }

    //method to roll the dice
    public int roll(){
        return(int)(Math.random() * sides) + 1;
        //use Math.random instead importing Random (said in lecture)
    }

}

