package company;


public class PieceQuantite {

	private int piece;
	private TypeMonnaie typeMonnaie;
	private Symbole symbole;
	private int quantite;

	public PieceQuantite(int piece, int quantite, Symbole symbole, TypeMonnaie typeMonnaie) {
		this.piece = piece;
		this.quantite = quantite;
		this.symbole = symbole;
		this.typeMonnaie = typeMonnaie;
	}

	public Symbole getSymbole() {
		return symbole;
	}

	public void setSymbole(Symbole symbole) {
		this.symbole = symbole;
	}

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public TypeMonnaie getTypeMonnaie() {
		return typeMonnaie;
	}

	public void setTypeMonnaie(TypeMonnaie typeMonnaie) {
		this.typeMonnaie = typeMonnaie;
	}
}
