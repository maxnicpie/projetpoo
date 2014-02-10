import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class DocumentCreation extends JDialog {
	private JTextField nomDocument;
	private JTextField lienField;

	public DocumentCreation(final Statement st, final Domaine domaineSelect) {

		final CategorieMotClef c = new CategorieMotClef(st);
		String[] listeCategories = c
				.getCategories(domaineSelect.getIdDomaine());
		final MotClef mot1 = new MotClef(st);

		this.setModal(true);
		this.setTitle("Ajout d'un nouveau domaine");
		this.getContentPane().setLayout(null);

		JLabel lblNomDuDocument = new JLabel("Nom du document : ");
		lblNomDuDocument.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomDuDocument.setBounds(31, 49, 187, 34);
		getContentPane().add(lblNomDuDocument);

		nomDocument = new JTextField();
		nomDocument.setColumns(10);
		nomDocument.setBounds(77, 94, 205, 27);
		getContentPane().add(nomDocument);

		final JRadioButton radioElectronique = new JRadioButton("Electronique");
		radioElectronique.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radioElectronique.setBounds(29, 227, 109, 23);
		getContentPane().add(radioElectronique);

		JLabel lblTypeDeDocument = new JLabel("Type de document\r\n : ");
		lblTypeDeDocument.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTypeDeDocument.setBounds(29, 167, 187, 34);
		getContentPane().add(lblTypeDeDocument);

		final JRadioButton radioPapier = new JRadioButton("Papier");
		radioPapier.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radioPapier.setBounds(29, 328, 109, 23);
		getContentPane().add(radioPapier);

		lienField = new JTextField();
		lienField.setEnabled(false);
		lienField.setBounds(29, 283, 287, 23);
		getContentPane().add(lienField);
		lienField.setColumns(10);

		JLabel lblLien = new JLabel("Lien : ");
		lblLien.setBounds(29, 267, 46, 14);
		getContentPane().add(lblLien);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 358, 287, 118);
		getContentPane().add(scrollPane);

		final JTextArea commentaireField = new JTextArea();
		commentaireField.setEnabled(false);
		scrollPane.setViewportView(commentaireField);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(359, 227, 201, 259);
		getContentPane().add(scrollPane_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(582, 267, 201, 219);
		getContentPane().add(scrollPane_2);

		@SuppressWarnings("rawtypes")
		final JList listMotsCles = new JList();
		listMotsCles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(listMotsCles);

		@SuppressWarnings("rawtypes")
		final JList listeMotsClefsExistants = new JList();
		listeMotsClefsExistants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(listeMotsClefsExistants);

		radioElectronique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lienField.setEnabled(true);
				if (radioPapier.isSelected() == true) {
					radioPapier.setSelected(false);
					commentaireField.setEnabled(false);
					commentaireField.setText("");
				}
			}
		});

		radioPapier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				commentaireField.setEnabled(true);
				if (radioPapier.isSelected() == true) {
					radioElectronique.setSelected(false);
					lienField.setEnabled(false);
					lienField.setText("");
				}
			}
		});

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!nomDocument.getText().equals("")) {
					if (listMotsCles.getModel().getSize() != 0) {
						if (radioPapier.isSelected()
								|| radioElectronique.isSelected()) {
							Document doc1 = new Document(st);
							doc1.setNom(nomDocument.getText());
							doc1.creerDocument();
							doc1.affecterMotsCles(domaineSelect.getIdDomaine(),
									listMotsCles.getModel());
						} else {
							JOptionPane.showMessageDialog(null,
									"Selectionnez un type de document",
									"Message d'erreur",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Affectez des motsCles", "Message d'erreur",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Entrez un nom de document", "Message d'erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAjouter.setBounds(29, 499, 287, 47);
		getContentPane().add(btnAjouter);

		@SuppressWarnings({ "unchecked", "rawtypes" })
		final JComboBox listeCategoriesExistantes = new JComboBox(
				listeCategories);
		listeCategoriesExistantes.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent event) {
				listeMotsClefsExistants.setListData(mot1
						.getMotsClefs((String) listeCategoriesExistantes
								.getSelectedItem()));
			}
		});
		listeCategoriesExistantes.setBounds(359, 94, 196, 27);
		getContentPane().add(listeCategoriesExistantes);

		JLabel lblCategorie = new JLabel("Categorie");
		lblCategorie.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategorie.setBounds(344, 49, 187, 34);
		getContentPane().add(lblCategorie);

		JLabel lblMotsCls = new JLabel("Mots - Clés  existants :");
		lblMotsCls.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMotsCls.setBounds(344, 167, 187, 34);
		getContentPane().add(lblMotsCls);

		@SuppressWarnings("rawtypes")
		final
		JList listeMotsClefs = new JList();
		scrollPane_2.setViewportView(listeMotsClefs);

		JButton btnAffecter = new JButton("Affecter");
		btnAffecter.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				if (listeMotsClefsExistants.getSelectedValue() != null) {
					mot1.ajouterMotClef((String) listeMotsClefsExistants
							.getSelectedValue());
					listeMotsClefs.setListData(mot1.afficherListeMotsClefs());
				} else {
					JOptionPane.showMessageDialog(null,
							"Selectionnez un mot clé", "Message d'erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAffecter.setBounds(582, 227, 95, 34);
		getContentPane().add(btnAffecter);

		JButton buttonSupprimer = new JButton("Retirer");
		buttonSupprimer.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				if (listeMotsClefs.getSelectedValue() != null) {
					mot1.supprimerMotClef(listeMotsClefs.getSelectedValue());
					listeMotsClefs.setListData(mot1.afficherListeMotsClefs());
				} else {
					JOptionPane.showMessageDialog(null,
							"Selectionnez un mot clé", "Message d'erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonSupprimer.setBounds(687, 227, 96, 34);
		getContentPane().add(buttonSupprimer);

		JLabel lblMotsCls_1 = new JLabel("Mots - Clés document :");
		lblMotsCls_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMotsCls_1.setBounds(582, 167, 187, 34);
		getContentPane().add(lblMotsCls_1);

		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
	}
}
