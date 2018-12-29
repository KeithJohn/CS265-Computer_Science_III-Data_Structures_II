import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

import java.io.*;
import java.util.*;

public class UpdateVendorForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateVendorForm frame = new UpdateVendorForm();
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
	
	ArrayList<Vendor> vendors;
	int sel;
	public UpdateVendorForm() {
		
		sel = 0;
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
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(80, 50, 205, 20);
		if (!vendors.isEmpty()) {
		textField.setText(Integer.toString(vendors.get(sel).getID()));
		}
		textField.setEditable(false);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(80, 81, 370, 20);
		if (!vendors.isEmpty()) {
			textField_1.setText(vendors.get(sel).getName());
		}
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(80, 112, 138, 20);
		if (!vendors.isEmpty()) {
			textField_2.setText(vendors.get(sel).getPhone1());
		}
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(305, 112, 145, 20);
		if (!vendors.isEmpty()) {
			textField_3.setText(vendors.get(sel).getPhone2());
		}
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(80, 143, 138, 20);
		if (!vendors.isEmpty()) {
			textField_4.setText(vendors.get(sel).getFax());
		}
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(80, 174, 370, 20);
		if (!vendors.isEmpty()) {
			textField_5.setText(vendors.get(sel).getEmail());
		}
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 204, 464, 150);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField_6 = new JTextField();
		textField_6.setBounds(69, 26, 370, 20);
		if (!vendors.isEmpty()) {
			textField_6.setText(vendors.get(sel).getAddress()[0]);
		}
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(70, 57, 250, 20);
		if (!vendors.isEmpty()) {
			textField_7.setText(vendors.get(sel).getAddress()[1]);
		}
		panel.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(70, 88, 250, 20);
		if (!vendors.isEmpty()) {
			textField_8.setText(vendors.get(sel).getAddress()[2]);
		}
		panel.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(70, 119, 250, 20);
		if (!vendors.isEmpty()) {
			textField_9.setText(vendors.get(sel).getAddress()[3]);
		}
		panel.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblAddress_1 = new JLabel("Address");
		lblAddress_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress_1.setBounds(69, 1, 251, 27);
		panel.add(lblAddress_1);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setBounds(13, 29, 46, 14);
		panel.add(lblStreet);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(14, 60, 46, 14);
		panel.add(lblCity);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(13, 91, 46, 14);
		panel.add(lblState);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(14, 122, 46, 14);
		panel.add(lblCountry);
		
		JButton btnUpdateButton = new JButton("Update");
		btnUpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Vendor vendor = new Vendor(Integer.parseInt(textField.getText()), textField_1.getText(), textField_2.getText(), textField_3.getText(), textField_4.getText(), textField_5.getText());
					vendor.setAddress(textField_6.getText(), textField_7.getText(), textField_8.getText(), textField_9.getText());
					vendors.set(sel, vendor);
					FileOutputStream fileOut = new FileOutputStream("src/Data/Vendors.dat");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(vendors);
					out.close();
					fileOut.close();
				} catch(IOException i) {
					i.printStackTrace();
				}
				setVisible(false);
			}
		});
		btnUpdateButton.setBounds(10, 367, 150, 23);
		contentPane.add(btnUpdateButton);
		
		JButton btnDeleteButton = new JButton("Delete");
		btnDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					vendors.remove(sel);
					FileOutputStream fileOut = new FileOutputStream("src/Data/Vendors.dat");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(vendors);
					out.close();
					fileOut.close();
				} catch(IOException i) {
					i.printStackTrace();
				}
				setVisible(false);
			}
		});
		btnDeleteButton.setBounds(170, 367, 150, 23);
		contentPane.add(btnDeleteButton);
		
		JButton btnNewButton_2 = new JButton("Cancel");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_2.setBounds(330, 367, 144, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(24, 53, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(24, 84, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(24, 115, 46, 14);
		contentPane.add(lblPhone);
		
		JLabel lblFax = new JLabel("Fax");
		lblFax.setBounds(24, 146, 46, 14);
		contentPane.add(lblFax);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(24, 177, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 204, 46, 14);
		contentPane.add(lblAddress);
		
		JLabel lblPhone_1 = new JLabel("Phone II\r\n");
		lblPhone_1.setBounds(249, 115, 46, 14);
		contentPane.add(lblPhone_1);
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setBounds(80, 19, 370, 20);
		for (int i = 0; i < vendors.size(); i++) {
			comboBox.addItem(vendors.get(i).getName());
		}
		if (!vendors.isEmpty()) {
			comboBox.setSelectedIndex(0);
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				if (comboBox.getSelectedIndex() != -1) {                     
					sel = comboBox.getSelectedIndex();
					textField.setText(Integer.toString(vendors.get(sel).getID()));
					textField_1.setText(vendors.get(sel).getName());
					textField_2.setText(vendors.get(sel).getPhone1());
					textField_3.setText(vendors.get(sel).getPhone2());
					textField_4.setText(vendors.get(sel).getFax());
					textField_5.setText(vendors.get(sel).getEmail());
					
					textField_6.setText(vendors.get(sel).getAddress()[0]);
					textField_7.setText(vendors.get(sel).getAddress()[1]);
					textField_8.setText(vendors.get(sel).getAddress()[2]);
					textField_9.setText(vendors.get(sel).getAddress()[3]);
				}              
			}
		});
		contentPane.add(comboBox);
		
		JLabel lblVendor = new JLabel("Vendor");
		lblVendor.setBounds(24, 22, 46, 14);
		contentPane.add(lblVendor);
	}

}
