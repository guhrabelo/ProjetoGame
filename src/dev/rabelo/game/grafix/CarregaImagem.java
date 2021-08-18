package dev.rabelo.game.grafix;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CarregaImagem {
	
	public static BufferedImage carregaImagem(String path) {
		try {
			return ImageIO.read(new FileInputStream(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
