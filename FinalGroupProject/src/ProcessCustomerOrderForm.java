import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ProcessCustomerOrderForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	ArrayList<CustomerOrder> CustomerOrders = new ArrayList<CustomerOrder>();
	ArrayList<Recipe> RecipesForOrder = new ArrayList<Recipe>();
	ArrayList<Ingredient> Ingredients = new ArrayList<Ingredient>();
	DefaultListModel orderModel = new DefaultListModel();
	DefaultListModel recipeModel = new DefaultListModel();
	DefaultListModel clearModel = new DefaultListModel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProcessCustomerOrderForm frame = new ProcessCustomerOrderForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public ProcessCustomerOrderForm() throws IOException, ClassNotFoundException {
		
		try {
			ObjectInputStream ingIn = new ObjectInputStream(new FileInputStream("src/Data/Ingredients.dat"));
			Ingredients = (ArrayList<Ingredient>) ingIn.readObject();
			ingIn.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/Data/TempCostomerOrders.dat"));
			CustomerOrders = (ArrayList<CustomerOrder>) in.readObject();
			in.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		String[] Orderarr = new String[CustomerOrders.size()];
		JList list;
		if(Orderarr.length!=0){
		int j = 0;
		for(CustomerOrder c: CustomerOrders){
			Orderarr[j] = Integer.toString(c.getID());
			j++;
			orderModel.addElement(c.getID());
		}
		list = new JList(Orderarr);
		list.setEnabled(false);
		list.setBounds(10, 42, 232, 309);
		contentPane.add(list);
		}else{
			list = new JList();
			list.setEnabled(false);
			list.setBounds(10, 42, 232, 309);
			contentPane.add(list);
		}
		
		JLabel lblRecipeList = new JLabel("Order List");
		lblRecipeList.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecipeList.setBounds(10, 17, 232, 14);
		contentPane.add(lblRecipeList);
		
		JList list_1;
		if(CustomerOrders.size()!=0){
		RecipesForOrder = CustomerOrders.get(0).recipies;
		String[] Recipearr = new String[RecipesForOrder.size()];
		int i = 0;
		for(Recipe r: RecipesForOrder){
			Recipearr[i] = r.getName();
			i++;
		}
		 list_1 = new JList(Recipearr);
		list_1.setEnabled(false);
		list_1.setBounds(287, 98, 286, 167);
		contentPane.add(list_1);
		}else{
			list_1 = new JList();
			list_1.setEnabled(false);
			list_1.setBounds(287, 98, 286, 167);
			contentPane.add(list_1);
		}
		JButton btnFinishOrder = new JButton("Finish Order >");
		btnFinishOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CustomerOrders.size()==0){
					JOptionPane.showMessageDialog(null, "No Order to Process. ");
				}else{
				CustomerOrder order;
				orderModel.removeAllElements();
				
				for(Recipe r : RecipesForOrder){
					ArrayList<Ingredient> IngForRecipe = r.getIngredients();
					for(Ingredient i1 : IngForRecipe){	
					for(Ingredient i : Ingredients){
						if(i.getID()==i1.getID()){
							i.SubFromStock(1);
						}
					}
					}
				}
				
				try {
					FileOutputStream ingfileOut = new FileOutputStream("src/Data/Ingredients.dat");
					ObjectOutputStream ingout = new ObjectOutputStream(ingfileOut);
					ingout.writeObject(Ingredients);
					ingout.close();
					ingfileOut.close();
				}catch(IOException i) {
					i.printStackTrace();
				}
				
				for(int i = 0; i<CustomerOrders.size();i++){
					if(i==CustomerOrders.size()-1){
						CustomerOrders.remove(i);
					}else{
					order = CustomerOrders.get(i+1);
					CustomerOrders.set(i, order);
					}
					
				}
				int j = 0;
				for(CustomerOrder c: CustomerOrders){
					Orderarr[j] = Integer.toString(c.getID());
					j++;
					orderModel.addElement(c.getID());
				}
				list.setModel(orderModel);
				ArrayList<Recipe> RecipesForOrder = new ArrayList<Recipe>();
				if (CustomerOrders.size() > 0) {
					RecipesForOrder= CustomerOrders.get(0).getRecipes();
				}
				for(Recipe r: RecipesForOrder){
					recipeModel.addElement(r.getName());
				}
				list_1.setModel(recipeModel);
			}
			
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/Data/TempCostomerOrders.dat"));
					out.writeObject(CustomerOrders);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (CustomerOrders.size() > 0) {
					JOptionPane.showMessageDialog(null, "Order Processed. ");
					textField.setText(Integer.toString(CustomerOrders.get(0).getID()));
				}
				
				
			}
		});
		btnFinishOrder.setBounds(287, 39, 119, 23);
		contentPane.add(btnFinishOrder);
		
		JLabel lblOrderedItems = new JLabel("Ordered Items");
		lblOrderedItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderedItems.setBounds(287, 73, 286, 14);
		contentPane.add(lblOrderedItems);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(287, 300, 286, 23);
		contentPane.add(btnCancel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(416, 40, 157, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		if(CustomerOrders.size()!=0){
		textField.setText(Integer.toString(CustomerOrders.get(0).getID()));
		}
		JLabel lblOrderLabel = new JLabel("Order Being Process");
		lblOrderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderLabel.setBounds(416, 17, 157, 14);
		contentPane.add(lblOrderLabel);
	}

}