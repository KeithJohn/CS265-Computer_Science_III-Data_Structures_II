import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.*;
import java.util.ArrayList;

public class UpdateIngredientForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateIngredientForm frame = new UpdateIngredientForm();
					frame.setVisible(true);
				}
				catch   (IndexOutOfBoundsException e)
				{
					JOptionPane.showMessageDialog(null,
						    "You have no ingredients stored. Please add ingredients"
						    + " using the New Ingredient form.\nIndexOutOfBoundsException",
						    "No ingredients found!",
						    JOptionPane.WARNING_MESSAGE);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	ArrayList<Ingredient> ingredients;
	ArrayList<Vendor> vendors;
	int sel;
	public UpdateIngredientForm() {
		
		sel = 0;
		try {
			FileInputStream fileIn = new FileInputStream("src/Data/Ingredients.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ingredients = (ArrayList<Ingredient>) in.readObject();
			if( ingredients.get(0) == null ) //check top of list in case it is empty
			{
				in.close();
				fileIn.close();
				throw new IndexOutOfBoundsException();
			}
			in.close();
			fileIn.close();
		}catch(IOException i) {
			i.printStackTrace();
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
		}catch   (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null,
				    "You have no ingredients stored. Please add ingredients"
				    + " using the New Ingredient form.\nIndexOutOfBoundsException",
				    "No ingredients found!",
				    JOptionPane.WARNING_MESSAGE);
		}
		try {
			FileInputStream fileIn = new FileInputStream("src/Data/Vendors.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			vendors = (ArrayList<Vendor>) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i) {
			i.printStackTrace();
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
		}catch   (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null,
				    "You have no vendors stored. Please add vendors"
				    + " using the New Vendor form.\nIndexOutOfBoundsException",
				    "No vendors found!",
				    JOptionPane.WARNING_MESSAGE);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		if (!ingredients.isEmpty()) {
			textField.setText( "" + ingredients.get(sel).getID() );
		}
		textField.setBounds(86, 81, 224, 20);
		textField.setEditable(false);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		if (!ingredients.isEmpty()) {
		textField_1.setText(ingredients.get(sel).getName());
		}
		textField_1.setBounds(86, 112, 420, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(20, 174, 594, 141);
		contentPane.add(panel);
		
		JTextArea textArea = new JTextArea();
		if (!ingredients.isEmpty()) {
		textArea.setText(ingredients.get(sel).getDesc());
		}
		textArea.setBounds(182, 36, 402, 94);
		panel.add(textArea);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(80, 50, 370, 20);
		for (int i = 0; i < ingredients.size(); i++) {
			comboBox.addItem(ingredients.get(i).getName());
		}
		if (!ingredients.isEmpty()) {
			comboBox.setSelectedIndex(0);
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				if (comboBox.getSelectedIndex() != -1) {                     
					sel = comboBox.getSelectedIndex();
					textField.setText("" + ingredients.get(sel).getID());
					textField_1.setText(ingredients.get(sel).getName());
					textField_2.setText(ingredients.get(sel).getVendor().getName());
					textArea.setText(ingredients.get(sel).getDesc());
				}              
			}
		});
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Ingredient List");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(86, 11, 420, 35);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("ID");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(10, 84, 66, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(10, 115, 66, 14);
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		if (!ingredients.isEmpty()) {
			textField_2.setText(ingredients.get(sel).getVendor().getName());
		}
		textField_2.setColumns(10);
		textField_2.setBounds(86, 143, 420, 20);
		contentPane.add(textField_2);
		
		JLabel label_2 = new JLabel("Vendor");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(10, 146, 66, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("About");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(182, 11, 402, 14);
		panel.add(label_3);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Vendor vendor;
					int vndr = -1;
					for (int i = 0; i<vendors.size(); i++) {
						if (vendors.get(i).getName().equalsIgnoreCase(textField_2.getText())) {
							vndr = i;
							break;
						}
					}
					vendor = vendors.get(vndr);
					
					Ingredient ingredient = new Ingredient(Integer.parseInt(textField.getText()), textField_1.getText(), textArea.getText(), vendor, 0);
					ingredients.set(sel, ingredient);
					FileOutputStream fileOut = new FileOutputStream("src/Data/Ingredients.dat");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(ingredients);
					out.close();
					fileOut.close();
				} catch(IOException i) {
					i.printStackTrace();
				}
				setVisible(false);
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(30, 320, 184, 30);
		contentPane.add(btnUpdate);
		
		JButton btnDeleteButton = new JButton("Delete");
		btnDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ingredients.remove(sel);
					FileOutputStream fileOut = new FileOutputStream("src/Data/Ingredients.dat");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(ingredients);
					out.close();
					fileOut.close();
				} catch(IOException i) {
					i.printStackTrace();
				}
				setVisible(false);
			}
		});
		btnDeleteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeleteButton.setBounds(224, 320, 190, 30);
		contentPane.add(btnDeleteButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBounds(424, 320, 190, 30);
		contentPane.add(btnCancel);
	}
}
