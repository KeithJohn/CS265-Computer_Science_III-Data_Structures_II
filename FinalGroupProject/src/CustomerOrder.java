import java.io.Serializable;
import java.util.ArrayList;

public class CustomerOrder implements Serializable {
    int id;
    ArrayList<Recipe> recipies;
    
    public CustomerOrder(int ID) {
    	id = ID;
    	recipies = new ArrayList<Recipe>();
    }
    
    public void addRecipe(Recipe recipe) {
    	recipies.add(recipe);
    }
    public ArrayList<Recipe> getRecipes(){
    	return recipies;
    }
    
    public void removeRecipes() {
    	recipies.clear();
    }
    public void setRecipes(ArrayList<Recipe> newRecipes){
    	recipies = newRecipes;
    }
    
    public int getID() {
    	return id;
    }
}