package ConexionDB;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagenFondo extends JPanel {

	public ImagenFondo() {
		this.setSize(650, 420);
	}

	@Override
	public void paint(Graphics g) {
		Dimension tamanio = getSize();
		ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/Imagenes/Logo.jpg"));
		g.drawImage(imagenFondo.getImage(), 0, 0, tamanio.width, tamanio.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}