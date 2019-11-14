package bot;

public enum BotState {
	idle, 
	waitNewCommand,
	cadastrarBem, cadastrarCategoria, cadastrarLocalizacao,
	waitingLocName, waitingLocDescription, 
	waitingBemNome, waitingBemLoc, waitingBemDescription, waitingBemCodigo, waitingBemCat,
	waitingCatCodigo, waitingCatNome, waitingCatDescription,
	listandoBemLoc, listandoCat, listandoLoc,
	buscarBemCod, buscarBemNome, buscarBemDescription,
	movimentandoBem,
	gerandoRelatorio,
	finalize
}
