package dev.rabelo.game.states;

import java.awt.Graphics;

public abstract class Estado {
	
	private static Estado estadoAtual = null;

	
	public static Estado getEstado() {
		return estadoAtual;
	}
	
	public static void setEstado(Estado estado) {
		estadoAtual = estado;
	}
	
	public abstract void marcacao();
	
	public abstract void render(Graphics graphics);
	
}
