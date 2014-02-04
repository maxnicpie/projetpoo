import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DomaineCreation extends JDialog {
	private JTextField nomDomaine;
	private JTextField nomCategorie;
	private JTextField nomMotsCles;
	private JTextField nomCritere;

	public DomaineCreation(Statement st) {

		this.setModal(true);
		this.setTitle("Ajout d'un nouveau domaine");
		this.getContentPane().setLayout(null);

		JLabel lblNomDuDomaine = new JLabel("Nom du domaine : ");
		lblNomDuDomaine.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomDuDomaine.setBounds(25, 27, 159, 34);
		getContentPane().add(lblNomDuDomaine);

		nomDomaine = new JTextField();
		nomDomaine.setBounds(41, 72, 205, 27);
		getContentPane().add(nomDomaine);
		nomDomaine.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(314, 27, 205, 446);
		getContentPane().add(scrollPane);

		JList listeCategories = new JList();
		scrollPane.setViewportView(listeCategories);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(563, 27, 205, 446);
		getContentPane().add(scrollPane_1);

		JList listeMotsCles = new JList();
		listeMotsCles.setEnabled(false);
		scrollPane_1.setViewportView(listeMotsCles);

		JButton btnAddCategorie = new JButton("Ajouter Categorie");
		btnAddCategorie.setBounds(314, 521, 205, 27);
		getContentPane().add(btnAddCategorie);

		nomCategorie = new JTextField();
		nomCategorie.setBounds(314, 483, 205, 27);
		getContentPane().add(nomCategorie);
		nomCategorie.setColumns(10);

		nomMotsCles = new JTextField();
		nomMotsCles.setEnabled(false);
		nomMotsCles.setColumns(10);
		nomMotsCles.setBounds(563, 483, 205, 27);
		getContentPane().add(nomMotsCles);

		JButton btnAddMotsCles = new JButton("Ajouter Mot Clé");
		btnAddMotsCles.setEnabled(false);
		btnAddMotsCles.setBounds(563, 521, 205, 27);
		getContentPane().add(btnAddMotsCles);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(41, 125, 205, 223);
		getContentPane().add(scrollPane_2);

		JList listCriteres = new JList();
		scrollPane_2.setViewportView(listCriteres);

		nomCritere = new JTextField();
		nomCritere.setColumns(10);
		nomCritere.setBounds(41, 359, 205, 27);
		getContentPane().add(nomCritere);

		JButton btnAjouterCritere = new JButton("Ajouter Critère");
		btnAjouterCritere.setBounds(41, 397, 205, 27);
		getContentPane().add(btnAjouterCritere);

		JButton btnNewButton = new JButton("Ajouter domaine");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(41, 464, 205, 84);
		getContentPane().add(btnNewButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 110, 266, 2);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 435, 266, 2);
		getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(276, 27, 2, 521);
		getContentPane().add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(540, 27, 2, 521);
		getContentPane().add(separator_3);

		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
	}

}
