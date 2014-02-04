import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Authentification extends JDialog {
	private JTextField txtRoot;
	private JTextField textField_1;
	private JTextField txtLocalhostbasegbp;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification auth = new Authentification();
					auth.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Authentification() {
		this.setModal(true);
		this.setTitle("Connexion");
		this.getContentPane().setLayout(null);
		
		JLabel lblUtilisateur = new JLabel("Utilisateur : ");
		lblUtilisateur.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblUtilisateur.setBounds(40, 54, 130, 17);
		getContentPane().add(lblUtilisateur);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe : ");
		lblMotDePasse.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblMotDePasse.setBounds(40, 117, 130, 33);
		getContentPane().add(lblMotDePasse);
		
		JLabel lblAdresse = new JLabel("Adresse :");
		lblAdresse.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAdresse.setBounds(40, 198, 130, 27);
		getContentPane().add(lblAdresse);
		
		txtRoot = new JTextField();
		txtRoot.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		txtRoot.setText("root");
		txtRoot.setBounds(85, 81, 110, 25);
		getContentPane().add(txtRoot);
		txtRoot.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		textField_1.setBounds(85, 162, 110, 25);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		txtLocalhostbasegbp = new JTextField();
		txtLocalhostbasegbp.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		txtLocalhostbasegbp.setText("localhost/base_gbp");
		txtLocalhostbasegbp.setBounds(10, 236, 274, 33);
		getContentPane().add(txtLocalhostbasegbp);
		txtLocalhostbasegbp.setColumns(10);
		
		JButton btnSeConnecter = new JButton("Se Connecter");
		btnSeConnecter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nom = txtRoot.getText();
				String mdp = textField_1.getText();
				String adresse = txtLocalhostbasegbp.getText();
				
				JDBC j1 = new JDBC();
		    	final Statement st = j1.connexion(nom,mdp,adresse);

		    	if(st == null){
		    		JOptionPane.showMessageDialog(null,
					"Erreur de connexion",
					"Message d'erreur", JOptionPane.ERROR_MESSAGE);
		    	}
		    	else{
		    		dispose();
					AccueilMenu menu = new AccueilMenu(st);
					menu.setVisible(true);
		    	}
			}
		});
		btnSeConnecter.setBounds(49, 316, 201, 33);
		getContentPane().add(btnSeConnecter);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(300, 400);
		this.setLocationRelativeTo(null);
	}
}
