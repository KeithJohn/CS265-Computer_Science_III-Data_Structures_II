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
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class UpdateCustomerOrderForm extends JFrame {

	private JPanel contentPane;
	ArrayList<CustomerOrder> CustomerOrders = new ArrayList<CustomerOrder>();
	ArrayList<Recipe> RecipesForOrder = new ArrayList<Recipe>();
	ArrayList<Recipe> RecipeList = new ArrayList<Recipe>();
	DefaultListModel model = new DefaultListModel();
	DefaultListModel clearModel = new DefaultListModel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCustomerOrderForm frame = new UpdateCustomerOrderForm();
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
	public UpdateCustomerOrderForm() throws IOException, ClassNotFoundException {
		
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
		
		if (!CustomerOrders.isEmpty()) {
			RecipesForOrder = CustomerOrders.get(0).getRecipes();
		}
		String[] Recipearr = new String[RecipesForOrder.size()];
		int i = 0;
		for(Recipe r: RecipesForOrder){
			Recipearr[i] = r.getName();
			i++;
		}
		JList list_1 = new JList(Recipearr);
		list_1.setBounds(341, 100, 232, 165);
		contentPane.add(list_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.removeAllElements();
				if (comboBox.getSelectedIndex() >= 0) {
					RecipesForOrder = CustomerOrders.get(comboBox.getSelectedIndex()).getRecipes();
				}
				for(Recipe r : RecipesForOrder){
					model.addElement(r.getName());
			}
				list_1.setModel(model);
			}
		});
		comboBox.setBounds(319, 40, 254, 20);
		contentPane.add(comboBox);
		for(CustomerOrder c: CustomerOrders){
			comboBox.addItem(c.getID());
		}
		
		JButton btnNewButton = new JButton(">");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecipesForOrder.add(RecipeList.get(list.getSelectedIndex()));
				model.addElement(RecipeList.get(list.getSelectedIndex()).getName());
				list_1.setModel(model);
			}
		});
		btnNewButton.setBounds(252, 138, 79, 52);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(RecipesForOrder.size()==1){
					JOptionPane.showMessageDialog(null, "Cannot Remove.");
				}else{
				RecipesForOrder.remove(RecipesForOrder.get(list_1.getSelectedIndex()));
				model.removeElement(list_1.getSelectedValue());
				list_1.setModel(model);
			}
			}
		});
		button.setBounds(252, 201, 79, 52);
		contentPane.add(button);
		
		JLabel lblOrderedItems = new JLabel("Ordered Items");
		lblOrderedItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderedItems.setBounds(341, 75, 232, 14);
		contentPane.add(lblOrderedItems);
		
		JButton btnNewButton_1 = new JButton("Update Order");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerOrder order = CustomerOrders.get(comboBox.getSelectedIndex());
				order.setRecipes(RecipesForOrder);
				CustomerOrders.set(comboBox.getSelectedIndex(), order);
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
				
				JOptionPane.showMessageDialog(null, "Order Updated ");
				
			}
		});
		btnNewButton_1.setBounds(252, 300, 110, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(499, 300, 74, 23);
		contentPane.add(btnCancel);
	
		
		
		JLabel lblOrders = new JLabel("Orders");
		lblOrders.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrders.setBounds(319, 17, 254, 14);
		contentPane.add(lblOrders);
		
		JButton btnRemoveOrder = new JButton("Remove Order");
		btnRemoveOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CustomerOrders.size()==0){
					JOptionPane.showMessageDialog(null, "No Order to Remove. ");
				}else{
				CustomerOrder order;
				int index = comboBox.getSelectedIndex();
				if (index >= 0) {
					comboBox.removeItem(CustomerOrders.get(index).getID());
					CustomerOrders.remove(index);
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
				}
				JOptionPane.showMessageDialog(null, "Order Removed. ");
			}
			
		});
		btnRemoveOrder.setBounds(372, 300, 117, 23);
		contentPane.add(btnRemoveOrder);
	}

}
