package gr.aueb.cf.schoolpro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolpro.util.DBUtil;
import gr.aueb.cf.schoolpro.util.DateUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;

public class AdminInsertStudentsForm extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private JTextField firstnameTxt;
	private JLabel lastnameLbl;
	private JTextField lastnameTxt;
	private JLabel genderLbl;
	private JLabel cityLbl;
	private JComboBox<String> cityComboBox = new JComboBox<>();
	private JComboBox<String> usernameComboBox = new JComboBox<>();
	private Map<String, Integer> cities;
	private Map<String, Integer> usernames;
	private DefaultComboBoxModel<String> citiesModel;
	private DefaultComboBoxModel<String> usernamesModel;
	private JTextField birthDateTxt;
	private JLabel usernameLbl;
	private ButtonGroup buttonGroup = new ButtonGroup(); 
	private JRadioButton maleRdBtn;
	private JRadioButton femaleRdBtn;

	public AdminInsertStudentsForm() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminInsertStudentsForm.class.getResource("/resources/eduv2.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
 				firstnameTxt.setText("");
				lastnameTxt.setText("");
				maleRdBtn.setSelected(true);
				birthDateTxt.setText("");
				cityComboBox.setSelectedItem(null);
				usernameComboBox.setSelectedItem(null);	
			}
		});
		setTitle("Εισαγωγή Εκπαιδευόμενου");
		setBounds(100, 100, 409, 354);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel firstnameLbl = new JLabel("Όνομα");
		firstnameLbl.setForeground(new Color(128, 0, 0));
		firstnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstnameLbl.setBounds(80, 21, 50, 17);
		contentPane.add(firstnameLbl);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		firstnameTxt.setBounds(141, 19, 207, 20);
		contentPane.add(firstnameTxt);
		firstnameTxt.setColumns(10);
		
		lastnameLbl = new JLabel("Επώνυμο");
		lastnameLbl.setForeground(new Color(128, 0, 0));
		lastnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lastnameLbl.setBounds(63, 59, 67, 17);
		contentPane.add(lastnameLbl);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lastnameTxt.setColumns(10);
		lastnameTxt.setBounds(141, 57, 207, 20);
		contentPane.add(lastnameTxt);
		
		genderLbl = new JLabel("Φύλο");
		genderLbl.setForeground(new Color(128, 0, 0));
		genderLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		genderLbl.setBounds(90, 97, 40, 17);
		contentPane.add(genderLbl);
		
		// buttonGroup = new ButtonGroup();
		maleRdBtn = new JRadioButton("Άνδρας");
		maleRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		maleRdBtn.setBounds(141, 94, 81, 23);
		contentPane.add(maleRdBtn);
		
		femaleRdBtn = new JRadioButton("Γυναίκα");
		femaleRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		femaleRdBtn.setBounds(224, 94, 95, 23);
		contentPane.add(femaleRdBtn);
		
		maleRdBtn.setActionCommand("M");
        femaleRdBtn.setActionCommand("F");
		
		buttonGroup.add(maleRdBtn);
		buttonGroup.add(femaleRdBtn);
		
		cityLbl = new JLabel("Πόλη");
		cityLbl.setForeground(new Color(128, 0, 0));
		cityLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		cityLbl.setBounds(90, 173, 40, 17);
		contentPane.add(cityLbl);
		cityComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		//cityComboBox = new JComboBox<>();
		
		cityComboBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				String sql = "SELECT * FROM CITIES";
				//connection = Login.getConnection();
				
			    try (Connection connection = DBUtil.getConnection();
			    		PreparedStatement ps = connection.prepareStatement(sql);
			    		ResultSet rs = ps.executeQuery()) {
			    	cities = new HashMap<>();
			    	citiesModel = new DefaultComboBoxModel<>();
			    	
			    	while (rs.next()) {
			    		String city = rs.getString("CITY");
			    		int id = rs.getInt("ID");
			    		cities.put(city, id);
			    		citiesModel.addElement(city);
			    	}
			    	cityComboBox.setModel(citiesModel);
			    	cityComboBox.setMaximumRowCount(5);
			    	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		cityComboBox.setBounds(141, 171, 207, 20);
		contentPane.add(cityComboBox);
		
		JLabel birthDateLbl = new JLabel("Ημ. Γέννησης");
		birthDateLbl.setForeground(new Color(128, 0, 0));
		birthDateLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		birthDateLbl.setBounds(33, 135, 97, 17);
		contentPane.add(birthDateLbl);
		
		birthDateTxt = new JTextField();
		birthDateTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		birthDateTxt.setColumns(10);
		birthDateTxt.setBounds(141, 133, 207, 20);
		contentPane.add(birthDateTxt);
		
		usernameLbl = new JLabel("Username");
		usernameLbl.setForeground(new Color(128, 0, 0));
		usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		usernameLbl.setBounds(55, 211, 75, 17);
		contentPane.add(usernameLbl);
		usernameComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		//usernameComboBox = new JComboBox<String>();
		
		usernameComboBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				String sql = "SELECT ID, USERNAME FROM USERS";
				//connection = Login.getConnection();
				
			    try (Connection connection = DBUtil.getConnection();
			    		PreparedStatement ps = connection.prepareStatement(sql);
			    		ResultSet rs = ps.executeQuery(sql)) {
			    	usernames = new HashMap<>();
			    	usernamesModel = new DefaultComboBoxModel<>();
			    	
			    	while (rs.next()) {
			    		String username = rs.getString("USERNAME");
			    		int id = rs.getInt("ID");
			    		usernames.put(username, id);
			    		usernamesModel.addElement(username);
			    	}
			    	usernameComboBox.setModel(usernamesModel);
			    	usernameComboBox.setMaximumRowCount(5);
			    	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		
		usernameComboBox.setBounds(141, 209, 207, 20);
		contentPane.add(usernameComboBox);
		
		JButton insertBtn = new JButton("Εισαγωγή");
		insertBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				if (buttonGroup.getSelection() == null || cityComboBox.getSelectedItem() == null
						|| usernameComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Please select gender / city / username", "Gender", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String firstname = firstnameTxt.getText().trim();
				String lastname = lastnameTxt.getText().trim();
				String gender = buttonGroup.getSelection().getActionCommand();
				String city = (String) cityComboBox.getSelectedItem();
				String username = (String) usernameComboBox.getSelectedItem();
				int cityId = cities.get(city);
				int usernameId = usernames.get(username);
				
				java.sql.Date sqlBirthDate;
				try {
					sqlBirthDate = DateUtil.toSQLDate(DateUtil.toDate(birthDateTxt.getText()));
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Please insert a valid date (dd-MM-yyyy)", "Date", JOptionPane.ERROR_MESSAGE);
					//e1.printStackTrace();
					return;
				}
				
				if (firstname == "" || lastname == "") {
					JOptionPane.showMessageDialog(null, "Please fill firstname / lastname", "Basic info", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				PreparedStatement ps = null;
				try (Connection conn = DBUtil.getConnection();) {
					String sql = "INSERT INTO STUDENTS (FIRSTNAME, LASTNAME, GENDER, BIRTH_DATE, CITY_ID, USER_ID) " +
								"VALUES(?, ?, ?, ?, ?, ?)";
					
					ps = conn.prepareStatement(sql);
					ps.setString(1, firstname);
					ps.setString(2,  lastname);
					ps.setString(3, gender);
					ps.setDate(4, sqlBirthDate);
					ps.setInt(5, cityId);
					ps.setInt(6, usernameId);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Η εγγραφή έγινε επιτυχώς", "Insert", JOptionPane.INFORMATION_MESSAGE);			
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					try {
						if (ps != null) ps.close();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
				
//				System.out.println(ssnTxt.getText());
//				System.out.println(firstnameTxt.getText());
//				System.out.println(lastnameTxt.getText());
//				System.out.println(buttonGroup.getSelection().getActionCommand());
//				System.out.println(birthDateTxt.getText());
//				System.out.println(cityComboBox.getSelectedItem() + ", id: " + cities.get(cityComboBox.getSelectedItem()));
//				System.out.println(usernameComboBox.getSelectedItem() + ", id: " + usernames.get(usernameComboBox.getSelectedItem()));
			}
		});
		insertBtn.setForeground(new Color(0, 0, 255));
		insertBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		insertBtn.setBounds(114, 249, 108, 43);
		contentPane.add(insertBtn);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminStudentsMenu().setEnabled(true);
				Main.getAdminInsertStudentsForm().setVisible(false);
			}
		});
		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(240, 248, 108, 43);
		contentPane.add(closeBtn);
		
	}
}