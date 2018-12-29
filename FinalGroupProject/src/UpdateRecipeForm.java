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

public class UpdateRecipeForm extends JFrame {

	private int select;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JList list;
	private JList list_1;
	private JList list_3;
	
	ArrayList<Recipe> RecipeList = new ArrayList<Recipe>();
	DefaultListModel model = new DefaultListModel();
	DefaultListModel model_3 = new DefaultListModel();
	ArrayList<Ingredient> ingForRecipe = new ArrayList<Ingredient>();
	DefaultListModel clearModel = new DefaultListModel();
	ArrayList<Ingredient> ingredients = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateRecipeForm frame = new UpdateRecipeForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateRecipeForm() {
		
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("src/Data/Recipies.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			RecipeList = (ArrayList<Recipe>) in.readObject();
			in.close();
			fileIn.close();
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
		
		FileInputStream fileInIngredients;
		try {
			fileInIngredients = new FileInputStream("src/Data/Ingredients.dat");
			ObjectInputStream inIngredients = new ObjectInputStream(fileInIngredients);
			ingredients = (ArrayList<Ingredient>) inIngredients.readObject();
			inIngredients.close();
			fileInIngredients.close();
		} 
		catch (FileNotFoundException e) 
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
		if (!RecipeList.isEmpty()) {
			textField.setText( "" + RecipeList.get( select ).getID() );
		}
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(80, 73, 378, 20);
		if (!RecipeList.isEmpty()) {
		textField_1.setText( RecipeList.get( select ).getName() );
		}
		contentPane.add(textField_1);
		textField_1.setColumns(10);
				
		list = new JList();
		list.setBounds(124, 210, -58, -75);
		contentPane.add(list);
		
		list_1 = new JList();
		list_1.setBounds(80, 219, -51, -130);
		contentPane.add(list_1);
		
		String[] stringArray = new String[ingredients.size()];
		int j = 0;
		for(Ingredient i: ingredients){
			stringArray[j] = i.getName();
			j++;
		}
		
		JList list_2 = new JList(stringArray);
		list_2.setBounds(10, 133, 182, 162);
		contentPane.add(list_2);
		
		list_3 = new JList();
		list_3.setModel( model_3 );
		model_3.removeAllElements();
		if (!RecipeList.isEmpty()) {
			for( int i = 0; i < RecipeList.get( select ).getIngredients().size(); i++ )
			{
				model_3.addElement( RecipeList.get( select ).getIngredients().get(i).getName() );
				ingForRecipe.add( RecipeList.get( select ).getIngredients().get(i) );
			}
		}
		list_3.setBounds(317, 133, 182, 162);
		contentPane.add(list_3);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(188, 332, 311, 150);
		if (!RecipeList.isEmpty()) {
			textArea.setText( RecipeList.get( select ).getDesc() );
		}
		contentPane.add(textArea);
		
		JComboBox comboBox = new JComboBox();
		
		for( int i = 0; i < RecipeList.size(); i++ )
		{
			comboBox.addItem( RecipeList.get(i).getName() );
		}
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				select = comboBox.getSelectedIndex();
				
				
					textField.setText( "" + RecipeList.get( comboBox.getSelectedIndex() ).getID() );
					textField_1.setText( RecipeList.get( comboBox.getSelectedIndex() ).getName() );
					textArea.setText( RecipeList.get( comboBox.getSelectedIndex() ).getDesc() );
				
				list_3.setModel( model_3 );
				model_3.removeAllElements();
				for( int i = 0; i < RecipeList.get( comboBox.getSelectedIndex() ).getIngredients().size(); i++ )
				{
					model_3.addElement( RecipeList.get( comboBox.getSelectedIndex() ).getIngredients().get(i).getName() );
					ingForRecipe.add( RecipeList.get( comboBox.getSelectedIndex() ).getIngredients().get(i) );
				}
				
				
			}
		});
		comboBox.setBounds(80, 42, 378, 20);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton(">");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( ingForRecipe.contains( ingredients.get( list_2.getSelectedIndex() ) ) )
				{
					JOptionPane.showMessageDialog( null, "Ingredient already entered." );	
				}
				else 
				{
				ingForRecipe.add( ingredients.get(list_2.getSelectedIndex() ) );
				model_3.addElement( ingredients.get( list_2.getSelectedIndex()).getName() );
				//list_3.setModel( model_3 );
				}
			}
		});
		btnNewButton.setBounds(212, 151, 89, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("<");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ingForRecipe.remove( ingForRecipe.get( list_3.getSelectedIndex() ) );
				model_3.removeElementAt( list_3.getSelectedIndex() );
				//list_3.setModel( model_3 );
			}
		});
		btnNewButton_1.setBounds(212, 219, 89, 40);
		contentPane.add(btnNewButton_1);
		
		JLabel lblAbout = new JLabel("About");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setBounds(188, 306, 311, 25);
		contentPane.add(lblAbout);
		
		JLabel lblI = new JLabel("Information");
		lblI.setHorizontalAlignment(SwingConstants.CENTER);
		lblI.setBounds(24, 306, 116, 25);
		contentPane.add(lblI);
		
		JButton btnNewButton_2 = new JButton("SAVE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int ID = Integer.parseInt( textField.getText() );
				String Name = textField_1.getText();
				String Desc = textArea.getText();
				Recipe newRecipe = new Recipe(ID, Name, Desc);	
				
				for(Ingredient i:ingForRecipe){
					newRecipe.addIngredient(i);
				}
				
				RecipeList.set(select, newRecipe);
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/Data/Recipies.dat"));
					out.writeObject( RecipeList );
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				}
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				ingForRecipe.clear();
				JOptionPane.showMessageDialog(null, "Recipe saved.");
			}
		});
		btnNewButton_2.setBounds(10, 493, 155, 35);
		contentPane.add(btnNewButton_2);
		
		JButton btnRemve = new JButton("Remove\r\n");
		btnRemve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				RecipeList.remove( comboBox.getSelectedIndex() );
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/Data/Recipies.dat"));
					out.writeObject( RecipeList );
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				}
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				
				comboBox.removeItemAt( comboBox.getSelectedIndex() );
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
		lblNewLabel.setBounds(10, 110, 182, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblRecipeIngredients = new JLabel("Recipe Ingredients");
		lblRecipeIngredients.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecipeIngredients.setBounds(317, 110, 182, 25);
		contentPane.add(lblRecipeIngredients);
				
		JLabel lblId = new JLabel("ID ");
		lblId.setBounds(24, 14, 55, 14);
		contentPane.add(lblId);
		
		JLabel lblRecipe = new JLabel("Recipe");
		lblRecipe.setBounds(24, 45, 55, 14);
		contentPane.add(lblRecipe);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(24, 76, 55, 14);
		contentPane.add(lblName);
	}
}
