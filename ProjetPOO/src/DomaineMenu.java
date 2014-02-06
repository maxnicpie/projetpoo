import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class DomaineMenu extends JPanel {
	
	private JPanel panelEntreprise;
	
	public DomaineMenu(JFrame frame) {
		
    	//this.panelEntreprise = new JPanel();
		JFrame panelEntreprise = new JFrame();
    	frame.getContentPane().add(panelEntreprise);
        
        panelEntreprise.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    //panelEntreprise.getContentPane().setLayout(null);
	    panelEntreprise.setBounds(10, 51, 764, 499);
	}

	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return panelEntreprise;
	}
}