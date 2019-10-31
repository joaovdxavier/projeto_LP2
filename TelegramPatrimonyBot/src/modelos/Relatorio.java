package modelos;

import java.util.ArrayList;

public class Relatorio implements Ordenacao {
	public ArrayList<Bem> bensNome;
	public ArrayList<Bem> bensLocalizacao;
	public ArrayList<Bem> bensCategoria;

	// ArrayList<Categoria> categorias;
	// ArrayList<Localizacao> localizacoes;

	public ArrayList<Bem> ordenaLoc(ArrayList<Bem> bens) {
		ArrayList<Bem> bens1 = new ArrayList<>();
		Bem[] arrayBens = {};
		bens1.toArray(arrayBens);

		Bem aux;
		//bubble sort
		for (int i = 0; i < arrayBens.length; i++) {
			for (int j = 0; j < arrayBens.length-1; j++) {
				if (arrayBens[j].getLocalizacao().getNome().compareTo(arrayBens[j + 1].getLocalizacao().getNome()) > 0) {
					aux = arrayBens[j];
					arrayBens[j] = arrayBens[j + 1];
					arrayBens[j + 1] = aux;
				}
			}
		}
		for(int i = 0; i < arrayBens.length; i++) {
			bens1.add(arrayBens[i]);
		}
		return bens1;
	}

	public ArrayList<Bem> ordenaCat(ArrayList<Bem> bens) {
		ArrayList<Bem> bens2 = new ArrayList<>();

		return bens2;
	}

	public ArrayList<Bem> ordena(ArrayList<Bem> bens) {
		ArrayList<Bem> bens3 = new ArrayList<>();

		return bens3;
	}

	public Relatorio gerarRelatorio(Controller c) {
		ArrayList<Bem> bens = new ArrayList<>(c.bens.values());
		Relatorio r = new Relatorio();
		r.bensLocalizacao = this.ordenaLoc(bens);
		//r.bensCategoria = this.ordenaCat(bens);
		//r.bensNome = this.ordena(bens);

		return r;
	}
}
