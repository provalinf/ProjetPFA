package company.Devise;

import company.PieceQuantite;
import company.Symbole;
import company.TypeMonnaie;

public class Euro extends Devise {

	public Euro() {
		super("EUR");
		initPieceEuro();
	}

	private void initPieceEuro() {
		PieceQuantite piece10C = new PieceQuantite(10, 10, Symbole.CENTIME, TypeMonnaie.PIECE);
		PieceQuantite piece20C = new PieceQuantite(20, 10, Symbole.CENTIME, TypeMonnaie.PIECE);
		PieceQuantite piece50C = new PieceQuantite(50, 10, Symbole.CENTIME, TypeMonnaie.PIECE);
		PieceQuantite piece1E = new PieceQuantite(1, 10, Symbole.EURO, TypeMonnaie.PIECE);
		PieceQuantite piece2E = new PieceQuantite(2, 5, Symbole.EURO, TypeMonnaie.PIECE);
		PieceQuantite billet5E = new PieceQuantite(5, 5, Symbole.EURO, TypeMonnaie.BILLET);
		PieceQuantite billet10E = new PieceQuantite(10, 5, Symbole.EURO, TypeMonnaie.BILLET);
		PieceQuantite billet20E = new PieceQuantite(20, 5, Symbole.EURO, TypeMonnaie.BILLET);
		PieceQuantite billet50E = new PieceQuantite(50, 4, Symbole.EURO, TypeMonnaie.BILLET);

		addPieceToList(piece10C, piece20C, piece50C, piece1E, piece2E, billet5E, billet10E, billet20E, billet50E);
	}

}
