import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class ViewRecipeInformationForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JList list;
	private JList list_1;
	private JList list_3;
	
	ArrayList<Recipe> RecipeList = new ArrayList<Recipe>();
	ArrayList<Ingredient> ingredients = null;
	DefaultListModel model = new DefaultListModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRecipeInformationForm frame = new ViewRecipeInformationForm();
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
	int sel;
	public ViewRecipeInformationForm() {
		
		sel = 0;
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
		setBounds(100, 100, 525, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(80, 11, 176, 20);
		textField.setText("" + RecipeList.get( sel ).getID() );
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(80, 42, 378, 20);
		textField_1.setText( RecipeList.get( sel ).getName() );
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		list = new JList();
		list.setBounds(124, 210, -58, -75);
		model.removeAllElements();
		for(int i = 0; i < RecipeList.get( sel ).getIngredients().size(); i++ )
		{
			model.addElement( RecipeList.get( sel ).getIngredients().get(i).getName() );
		}
		contentPane.add(list);
		
		list_1 = new JList();
		list_1.setBounds(80, 219, -51, -130);
		contentPane.add(list_1);
		
		list_3 = new JList();
		list_3.setBounds(27, 130, 217, 257);
		contentPane.add(list_3);
		list_3.setModel( model );
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(254, 130, 224, 211);
		textArea.setText( RecipeList.get( sel ).getDesc() );
		contentPane.add(textArea);
		
		JLabel lblAbout = new JLabel("About");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setBounds(254, 104, 224, 25);
		contentPane.add(lblAbout);
		
		JButton btnCancel = new JButton("Cancel\r\n");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(271, 352, 187, 35);
		contentPane.add(btnCancel);
		
		JLabel lblRecipeIngredients = new JLabel("Recipe Ingredients");
		lblRecipeIngredients.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecipeIngredients.setBounds(27, 104, 217, 25);
		contentPane.add(lblRecipeIngredients);
		
		JLabel lblId = new JLabel("ID ");
		lblId.setBounds(24, 14, 55, 14);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(24, 45, 55, 14);
		contentPane.add(lblName);
		
		JComboBox comboBox = new JComboBox();
		
		for( int i = 0; i < RecipeList.size(); i++ )
		{
			comboBox.addItem( RecipeList.get(i).getName() );
		}
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				sel = comboBox.getSelectedIndex();
				model.removeAllElements();
				for(int i = 0; i < RecipeList.get( comboBox.getSelectedIndex() ).getIngredients().size(); i++ )
				{
					model.addElement( RecipeList.get( comboBox.getSelectedIndex() ).getIngredients().get(i).getName() );
				}
				
				textField.setText("" + RecipeList.get( comboBox.getSelectedIndex() ).getID() );
				textField_1.setText( RecipeList.get( comboBox.getSelectedIndex() ).getName() );
				textArea.setText( RecipeList.get( comboBox.getSelectedIndex() ).getDesc() );
			}
		});
		comboBox.setBounds(80, 73, 378, 20);
		contentPane.add(comboBox);
		
		JLabel lblRecipes = new JLabel("Recipes");
		lblRecipes.setBounds(27, 75, 46, 14);
		contentPane.add(lblRecipes);
	}
}
