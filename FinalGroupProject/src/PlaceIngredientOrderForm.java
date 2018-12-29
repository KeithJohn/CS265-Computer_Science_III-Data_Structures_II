import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.*;
import java.util.ArrayList;

public class PlaceIngredientOrderForm extends JFrame {

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
					PlaceIngredientOrderForm frame = new PlaceIngredientOrderForm();
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
	ArrayList<Ingredient> ingredients;
	ArrayList<IngredientOrder> ingredientOrders = new ArrayList<IngredientOrder>();
	@SuppressWarnings("unchecked")
	public PlaceIngredientOrderForm() {
		
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
			FileInputStream fileIn = new FileInputStream("src/Data/OrdersToVendors.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ingredientOrders = (ArrayList<IngredientOrder>) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i) {
			i.printStackTrace();
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setText(ingredients.get(0).getVendor().getName());
		textField_1.setEditable(false);
		textField_1.setBounds(250, 175, 152, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
	    final DefaultListModel<String> ingredientList = new DefaultListModel();
	    for (int i = 0; i < ingredients.size(); i++) {
	    ingredientList.addElement(ingredients.get(i).getName());
	    }
		JList list = new JList(ingredientList);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				textField_1.setText(ingredients.get(list.getSelectedIndex()).getVendor().getName());
				
			}
		});
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list.setSelectedIndex(0);
	    JScrollPane listScrollPane = new JScrollPane(list);
	    listScrollPane.setBounds(10, 32, 152, 218);
	    contentPane.add(listScrollPane);
		
		JLabel lblIngredientList = new JLabel("Ingredient List");
		lblIngredientList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIngredientList.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredientList.setBounds(10, 11, 152, 21);
		contentPane.add(lblIngredientList);
		
		textField = new JTextField();
		textField.setBounds(250, 118, 152, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblOrderAmount = new JLabel("Order Amount");
		lblOrderAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderAmount.setBounds(250, 93, 152, 14);
		contentPane.add(lblOrderAmount);
		
		JLabel lblVendor = new JLabel("Vendor");
		lblVendor.setHorizontalAlignment(SwingConstants.CENTER);
		lblVendor.setBounds(250, 150, 152, 14);
		contentPane.add(lblVendor);
		
		JButton btnNewButton = new JButton("Place Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					IngredientOrder order = new IngredientOrder(Integer.parseInt(textField_2.getText()), ingredients.get(list.getSelectedIndex()), Integer.parseInt(textField.getText()));
					ingredientOrders.add(order);
					FileOutputStream fileOut = new FileOutputStream("src/Data/OrdersToVendors.dat");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(ingredientOrders);
					out.close();
					fileOut.close();
				} catch(IOException i) {
					i.printStackTrace();
				}
				setVisible(false);
			}
		});
		btnNewButton.setBounds(172, 221, 119, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(301, 221, 123, 29);
		contentPane.add(btnNewButton_1);
		
		textField_2 = new JTextField();
		textField_2.setText(""+ingredientOrders.size());
		textField_2.setEditable(false);
		textField_2.setBounds(250, 58, 152, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblOrderNumber = new JLabel("Order Number");
		lblOrderNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderNumber.setBounds(250, 33, 152, 14);
		contentPane.add(lblOrderNumber);
	}

}