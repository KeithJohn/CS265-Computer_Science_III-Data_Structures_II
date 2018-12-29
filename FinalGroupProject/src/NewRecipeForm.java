import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class NewRecipeForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JList list;
	private JList list_1;
	private JList list_3;
	ArrayList<Ingredient> ingredients = null;
	DefaultListModel model = new DefaultListModel();
	ArrayList<Ingredient> ingForRecipe = new ArrayList<Ingredient>();
	ArrayList<Recipe> RecipeList = new ArrayList<Recipe>();
	DefaultListModel clearModel = new DefaultListModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewRecipeForm frame = new NewRecipeForm();
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
	public NewRecipeForm() throws IOException, ClassNotFoundException {
		
		
		
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
		setBounds(100, 100, 525, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(80, 11, 176, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(Integer.toString(RecipeList.size()+1));
		
		textField_1 = new JTextField();
		textField_1.setBounds(80, 42, 378, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		list = new JList();
		list.setBounds(124, 210, -58, -75);
		contentPane.add(list);
		
		list_1 = new JList();
		list_1.setBounds(80, 219, -51, -130);
		contentPane.add(list_1);
		
		String[] arr = new String[ingredients.size()];
		int j = 0;
		for(Ingredient i: ingredients){
			arr[j] = i.getName();
			j++;
		}
		JList list_2 = new JList(arr);
		list_2.setBounds(10, 100, 182, 195);
		contentPane.add(list_2);
		
		
		list_3 = new JList();
		list_3.setBounds(317, 100, 182, 195);
		contentPane.add(list_3);
		
		JButton btnNewButton = new JButton(">");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(ingForRecipe.contains(ingredients.get(list_2.getSelectedIndex()))){
					JOptionPane.showMessageDialog(null, "Ingredient already entered.");	
				}else{
				ingForRecipe.add(ingredients.get(list_2.getSelectedIndex()));
				model.addElement(ingredients.get(list_2.getSelectedIndex()).getName());
				list_3.setModel(model);
				}
			}
		});
		btnNewButton.setBounds(212, 123, 89, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("<");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				ingForRecipe.remove(ingForRecipe.get(list_3.getSelectedIndex()));
				model.removeElement(list_3.getSelectedValue());
				list_3.setModel(model);
				
			}
		});
		btnNewButton_1.setBounds(212, 192, 89, 40);
		contentPane.add(btnNewButton_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(188, 332, 311, 150);
		contentPane.add(textArea);
		
		JLabel lblAbout = new JLabel("About");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setBounds(188, 306, 311, 25);
		contentPane.add(lblAbout);
		
		JLabel lblI = new JLabel("Information");
		lblI.setHorizontalAlignment(SwingConstants.CENTER);
		lblI.setBounds(24, 306, 116, 25);
		contentPane.add(lblI);
		
		JButton btnNewButton_2 = new JButton("Save");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = RecipeList.size();
				String Name = textField_1.getText();
				String Desc = textArea.getText();
				Recipe newRecipe = new Recipe(ID, Name, Desc);	
				
				for(Ingredient i:ingForRecipe){
					newRecipe.addIngredient(i);
				}
				//RecipeList.add(newRecipe);
				RecipeList.add( RecipeList.size(), newRecipe );
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/Data/Recipies.dat"));
					out.writeObject( RecipeList );
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ingForRecipe.clear();
				//list_3.setModel(clearModel);
				model.removeAllElements();
				textField_1.setText(null);
				textArea.setText(null);
				textField.setText(Integer.toString(RecipeList.size()+1));
				JOptionPane.showMessageDialog(null, "Recipe saved.");				
			}
		});
		btnNewButton_2.setBounds(10, 493, 155, 35);
		contentPane.add(btnNewButton_2);
		
		JButton btnRemve = new JButton("Clear\r\n");
		btnRemve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingForRecipe.clear();
				list_3.setModel(clearModel);
				textField_1.setText(null);
				textArea.setText(null);
				
			}
		});
		btnRemve.setBounds(175, 493, 155, 35);
		contentPane.add(btnRemve);
		
		JButton btnCancel = new JButton("Cancel\r\n");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(340, 493, 155, 35);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("Available Ingredients");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 73, 182, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblRecipeIngredients = new JLabel("Recipe Ingredients");
		lblRecipeIngredients.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecipeIngredients.setBounds(317, 73, 182, 25);
		contentPane.add(lblRecipeIngredients);
		
		JLabel lblId = new JLabel("ID ");
		lblId.setBounds(24, 14, 55, 14);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(24, 45, 55, 14);
		contentPane.add(lblName);
	}
}
