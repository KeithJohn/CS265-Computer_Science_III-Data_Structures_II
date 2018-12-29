import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ViewPendingOrderForm extends JFrame {

	ArrayList<CustomerOrder> CustomerOrders = new ArrayList<CustomerOrder>();
	ArrayList<Recipe> RecipesForOrder = new ArrayList<Recipe>();
	DefaultListModel orderModel = new DefaultListModel();
	DefaultListModel recipeModel = new DefaultListModel();
	DefaultListModel clearModel = new DefaultListModel();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPendingOrderForm frame = new ViewPendingOrderForm();
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
	public ViewPendingOrderForm() throws IOException, ClassNotFoundException {
		
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
		
		JList list_1 = new JList();
		list_1.setBounds(287, 42, 286, 223);
		contentPane.add(list_1);
		
		String[] Orderarr = new String[CustomerOrders.size()];
		int j = 0;
		for(CustomerOrder c: CustomerOrders){
			Orderarr[j] = Integer.toString(c.getID());
			j++;
			orderModel.addElement(c.getID());
		}
		JList list = new JList(Orderarr);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				
				recipeModel.removeAllElements();
				if (list.getSelectedIndex() >= 0) {
					RecipesForOrder = CustomerOrders.get(list.getSelectedIndex()).getRecipes();
				}
				for(Recipe r : RecipesForOrder){
					recipeModel.addElement(r.getName());
				}
				list_1.setModel(recipeModel);
			}
		});
		list.setBounds(10, 42, 232, 309);
		contentPane.add(list);
		
		JLabel lblRecipeList = new JLabel("Order List");
		lblRecipeList.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecipeList.setBounds(10, 17, 146, 14);
		contentPane.add(lblRecipeList);
		
		
		
		JLabel lblOrderedItems = new JLabel("Ordered Items");
		lblOrderedItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderedItems.setBounds(287, 17, 286, 14);
		contentPane.add(lblOrderedItems);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(287, 300, 286, 23);
		contentPane.add(btnCancel);
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/Data/TempCostomerOrders.dat"));
					CustomerOrders = (ArrayList<CustomerOrder>) in.readObject();
					in.close();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				orderModel.removeAllElements();
				for (CustomerOrder c: CustomerOrders){
					orderModel.addElement(c.getID());
				}
				list.setModel(orderModel);
			}
		});
		btnNewButton.setBounds(153, 13, 89, 23);
		contentPane.add(btnNewButton);
	}
}