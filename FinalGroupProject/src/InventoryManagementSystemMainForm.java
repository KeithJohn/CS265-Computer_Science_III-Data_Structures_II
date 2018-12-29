import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class InventoryManagementSystemMainForm {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryManagementSystemMainForm window = new InventoryManagementSystemMainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InventoryManagementSystemMainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Inventory");
		menuBar.add(mnNewMenu);
		
		JMenu mnIngredients = new JMenu("Ingredients");
		mnNewMenu.add(mnIngredients);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  NewIngredientForm NewIngredient = new NewIngredientForm();
				  NewIngredient.setVisible(true);
				  
			}
		});
		mnIngredients.add(mntmNewMenuItem);
		
		JMenuItem mntmUpdate = new JMenuItem("Update/Remove");
		mntmUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateIngredientForm UpdateIngredient = new UpdateIngredientForm();
				UpdateIngredient.setVisible(true);
			}
		});
		mnIngredients.add(mntmUpdate);
		
		JMenu mnRecipe = new JMenu("Recipe");
		mnNewMenu.add(mnRecipe);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewRecipeForm NewRecipe = null;
				try {
					NewRecipe = new NewRecipeForm();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				NewRecipe.setVisible(true);
			}
		});
		mnRecipe.add(mntmNew);
		
		JMenuItem mntmUpdate_1 = new JMenuItem("Update/Remove");
		mntmUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateRecipeForm UpdateRecipe = new UpdateRecipeForm();
				UpdateRecipe.setVisible(true);
			}
		});
		mnRecipe.add(mntmUpdate_1);
		
		JMenu mnVendors = new JMenu("Vendors");
		mnNewMenu.add(mnVendors);
		
		JMenuItem mntmNew_1 = new JMenuItem("New");
		mntmNew_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewVendorForm NewVendor = new NewVendorForm();
				NewVendor.setVisible(true);
			}
		});
		mnVendors.add(mntmNew_1);
		
		JMenuItem mntmUpdate_2 = new JMenuItem("Update/Remove");
		mntmUpdate_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateVendorForm UpdateVendor = new UpdateVendorForm();
				UpdateVendor.setVisible(true);
			}
		});
		mnVendors.add(mntmUpdate_2);
		
		JMenu mnRestocking = new JMenu("Restocking");
		mnNewMenu.add(mnRestocking);
		
		JMenu mnOrderingIngredients = new JMenu("Ingredient Orders");
		mnRestocking.add(mnOrderingIngredients);
		
		JMenuItem mntmPlaceAnOrder = new JMenuItem("New Order");
		mntmPlaceAnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlaceIngredientOrderForm PlaceOrder = new PlaceIngredientOrderForm();
				PlaceOrder.setVisible(true);
			}
		});
		mnOrderingIngredients.add(mntmPlaceAnOrder);
		
		JMenuItem mntmUpdateAnOrder = new JMenuItem("Update/Remove");
		mntmUpdateAnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateIngredientOrderForm UpdateIngredientOrder = new UpdateIngredientOrderForm();
				UpdateIngredientOrder.setVisible(true);
			}
		});
		mnOrderingIngredients.add(mntmUpdateAnOrder);
		
		JMenuItem mntmViewIngredientOrders = new JMenuItem("View Ingredient Orders");
		mntmViewIngredientOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewIngredientOrderForm ViewIngredientOrder = new ViewIngredientOrderForm();
				ViewIngredientOrder.setVisible(true);
			}
		});
		mnRestocking.add(mntmViewIngredientOrders);
		
		JMenuItem mntmUpdateInventory = new JMenuItem("Receive Order");
		mntmUpdateInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecieveIngredientOrderForm RecieveIngredient = new RecieveIngredientOrderForm();
				RecieveIngredient.setVisible(true);
			}
		});
		mnRestocking.add(mntmUpdateInventory);
		
		JMenu mnCustomers = new JMenu("Customers");
		menuBar.add(mnCustomers);
		
		JMenuItem mntmAddOrder = new JMenuItem("Add Order");
		mntmAddOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCustomerOrderForm AddOrder = null;
				try {
					AddOrder = new AddCustomerOrderForm();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AddOrder.setVisible(true);
			}
		});
		mnCustomers.add(mntmAddOrder);
		
		JMenuItem mntmUpdateOrder = new JMenuItem("Update/Cancel Order");

		mntmUpdateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateCustomerOrderForm UpdateCustomerOrder = null;
				try {
					UpdateCustomerOrder = new UpdateCustomerOrderForm();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				UpdateCustomerOrder.setVisible(true);
			}
		});
		
		mnCustomers.add(mntmUpdateOrder);
		
		JMenu mnKitchen = new JMenu("Kitchen");
		menuBar.add(mnKitchen);
		
		JMenuItem mntmProcessOrders = new JMenuItem("Process Orders");
		mntmProcessOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProcessCustomerOrderForm ProcessCustomerOrder = null;
				try {
					ProcessCustomerOrder = new ProcessCustomerOrderForm();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ProcessCustomerOrder.setVisible(true);
			}
		});
		mnKitchen.add(mntmProcessOrders);
		
		JMenuItem mntmListPendingOrders = new JMenuItem("Show Pending Orders");
		mntmListPendingOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPendingOrderForm ViewPendingOrder = null;
				try {
					ViewPendingOrder = new ViewPendingOrderForm();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ViewPendingOrder.setVisible(true);
			}
		});
		mnKitchen.add(mntmListPendingOrders);
		
		JMenuItem mntmShowRecipeInformation = new JMenuItem("Show Recipe Information");
		mntmShowRecipeInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewRecipeInformationForm ViewRecipeInformation = new ViewRecipeInformationForm();
				ViewRecipeInformation.setVisible(true);
			}
		});
		mnKitchen.add(mntmShowRecipeInformation);
		
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenuItem mntmViewIngredientReport = new JMenuItem("View Ingredient Report");
		mntmViewIngredientReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewIngredientReportForm ViewIngredientReport = null;
				try {
					ViewIngredientReport = new ViewIngredientReportForm();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ViewIngredientReport.setVisible(true);
			}
		});
		mnReports.add(mntmViewIngredientReport);
	}

}
