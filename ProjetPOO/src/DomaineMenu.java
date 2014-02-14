import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class DomaineMenu extends JPanel {
	
	private JPanel panelDomaine;

	@SuppressWarnings("unused")
	private Statement st;

	@SuppressWarnings("unchecked")
	public DomaineMenu(JFrame frame, final Domaine domaineSelect, final Statement st) {

		this.panelDomaine = new JPanel();
    	frame.getContentPane().add(panelDomaine);
		
		panelDomaine.setLayout(null);

		JLabel lblNomDeLa = new JLabel("Nom de la catégorie :");
		lblNomDeLa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomDeLa.setBounds(34, 26, 191, 22);
		panelDomaine.add(lblNomDeLa);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 109, 191, 265);
		panelDomaine.add(scrollPane);

		@SuppressWarnings({ "rawtypes" })
		final JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		

		final MotClef mot = new MotClef(st);
		final CategorieMotClef c = new CategorieMotClef(st);
		String[] categories = c.getCategories(domaineSelect.getIdDomaine());

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(295, 61, 196, 315);
		panelDomaine.add(scrollPane_1);

		@SuppressWarnings({ "rawtypes" })
		final JList list_1 = new JList();
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(list_1);

		Critere c1 = new Critere(null, st);
		String[] criteres = c1.getCriteres(domaineSelect.getIdDomaine());
		list_1.setListData(criteres);

		@SuppressWarnings({ "rawtypes" })
		final JComboBox comboBox = new JComboBox(categories);
		comboBox.setBounds(34, 59, 191, 28);
		panelDomaine.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comboBox.getSelectedItem().toString()
						.equals("Categorie...")) {
					String[] motscles = mot.getMotsClefs(comboBox
							.getSelectedItem().toString());
					list.setListData(motscles);
				}

			}
		});

		JButton btnNewButton = new JButton("Rechercher");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!list.isSelectionEmpty()){
					Recherche recherche = new Recherche(st,(String) list.getSelectedValue(),false);
					recherche.setVisible(true);
				}
				else{
					JOptionPane.showMessageDialog(null,
							"Selectionnez un mot clé !", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(34, 394, 191, 66);
		panelDomaine.add(btnNewButton);

		JLabel lblNomDuCritre = new JLabel("Nom du critère :");
		lblNomDuCritre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomDuCritre.setBounds(295, 26, 191, 22);
		panelDomaine.add(lblNomDuCritre);

		JLabel lblEtOu = new JLabel("OU");
		lblEtOu.setBounds(248, 186, 25, 14);
		panelDomaine.add(lblEtOu);

		JButton btnNouveauDocument = new JButton("Nouveau Document");
		btnNouveauDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DocumentCreation dom = new DocumentCreation(st, domaineSelect);
				dom.setVisible(true);
			}
		});
		btnNouveauDocument.setBounds(517, 61, 220, 44);
		panelDomaine.add(btnNouveauDocument);

		// this.panelEntreprise = new JPanel();
		// JFrame panelEntreprise = new JFrame();
		// frame.getContentPane().add(panelEntreprise);

		// panelEntreprise.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
		// null, null, null));
		// panelEntreprise.getContentPane().setLayout(null);
		// panelEntreprise.setBounds(10, 51, 764, 499);

		panelDomaine.setSize(764, 499);
		panelDomaine.setBounds(10, 51, 764, 499);
		
		JButton button = new JButton("Rechercher");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!list_1.isSelectionEmpty()){
					Recherche recherche = new Recherche(st,(String) list_1.getSelectedValue(),true);
					recherche.setVisible(true);
				}
				else{
					JOptionPane.showMessageDialog(null,
							"Selectionnez un critère !", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(295, 394, 196, 66);
		panelDomaine.add(button);
	}

	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return panelDomaine;
	}
}
