package modelos;

import java.util.ArrayList;

public interface Ordenacao {
	public ArrayList<Bem> ordenaLoc(ArrayList<Bem> bens);
	public ArrayList<Bem> ordenaCat(ArrayList<Bem> bens);
	public ArrayList<Bem> ordena(ArrayList<Bem> bens);
}
