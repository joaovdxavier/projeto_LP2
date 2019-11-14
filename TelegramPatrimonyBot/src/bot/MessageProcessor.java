package bot;

import java.util.ArrayList;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import modelos.Bem;
import modelos.Categoria;
import modelos.Controller;
import modelos.Localizacao;

public class MessageProcessor {
	Controller c = new Controller();
	Bem b = new Bem();
	Localizacao l = new Localizacao();
	Categoria cat = new Categoria();
	private long chat_id;
	private BotState state;
	

	public String iniciar() {
		return "Ola, eu sou o Bob Patrimonio e vou te ajudar a administrar os seus bens. O que deseja fazer?";
	}

	public String cadastroBemBot() {
		return "Digite os dados do bem (nome, categoria, descricao, localizacao)";
	}
	
	public MessageProcessor(long chat_id) {
		this.chat_id = chat_id;
		this.state = BotState.idle;
	}
	
	public Object[] update(Update update) {
		String lastCommand = update.getMessage().getText();
		SendMessage message = new SendMessage();
		System.out.println("State atual: " + this.state);
		if (update.getMessage().getText().equals("/start")) {
			message.setText(this.iniciar());
			this.state = BotState.waitNewCommand;
		}
		if (this.state == BotState.waitNewCommand) {
			if (lastCommand.equals("/cadbem")) {
				this.state = BotState.cadastrarBem;
			} else if (lastCommand.equals("/cadlocalizacao")) {
				this.state = BotState.cadastrarLocalizacao;
			} else if (lastCommand.equals("/cadcategoria")) {
				this.state = BotState.cadastrarCategoria;
			} else if (lastCommand.equals("/listabemloc")) {
				message.setText("Me diz em qual localizacao seu bem esta ;)");
				this.state = BotState.listandoBemLoc;
			}else if(lastCommand.equals("/listacat")) { 
				this.state = BotState.listandoCat;
				message.setText("Aqui vao as categorias cadastradas ate o momento");
			}else if (lastCommand.equals("/listaloc")) {
				this.state = BotState.listandoLoc;
			} else if (lastCommand.equals("/buscarbemcodigo")) {
				this.state = BotState.buscarBemCod;
			} else if (lastCommand.equals("/buscarbemnome")) {
				this.state = BotState.buscarBemNome;
			} else if (lastCommand.equals("/buscarbemdescricao")) {
				this.state = BotState.buscarBemDescription;
			} else if (lastCommand.equals("/movimentarbem")) {
				this.state = BotState.movimentandoBem;
			} else if (lastCommand.equals("/gerarrelatorio")) {
				this.state = BotState.gerandoRelatorio;
			} else if (lastCommand.equals("/finalizar")) {
				this.state = BotState.finalize;
			}else {
				message.setText("Nao entendi o que voce quer fazer! Digite novamente.");
			}
		}else if (this.state == BotState.cadastrarBem) {
			message.setText("Digite o codigo do bem");
			this.state = BotState.waitingBemCodigo;
		} else if (this.state == BotState.cadastrarCategoria) {
			message.setText("Digite o codigo da categoria");
			this.state = BotState.waitingCatCodigo;
		} else if (this.state == BotState.cadastrarLocalizacao) {
			message.setText("Digite o nome da localizacao");
			this.state = BotState.waitingLocName;
		} else if (this.state == BotState.waitingBemCodigo) {
			Bem buscado = c.buscarBem(Integer.parseInt(lastCommand));
			if (buscado == null) {
				b.setCodigo(Integer.parseInt(lastCommand));
				message.setText("Digite o nome do bem");
				this.state = BotState.waitingBemNome;
			} else {
				message.setText("Ja existe um bem cadastrado com esse codigo. O nome dele eh " + buscado.getNome());
			}
		} else if (this.state == BotState.waitingBemNome) {
			message.setText("Digite a descricao do bem");
			b.setNome(lastCommand);
			this.state = BotState.waitingBemDescription;
		} else if (this.state == BotState.waitingBemDescription) {
			b.setDescricao(lastCommand);
			message.setText("Digite a localizacao do bem");
			this.state = BotState.waitingBemLoc;
		} else if (this.state == BotState.waitingBemLoc) {
			Localizacao loc = c.buscarLocalizacao(lastCommand);
			if (loc != null) {
				b.setLocalizacao(loc);
				message.setText("Digite a categoria do bem");
				this.state = BotState.waitingBemCat;
			} else {
				message.setText("Essa localizacao nao existe nos meus registros");
			}
		} else if (this.state == BotState.waitingBemCat) {
			Categoria cate = c.buscarCategoria(Integer.parseInt(lastCommand));
			if (cate != null) {
				b.setCategoria(cate);
				message.setText("Ok, seu bem foi cadastrado!");
				this.state = BotState.waitNewCommand;
				c.cadastrarBem(b.getCodigo(), b.getNome(), b.getDescricao(), b.getLocalizacao().getNome(),
						b.getCategoria().getCodigo());
			} else {
				message.setText("Essa categoria nao existe nos meus registros");
			}
		} else if (this.state == BotState.waitingLocName) {
			Localizacao loc = c.buscarLocalizacao(lastCommand);
			if (loc == null) {
				l.setNome(lastCommand);
				message.setText("Digite a descricao da localizacao");
				this.state = BotState.waitingLocDescription;
			} else {
				message.setText("Ja existe uma localizacao com esse nome nos meus registros");
			}
		} else if (this.state == BotState.waitingLocDescription) {
			l.setDescricao(lastCommand);
			this.state = BotState.waitNewCommand;
			message.setText("Ok, sua localizacao foi cadastrada!");
			c.cadastrarLocalizacao(l.getNome(), l.getDescricao());
		} else if (this.state == BotState.waitingCatCodigo) {
			Categoria cat = c.buscarCategoria(Integer.parseInt(lastCommand));
			if (cat == null) {
				this.cat.setCodigo(Integer.parseInt(lastCommand));
				message.setText("Digite o nome da categoria");
				this.state = BotState.waitingCatNome;
			} else {
				message.setText("Essa categoria ja existe nos meus registros");
			}
		} else if (this.state == BotState.waitingCatNome) {
			this.cat.setNome(lastCommand);
			message.setText("Digite a descricao da categoria");
			this.state = BotState.waitingCatDescription;
		} else if (this.state == BotState.waitingCatDescription) {
			this.cat.setDescricao(lastCommand);
			message.setText("Essa categoria acaba de entrar nos meus registros! :)");
			c.cadastrarCategoria(cat.getCodigo(), cat.getNome(), cat.getDescricao());
			this.state = BotState.waitNewCommand;
		} else if(this.state == BotState.listandoBemLoc) {
			ArrayList<Bem> bens = c.listarBem(lastCommand);
			ArrayList<String> bens_string = new ArrayList<>();
			if(bens != null && !bens.isEmpty()){
				message.setText("Ok, aqui estao os bens em "+lastCommand);
				bens.forEach(b -> bens_string.add(b.getNome()));
				message.setText(String.valueOf(bens_string));
			}else {
				message.setText("Nao ha bens cadastrados nessa localizacao");
			}
			this.state = BotState.waitNewCommand;
		} else if(this.state == BotState.listandoCat) {
			ArrayList<Categoria> cats = c.listarCategorias();
			ArrayList<String> catss = new ArrayList<>();
			if(!cats.isEmpty()) {
				cats.forEach(d -> catss.add(d.getNome()));
				message.setText(String.valueOf(catss));
			}else {
				message.setText("Nao tem categorias cadastradas no momento. :(");
			}
			
			this.state = BotState.waitNewCommand;
		}
		message.setChatId(update.getMessage().getChatId());
		
		Object []objetos = {this.state, message};
		return objetos;
	}
	
	public long getChatId() {
		return this.chat_id;
	}
}
