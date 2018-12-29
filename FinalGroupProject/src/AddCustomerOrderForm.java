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

public class AddCustomerOrderForm extends JFrame {

	private JPanel contentPane;
	ArrayList<CustomerOrder> CustomerOrders = new ArrayList<CustomerOrder>();
	ArrayList<Recipe> RecipesForOrder = new ArrayList<Recipe>();
	ArrayList<Recipe> RecipeList = new ArrayList<Recipe>();
	DefaultListModel model = new DefaultListModel();
	DefaultListModel clearModel = new DefaultListModel();
	ArrayList<Ingredient> Ingredients = new ArrayList<Ingredient>();
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCustomerOrderForm frame = new AddCustomerOrderForm();
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
	public AddCustomerOrderForm() throws IOException, ClassNotFoundException {
		
		try {
			ObjectInputStream ingIn = new ObjectInputStream(new FileInputStream("src/Data/Ingredients.dat"));
			Ingredients = (ArrayList<Ingredient>) ingIn.readObject();
			ingIn.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ObjectInputStream in2 = new ObjectInputStream(new FileInputStream("src/Data/Recipies.dat"));
			RecipeList = (ArrayList<Recipe>) in2.readObject();
			in2.close();
			
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
		
		String[] arr = new String[RecipeList.size()];
		int j = 0;
		for(Recipe r: RecipeList){
			arr[j] = r.getName();
			j++;
		}
		JList list = new JList(arr);
		list.setBounds(10, 42, 232, 309);
		contentPane.add(list);
		
		
		
		JLabel lblRecipeList = new JLabel("Recipe List");
		lblRecipeList.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecipeList.setBounds(10, 17, 232, 14);
		contentPane.add(lblRecipeList);
		
		JList list_1 = new JList();
		list_1.setBounds(341, 42, 232, 223);
		contentPane.add(list_1);
		
		JButton btnNewButton = new JButton(">");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecipesForOrder.add(RecipeList.get(list.getSelectedIndex()));
				model.addElement(RecipeList.get(list.getSelectedIndex()).getName());
				list_1.setModel(model);
			}
		});
		btnNewButton.setBounds(252, 83, 79, 52);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RecipesForOrder.remove(RecipesForOrder.get(list_1.getSelectedIndex()));
				model.removeElement(list_1.getSelectedValue());
				list_1.setModel(model);
			}
		});
		button.setBounds(252, 153, 79, 52);
		contentPane.add(button);
		
		JLabel lblOrderedItems = new JLabel("Ordered Items");
		lblOrderedItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderedItems.setBounds(428, 17, 145, 14);
		contentPane.add(lblOrderedItems);
		
		JButton btnNewButton_1 = new JButton("Place Order");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID;
				ArrayList<Ingredient> ingForRec = new ArrayList<Ingredient>();
				for(Recipe rec : RecipesForOrder){
					ingForRec=rec.getIngredients();
					for(Ingredient ing:ingForRec){
						for(Ingredient i:Ingredients){
							if(ing.getID()==i.getID() && i.getStock()<=0){
								JOptionPane.showMessageDialog(null, "We are out of " + ing.getName());
								return;
							}
						}
					}
				}
				if(CustomerOrders.size()==0){
					 ID = 1;
				}else{
				ID = CustomerOrders.get(CustomerOrders.size()-1).getID()+1;
				}
				CustomerOrder newCustomerOrder = new CustomerOrder(ID);
				for(Recipe r: RecipesForOrder){
					newCustomerOrder.addRecipe(r);
				}
				CustomerOrders.add(newCustomerOrder);
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
				
				RecipesForOrder.clear();
				model.removeAllElements();
				list_1.setModel(clearModel);
				textField.setText(Integer.toString(CustomerOrders.get(CustomerOrders.size()-1).getID()+1));
				JOptionPane.showMessageDialog(null, "Order Placed. Order Number is " + ID);
			}
		});
		btnNewButton_1.setBounds(313, 300, 125, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(448, 300, 125, 23);
		contentPane.add(btnCancel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(364, 14, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		if(CustomerOrders.size()==0){
			textField.setText(Integer.toString(1));
		}else{
		textField.setText(Integer.toString(CustomerOrders.get(CustomerOrders.size()-1).getID()+1));
		}
		
		JLabel lblOrderNumber = new JLabel("Order Number");
		lblOrderNumber.setBounds(286, 17, 68, 14);
		contentPane.add(lblOrderNumber);
	}
}
