public class Vendor implements java.io.Serializable {
    int id;
    String name;
    String phone1;
    String phone2;
    String fax;
    String email;
    String[] address;//[street, city, state, country];
    
    public Vendor(int ID, String nme, String phne1, String phne2, String fx, String eml) {
    	id = ID;
    	name = nme;
    	phone1 = phne1;
    	phone2 = phne2;
    	fax = fx;
    	email = eml;
    	address = new String[4];
    }
    
    public void setAddress(String street, String city, String state, String country) {
    	address[0] = street;
    	address[1] = city;
    	address[2] = state;
    	address[3] = country;
    }
    
    public int getID() {
    	return id;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getPhone1() {
    	return phone1;
    }
    
    public String getPhone2() {
    	return phone2;
    }
    
    public String getFax() {
    	return fax;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public String[] getAddress() {
    	return address;
    }
}