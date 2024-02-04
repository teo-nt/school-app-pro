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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AdminUpdateDeleteStudentsForm extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private JTextField idTxt;
	private JTextField firstnameTxt;
	private JTextField lastnameTxt;
	private JTextField birthDateTxt;
	
	private JComboBox<String> cityComboBox = new JComboBox<>();
	private JComboBox<String> userComboBox = new JComboBox<>();
	private Map<Integer, String> cities;
	private Map<Integer, String> usernames;
	private DefaultComboBoxModel<String> citiesModel;
	private DefaultComboBoxModel<String> usernamesModel;
	private ButtonGroup buttonGroup = new ButtonGroup(); 
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private JRadioButton maleRdBtn;
	private JRadioButton femaleRdBtn;
	private JButton firstBtn;
	private JButton prevBtn;
	private JButton nextBtn;
	private JButton lastBtn;
	Connection conn = null;
	private JButton closeBtn;

	public AdminUpdateDeleteStudentsForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminUpdateDeleteStudentsForm.class.getResource("/resources/eduv2.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				String sql = "SELECT * FROM STUDENTS WHERE LASTNAME LIKE ?";
				// Connection conn = Login.getConnection();
				//Connection conn = null;
				try {
					conn = DBUtil.getConnection();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					//DBUtil.getConnection();
					//System.out.println("Search name" + Main.getStudentsMenu().getLastname().trim())
					ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, Main.getAdminStudentsMenu().getLastname() + "%");
					rs = ps.executeQuery();
					
					if (!rs.next()) {
						JOptionPane.showMessageDialog(null, "Empty", "Result", JOptionPane.INFORMATION_MESSAGE);
						Main.getAdminStudentsMenu().setEnabled(true);
						Main.getAdminUpdateDeleteStudentsForm().setVisible(false);
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
				
				PreparedStatement psCities;
				ResultSet rsCities;
			    try {
			    	String sqlCities = "SELECT * FROM CITIES";
			    	psCities = conn.prepareStatement(sqlCities);
		    		rsCities = psCities.executeQuery();
			    	cities = new HashMap<>();
			    	citiesModel = new DefaultComboBoxModel<>();
			    	
			    	while (rsCities.next()) {
			    		String city = rsCities.getString("CITY");
			    		int id = rsCities.getInt("ID");
			    		cities.put(id, city);
			    		citiesModel.addElement(city);
			    	}
			    	cityComboBox.setModel(citiesModel);
			    	cityComboBox.setMaximumRowCount(5);
			    	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    
			  
			    PreparedStatement psUsers;
			    ResultSet rsUsers;
			    try {
			    	    String sqlUsers = "SELECT ID, USERNAME FROM USERS";
					    psUsers = conn.prepareStatement(sqlUsers);
			    		rsUsers = psUsers.executeQuery();
			    	usernames = new HashMap<>();
			    	usernamesModel = new DefaultComboBoxModel<>();
			    	
			    	while (rsUsers.next()) {
			    		String username = rsUsers.getString("USERNAME");
			    		int id = rsUsers.getInt("ID");
			    		usernames.put(id, username);
			    		usernamesModel.addElement(username);
			    	}
			    	userComboBox.setModel(usernamesModel);
			    	userComboBox.setMaximumRowCount(5);
			    	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    
			    try {
					idTxt.setText(Integer.toString(rs.getInt("ID")));
					firstnameTxt.setText(rs.getString("FIRSTNAME"));
					lastnameTxt.setText(rs.getString("LASTNAME"));
					if (rs.getString("GENDER").equals("M")) {
						maleRdBtn.setSelected(true);
					} else {
						femaleRdBtn.setSelected(true);
					}
					birthDateTxt.setText(DateUtil.toSQLDateString(rs.getDate("BIRTH_DATE")));
					cityComboBox.setSelectedItem(cities.get(rs.getInt("CITY_ID")));
					userComboBox.setSelectedItem(usernames.get(rs.getInt("USER_ID")));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		setTitle("Ενημέρωση / Διαγραφή Μαθητή");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 400, 525);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel idLbl = new JLabel("ID");
		idLbl.setBounds(87, 30, 18, 17);
		idLbl.setForeground(new Color(128, 0, 0));
		idLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(idLbl);
		
		idTxt = new JTextField();
		idTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idTxt.setBounds(124, 26, 59, 24);
		idTxt.setBackground(new Color(252, 252, 205));
		idTxt.setEditable(false);
		contentPane.add(idTxt);
		idTxt.setColumns(10);
		
		JLabel firstnameLbl = new JLabel("Όνομα");
		firstnameLbl.setBounds(56, 77, 49, 17);
		firstnameLbl.setForeground(new Color(128, 0, 0));
		firstnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(firstnameLbl);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		firstnameTxt.setBounds(124, 73, 203, 24);
		contentPane.add(firstnameTxt);
		firstnameTxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Επώνυμο");
		lblNewLabel.setBounds(39, 124, 66, 17);
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lastnameTxt.setBounds(124, 120, 203, 24);
		contentPane.add(lastnameTxt);
		lastnameTxt.setColumns(10);
		
		JLabel genderLbl = new JLabel("Φύλο");
		genderLbl.setBounds(67, 168, 38, 17);
		genderLbl.setForeground(new Color(128, 0, 0));
		genderLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(genderLbl);
		
		maleRdBtn = new JRadioButton("Άνδρας");
		maleRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		maleRdBtn.setBounds(124, 165, 71, 23);
		contentPane.add(maleRdBtn);
		
		femaleRdBtn = new JRadioButton("Γυναίκα");
		femaleRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		femaleRdBtn.setBounds(208, 165, 95, 23);
		contentPane.add(femaleRdBtn);
		
		buttonGroup.add(maleRdBtn);
		buttonGroup.add(femaleRdBtn);
		
		JLabel birthDateLbl = new JLabel("Ημ. Γέννησης");
		birthDateLbl.setBounds(6, 212, 99, 17);
		birthDateLbl.setForeground(new Color(128, 0, 0));
		birthDateLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(birthDateLbl);
		
		birthDateTxt = new JTextField();
		birthDateTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		birthDateTxt.setBounds(124, 208, 133, 24);
		contentPane.add(birthDateTxt);
		birthDateTxt.setColumns(10);
		
		JLabel cityLbl = new JLabel("Πόλη");
		cityLbl.setBounds(63, 256, 42, 17);
		cityLbl.setForeground(new Color(128, 0, 0));
		cityLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(cityLbl);
		cityComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cityComboBox.setBounds(124, 252, 173, 24);
		contentPane.add(cityComboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(30, 300, 75, 17);
		lblNewLabel_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1);
		userComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		userComboBox.setBounds(124, 296, 173, 24);
		contentPane.add(userComboBox);
		
		firstBtn = new JButton("");
		firstBtn.setBounds(38, 376, 49, 35);
		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.first()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						if (rs.getString("GENDER").equals("M")) {
							maleRdBtn.setSelected(true);
						} else {
							femaleRdBtn.setSelected(true);
						}
						birthDateTxt.setText(DateUtil.toSQLDateString(rs.getDate("BIRTH_DATE")));
						cityComboBox.setSelectedItem(cities.get(rs.getInt("CITY_ID")));
						userComboBox.setSelectedItem(usernames.get(rs.getInt("USER_ID")));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		firstBtn.setIcon(new ImageIcon(AdminUpdateDeleteStudentsForm.class.getResource("/resources/First record.png")));
		contentPane.add(firstBtn);
		
		prevBtn = new JButton("");
		prevBtn.setBounds(125, 376, 49, 35);
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.previous()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						if (rs.getString("GENDER").equals("M")) {
							maleRdBtn.setSelected(true);
						} else {
							femaleRdBtn.setSelected(true);
						}
						birthDateTxt.setText(DateUtil.toSQLDateString(rs.getDate("BIRTH_DATE")));
						cityComboBox.setSelectedItem(cities.get(rs.getInt("CITY_ID")));
						userComboBox.setSelectedItem(usernames.get(rs.getInt("USER_ID")));
					} else {
						rs.first();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		prevBtn.setIcon(new ImageIcon(AdminUpdateDeleteStudentsForm.class.getResource("/resources/Previous_record.png")));
		contentPane.add(prevBtn);
		
		nextBtn = new JButton("");
		nextBtn.setBounds(212, 376, 49, 35);
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.next()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						if (rs.getString("GENDER").equals("M")) {
							maleRdBtn.setSelected(true);
						} else {
							femaleRdBtn.setSelected(true);
						}
						birthDateTxt.setText(DateUtil.toSQLDateString(rs.getDate("BIRTH_DATE")));
						cityComboBox.setSelectedItem(cities.get(rs.getInt("CITY_ID")));
						userComboBox.setSelectedItem(usernames.get(rs.getInt("USER_ID")));
					} else {
						rs.last();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		nextBtn.setIcon(new ImageIcon(AdminUpdateDeleteStudentsForm.class.getResource("/resources/Next_track.png")));
		contentPane.add(nextBtn);
		
		lastBtn = new JButton("");
		lastBtn.setBounds(299, 376, 49, 35);
		lastBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.last()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						if (rs.getString("GENDER").equals("M")) {
							maleRdBtn.setSelected(true);
						} else {
							femaleRdBtn.setSelected(true);
						}
						birthDateTxt.setText(DateUtil.toSQLDateString(rs.getDate("BIRTH_DATE")));
						cityComboBox.setSelectedItem(cities.get(rs.getInt("CITY_ID")));
						userComboBox.setSelectedItem(usernames.get(rs.getInt("USER_ID")));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		lastBtn.setIcon(new ImageIcon(AdminUpdateDeleteStudentsForm.class.getResource("/resources/Last_Record.png")));
		contentPane.add(lastBtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(24, 344, 303, 2);
		contentPane.add(separator);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.setForeground(new Color(0, 0, 128));
		updateBtn.setBounds(15, 432, 108, 43);
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(updateBtn);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setForeground(new Color(0, 0, 128));
		deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		deleteBtn.setBounds(138, 432, 108, 43);
		contentPane.add(deleteBtn);
		
		closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminStudentsMenu().setEnabled(true);
				Main.getAdminUpdateDeleteStudentsForm().setVisible(false);
			}
		});
		closeBtn.setForeground(new Color(0, 0, 128));
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(261, 432, 108, 43);
		contentPane.add(closeBtn);
	}
}