package company.Devise;

import company.PieceQuantite;
import company.Symbole;
import company.TypeMonnaie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public abstract class Devise {

	private List<PieceQuantite> ListPieces;
	private String nomDevise;

	public Devise(String nomDevise) {
		ListPieces = new ArrayList<>();
		this.nomDevise = nomDevise;
	}

	public List<PieceQuantite> getListPieces() {
		return ListPieces;
	}

	public void setListPieces(List<PieceQuantite> listPieces) {
		ListPieces = listPieces;
	}

	protected void addPieceToList(PieceQuantite... pieces) {
		Collections.addAll(ListPieces, pieces);
	}

	public String getNomDevise() {
		return nomDevise;
	}

	public void affichageEtAjout(double valeur, List<Double> list) {
		boolean piece = getListPieces().stream()
				.filter(p -> (p.getSymbole() == Symbole.CENTIME) ?
						String.format(Locale.ROOT, "%.2f", valeur).equals("0." + p.getMontant())
						: p.getMontant() == (int) valeur)
				.anyMatch(p -> p.getTypeMonnaie() == TypeMonnaie.PIECE);

		if (list.isEmpty())
			System.out.println("Introduction d’un" + (piece ? "e piece" : " billet") + " de " + valeur + " " + getNomDevise());
		else
			System.out.println("puis d’un" + (piece ? "e piece" : " billet") + " de " + valeur + " " + getNomDevise());
		list.add(valeur);
	}

	public Boolean verification(String valeur) {
		for (PieceQuantite pieceQuantite : getListPieces())
			if (pieceQuantite.getSymbole() == Symbole.CENTIME) {
				if (String.format(Locale.ROOT, "%.2f", Double.valueOf(valeur)).equals("0." + pieceQuantite.getMontant()))
					return true;
			} else if (valeur.equals(Integer.toString(pieceQuantite.getMontant())))
				return true;
		return false;
	}
}
