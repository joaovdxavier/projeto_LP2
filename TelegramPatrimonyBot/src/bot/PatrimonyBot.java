package bot;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import modelos.*;

public class PatrimonyBot extends TelegramLongPollingBot {
	List<MessageProcessor> processos = new ArrayList<>();

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			MessageProcessor mp = new MessageProcessor(update.getMessage().getChatId());
			boolean hasProcess = false;
			
			for(MessageProcessor process : processos) {
				hasProcess = hasProcess || process.getChatId() == mp.getChatId();
				
				if(hasProcess) {
					mp = process;
				}
			}
			
			if(!hasProcess) {
				processos.add(mp);
			}
			Object[] results = mp.update(update);
			SendMessage message = (SendMessage) results[1];
			if(results[0] == BotState.finalize) {
				processos.remove(mp);
			}else {
				try {
					execute(message);
				} catch (TelegramApiException e) {
					
				}
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
