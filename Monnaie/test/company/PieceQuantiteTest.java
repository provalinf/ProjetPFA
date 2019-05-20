package company;

import company.Devise.Devise;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceQuantiteTest {

    private PieceQuantite pieceQuantite = new PieceQuantite(10,15,Symbole.EURO, TypeMonnaie.BILLET);

    @Test
    void getSymbole() {
        Assertions.assertEquals(Symbole.EURO, pieceQuantite.getSymbole());
    }

    @Test
    void setSymbole() {
        PieceQuantite p = new PieceQuantite();
        p.setSymbole(Symbole.EURO);
        assertEquals(p.getSymbole(),Symbole.EURO);

        PieceQuantite p2 = new PieceQuantite();
        p2.setSymbole(Symbole.FRANC);
        assertEquals(p2.getSymbole(),Symbole.FRANC);
    }

    @Test
    void getMontant() {
        Assertions.assertEquals(10,pieceQuantite.getMontant());
    }

    @Test
    void setMontant() {
        PieceQuantite p = new PieceQuantite();
        p.setMontant(50);
        assertEquals(p.getMontant(),50);
    }

    @Test
    void getQuantite() {
        Assertions.assertEquals(15, pieceQuantite.getQuantite());
    }

    @Test
    void setQuantite() {
        PieceQuantite p = new PieceQuantite();
        p.setQuantite(4);
        assertEquals(p.getQuantite(),4);
    }

    @Test
    void getTypeMonnaie() {
        Assertions.assertEquals(TypeMonnaie.BILLET, pieceQuantite.getTypeMonnaie());
    }

    @Test
    void setTypeMonnaie() {
        PieceQuantite p = new PieceQuantite();
        p.setTypeMonnaie(TypeMonnaie.BILLET);
        assertEquals(p.getTypeMonnaie(), TypeMonnaie.BILLET);
    }
}