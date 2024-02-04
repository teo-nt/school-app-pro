package gr.aueb.cf.schoolpro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AdminTeachersMenu extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private JTextField lastnameTxt;

	public AdminTeachersMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminTeachersMenu.class.getResource("/resources/eduv2.png")));
		setTitle("Μενού Εκπαιδευτών");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 449, 425);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchPanel.setBounds(40, 23, 355, 156);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);
		
		JLabel lastnameLbl = new JLabel("Επώνυμο");
		lastnameLbl.setForeground(new Color(128, 0, 0));
		lastnameLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		lastnameLbl.setBounds(142, 10, 70, 35);
		searchPanel.add(lastnameLbl);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lastnameTxt.setBounds(69, 55, 216, 35);
		searchPanel.add(lastnameTxt);
		lastnameTxt.setColumns(10);
		
		JButton searchBtn = new JButton("Αναζήτηση");
		searchBtn.setForeground(new Color(0, 0, 255));
		searchBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchBtn.setBounds(116, 100, 122, 43);
		searchPanel.add(searchBtn);
		
		JPanel insertPanel = new JPanel();
		insertPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		insertPanel.setBounds(40, 204, 355, 97);
		contentPane.add(insertPanel);
		insertPanel.setLayout(null);
		
		JButton insertbtn = new JButton("Εισαγωγή");
		insertbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminInsertTeachersForm().setVisible(true);
				Main.getAdminTeachersMenu().setEnabled(false);
			}
		});
		insertbtn.setForeground(new Color(0, 0, 255));
		insertbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		insertbtn.setBounds(116, 27, 122, 43);
		insertPanel.add(insertbtn);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminMenu().setEnabled(true);
				Main.getAdminTeachersMenu().setVisible(false);
			}
		});
		closeBtn.setForeground(new Color(0, 0, 255));
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(287, 324, 108, 43);
		contentPane.add(closeBtn);
	}

}