import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ViewIngredientReportForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	ArrayList<Ingredient> ingredients = null;
	ArrayList<Recipe> RecipeList = new ArrayList<Recipe>();
	ArrayList<IngredientOrder> ingredientOrders;
	DefaultListModel recipeModel = new DefaultListModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewIngredientReportForm frame = new ViewIngredientReportForm();
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
	public ViewIngredientReportForm() throws IOException, ClassNotFoundException {
		
		
		
		try {
			FileInputStream fileIn1 = new FileInputStream("src/Data/OrdersToVendors.dat");
			ObjectInputStream in1 = new ObjectInputStream(fileIn1);
			ingredientOrders = (ArrayList<IngredientOrder>) in1.readObject();
			in1.close();
			fileIn1.close();
		}catch(IOException i) {
			i.printStackTrace();
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("src/Data/Ingredients.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ingredients = (ArrayList<Ingredient>) in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileInputStream fileInRecipes;
		try {
			fileInRecipes = new FileInputStream("src/Data/Recipies.dat");
			ObjectInputStream inRecipes = new ObjectInputStream(fileInRecipes);
			RecipeList = (ArrayList<Recipe>) inRecipes.readObject();
			inRecipes.close();
			fileInRecipes.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch( IOException e )
		{
			e.printStackTrace();
		} 
		catch( ClassNotFoundException e )
		{
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] arr = new String[ingredients.size()];
		int j = 0;
		for(Ingredient i: ingredients){
			arr[j] = i.getName();
			j++;
		}
		
		JList list_1 = new JList();
		list_1.setBounds(274, 88, 150, 128);
		contentPane.add(list_1);
		
		JList list = new JList(arr);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				textField.setText(Integer.toString(ingredients.get(list.getSelectedIndex()).getStock()));
				boolean isOrdered = false;
				for(IngredientOrder ingOrder : ingredientOrders){
					if(ingredients.get(list.getSelectedIndex()).getID()==ingOrder.getIngredient().getID()){
						isOrdered=true;
					}
				}
				textField_1.setText(Boolean.toString(isOrdered));
				ArrayList<Ingredient> recipeIngList;
				recipeModel.removeAllElements();
				Ingredient i = ingredients.get(list.getSelectedIndex());
				ArrayList<Ingredient> ingList = new ArrayList<Ingredient>();
				for(Recipe r: RecipeList){
						ingList=r.getIngredients();
						for(Ingredient ing: ingList){
						if(ing.getID()==i.getID()){
						recipeModel.addElement(r.getName());
					}
						}
				}
				list_1.setModel(recipeModel);
			}
		});
		
		list.setBounds(10, 39, 175, 211);
		contentPane.add(list);
		
		JLabel lblIngredientList = new JLabel("Ingredient List");
		lblIngredientList.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredientList.setBounds(10, 14, 175, 14);
		contentPane.add(lblIngredientList);
		
		textField = new JTextField();
		textField.setBounds(274, 11, 150, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(274, 37, 150, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(218, 14, 46, 14);
		contentPane.add(lblAmount);
		
		JLabel lblOrderPlaced = new JLabel("Order Placed");
		lblOrderPlaced.setBounds(195, 40, 81, 14);
		contentPane.add(lblOrderPlaced);
		
		
		
		JLabel lblRecipesUsedIn = new JLabel("Recipes Used In");
		lblRecipesUsedIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecipesUsedIn.setBounds(274, 63, 150, 14);
		contentPane.add(lblRecipesUsedIn);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(274, 227, 150, 23);
		contentPane.add(btnNewButton);
	}

}
