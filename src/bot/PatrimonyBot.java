package bot;

import java.util.ArrayList;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import exceptions.*;
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
			if (lastCommand.equals("/start")) {
				message.setText("Olá, eu sou o Bob Patrimônio e vou te ajudar a administrar os seus bens. O que deseja fazer?");
				this.state = BotState.waitNewCommand;
			}else if(lastCommand.equals("cancelar")) {
				message.setText("Ok, vou parar de fazer o que eu estava fazendo.");
				this.state = BotState.waitNewCommand;
			}else if (this.state == BotState.waitNewCommand) {
				if (lastCommand.equals("/cadbem")) {
					message.setText("Me diz o código do seu bem.");
					this.state = BotState.waitingBemCodigo;
				} else if (lastCommand.equals("/cadlocalizacao")) {
					message.setText("Me diz o nome da localização");
					this.state = BotState.waitingLocName;
				} else if (lastCommand.equals("/cadcategoria")) {
					message.setText("Me diz o código da sua categoria");
					this.state = BotState.waitingCatCodigo;
				} else if (lastCommand.equals("/listabemloc")) {
					message.setText("Me diz em qual localização seu bem está");
					this.state = BotState.listandoBemLoc;
				} else if (lastCommand.equals("/listacat")) {
					ArrayList<Categoria> cats = c.listarCategorias();
					String catss = "";
					if (!cats.isEmpty()) {
						catss += "Ok, aqui estão suas categorias cadastradas\n";
						for(int i = 0; i < cats.size(); i++) {
							catss += cats.get(i).toString()+"\n***\n";
						}
						message.setText(catss);
					} else {
						message.setText("Não tem categorias cadastradas no momento. :(");
					}
					this.state = BotState.waitNewCommand;
				} else if (lastCommand.equals("/listaloc")) {
					ArrayList<Localizacao> locs = c.listarLocalizacao();
					String locss = "";
					if (!locs.isEmpty()) {
						locss += "Ok, aqui estão suas localizações cadastradas\n";
						for(int i = 0; i < locs.size(); i++) {
							locss += locs.get(i).toString()+"\n***\n";
						}
						message.setText(locss);
					} else {
						message.setText("Não tem localizações cadastradas no momento. :(");
					}
					this.state = BotState.waitNewCommand;
				} else if (lastCommand.equals("/buscabemcod")) {
					message.setText("Digite o código do bem que você quer procurar");
					this.state = BotState.buscarBemCod;
				} else if (lastCommand.equals("/buscabemnome")) {
					message.setText("Digite o nome do bem que você quer procurar");
					this.state = BotState.buscarBemNome;
				} else if (lastCommand.equals("/buscabemdes")) {
					message.setText("Digite a descrição do bem que você quer procurar");
					this.state = BotState.buscarBemDescription;
				} else if (lastCommand.equals("/movimentabem")) {
					message.setText("Digite o código do bem");
					this.state = BotState.movimentandoBemWaitingNome;
				} else if (lastCommand.equals("/gerarrelatorio")) {
					Relatorio r = new Relatorio();
					r = r.gerarRelatorio(c);
					message.setText("Seus bens ordenados por nome, categoria e localização:\n"+r.toString());
					this.state = BotState.waitNewCommand;
				} else {
					message.setText("Não entendi o que voce quer fazer! Digite novamente.");
				}
			} else if (this.state == BotState.waitingBemCodigo) {
				Bem buscado = c.buscarBem(Integer.parseInt(lastCommand));
				try {
					if (buscado == null) {
						b.setCodigo(Integer.parseInt(lastCommand));
						message.setText("Digite o nome do bem");
						this.state = BotState.waitingBemNome;
					} else {
						throw new BemDuplicadoException();
					}
				}catch (BemDuplicadoException e){
					message.setText(e.getMessage());
				}
			} else if (this.state == BotState.waitingBemNome) {
				message.setText("Digite a descrição do bem");
				b.setNome(lastCommand);
				this.state = BotState.waitingBemDescription;
				
			} else if (this.state == BotState.waitingBemDescription) {
				b.setDescricao(lastCommand);
				message.setText("Digite a localização do bem");
				this.state = BotState.waitingBemLoc;
				
			} else if (this.state == BotState.waitingBemLoc) {
				Localizacao loc = c.buscarLocalizacao(lastCommand);
				try {
					if (loc != null) {
						b.setLocalizacao(loc);
						message.setText("Digite a categoria do bem");
						this.state = BotState.waitingBemCat;
					} else {
						throw new NullLocException();
					}
				}catch (NullLocException e) {
					message.setText(e.getMessage());
				}
				
			} else if (this.state == BotState.waitingBemCat) {
				Categoria cate = c.buscarCategoria(Integer.parseInt(lastCommand));
				try {
					if (cate != null) {
						b.setCategoria(cate);
						message.setText("Ok, seu bem foi cadastrado!");
						this.state = BotState.waitNewCommand;
						c.cadastrarBem(b.getCodigo(), b.getNome(), b.getDescricao(), b.getLocalizacao().getNome(),
								b.getCategoria().getCodigo());
					} else {
						throw new NullCatException();
					}
				}catch (NullCatException e) {
					message.setText(e.getMessage());
				}
				
			} else if (this.state == BotState.waitingLocName) {
				Localizacao loc = c.buscarLocalizacao(lastCommand);
				try {
					if (loc == null) {
						l.setNome(lastCommand);
						message.setText("Digite a descrição da localização");
						this.state = BotState.waitingLocDescription;
					} else {
						throw new LocDuplicadaException();
					}
				}catch(LocDuplicadaException e) {
					message.setText(e.getMessage());
				}
				
			} else if (this.state == BotState.waitingLocDescription) {
				l.setDescricao(lastCommand);
				this.state = BotState.waitNewCommand;
				message.setText("Ok, sua localização foi cadastrada!");
				c.cadastrarLocalizacao(l.getNome(), l.getDescricao());
				
			} else if (this.state == BotState.waitingCatCodigo) {
				Categoria cat = c.buscarCategoria(Integer.parseInt(lastCommand));
				try {
					if (cat == null) {
						this.cat.setCodigo(Integer.parseInt(lastCommand));
						message.setText("Digite o nome da categoria");
						this.state = BotState.waitingCatNome;
					} else {
						throw new CatDuplicadaException();
					}
				}catch(CatDuplicadaException e) {
					message.setText(e.getMessage());
				}
				
			} else if (this.state == BotState.waitingCatNome) {
				this.cat.setNome(lastCommand);
				message.setText("Digite a descrição da categoria");
				this.state = BotState.waitingCatDescription;
				
			} else if (this.state == BotState.waitingCatDescription) {
				this.cat.setDescricao(lastCommand);
				message.setText("Essa categoria acaba de entrar nos meus registros! :)");
				c.cadastrarCategoria(cat.getCodigo(), cat.getNome(), cat.getDescricao());
				this.state = BotState.waitNewCommand;
				
			} else if (this.state == BotState.listandoBemLoc) {
				ArrayList<Bem> bens = c.listarBem(lastCommand);
				String bens_string = "";
				if (bens != null && !bens.isEmpty()) {
					bens_string += "Ok, aqui estão os bens em " + lastCommand + "\n";
					for(int i = 0; i < bens.size(); i++) {
						bens_string += bens.get(i).toString()+"\n***\n";
					}
					message.setText(bens_string);
				} else {
					message.setText("Não há bens cadastrados nessa localização");
				}
				this.state = BotState.waitNewCommand;
				
			} else if (this.state == BotState.movimentandoBemWaitingNome) {
				this.cod = Integer.parseInt(lastCommand);
				newBem = c.buscarBem(cod);
				if(newBem != null) {
					message.setText("Ok, seu bem foi encontrado! Fala pra mim a nova localização dele.");
					this.state = BotState.movimentandoBemWaitingLoc;
				}else {
					message.setText("Não encontrei nenhum bem com esse código nos meus registros");
				}
				
			}else if(this.state == BotState.movimentandoBemWaitingLoc) {
				Localizacao loc = c.buscarLocalizacao(lastCommand);
				try {
					if(loc != null) {
						message.setText("Ok, to substituindo "+newBem.getLocalizacao().getNome()+" por "+lastCommand);
						newBem.setLocalizacao(loc);
						c.putBem(newBem);
						this.state = BotState.waitNewCommand;
					}else {
						throw new NullLocException();
					}
				}catch(NullLocException e) {
					message.setText(e.getMessage());
				}
			} else if (this.state == BotState.buscarBemCod) {
				int cod = Integer.parseInt(lastCommand);
				Bem b = c.buscarBem(cod);
				if(b != null) {
					message.setText(b.toString());
					this.state = BotState.waitNewCommand;
				}else {
					message.setText("Não encontrei seu bem :(");
				}
				
			}else if(this.state == BotState.buscarBemNome) {
				ArrayList<Bem> bens = c.buscarBem(lastCommand, 1);
				if(!bens.isEmpty()) {
					String bensBuscados = "Esses são os bens que eu achei que contém esse nome\n";
					for(int i = 0; i < bens.size(); i++) {
						Bem bem = bens.get(i);
						bensBuscados += bem.toString() + "\n***\n";
					}
					message.setText(bensBuscados);
					this.state = BotState.waitNewCommand;
				}else {
					message.setText("Não encontrei bens com esse nome.");
				}
				
				
			}else if(this.state == BotState.buscarBemDescription) {
				ArrayList<Bem> bens = c.buscarBem(lastCommand, 2);
				if(!bens.isEmpty()) {
					String bensBuscados = "Esses são os bens que eu achei que contém essa descrição\n";
					for(int i = 0; i < bens.size(); i++) {
						Bem bem = bens.get(i);
						bensBuscados += bem.toString() + "\n***\n";
					}
					message.setText(bensBuscados);
					this.state = BotState.waitNewCommand;
				}else {
					message.setText("Não encontrei bens com essa descrição.");
				}
				
			}
			
			message.setChatId(update.getMessage().getChatId());
			System.out.println("State atual: " + this.state);


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
