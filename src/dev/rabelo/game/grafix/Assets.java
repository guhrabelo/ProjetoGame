package dev.rabelo.game.grafix;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 68, height = 99;
	
	public static BufferedImage jogador, jogador1,jogador2;
	
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(CarregaImagem.carregaImagem("resources/texturas/x-base-mov2.png"));
		
		jogador = sheet.corte( 1, 1, width, height);
		jogador1 = sheet.corte(width, 0, width, height);
		jogador2 = sheet.corte(width*2, 0, width, height);
	}
	
}
