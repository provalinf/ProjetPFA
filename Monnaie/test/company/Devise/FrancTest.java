package company.Devise;

import company.PieceQuantite;
import company.Symbole;
import company.TypeMonnaie;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FrancTest {

	private Franc franc = new Franc();
	private int taille = 8;

	@Test
	void getListPieces() {
		assertEquals(taille, franc.getListPieces().size());

		assertEquals(50, franc.getListPieces().get(0).getMontant());
		assertEquals(10, franc.getListPieces().get(0).getQuantite());
		assertEquals(1, franc.getListPieces().get(1).getMontant());
		assertEquals(10, franc.getListPieces().get(1).getQuantite());
		assertEquals(2, franc.getListPieces().get(2).getMontant());
		assertEquals(10, franc.getListPieces().get(2).getQuantite());
		assertEquals(5, franc.getListPieces().get(3).getMontant());
		assertEquals(5, franc.getListPieces().get(3).getQuantite());
		assertEquals(10, franc.getListPieces().get(4).getMontant());
		assertEquals(5, franc.getListPieces().get(4).getQuantite());
		assertEquals(20, franc.getListPieces().get(5).getMontant());
		assertEquals(5, franc.getListPieces().get(5).getQuantite());
		assertEquals(50, franc.getListPieces().get(6).getMontant());
		assertEquals(5, franc.getListPieces().get(6).getQuantite());
		assertEquals(100, franc.getListPieces().get(7).getMontant());
		assertEquals(4, franc.getListPieces().get(7).getQuantite());

		assertEquals(TypeMonnaie.PIECE, franc.getListPieces().get(0).getTypeMonnaie());
		assertEquals(TypeMonnaie.PIECE, franc.getListPieces().get(1).getTypeMonnaie());
		assertEquals(TypeMonnaie.PIECE, franc.getListPieces().get(2).getTypeMonnaie());
		assertEquals(TypeMonnaie.PIECE, franc.getListPieces().get(3).getTypeMonnaie());
		assertEquals(TypeMonnaie.BILLET, franc.getListPieces().get(4).getTypeMonnaie());
		assertEquals(TypeMonnaie.BILLET, franc.getListPieces().get(5).getTypeMonnaie());
		assertEquals(TypeMonnaie.BILLET, franc.getListPieces().get(6).getTypeMonnaie());
		assertEquals(TypeMonnaie.BILLET, franc.getListPieces().get(7).getTypeMonnaie());
	}

	@Test
	void setListPieces() {
		List<PieceQuantite> lp = new ArrayList<>();
		PieceQuantite p1 = new PieceQuantite(2, 10, Symbole.FRANC, TypeMonnaie.PIECE);
		PieceQuantite p2 = new PieceQuantite(20, 5, Symbole.FRANC, TypeMonnaie.BILLET);
		lp.add(p1);
		lp.add(p2);

		franc.setListPieces(lp);
		assertEquals(2, franc.getListPieces().size());
		assertEquals(2, franc.getListPieces().get(0).getMontant());
		assertEquals(10, franc.getListPieces().get(0).getQuantite());
		assertEquals(20, franc.getListPieces().get(1).getMontant());
		assertEquals(5, franc.getListPieces().get(1).getQuantite());
	}

	@Test
	void addPieceToList() {
		PieceQuantite p1 = new PieceQuantite(200, 1, Symbole.EURO, TypeMonnaie.BILLET);
		franc.addPieceToList(p1);
		taille = 9;
		assertEquals(200, franc.getListPieces().get(8).getMontant());
		assertEquals(1, franc.getListPieces().get(8).getQuantite());
		getListPieces();
	}

	@Test
	void getNomDevise() {
		assertEquals(Devise.CodeISO.CHF, franc.getNomDevise());
	}

	@Test
	void affichageEtAjout() {
		ByteArrayOutputStream systemOutContent;
		systemOutContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(systemOutContent));

		List<Double> list = new ArrayList<>();
		franc.affichageEtAjout(1, list);
		assertEquals(1, list.size());
		assertEquals(1, list.get(0));
		franc.affichageEtAjout(20, list);
		assertEquals(2, list.size());
		assertEquals(20, list.get(1));
		franc.affichageEtAjout(50, list);
		assertEquals(3, list.size());
		assertEquals(50, list.get(2));

		String newLine = System.getProperty("line.separator");
		assertEquals("Introduction d’une piece de 1.0 CHF puis d’un billet de 20.0 CHF puis d’un billet de 50.0 CHF", systemOutContent.toString().trim().replaceAll(newLine, " "));
	}

	@Test
	void verification() {
		assertFalse(franc.verification("0.05"));
		assertFalse(franc.verification("0.10"));
		assertFalse(franc.verification("0.20"));
		assertTrue(franc.verification("0.50"));
		assertTrue(franc.verification("1"));
		assertTrue(franc.verification("2"));
		assertTrue(franc.verification("5"));
		assertTrue(franc.verification("10"));
		assertTrue(franc.verification("20"));
		assertTrue(franc.verification("50"));
		assertTrue(franc.verification("100"));
		assertFalse(franc.verification("200"));
	}
}