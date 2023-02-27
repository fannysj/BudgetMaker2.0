package Model;


public class ExtraAmount {


    private int extraAmount;
    private Category category;


    /**
     * Constructor of ExtraAmount.
     * @param amount of the extra amount
     * @param c transactions extra amount
     */
    public ExtraAmount(int amount, Category c) {
        this.extraAmount = amount;
        this.category = c;
    }


    public int getExtraAmount(){
        return extraAmount;
    }

    public Category getCategory(){
        return this.category;
    }
}
