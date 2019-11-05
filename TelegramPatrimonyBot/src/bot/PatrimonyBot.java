package bot;

import modelos.*;

public class PatrimonyBot {
	public static void main(String[] args) {
		System.out.println("TESTANDO");
		Controller c = new Controller();
		c.cadastrarCategoria(0, "Movel", "cadeiras, mesas");
		c.cadastrarCategoria(1, "Automovel", "carros, avioes");
		c.cadastrarCategoria(2, "Brinquedo", "carrinhos, bonecas");
		
		c.cadastrarLocalizacao("Porto da Barra", "ao lado da barra");
		c.cadastrarLocalizacao("Rainha do pastel", "blabla");
		c.cadastrarLocalizacao("Cariri", "aa");
		c.cadastrarLocalizacao("Rosangela", "crra");
		c.cadastrarLocalizacao("Besteira", "ao lado da barsadasra");
		
		c.cadastrarBem(0, "Fiat Uno", "#meucarango", "Porto da Barra", 1);
		c.cadastrarBem(1, "Max Steel", "boneco de movimento", "Rosangela", 2);
		c.cadastrarBem(2, "Maquina de Suco", "faz suco", "Rainha do Pastel", 0);
		c.cadastrarBem(3, "Mesa de vovo", "mesa italiana", "Cariri", 0);
		c.cadastrarBem(4, "Cama de Pai", "cama de pai dormir", "Cariri", 0);
		
		Relatorio r = new Relatorio();
		Relatorio criado = r.gerarRelatorio(c);
		
		System.out.println("Ordenados por Localizacao");
		for(int i = 0; i < criado.bensLocalizacao.length; i++) {
			System.out.println(criado.bensLocalizacao[i]);
		}
		System.out.println();
		
		System.out.println("Ordenados por Categoria");
		for(int i = 0; i < criado.bensCategoria.length; i++) {
			System.out.println(criado.bensCategoria[i]);
		}
		System.out.println();
		
		System.out.println("Ordenados por Nome");
		for(int i = 0; i < criado.bensNome.length; i++) {
			System.out.println(criado.bensNome[i]);
		}
		System.out.println();
	}
}
