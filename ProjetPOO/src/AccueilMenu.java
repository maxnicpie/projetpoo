import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class AccueilMenu extends JFrame {

	private JFrame frame = this;

	private Statement st;

	public AccueilMenu(Statement statement) {

		// Créer la connection a la base

		this.st = statement;
		
		final Domaine d = new Domaine(st);
		String[] listeDomaines = d.afficherListeDomaines();

		frame.setTitle("Gestionnaire électronique de documents");
		frame.getContentPane().setLayout(null);

		final JPanel panel = new JPanel() {
			public void paint(Graphics g) {
				try {
					BufferedImage image = ImageIO
							.read(new File("documents.jpg"));
					g.drawImage(image, 0, 0, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
				null));
		panel.setBounds(10, 51, 764, 499);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final
		JComboBox comboBox = new JComboBox(listeDomaines);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(180, 11, 166, 29);
		getContentPane().add(comboBox);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(450, 0, 2, 53);
		getContentPane().add(separator);
		
		JButton btnNewButton = new JButton("Nouveau domaine\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DomaineCreation creation = new DomaineCreation(st);
				creation.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(474, 11, 300, 29);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Domaine existant : \r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 160, 29);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("-->");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DomaineMenu dm = new DomaineMenu(new Domaine((String)comboBox.getSelectedItem(),st));
				dm.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(369, 12, 60, 29);
		getContentPane().add(btnNewButton_1);

		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null,
						"Êtes-vous sûr de vouloir quitter ?", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
					try {
						st.close();
						System.exit(0);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		frame.setVisible(true);
	}
}
