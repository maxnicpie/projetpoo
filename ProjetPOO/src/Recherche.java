import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Recherche extends JDialog {
	
	private Statement sta;
	
	public Recherche(final Statement st, String selectedValue, boolean critere) {

		this.sta=st;
		this.setModal(true);
		this.setTitle("Resultat de la recherche");
		this.getContentPane().setLayout(null);
		
		String[] recherche;

		Document doc = new Document(sta);
		if(critere){
			Critere c1 = new Critere(selectedValue,st);
			recherche = doc.getDocumentByCritere(c1);
		}
		else{
			MotClef mot = new MotClef(selectedValue,st);
			recherche = doc.getDocumentByMotClef(mot);
		}
		
		if(recherche.length == 0){
			recherche[0] = "Aucun document trouvé";
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 294, 371);
		getContentPane().add(scrollPane);
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JList list = new JList(recherche);
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBounds(70, 382, 165, 28);
		getContentPane().add(btnNewButton);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(300, 450);
		this.setLocationRelativeTo(null);
	}
}
