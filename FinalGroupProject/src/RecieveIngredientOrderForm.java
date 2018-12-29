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

public class RecieveIngredientOrderForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecieveIngredientOrderForm frame = new RecieveIngredientOrderForm();
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
	ArrayList<IngredientOrder> ingredientOrders;
	ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	int sel;
	public RecieveIngredientOrderForm() {
		
		sel = 0;
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(290, 93, 152, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblOrderAmount = new JLabel("Order Amount");
		lblOrderAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderAmount.setBounds(290, 68, 152, 14);
		contentPane.add(lblOrderAmount);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(290, 150, 152, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblVendor = new JLabel("Vendor");
		lblVendor.setHorizontalAlignment(SwingConstants.CENTER);
		lblVendor.setBounds(290, 125, 152, 14);
		contentPane.add(lblVendor);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(370, 201, 152, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblOrderList = new JLabel("Delivered Order List");
		lblOrderList.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOrderList.setBounds(10, 11, 188, 21);
		contentPane.add(lblOrderList);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(290, 36, 152, 21);
		contentPane.add(textField_3);
		
	    final DefaultListModel<String> ingredientList = new DefaultListModel();
	    for (int i = 0; i < ingredientOrders.size(); i++) {
	    ingredientList.addElement(ingredientOrders.get(i).getIngredient().getName());
	    }
		JList list = new JList(ingredientList);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				sel = list.getSelectedIndex();
				textField_1.setText(ingredientOrders.get(sel).getVendor().getName());
				textField.setText(Integer.toString(ingredientOrders.get(sel).getAmount()));
				textField_3.setText(ingredientOrders.get(sel).getIngredient().getName());
			}
		});
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list.setSelectedIndex(0);
	    JScrollPane listScrollPane = new JScrollPane(list);
	    listScrollPane.setBounds(10, 32, 152, 218);
	    contentPane.add(listScrollPane);
		
		JLabel lblIngredient = new JLabel("Ingredient");
		lblIngredient.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredient.setBounds(290, 11, 152, 14);
		contentPane.add(lblIngredient);
		
		JButton btnRecieveOrder = new JButton("Recieve Order");
		btnRecieveOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<IngredientOrder> recievedOrders = new ArrayList<IngredientOrder>();
					for (int i = 0; i < ingredients.size(); i++) {
						if (ingredientOrders.get(sel).getIngredient().getID() == ingredients.get(i).getID()) {
							ingredients.get(i).addToStock(ingredientOrders.get(sel).getAmount());
						}
					}
					recievedOrders.add(ingredientOrders.get(sel));
					FileOutputStream fileOut = new FileOutputStream("src/Data/RecievedAndEntered.dat");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(recievedOrders);
					out.close();
					fileOut.close();
					ingredientOrders.remove(sel);
					fileOut = new FileOutputStream("src/Data/OrdersToVendors.dat");
					out = new ObjectOutputStream(fileOut);
					out.writeObject(ingredientOrders);
					out.close();
					fileOut.close();
					fileOut = new FileOutputStream("src/Data/Ingredients.dat");
					out = new ObjectOutputStream(fileOut);
					out.writeObject(ingredients);
					out.close();
					fileOut.close();
				} catch(IOException i) {
					i.printStackTrace();
				}
				setVisible(false);
			}
		});
		btnRecieveOrder.setBounds(208, 201, 152, 29);
		contentPane.add(btnRecieveOrder);
	}
}
