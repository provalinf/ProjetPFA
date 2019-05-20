package company;

import company.Devise.Devise;
import company.Devise.Euro;
import company.Devise.Franc;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MachineConversionTest {

    private MachineConversion mc = new MachineConversion();
    private Euro euro = new Euro();
    private Franc franc = new Franc();

    @Test
    void maj_stock_pieces() {
        List<Double> ld = new ArrayList<>();
        ld.add(0.10);
        ld.add(0.50);
        ld.add(0.50);
        ld.add(0.50);
        ld.add(1.0);
        ld.add(2.0);
        ld.add(5.0);
        ld.add(2.0);
        ld.add(10.0);
        ld.add(50.0);
        mc.maj_stock_pieces(euro, ld);

        assertEquals(9, euro.getListPieces().size());

        assertEquals(10,euro.getListPieces().get(0).getMontant());
        assertEquals(11,euro.getListPieces().get(0).getQuantite());
        assertEquals(20,euro.getListPieces().get(1).getMontant());
        assertEquals(10,euro.getListPieces().get(1).getQuantite());
        assertEquals(50,euro.getListPieces().get(2).getMontant());
        assertEquals(13,euro.getListPieces().get(2).getQuantite());
        assertEquals(1,euro.getListPieces().get(3).getMontant());
        assertEquals(11,euro.getListPieces().get(3).getQuantite());
        assertEquals(2,euro.getListPieces().get(4).getMontant());
        assertEquals(7,euro.getListPieces().get(4).getQuantite());
        assertEquals(5,euro.getListPieces().get(5).getMontant());
        assertEquals(6,euro.getListPieces().get(5).getQuantite());
        assertEquals(10,euro.getListPieces().get(6).getMontant());
        assertEquals(6,euro.getListPieces().get(6).getQuantite());
        assertEquals(20,euro.getListPieces().get(7).getMontant());
        assertEquals(5,euro.getListPieces().get(7).getQuantite());
        assertEquals(50,euro.getListPieces().get(8).getMontant());
        assertEquals(5,euro.getListPieces().get(8).getQuantite());

        List<Double> ldf = new ArrayList<>();
        ldf.add(0.50);
        ldf.add(0.50);
        ldf.add(0.50);
        ldf.add(0.50);
        ldf.add(1.0);
        ldf.add(5.0);
        ldf.add(20.0);
        ldf.add(20.0);
        ldf.add(50.0);
        ldf.add(50.0);
        ldf.add(100.0);
        mc.maj_stock_pieces(franc, ldf);

        assertEquals(8, franc.getListPieces().size());

        assertEquals(50,franc.getListPieces().get(0).getMontant());
        assertEquals(14,franc.getListPieces().get(0).getQuantite());
        assertEquals(1,franc.getListPieces().get(1).getMontant());
        assertEquals(11,franc.getListPieces().get(1).getQuantite());
        assertEquals(2,franc.getListPieces().get(2).getMontant());
        assertEquals(5,franc.getListPieces().get(2).getQuantite());
        assertEquals(5,franc.getListPieces().get(3).getMontant());
        assertEquals(6,franc.getListPieces().get(3).getQuantite());
        assertEquals(10,franc.getListPieces().get(4).getMontant());
        assertEquals(5,franc.getListPieces().get(4).getQuantite());
        assertEquals(20,franc.getListPieces().get(5).getMontant());
        assertEquals(7,franc.getListPieces().get(5).getQuantite());
        assertEquals(50,franc.getListPieces().get(6).getMontant());
        assertEquals(7,franc.getListPieces().get(6).getQuantite());
        assertEquals(100,franc.getListPieces().get(7).getMontant());
        assertEquals(5,franc.getListPieces().get(7).getQuantite());
    }

    @Test
    void convert() {
        MachineConversion mc2 = new MachineConversion();
        assertFalse(mc2.convert(452,euro));
        assertTrue(mc2.convert(451.00,euro));

        MachineConversion mc4 = new MachineConversion();
        assertFalse(mc4.convert(768.40,franc));
        assertTrue(mc4.convert(767.90,franc));
    }


}