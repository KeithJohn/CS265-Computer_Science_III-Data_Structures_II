import java.util.ArrayList;

public class Recipe implements java.io.Serializable {
    int id;
    String name;
    ArrayList<Ingredient> ingredients;
    String description;
    
    public Recipe(int ID, String nme, String desc) {
    	id = ID;
    	name = nme;
    	ingredients = new ArrayList<Ingredient>();
    	description = desc;
    }
    
    public void addIngredient(Ingredient ingredient) {
    	ingredients.add(ingredient);
    }
    
    public void removeIngredients(){
    	ingredients.clear();
    }
    public void removeIngredient(int id) {
    	for (int i = 0; i < ingredients.size(); i++) {
    		if (ingredients.get(i).getID() == id) {
    			ingredients.remove(i);
    			break;
    		}
    	}
    }
    
    public int getID() {
    	return id;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getDesc() {
    	return description;
    }
    
    public ArrayList<Ingredient> getIngredients()
    {
    	return this.ingredients;
    }
}