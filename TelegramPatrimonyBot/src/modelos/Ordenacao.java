package modelos;

import java.util.ArrayList;

public interface Ordenacao {
	/*
	 * Interface implementada pela classe Relatorio
	 */
	public Bem[] ordenaLoc(ArrayList<Bem> bens);
	public Bem[] ordenaCat(ArrayList<Bem> bens);
	public Bem[] ordena(ArrayList<Bem> bens);
}
