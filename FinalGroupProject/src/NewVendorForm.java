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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

import java.io.*;
import java.util.*;

public class NewVendorForm extends JFrame {

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
					NewVendorForm frame = new NewVendorForm();
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
	public NewVendorForm() {
		
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
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setText(""+vendors.size());
		textField.setBounds(80, 11, 205, 20);
		textField.setEditable(false);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(80, 42, 370, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(80, 73, 138, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(305, 73, 145, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(80, 104, 138, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(80, 135, 370, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 165, 464, 150);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField_6 = new JTextField();
		textField_6.setBounds(69, 26, 370, 20);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(70, 57, 250, 20);
		panel.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(70, 88, 250, 20);
		panel.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(70, 119, 250, 20);
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
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Vendor vendor = new Vendor(Integer.parseInt(textField.getText()), textField_1.getText(), textField_2.getText(), textField_3.getText(), textField_4.getText(), textField_5.getText());
					vendor.setAddress(textField_6.getText(), textField_7.getText(), textField_8.getText(), textField_9.getText());
					vendors.add(vendor);
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
		btnNewButton.setBounds(10, 328, 150, 23);
		contentPane.add(btnNewButton);
		
		JButton btnClearButton = new JButton("Clear");
		btnClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
				textField_9.setText("");
			}
		});
		btnClearButton.setBounds(170, 328, 150, 23);
		contentPane.add(btnClearButton);
		
		JButton btnNewButton_2 = new JButton("Cancel");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_2.setBounds(330, 328, 144, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(24, 14, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(24, 45, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(24, 76, 46, 14);
		contentPane.add(lblPhone);
		
		JLabel lblFax = new JLabel("Fax");
		lblFax.setBounds(24, 107, 46, 14);
		contentPane.add(lblFax);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(24, 138, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 165, 46, 14);
		contentPane.add(lblAddress);
		
		JLabel lblPhone_1 = new JLabel("Phone II\r\n");
		lblPhone_1.setBounds(249, 76, 46, 14);
		contentPane.add(lblPhone_1);
	}

}
