package company.Devise;

import company.PieceQuantite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	protected void addPieceToList(PieceQuantite... pieces) {
		Collections.addAll(ListPieces, pieces);
	}

	public void affichage(int valeur, List<Integer> list) {
		if (list.isEmpty()) {
			if (valeur > 2) {
				System.out.println("Introduction d’un billet de " + valeur + " " + nomDevise);
			} else {
				System.out.println("Introduction d’une piece de " + valeur + " " + nomDevise);
			}
		} else {
			if (valeur > 2) {
				System.out.println("puis d’un billet de " + valeur + " " + nomDevise);
			} else {
				System.out.println("puis d’une piece de " + valeur + " " + nomDevise);
			}
		}
	}

	public Boolean verification(int valeur) {
		for (PieceQuantite pieceQuantite : getListPieces()) {
			if (pieceQuantite.getPiece() == valeur) {
				return true;
			}
		}
		return false;
	}
}
