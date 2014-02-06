import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class DomaineMenu extends JDialog {
	
	//private JPanel panelEntreprise;
	
	public DomaineMenu(Domaine domaineSelect) {
				
		this.setModal(true);
		this.setTitle("Ajout d'un nouveau domaine");
		this.getContentPane().setLayout(null);
		
		JLabel lblNomDeLa = new JLabel("Nom de la catégorie :");
		lblNomDeLa.setBounds(29, 26, 124, 22);
		getContentPane().add(lblNomDeLa);
		
		domaineSelect.getCategories(domaineSelect.getIdDomaine());
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(29, 59, 191, 22);
		getContentPane().add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 109, 191, 265);
		getContentPane().add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("Rechercher");
		btnNewButton.setBounds(29, 394, 191, 66);
		getContentPane().add(btnNewButton);
		
    	//this.panelEntreprise = new JPanel();
		//JFrame panelEntreprise = new JFrame();
    	//frame.getContentPane().add(panelEntreprise);
        
        //panelEntreprise.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    //panelEntreprise.getContentPane().setLayout(null);
	    // panelEntreprise.setBounds(10, 51, 764, 499);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(764, 500);
		this.setLocationRelativeTo(null);
	}
}