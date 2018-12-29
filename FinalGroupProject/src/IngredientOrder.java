public class IngredientOrder implements java.io.Serializable {
    int id;
    Ingredient ingredient;
    Vendor vendor;
    int amount;
    
    public IngredientOrder(int ID, Ingredient ingr, int amnt) {
    	id = ID;
    	ingredient = ingr;
    	vendor = ingredient.getVendor();
    	amount = amnt;
    }
    
    public void updateAmount(int amnt) {
    	amount = amnt;
    }
    
    public int getID() {
    	return id;
    }
    
    public Ingredient getIngredient() {
    	return ingredient;
    }
    
    public Vendor getVendor() {
    	return vendor;
    }
    
    public int getAmount() {
    	return amount;
    }
}