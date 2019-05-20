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

class EuroTest {
    private Euro euro = new Euro();
    private int taille = 9;
    @Test
    void getListPieces() {

        assertEquals(taille, euro.getListPieces().size());

        assertEquals(10,euro.getListPieces().get(0).getMontant());
        assertEquals(10,euro.getListPieces().get(0).getQuantite());
        assertEquals(20,euro.getListPieces().get(1).getMontant());
        assertEquals(10,euro.getListPieces().get(1).getQuantite());
        assertEquals(50,euro.getListPieces().get(2).getMontant());
        assertEquals(10,euro.getListPieces().get(2).getQuantite());
        assertEquals(1,euro.getListPieces().get(3).getMontant());
        assertEquals(10,euro.getListPieces().get(3).getQuantite());
        assertEquals(2,euro.getListPieces().get(4).getMontant());
        assertEquals(5,euro.getListPieces().get(4).getQuantite());
        assertEquals(5,euro.getListPieces().get(5).getMontant());
        assertEquals(5,euro.getListPieces().get(5).getQuantite());
        assertEquals(10,euro.getListPieces().get(6).getMontant());
        assertEquals(5,euro.getListPieces().get(6).getQuantite());
        assertEquals(20,euro.getListPieces().get(7).getMontant());
        assertEquals(5,euro.getListPieces().get(7).getQuantite());
        assertEquals(50,euro.getListPieces().get(8).getMontant());
        assertEquals(4,euro.getListPieces().get(8).getQuantite());

        assertEquals(TypeMonnaie.PIECE,euro.getListPieces().get(0).getTypeMonnaie());
        assertEquals(TypeMonnaie.PIECE,euro.getListPieces().get(1).getTypeMonnaie());
        assertEquals(TypeMonnaie.PIECE,euro.getListPieces().get(2).getTypeMonnaie());
        assertEquals(TypeMonnaie.PIECE,euro.getListPieces().get(3).getTypeMonnaie());
        assertEquals(TypeMonnaie.PIECE,euro.getListPieces().get(4).getTypeMonnaie());
        assertEquals(TypeMonnaie.BILLET,euro.getListPieces().get(5).getTypeMonnaie());
        assertEquals(TypeMonnaie.BILLET,euro.getListPieces().get(6).getTypeMonnaie());
        assertEquals(TypeMonnaie.BILLET,euro.getListPieces().get(7).getTypeMonnaie());
        assertEquals(TypeMonnaie.BILLET,euro.getListPieces().get(8).getTypeMonnaie());
    }

    @Test
    void setListPieces() {
        List<PieceQuantite> lp = new ArrayList<>();
        PieceQuantite p1 = new PieceQuantite(50,10, Symbole.EURO,TypeMonnaie.BILLET);
        PieceQuantite p2 = new PieceQuantite(1,5, Symbole.EURO,TypeMonnaie.PIECE);
        lp.add(p1);
        lp.add(p2);

        euro.setListPieces(lp);
        assertEquals(2, euro.getListPieces().size());
        assertEquals(50,euro.getListPieces().get(0).getMontant());
        assertEquals(10,euro.getListPieces().get(0).getQuantite());
        assertEquals(1,euro.getListPieces().get(1).getMontant());
        assertEquals(5,euro.getListPieces().get(1).getQuantite());
    }

    @Test
    void addPieceToList() {
        PieceQuantite p1 = new PieceQuantite(100,1, Symbole.EURO,TypeMonnaie.BILLET);
        euro.addPieceToList(p1);
        taille = 10;
        assertEquals(100,euro.getListPieces().get(9).getMontant());
        assertEquals(1,euro.getListPieces().get(9).getQuantite());
        getListPieces();
    }

    @Test
    void getNomDevise() {
        assertEquals(Devise.CodeISO.EUR,euro.getNomDevise());
    }

    @Test
    void affichageEtAjout() {
        ByteArrayOutputStream systemOutContent;
        systemOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent));

        List<Double> list = new ArrayList<>();
        euro.affichageEtAjout(10,list);
        assertEquals(1,list.size());
        assertEquals(10,list.get(0));
        euro.affichageEtAjout(10,list);
        assertEquals(2,list.size());
        assertEquals(10,list.get(1));
        euro.affichageEtAjout(50,list);
        assertEquals(3,list.size());
        assertEquals(50,list.get(2));

        String newLine = System.getProperty("line.separator");
        assertEquals("Introduction d’un billet de 10.0 EUR puis d’un billet de 10.0 EUR puis d’un billet de 50.0 EUR",systemOutContent.toString().trim().replaceAll(newLine," "));
    }

    @Test
    void verification() {
        assertFalse(euro.verification("0.05"));
        assertTrue(euro.verification("0.10"));
        assertTrue(euro.verification("0.20"));
        assertTrue(euro.verification("0.50"));
        assertTrue(euro.verification("1"));
        assertTrue(euro.verification("2"));
        assertTrue(euro.verification("5"));
        assertTrue(euro.verification("10"));
        assertTrue(euro.verification("20"));
        assertTrue(euro.verification("50"));
        assertFalse(euro.verification("100"));
    }
}