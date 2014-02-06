import javax.swing.JDialog;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DomaineMenu extends JDialog {
	
	//private JPanel panelEntreprise;
	
	public DomaineMenu(String selected) {
		
		this.setModal(true);
		this.setTitle("Ajout d'un nouveau domaine");
		this.getContentPane().setLayout(null);
		
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