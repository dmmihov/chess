package utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.google.common.collect.Lists;

public final class ImageUtility
{
	public static void writeLightImage(String pieceName, BufferedImage image) throws Exception
	{
		if (image == null)
			throw new Exception();
		ImageIO.write(image, "PNG", new File(FileUtility.getImagePath(LIGHT_PREFIX + pieceName + PNG, false)));
	}

	public static void writeDarkImage(String pieceName, BufferedImage image) throws Exception
	{
		if (image == null)
			throw new Exception();
		ImageIO.write(image, "PNG", new File(FileUtility.getImagePath(DARK_PREFIX + pieceName + PNG, false)));
	}

	public static ImageIcon getLightImage(String pieceName) throws IOException
	{
		boolean isBuiltInFile = PIECE_NAMES.contains(pieceName);
		return GuiUtility.createImageIcon(48, 48, FileUtility.getImagePath(LIGHT_PREFIX + pieceName + PNG, isBuiltInFile), isBuiltInFile);
	}

	public static ImageIcon getDarkImage(String pieceName)
	{
		boolean isBuiltInFile = PIECE_NAMES.contains(pieceName);
		try
		{
			return GuiUtility.createImageIcon(48, 48, FileUtility.getImagePath(DARK_PREFIX + pieceName + PNG, isBuiltInFile), isBuiltInFile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	private static final String LIGHT_PREFIX = "l_";
	private static final String DARK_PREFIX = "d_";
	private static final String PNG = ".png";
	private static final List<String> PIECE_NAMES = Lists.newArrayList("Pawn", "Rook", "Bishop", "Knight", "Queen", "King");
}
