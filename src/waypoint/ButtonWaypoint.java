package waypoint;

import java.awt.Cursor;
import java.awt.Dimension;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonWaypoint extends JButton {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ButtonWaypoint() {
        setContentAreaFilled(false);
        // Provide the absolute path to the icon file
        File iconFile = new File("C:\\Users\\doanv\\Downloads\\BTL3\\ImagesResource\\ImagesResource\\icon\\pin.png");
        String absolutePath = iconFile.getAbsolutePath();
        setIcon(new ImageIcon(absolutePath));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setSize(new Dimension(24, 24));
    }
}
