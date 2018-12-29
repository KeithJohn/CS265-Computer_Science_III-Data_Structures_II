public class Ingredient implements java.io.Serializable {
    int id;
    String name;
    Vendor vendor;
    String description;
    int stock;
    boolean Ordered= false;
    public Ingredient(int ID, String nme, String desc, Vendor vendr, int initStock) {
    	id = ID;
    	name = nme;
    	description = desc;
    	vendor = vendr;
    	stock = initStock;
    }
    
    public void addToStock(int amount) {
    	stock += amount;
    }
    
    public void SubFromStock(int amount) {
    	stock -= amount;
    }
    
    public int getID() {
    	return id;
    }
    
    public String getName() {
    	return name;
    }
    public Vendor getVendor() {
    	return vendor;
    }
    
    public String getDesc() {
    	return description;
    }
    
    public int getStock() {
    	return stock;
    }
    public void setIsOrdered(boolean bool){
    	Ordered = bool;
    }
    public boolean isOrdered(){
    	return Ordered;
    }
}