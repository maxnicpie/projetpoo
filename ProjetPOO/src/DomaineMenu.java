import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class DomaineMenu extends JDialog {

	@SuppressWarnings("unused")
	private Statement st;

	@SuppressWarnings("unchecked")
	public DomaineMenu(final Domaine domaineSelect, final Statement st) {

		this.setModal(true);
		this.setTitle("Ajout d'un nouveau domaine");
		this.getContentPane().setLayout(null);

		JLabel lblNomDeLa = new JLabel("Nom de la catégorie :");
		lblNomDeLa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomDeLa.setBounds(34, 26, 191, 22);
		getContentPane().add(lblNomDeLa);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 109, 191, 265);
		getContentPane().add(scrollPane);

		@SuppressWarnings({ "rawtypes" })
		final JList list = new JList();
		scrollPane.setViewportView(list);

		final MotClef mot = new MotClef(st);
		final CategorieMotClef c = new CategorieMotClef(st);
		String[] categories = c.getCategories(domaineSelect.getIdDomaine());

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(295, 61, 196, 315);
		getContentPane().add(scrollPane_1);

		@SuppressWarnings({ "rawtypes" })
		final JList list_1 = new JList();
		scrollPane_1.setViewportView(list_1);

		Critere c1 = new Critere(null, st);
		String[] criteres = c1.getCriteres(domaineSelect.getIdDomaine());
		list_1.setListData(criteres);

		@SuppressWarnings({ "rawtypes" })
		final JComboBox comboBox = new JComboBox(categories);
		comboBox.setBounds(34, 59, 191, 28);
		getContentPane().add(comboBox);
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
			}
		});
		btnNewButton.setBounds(34, 394, 457, 66);
		getContentPane().add(btnNewButton);

		JLabel lblNomDuCritre = new JLabel("Nom du critère :");
		lblNomDuCritre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomDuCritre.setBounds(295, 26, 191, 22);
		getContentPane().add(lblNomDuCritre);

		JLabel lblEtOu = new JLabel("ET / OU");
		lblEtOu.setBounds(239, 186, 46, 14);
		getContentPane().add(lblEtOu);

		JButton btnNouveauDocument = new JButton("Nouveau Document");
		btnNouveauDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DocumentCreation dom = new DocumentCreation(st, domaineSelect);
				dom.setVisible(true);
			}
		});
		btnNouveauDocument.setBounds(517, 61, 220, 44);
		getContentPane().add(btnNouveauDocument);

		// this.panelEntreprise = new JPanel();
		// JFrame panelEntreprise = new JFrame();
		// frame.getContentPane().add(panelEntreprise);

		// panelEntreprise.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
		// null, null, null));
		// panelEntreprise.getContentPane().setLayout(null);
		// panelEntreprise.setBounds(10, 51, 764, 499);

		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(764, 500);
		this.setLocationRelativeTo(null);
	}
}