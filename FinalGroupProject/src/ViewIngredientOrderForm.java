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

public class ViewIngredientOrderForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewIngredientOrderForm frame = new ViewIngredientOrderForm();
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
	ArrayList<IngredientOrder> recievedOrders;
	int sel;
	public ViewIngredientOrderForm() {
		
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
			FileInputStream fileIn = new FileInputStream("src/Data/RecievedAndEntered.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			recievedOrders = (ArrayList<IngredientOrder>) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i) {
			i.printStackTrace();
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(270, 162, 152, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblOrderAmount = new JLabel("Order Amount");
		lblOrderAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderAmount.setBounds(270, 137, 152, 14);
		contentPane.add(lblOrderAmount);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(270, 219, 152, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblVendor = new JLabel("Vendor");
		lblVendor.setHorizontalAlignment(SwingConstants.CENTER);
		lblVendor.setBounds(270, 194, 152, 14);
		contentPane.add(lblVendor);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(270, 270, 152, 29);
		contentPane.add(btnNewButton_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(270, 102, 152, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblOrderNumber = new JLabel("Order Status");
		lblOrderNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderNumber.setBounds(270, 77, 152, 14);
		contentPane.add(lblOrderNumber);
		
		JLabel lblOrderList = new JLabel("Order List");
		lblOrderList.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOrderList.setBounds(10, 11, 152, 21);
		contentPane.add(lblOrderList);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(270, 36, 152, 21);
		contentPane.add(textField_3);
		
	    final DefaultListModel<String> ingredientList = new DefaultListModel();
	    for (int i = 0; i < ingredientOrders.size(); i++) {
	    	ingredientList.addElement(ingredientOrders.get(i).getIngredient().getName());
	    }
	    for (int i = 0; i < recievedOrders.size(); i++) {
	    	ingredientList.addElement(recievedOrders.get(i).getIngredient().getName());
	    }
		JList list = new JList(ingredientList);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				sel = list.getSelectedIndex();
				if (sel < ingredientOrders.size()) {
					textField_2.setText("Pending");
					textField_1.setText(ingredientOrders.get(sel).getVendor().getName());
					textField.setText(Integer.toString(ingredientOrders.get(sel).getAmount()));
					textField_3.setText(ingredientOrders.get(sel).getIngredient().getName());
				}
				else {
					textField_2.setText("Delivered");
					textField_1.setText(recievedOrders.get(sel-ingredientOrders.size()).getVendor().getName());
					textField.setText(Integer.toString(recievedOrders.get(sel-ingredientOrders.size()).getAmount()));
					textField_3.setText(recievedOrders.get(sel-ingredientOrders.size()).getIngredient().getName());
				}
			}
		});
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list.setSelectedIndex(0);
	    JScrollPane listScrollPane = new JScrollPane(list);
	    listScrollPane.setBounds(10, 32, 152, 218);
	    contentPane.add(listScrollPane);
		
		JLabel lblIngredient = new JLabel("Ingredient");
		lblIngredient.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredient.setBounds(270, 11, 152, 14);
		contentPane.add(lblIngredient);
	}
}
