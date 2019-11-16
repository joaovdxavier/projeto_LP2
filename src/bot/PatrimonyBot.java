package bot;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import json.JSONWriter;
import modelos.*;

public class PatrimonyBot extends TelegramLongPollingBot {
	Controller c = new Controller();
	Bem b = new Bem();
	Localizacao l = new Localizacao();
	Categoria cat = new Categoria();
	private BotState state = BotState.idle;
	Bem newBem;
	int cod;

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {

			String lastCommand = update.getMessage().getText();
			SendMessage message = new SendMessage();
			System.out.println("State atual: " + this.state);
			if (lastCommand.equals("/start")) {
				message.setText("Ola, eu sou o Bob Patrimonio e vou te ajudar a administrar os seus bens. O que deseja fazer?");
				this.state = BotState.waitNewCommand;
			}else if(lastCommand.equals("cancelar")) {
				this.state = BotState.waitNewCommand;
			}else if (this.state == BotState.waitNewCommand) {
				if (lastCommand.equals("/cadbem")) {
					this.state = BotState.cadastrarBem;
				} else if (lastCommand.equals("/cadlocalizacao")) {
					this.state = BotState.cadastrarLocalizacao;
				} else if (lastCommand.equals("/cadcategoria")) {
					this.state = BotState.cadastrarCategoria;
				} else if (lastCommand.equals("/listabemloc")) {
					message.setText("Me diz em qual localizacao seu bem esta ;)");
					this.state = BotState.listandoBemLoc;
				} else if (lastCommand.equals("/listacat")) {
					this.state = BotState.listandoCat;
					message.setText("Aqui vao as categorias cadastradas ate o momento");
				} else if (lastCommand.equals("/listaloc")) {
					this.state = BotState.listandoLoc;
				} else if (lastCommand.equals("/buscarbemcodigo")) {
					this.state = BotState.buscarBemCod;
				} else if (lastCommand.equals("/buscarbemnome")) {
					this.state = BotState.buscarBemNome;
				} else if (lastCommand.equals("/buscarbemdescricao")) {
					this.state = BotState.buscarBemDescription;
				} else if (lastCommand.equals("/movimentarbem")) {
					message.setText("Digite o codigo do bem");
					this.state = BotState.movimentandoBemWaitingNome;
				} else if (lastCommand.equals("/gerarrelatorio")) {
					message.setText("Seus bens ordenados por localizacao, por categoria e por nome");
					this.state = BotState.gerandoRelatorio1;
				} else if (lastCommand.equals("/finalizar")) {
					this.state = BotState.finalize;
				} else {
					message.setText("Nao entendi o que voce quer fazer! Digite novamente.");
				}
			} else if (this.state == BotState.cadastrarBem) {
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
			} else if (this.state == BotState.listandoBemLoc) {
				ArrayList<Bem> bens = c.listarBem(lastCommand);
				ArrayList<String> bens_string = new ArrayList<>();
				if (bens != null && !bens.isEmpty()) {
					message.setText("Ok, aqui estao os bens em " + lastCommand);
					bens.forEach(b -> bens_string.add(b.getNome()));
					message.setText(String.valueOf(bens_string));
				} else {
					message.setText("Nao ha bens cadastrados nessa localizacao");
				}
				this.state = BotState.waitNewCommand;
			} else if (this.state == BotState.listandoCat) {
				ArrayList<Categoria> cats = c.listarCategorias();
				ArrayList<String> catss = new ArrayList<>();
				if (!cats.isEmpty()) {
					cats.forEach(d -> catss.add(d.getNome()));
					message.setText(String.valueOf(catss));
				} else {
					message.setText("Nao tem categorias cadastradas no momento. :(");
				}
				this.state = BotState.waitNewCommand;
			}else if(this.state == BotState.listandoLoc) {
				ArrayList<Localizacao> locs = c.listarLocalizacao();
				ArrayList<String> locss = new ArrayList<>();
				if (!locs.isEmpty()) {
					locs.forEach(d -> locss.add(d.getNome()));
					message.setText(String.valueOf(locss));
				} else {
					message.setText("Nao tem localizacoes cadastradas no momento. :(");
				}
				this.state = BotState.waitNewCommand;
			}else if (this.state == BotState.movimentandoBemWaitingNome) {
				this.cod = Integer.parseInt(lastCommand);
				newBem = c.buscarBem(cod);
				if(newBem != null) {
					message.setText("Ok, seu bem foi encontrado! Fala pra mim a nova localizacao dele.");
					this.state = BotState.movimentandoBemWaitingLoc;
				}else {
					message.setText("Nao encontrei nenhum bem com esse codigo nos meus registros");
				}
			}else if(this.state == BotState.movimentandoBemWaitingLoc) {
				Localizacao loc = c.buscarLocalizacao(lastCommand);
				if(loc != null) {
					message.setText("Ok, to substituindo "+newBem.getLocalizacao().getNome()+" por "+lastCommand);
					newBem.setLocalizacao(loc);
					c.putBem(newBem);
					this.state = BotState.waitNewCommand;
				}else {
					message.setText("Nao encontrei essa localizacao nos meus registros...");
				}
			}else if(this.state == BotState.gerandoRelatorio1) {
				Relatorio r = new Relatorio();
				r = r.gerarRelatorio(c);
				message.setText(String.valueOf(r.bensLocalizacao) + "\n" + String.valueOf(r.bensCategoria) +"\n"+String.valueOf(r.bensNome));
				this.state = BotState.waitNewCommand;
			} else if (this.state == BotState.buscarBemCod) {
				int cod = Integer.parseInt(lastCommand);
				Bem b = c.buscarBem(cod);
				if(b != null) {
					message.setText(b.toString());
					this.state = BotState.waitNewCommand;
				}else {
					message.setText("Nao encontrei seu bem :(");
				}
			}else if(this.state == BotState.buscarBemNome) {
				ArrayList<Bem> bens = c.buscarBem(lastCommand, 1);
				message.setText(String.valueOf(bens));
				this.state = BotState.waitNewCommand;
			}else if(this.state == BotState.buscarBemDescription) {
				ArrayList<Bem> bens = c.buscarBem(lastCommand, 2);
				message.setText(String.valueOf(bens));
				this.state = BotState.waitNewCommand;
			}
			message.setChatId(update.getMessage().getChatId());

			try {
				execute(message);
			} catch (TelegramApiException e) {
				
			}
		}
	}

	@Override
	public String getBotUsername() {
		return "PatrimonyBot";
	}

	@Override
	public String getBotToken() {
		return "890497092:AAGCCeXTN41Q_YxUsgkV0P_tQg-pgLR2b6A";
	}
}
