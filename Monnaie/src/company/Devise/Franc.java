package company.Devise;

import company.PieceQuantite;
import company.Symbole;
import company.TypeMonnaie;

public class Franc extends Devise {

	public Franc() {
		super(Symbole.FRANC);
		initPieceEuro();
	}

	private void initPieceEuro() {
		PieceQuantite piece50F_C = new PieceQuantite(50, 10, Symbole.CENTIME_FRANC, TypeMonnaie.PIECE);
		PieceQuantite piece1F = new PieceQuantite(1, 10, Symbole.FRANC, TypeMonnaie.PIECE);
		PieceQuantite piece2F = new PieceQuantite(2, 5, Symbole.FRANC, TypeMonnaie.PIECE);
		PieceQuantite billet5F = new PieceQuantite(5, 5, Symbole.FRANC, TypeMonnaie.PIECE);
		PieceQuantite billet10F = new PieceQuantite(10, 5, Symbole.FRANC, TypeMonnaie.BILLET);
		PieceQuantite billet20F = new PieceQuantite(20, 5, Symbole.FRANC, TypeMonnaie.BILLET);
		PieceQuantite billet50F = new PieceQuantite(50, 5, Symbole.FRANC, TypeMonnaie.BILLET);
		PieceQuantite billet100F = new PieceQuantite(100, 4, Symbole.FRANC, TypeMonnaie.BILLET);

		addPieceToList(piece50F_C, piece1F, piece2F, billet5F, billet10F, billet20F, billet50F, billet100F);
	}

}
