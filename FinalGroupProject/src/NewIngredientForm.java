import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.*;
import java.util.ArrayList;

public class NewIngredientForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewIngredientForm frame = new NewIngredientForm();
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
	ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	ArrayList<Vendor> vendors;
	public NewIngredientForm() {

		try {
			FileInputStream fileIn = new FileInputStream("src/Data/Ingredients.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ingredients = (ArrayList<Ingredient>) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i) {
			i.printStackTrace();
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		try {
			FileInputStream fileIn = new FileInputStream("src/Data/Vendors.dat");
			ObjectInputStream in2 = new ObjectInputStream(fileIn);
			vendors = (ArrayList<Vendor>) in2.readObject();
			in2.close();
			fileIn.close();
		}catch(IOException i) {
			i.printStackTrace();
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		if(ingredients.size()==0){
		textField.setText("1");	
		}else{
		textField.setText( "" + (ingredients.get(ingredients.size()-1).getID()+1 )); //size returns size of array, which is equal to next index.
		}
		textField.setBounds(86, 11, 224, 20);
		textField.setEditable(false);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(86, 42, 420, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(86, 73, 420, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblId.setBounds(10, 14, 66, 14);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(10, 45, 66, 14);
		contentPane.add(lblName);
		
		JLabel lblVendor = new JLabel("Vendor");
		lblVendor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVendor.setBounds(10, 76, 66, 14);
		contentPane.add(lblVendor);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 123, 523, 194);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(182, 36, 321, 147);
		panel.add(textArea);
		
		JLabel lblAbout = new JLabel("About");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAbout.setBounds(182, 11, 321, 14);
		panel.add(lblAbout);
		
		JLabel lblIngredientInformation = new JLabel("Ingredient Information");
		lblIngredientInformation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIngredientInformation.setBounds(10, 97, 224, 30);
		contentPane.add(lblIngredientInformation);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Vendor vendor;
					int vndr = 0;
					for (int i = 0; i<vendors.size(); i++) {
						if (vendors.get(i).getName().equalsIgnoreCase(textField_2.getText())) {
							vndr = i;
							break;
						}
					}
					vendor = vendors.get(vndr);
					int ID;
					if(ingredients.size()==0){
					ID = 1;
					}else{
					 ID = (ingredients.get(ingredients.size()-1).getID()+1);
				}
					Ingredient ingredient = new Ingredient(ID , textField_1.getText(), textArea.getText(), vendor, 0);
					ingredients.add(ingredient);
					FileOutputStream fileOut = new FileOutputStream("src/Data/Ingredients.dat");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(ingredients);
					out.close();
					fileOut.close();
					
				} catch(IOException i) {
					i.printStackTrace();
				}
				textField_1.setText("");
				textField_2.setText("");
				textArea.setText("");
				JOptionPane.showMessageDialog(null, "Ingredient Saved.");
				textField.setText(Integer.toString(ingredients.get(ingredients.size()-1).getID()+1));
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(10, 328, 157, 30);
		contentPane.add(btnNewButton);
		
		JButton btnClearButton = new JButton("CLEAR");
		btnClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				textField_2.setText("");
				textArea.setText("");
			}
		});
		btnClearButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnClearButton.setBounds(177, 328, 186, 30);
		contentPane.add(btnClearButton);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCancel.setBounds(373, 328, 160, 30);
		contentPane.add(btnCancel);
	}
}
