package utility;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public final class GuiUtility
{
	public static void setChessCrafter(ChessCrafter chessCrafter)
	{
		s_chessCrafter = chessCrafter;
	}
	
	public static ChessCrafter getChessCrafter()
	{
		if (s_chessCrafter == null)
			System.out.println("ChessCrafter object is null. Please use setChessCrafter() before calling get().");
		return s_chessCrafter;
	}
		
	public static void requestFocus(final JComponent component)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				component.requestFocus();
			}
		});
	}

	public static void setupCancelButton(JButton cancelButton, final JFrame popup)
	{
		cancelButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				popup.dispose();
			}
		});
	}

	public static void setupVariantCancelButton(JButton cancelButton, final JPanel displayPanel, final JFrame popup)
	{
		cancelButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				displayPanel.removeAll();
				popup.setVisible(false);
			}
		});
	}

	public static ImageIcon createImageIcon(int imageWidth, int imageHeight, String imageLocation) throws IOException
	{
		return createImageIcon(imageWidth, imageHeight, imageLocation, true);
	}

	public static ImageIcon createImageIcon(int imageWidth, int imageHeight, String imageLocation, boolean isBuiltInFile) throws IOException
	{
			BufferedImage bufferedImage = ImageIO.read(new File(imageLocation));

			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			imageIcon.setImage(imageIcon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH));
			return imageIcon;
	}
	
	public static boolean tryAIFileInstall(Component parent)
	{
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(parent);
		File file = fileChooser.getSelectedFile();

		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			if (!file.renameTo(FileUtility.getAIFile(file.getName())))
			{
				JOptionPane.showMessageDialog(parent, "File was not installed successfully", "Error",
						JOptionPane.PLAIN_MESSAGE);
			}
			else
			{
				return true;
			}
		}

		return false;
	}
	
	private static ChessCrafter s_chessCrafter;
}
