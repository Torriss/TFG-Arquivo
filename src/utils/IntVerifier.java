package utils;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class IntVerifier extends InputVerifier {
	@Override
	public boolean verify(JComponent input) {
		String num = ((JTextField)input).getText();
		try {
			Integer.parseInt(num);
	        return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,
					"Formato no válido, por favor, introduzca un número", "",
					JOptionPane.ERROR_MESSAGE);
			return false;
	    }
	}
}
