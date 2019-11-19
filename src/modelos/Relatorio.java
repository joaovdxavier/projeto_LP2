package modelos;

import java.util.ArrayList;

import interfaces.Ordenacao;

public class Relatorio implements Ordenacao {
	public Bem[] bensNome;
	public Bem[] bensLocalizacao;
	public Bem[] bensCategoria;
	
	/**
	 * Esse metodo recebe uma lista de bens e retorna um array ordenado por localizacao
	 * @param lista de bens 
	 * @return array ordenado por localizacao
	 */
	public Bem[] ordenaLoc(ArrayList<Bem> bens) {
		ArrayList<Bem> bens1 = new ArrayList<>(bens);
		Bem[] arrayBens = new Bem[bens1.size()];
		for(int i = 0; i < arrayBens.length; i++) 
			arrayBens[i] = bens1.get(i);
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
		return arrayBens;
	}
	
	/**
	 * Esse metodo recebe uma lista de bens e retorna um array ordenado por categoria
	 * @param lista de bens 
	 * @return array ordenado por categoria
	 */
	public Bem[] ordenaCat(ArrayList<Bem> bens) {
		ArrayList<Bem> bens1 = new ArrayList<>(bens);
		Bem[] arrayBens = new Bem[bens1.size()];
		for(int i = 0; i < arrayBens.length; i++) 
			arrayBens[i] = bens1.get(i);
		Bem aux;
		//bubble sort
		for (int i = 0; i < arrayBens.length; i++) {
			for (int j = 0; j < arrayBens.length-1; j++) {
				if (arrayBens[j].getCategoria().getNome().compareTo(arrayBens[j + 1].getCategoria().getNome()) > 0) {
					aux = arrayBens[j];
					arrayBens[j] = arrayBens[j + 1];
					arrayBens[j + 1] = aux;
				}
			}
		}
		return arrayBens;
	}

	/**
	 * Esse metodo recebe uma lista de bens e retorna um array ordenado por nome
	 * @param lista de bens 
	 * @return array ordenado por nome
	 */
	public Bem[] ordena(ArrayList<Bem> bens) {
		ArrayList<Bem> bens1 = new ArrayList<>(bens);
		Bem[] arrayBens = new Bem[bens1.size()];
		for(int i = 0; i < arrayBens.length; i++) 
			arrayBens[i] = bens1.get(i);
		Bem aux;
		//bubble sort
		for (int i = 0; i < arrayBens.length; i++) {
			for (int j = 0; j < arrayBens.length-1; j++) {
				if (arrayBens[j].getNome().compareTo(arrayBens[j + 1].getNome()) > 0) {
					aux = arrayBens[j];
					arrayBens[j] = arrayBens[j + 1];
					arrayBens[j + 1] = aux;
				}
			}
		}
		return arrayBens;
	}
	
	/**
	 * Esse metodo recebe um controlador e retorna um objeto Relatorio com arrays ordenados
	 * @param Controlador 
	 * @return objeto de relatorio
	 */
	public Relatorio gerarRelatorio(Controller c) {
		ArrayList<Bem> bens = new ArrayList<>(c.bens.values());
		Relatorio r = new Relatorio();
		r.bensLocalizacao = this.ordenaLoc(bens);
		r.bensCategoria = this.ordenaCat(bens);
		r.bensNome = this.ordena(bens);
		return r;
	}
	
	/**
	 * Esse método facilita a impressão de um objeto do tipo relatorio
	 * @return Uma string contendo os atributos do objeto
	 */
	public String toString() {
		String completa = "ORDENADO POR NOME\n";
		for(int i = 0; i < bensNome.length; i++)
			completa += bensNome[i].toString() + "\n***\n";
		
		completa += "\nORDENADO POR CATEGORIA\n";
		for(int i = 0; i < bensCategoria.length; i++)
			completa += bensCategoria[i].toString() + "\n***\n";
		
		completa += "\nORDENADO POR LOCALIZACAO\n";
		for(int i = 0; i < bensLocalizacao.length; i++)
			completa += bensLocalizacao[i].toString() + "\n***\n";
		
		return completa;
	}
}
