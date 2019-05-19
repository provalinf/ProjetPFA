package company;


public class PieceQuantite {

	private int montant;
	private TypeMonnaie typeMonnaie;
	private Symbole symbole;
	private int quantite;

	public PieceQuantite(int montant, int quantite, Symbole symbole, TypeMonnaie typeMonnaie) {
		this.montant = montant;
		this.quantite = quantite;
		this.symbole = symbole;
		this.typeMonnaie = typeMonnaie;
	}

	public PieceQuantite() {
	}

	public Symbole getSymbole() {
		return symbole;
	}

	public void setSymbole(Symbole symbole) {
		this.symbole = symbole;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
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
