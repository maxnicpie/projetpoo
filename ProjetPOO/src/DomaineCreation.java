import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class DomaineCreation extends JDialog {
	private JTextField nomDomaine;
	private JTextField nomCategorie;
	private JTextField nomMotsCles;
	private JTextField nomCritere;
	@SuppressWarnings({ "rawtypes", "unused" })
	private JComboBox listeComboDomaines;

	public DomaineCreation(Statement st,@SuppressWarnings("rawtypes") final JComboBox listeComboDomaines) {

		this.listeComboDomaines=listeComboDomaines;
		this.setModal(true);
		this.setTitle("Ajout d'un nouveau domaine");
		this.getContentPane().setLayout(null);
		
		final Domaine a = new Domaine(st);
		final CategorieMotClef cat1 = new CategorieMotClef();
		final Critere critere1 = new Critere();

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

		@SuppressWarnings("rawtypes")
		final JList listeCategories = new JList();
		scrollPane.setViewportView(listeCategories);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(563, 27, 205, 446);
		getContentPane().add(scrollPane_1);

		@SuppressWarnings("rawtypes")
		final JList listeMotsCles = new JList();
		listeMotsCles.setEnabled(false);
		scrollPane_1.setViewportView(listeMotsCles);

		listeCategories.addListSelectionListener(new ListSelectionListener() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void valueChanged(ListSelectionEvent e) {
				try {
					CategorieMotClef cat = cat1.chercherCategorie(((JList) e
							.getSource()).getSelectedValue());
					if (cat != null) {
						listeMotsCles.setListData(cat.afficherListeMotsClefs());
					}
				} catch (NullPointerException npe) {
				}
			}

		});

		nomCategorie = new JTextField();
		nomCategorie.setForeground(SystemColor.windowBorder);
		nomCategorie.setFont(new Font("Tahoma", Font.ITALIC, 11));
		nomCategorie.setText("  Catégorie ...");
		nomCategorie.setBounds(314, 483, 205, 27);
		getContentPane().add(nomCategorie);
		nomCategorie.setColumns(10);
		nomCategorie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nomCategorie.setText("");
				nomCategorie.setFont(new Font("Tahoma", Font.PLAIN, 11));
				nomCategorie.setForeground(Color.BLACK);
			}
		});

		nomMotsCles = new JTextField();
		nomMotsCles.setForeground(SystemColor.windowBorder);
		nomMotsCles.setFont(new Font("Tahoma", Font.ITALIC, 11));
		nomMotsCles.setText("  Mot clé ...");
		nomMotsCles.setEnabled(false);
		nomMotsCles.setColumns(10);
		nomMotsCles.setBounds(563, 483, 205, 27);
		nomMotsCles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nomMotsCles.setText("");
				nomMotsCles.setFont(new Font("Tahoma", Font.PLAIN, 11));
				nomMotsCles.setForeground(Color.BLACK);
			}
		});
		getContentPane().add(nomMotsCles);

		final JButton btnAddMotsCles = new JButton("Ajouter Mot Clé");
		btnAddMotsCles.setEnabled(false);
		btnAddMotsCles.setBounds(563, 521, 205, 27);
		btnAddMotsCles.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				if ((nomMotsCles.getText().equals("  Mot clé ..."))
						|| (nomMotsCles.getText().equals(""))) {
					JOptionPane.showMessageDialog(null, "Entrez un mot-clé",
							"Message d'erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					if (listeCategories.isSelectionEmpty()) {
						JOptionPane
								.showMessageDialog(
										null,
										"Selectionnez une catégorie pour ajouter un mot clé",
										"Message d'erreur",
										JOptionPane.ERROR_MESSAGE);
					} else {
						CategorieMotClef cat = cat1
								.chercherCategorie(listeCategories
										.getSelectedValue());
						cat.ajouterMotClef(nomMotsCles.getText());
						listeMotsCles.setListData(cat.afficherListeMotsClefs());
						nomMotsCles.setText("");
					}
				}
				
			}
		});
		getContentPane().add(btnAddMotsCles);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(41, 125, 205, 223);
		getContentPane().add(scrollPane_2);

		@SuppressWarnings("rawtypes")
		final JList listCriteres = new JList();
		scrollPane_2.setViewportView(listCriteres);

		nomCritere = new JTextField();
		nomCritere.setForeground(SystemColor.windowBorder);
		nomCritere.setFont(new Font("Tahoma", Font.ITALIC, 11));
		nomCritere.setText("  Critère ...");
		nomCritere.setToolTipText("");
		nomCritere.setColumns(10);
		nomCritere.setBounds(41, 359, 205, 27);
		nomCritere.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nomCritere.setText("");
				nomCritere.setFont(new Font("Tahoma", Font.PLAIN, 11));
				nomCritere.setForeground(Color.BLACK);
			}
		});
		getContentPane().add(nomCritere);

		JButton btnAjouterCritere = new JButton("Ajouter Critère");
		btnAjouterCritere.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				if ((nomCritere.getText().equals("  Critère ..."))
						|| (nomCritere.getText().equals(""))) {
					JOptionPane.showMessageDialog(null, "Entrez un critère",
							"Message d'erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					critere1.ajoutCritere(nomCritere.getText());
					listCriteres.setListData(critere1.afficherCriteres());
				}
				nomCritere.setText("");
			}
		});
		btnAjouterCritere.setBounds(41, 397, 205, 27);
		getContentPane().add(btnAjouterCritere);

		JButton btnNewButton = new JButton("Ajouter domaine");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				if (!nomDomaine.getText().equals("")) {
					a.setNomDomaine(nomDomaine.getText());
					a.creerDomaine(); // enregistrement nom domaine
					if (!critere1.getListeCriteres().isEmpty()) {
						critere1.enregistrerCriteres(a.getIdDomaine()); // enregistrement
																	// criteres
					}
					cat1.enregistrerCategoriesMotsClefs(a.getIdDomaine());

					JOptionPane.showMessageDialog(null, "Domaine ajouté",
							"Succes", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					listeComboDomaines.addItem(nomDomaine.getText());
					
				} else {
					JOptionPane.showMessageDialog(null,
							"Entrez un nom de domaine", "Message d'erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(41, 464, 205, 84);
		getContentPane().add(btnNewButton);

		JButton btnAddCategorie = new JButton("Ajouter Categorie");
		btnAddCategorie.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				if ((!nomCategorie.getText().equals("  Catégorie ..."))
						&& (!nomCategorie.getText().equals(""))) {

					listeMotsCles.setEnabled(true);
					nomMotsCles.setEnabled(true);
					btnAddMotsCles.setEnabled(true);

					cat1.ajouterCategorie(nomCategorie.getText());
					listeCategories.setListData(cat1.afficherCategories());
				} else {
					JOptionPane.showMessageDialog(null, "Entrez une catégorie",
							"Message d'erreur", JOptionPane.ERROR_MESSAGE);
				}
				nomCategorie.setText("");
			}
		});
		btnAddCategorie.setBounds(314, 521, 205, 27);
		getContentPane().add(btnAddCategorie);

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
