package gr.aueb.cf.schoolpro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolpro.util.DBUtil;

import javax.swing.JLabel;
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
import java.util.HashMap;
import java.util.Map;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminInsertTeachersForm extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private JTextField firstnameTxt;
	private JLabel lastnameLbl;
	private JTextField lastnameTxt;
	private JLabel genderLbl;
	private JLabel ssnLbl;
	private JTextField ssnTxt;
	private JLabel specialityLbl;
	private JComboBox<String> comboBox;
	private Map<String, Integer> cities;
	private DefaultComboBoxModel<String> model;

	public AdminInsertTeachersForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminInsertTeachersForm.class.getResource("/resources/eduv2.png")));
		setTitle("Εισαγωγή Εκπαιδευτή");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 406, 340);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel firstnameLbl = new JLabel("Όνομα");
		firstnameLbl.setForeground(new Color(128, 0, 0));
		firstnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstnameLbl.setBounds(64, 27, 56, 17);
		contentPane.add(firstnameLbl);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		firstnameTxt.setBounds(129, 25, 207, 20);
		contentPane.add(firstnameTxt);
		firstnameTxt.setColumns(10);
		
		lastnameLbl = new JLabel("Επώνυμο");
		lastnameLbl.setForeground(new Color(128, 0, 0));
		lastnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lastnameLbl.setBounds(52, 115, 72, 17);
		contentPane.add(lastnameLbl);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lastnameTxt.setColumns(10);
		lastnameTxt.setBounds(129, 113, 207, 20);
		contentPane.add(lastnameTxt);
		
		genderLbl = new JLabel("Φύλο");
		genderLbl.setForeground(new Color(128, 0, 0));
		genderLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		genderLbl.setBounds(80, 159, 44, 17);
		contentPane.add(genderLbl);
		
		ssnLbl = new JLabel("Αρ. Μητρώου");
		ssnLbl.setForeground(new Color(128, 0, 0));
		ssnLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		ssnLbl.setBounds(25, 71, 99, 17);
		contentPane.add(ssnLbl);
		
		ssnTxt = new JTextField();
		ssnTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ssnTxt.setColumns(10);
		ssnTxt.setBounds(129, 69, 207, 20);
		contentPane.add(ssnTxt);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton maleRdbtn = new JRadioButton("Άνδρας");
		maleRdbtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		maleRdbtn.setBounds(129, 156, 81, 23);
		contentPane.add(maleRdbtn);
		
		JRadioButton femaleRdbtn = new JRadioButton("Γυναίκα");
		femaleRdbtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		femaleRdbtn.setBounds(206, 156, 95, 23);
		contentPane.add(femaleRdbtn);
		
		buttonGroup.add(maleRdbtn);
		buttonGroup.add(femaleRdbtn);
		
		specialityLbl = new JLabel("Πόλη");
		specialityLbl.setForeground(new Color(128, 0, 0));
		specialityLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		specialityLbl.setBounds(80, 203, 44, 17);
		contentPane.add(specialityLbl);
		
		comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		comboBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				//comboBox.removeAll();
				String sql = "SELECT * FROM CITIES";
				//connection = Login.getConnection();
				
			    try (Connection connection = DBUtil.getConnection();
			    		PreparedStatement ps = connection.prepareStatement(sql);
			    		ResultSet rs = ps.executeQuery()) {
			    	cities = new HashMap<>();
			    	model = new DefaultComboBoxModel<>();
			    	
			    	while (rs.next()) {
			    		String city = rs.getString("CITY");
			    		int id = rs.getInt("ID");
			    		cities.put(city, id);
			    		//comboBox.addItem(city);
			    		model.addElement(city);
			    	}
			    	comboBox.setModel(model);
			    	comboBox.setMaximumRowCount(5);
			    	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		comboBox.setBounds(129, 201, 207, 20);
		contentPane.add(comboBox);
		
		JButton insertBtn = new JButton("Εισαγωγή");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		insertBtn.setForeground(Color.BLUE);
		insertBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		insertBtn.setBounds(105, 247, 108, 43);
		contentPane.add(insertBtn);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminTeachersMenu().setEnabled(true);
				Main.getAdminInsertTeachersForm().setVisible(false);
			}
		});
		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(228, 247, 108, 43);
		contentPane.add(closeBtn);
		
	}
}