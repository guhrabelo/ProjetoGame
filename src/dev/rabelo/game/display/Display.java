package dev.rabelo.game.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	
	private String titulo;
	private int width, height;
	
	public Display(String titulo, int width, int height) {
		this.titulo = titulo;
		this.width = width;
		this.height = height;
		
		criarDisplay();
	}
	
	private void criarDisplay() {
		frame = new JFrame(titulo);
		frame.setSize(width, height); //tamanho da tela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fechar o programa ao finalizar
		frame.setResizable(true); //não permitir que o usuário altere o tamanho da tela
		frame.setLocationRelativeTo(null); // ao abrir programa fica no centro da tela
		frame.setVisible(true); //deixar visível
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height)); // tamanho do canvas
		canvas.setMaximumSize(new Dimension(width, height)); // os dois próximos obrigam a ter o tamanho setado
		canvas.setMinimumSize(new Dimension(width, height));
		
		frame.add(canvas);
		frame.pack();
	}

	public Canvas getCanvas() {
		return canvas;
	}
	
	
	
}
