import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class password extends JPanel implements ActionListener {
	
	private static String OK = "OK";
	
	private static String HELP = "Help";
	
	private JFrame frame; 
	
	private JPasswordField passwrd;

	public password(JFrame jf) {
		
		
		
		frame = jf;
		passwrd = new JPasswordField(15);
		passwrd.setActionCommand(OK);
		passwrd.addActionListener(this);
		JLabel label = new JLabel("Enter the password: ");
		label.setLabelFor(passwrd);
		JComponent bttPanel = createButtonPanel();
		JPanel txtPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		txtPanel.add(label);
		txtPanel.add(passwrd);
		add(txtPanel);
		add(bttPanel);
	}

	protected JComponent createButtonPanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		JButton okBtt = new JButton("OK");
		JButton helpBtt = new JButton("Help");
		okBtt.setActionCommand(OK);
		helpBtt.setActionCommand(HELP);
		okBtt.addActionListener(this);
		helpBtt.addActionListener(this);
		p.add(okBtt);
		p.add(helpBtt);
		return p;
	}

	public void actionPerformed(ActionEvent e) {
		String pass = e.getActionCommand();
		if (OK.equals(pass)) { 
			char[] pass1 = passwrd.getPassword();
			if (isPasswordValid(pass1)) {
				JOptionPane.showMessageDialog(frame,"Password entered is valid");
			} else {
				JOptionPane.showMessageDialog(frame,"It's not a valid password", "Error Message",JOptionPane.ERROR_MESSAGE);
			}
			
			Arrays.fill(pass1, '0');
			passwrd.selectAll();
			reset();
		} else { 
			JOptionPane.showMessageDialog(frame,"Check the Code for the password, otherwise Good Luck!");
		}
	}

	private static boolean isPasswordValid(char[] pass) {
		boolean Valid = true;
		char[] ValidPasswrd = { 'b','u','g','a','b','o','o' };
		if (pass.length != ValidPasswrd.length) {
			Valid = false;
		} else {
			Valid = Arrays.equals(pass, ValidPasswrd);
		}
		
		Arrays.fill(ValidPasswrd, '0');
		return Valid;
	}

	
	protected void reset() {
		passwrd.requestFocusInWindow();
	}

	private static void createFrame() {
		
		JFrame frame = new JFrame("PasswordDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final password newContentPane = new password(frame);
		newContentPane.setOpaque(true); 
		frame.setContentPane(newContentPane);
		frame.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				newContentPane.reset();
			}
		});
		
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createFrame();
			}
		});
	}
}

