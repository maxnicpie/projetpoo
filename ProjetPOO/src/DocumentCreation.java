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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class DocumentCreation extends JDialog {
	private JTextField nomDocument;
	private JTextField lienField;
	private Statement sta;

	public DocumentCreation(final Statement st, final Domaine domaineSelect) {

		this.sta=st;
		final CategorieMotClef c = new CategorieMotClef(st);
		String[] listeCategories = c
				.getCategories(domaineSelect.getIdDomaine());
		final MotClef mot1 = new MotClef(st);
		Critere critere = new Critere(st);
		
		String[] listeCriteres = critere.getCriteres(domaineSelect.getIdDomaine());

		this.setModal(true);
		this.setTitle("Ajout d'un nouveau document");
		this.getContentPane().setLayout(null);

		JLabel lblNomDuDocument = new JLabel("Nom du document : ");
		lblNomDuDocument.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomDuDocument.setBounds(32, 11, 187, 34);
		getContentPane().add(lblNomDuDocument);

		nomDocument = new JTextField();
		nomDocument.setColumns(10);
		nomDocument.setBounds(32, 56, 285, 27);
		getContentPane().add(nomDocument);

		final JRadioButton radioElectronique = new JRadioButton("Electronique");
		radioElectronique.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radioElectronique.setBounds(30, 189, 109, 23);
		getContentPane().add(radioElectronique);

		JLabel lblTypeDeDocument = new JLabel("Type de document\r\n : ");
		lblTypeDeDocument.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTypeDeDocument.setBounds(30, 129, 187, 34);
		getContentPane().add(lblTypeDeDocument);

		final JRadioButton radioPapier = new JRadioButton("Papier");
		radioPapier.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radioPapier.setBounds(30, 290, 109, 23);
		getContentPane().add(radioPapier);

		lienField = new JTextField();
		lienField.setEnabled(false);
		lienField.setBounds(30, 245, 287, 23);
		getContentPane().add(lienField);
		lienField.setColumns(10);

		JLabel lblLien = new JLabel("Lien : ");
		lblLien.setBounds(30, 229, 46, 14);
		getContentPane().add(lblLien);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 320, 287, 128);
		getContentPane().add(scrollPane);

		final JTextArea commentaireField = new JTextArea();
		commentaireField.setEnabled(false);
		scrollPane.setViewportView(commentaireField);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(360, 189, 201, 259);
		getContentPane().add(scrollPane_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(583, 229, 201, 219);
		getContentPane().add(scrollPane_2);
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final JComboBox comboCriteres = new JComboBox(listeCriteres);
		comboCriteres.setBounds(406, 518, 196, 27);
		getContentPane().add(comboCriteres);

		@SuppressWarnings("rawtypes")
		final JList listeMotsClefs = new JList();
		listeMotsClefs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(listeMotsClefs);

		@SuppressWarnings("rawtypes")
		final JList listeMotsClefsExistants = new JList();
		listeMotsClefsExistants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(listeMotsClefsExistants);
		
		final JSpinner note = new JSpinner();
		note.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		note.setBounds(650, 518, 46, 27);
		getContentPane().add(note);

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
					if (listeMotsClefs.getModel().getSize() != 0) {
						if (radioPapier.isSelected()
								|| radioElectronique.isSelected()) {
							if((Integer) note.getValue()!=0){
								Document doc1 = new Document(sta);
								doc1.setNom(nomDocument.getText());
								doc1.creerDocument();
								doc1.affecterMotsCles(doc1.getIdDocument(),
										listeMotsClefs.getModel());

								if (radioPapier.isSelected()) {
									PaperDocument paper = new PaperDocument(
											doc1.getNom(), sta);
									paper.setCommentaire(commentaireField
											.getText());
									paper.enregistrerPapier(doc1
											.getIdDocument());
								} else {
									ElectronicDocument electronique = new ElectronicDocument(
											doc1.getNom(), sta);
									electronique.setLink(lienField.getText());
									electronique.enregistrerElectronique(doc1
											.getIdDocument());
								}
								
								Note note1 = new Note(sta,(Integer) note.getValue());
								note1.enregistrerNote((String) comboCriteres.getSelectedItem(),doc1.getIdDocument());

								JOptionPane.showMessageDialog(null,
										"Ajout du document réussi", "Succès",
										JOptionPane.INFORMATION_MESSAGE);
							}
							else{
								JOptionPane.showMessageDialog(null,
										"Affecter une note au document par critère",
										"Message d'erreur",
										JOptionPane.ERROR_MESSAGE);
							}
							
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
		btnAjouter.setBounds(30, 488, 287, 57);
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
		listeCategoriesExistantes.setBounds(360, 56, 201, 27);
		getContentPane().add(listeCategoriesExistantes);

		JLabel lblCategorie = new JLabel("Categorie :");
		lblCategorie.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategorie.setBounds(345, 11, 187, 34);
		getContentPane().add(lblCategorie);

		JLabel lblMotsCls = new JLabel("Mots - Clés  existants :");
		lblMotsCls.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMotsCls.setBounds(345, 129, 187, 34);
		getContentPane().add(lblMotsCls);

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
		btnAffecter.setBounds(583, 189, 95, 34);
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
		buttonSupprimer.setBounds(688, 189, 96, 34);
		getContentPane().add(buttonSupprimer);

		JLabel lblMotsCls_1 = new JLabel("Mots - Clés document :");
		lblMotsCls_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMotsCls_1.setBounds(583, 129, 187, 34);
		getContentPane().add(lblMotsCls_1);
		
		JLabel lblNoteDuDocument = new JLabel("Note du document :");
		lblNoteDuDocument.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNoteDuDocument.setBounds(391, 473, 187, 34);
		getContentPane().add(lblNoteDuDocument);

		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
	}
}
