package company;

import company.Devise.Devise;
import company.Devise.Euro;
import company.Devise.Franc;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MachineConversionTest {
    @Test
    void maj_stock_pieces() {
        Euro euro = new Euro();
        Franc franc = new Franc();

        MachineConversion mc = new MachineConversion();
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
        assertEquals(10,franc.getListPieces().get(2).getQuantite());
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
        String newLine = System.getProperty("line.separator");

        //Arrondi inférieur EURO
        ByteArrayOutputStream systemOutContent;
        systemOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent));
        MachineConversion mc1 = new MachineConversion();

        assertTrue(mc1.convert(26.00, mc1.getEuro()));

        //Actions successives
        assertTrue(mc1.convert(23.20, mc1.getFranc()));
        assertTrue(mc1.convert(50.00, mc1.getEuro()));

        //Arrondi supérieur EURO
        ByteArrayOutputStream systemOutContent2;
        systemOutContent2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent2));

        assertTrue(mc1.convert(28.00,mc1.getEuro()));
        assertEquals("24\tEUR\t99 Centimes 0 9 (EUR\t20 2 2 0.5 0.2 0.2 0.1 )",systemOutContent2.toString().trim().replaceAll(newLine," "));

        //Arrondi inférieur CHF
        ByteArrayOutputStream systemOutContent3;
        systemOutContent3 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent3));
        MachineConversion mc2 = new MachineConversion();

        assertTrue(mc2.convert(51.00,mc2.getFranc()));
        assertEquals("57\tCHF\t12 Centimes 0 12 (CHF\t50 5 2 )",systemOutContent3.toString().trim().replaceAll(newLine," "));

        //Arrondi supérieur CHF
        ByteArrayOutputStream systemOutContent4;
        systemOutContent4 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent4));

        assertTrue(mc2.convert(18.30,mc2.getFranc()));
        assertEquals("20\tCHF\t49 Centimes 0 49 (CHF\t20 0.5 )",systemOutContent4.toString().trim().replaceAll(newLine," "));

        //Limite max demande initial CHF
        ByteArrayOutputStream systemOutContent5;
        systemOutContent5 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent5));
        MachineConversion mc3 = new MachineConversion();

        assertTrue(mc3.convert(767.90,mc3.getFranc()));
        assertEquals("860\tCHF\t4 Centimes 0 4 (CHF\t100 100 100 100 50 50 50 50 50 20 20 20 20 20 10 10 10 10 10 5 5 5 5 5 2 2 2 2 2 2 2 2 2 2 1 1 1 1 1 1 1 1 1 1 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 )",systemOutContent5.toString().trim().replaceAll(newLine," "));

        ////Limite max demande initial EURO
        ByteArrayOutputStream systemOutContent6;
        systemOutContent6 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent6));
        MachineConversion mc4 = new MachineConversion();

        assertTrue(mc4.convert(451.00,mc4.getEuro()));
        assertEquals("402\tEUR\t67 Centimes 0 7 (EUR\t50 50 50 50 20 20 20 20 20 10 10 10 10 10 5 5 5 5 5 2 2 2 2 2 1 1 1 1 1 1 1 1 1 1 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.2 0.2 0.2 0.2 0.2 0.2 0.2 0.2 0.2 0.2 0.1 0.1 0.1 0.1 0.1 0.1 )",systemOutContent6.toString().trim().replaceAll(newLine," "));

        //Cas non passant reserve vide
        assertFalse(mc4.convert(10.00,mc4.getEuro()));
        assertFalse(mc4.convert(0.50,mc3.getFranc()));


    }

    @Test
    void afficherListe(){
        ByteArrayOutputStream systemOutContent;
        ByteArrayOutputStream systemOutContent2;
        systemOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent));

        MachineConversion m = new MachineConversion();
        List<Double> l = new ArrayList<>();
        l.add(10.0);
        l.add(10.0);
        l.add(50.0);
        l.add(1.0);
        l.add(0.20);
        m.afficherListe(Devise.CodeISO.EUR, l);
        assertEquals("(EUR\t10 10 50 1 0.2 )",systemOutContent.toString().trim());

        systemOutContent2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent2));

        MachineConversion m2 = new MachineConversion();
        List<Double> l2 = new ArrayList<>();
        l2.add(0.50);
        l2.add(0.50);
        l2.add(0.50);
        l2.add(2.0);
        l2.add(2.0);
        l2.add(20.0);
        l2.add(100.0);
        m.afficherListe(Devise.CodeISO.CHF, l2);
        assertEquals("(CHF\t0.5 0.5 0.5 2 2 20 100 )",systemOutContent2.toString().trim());
    }

    @Test
    void conversion() {

        StringBuilder keyboard = new StringBuilder();
        keyboard.append("get-chf");
        keyboard.append(System.lineSeparator());
        keyboard.append(50);
        keyboard.append(System.lineSeparator());
        keyboard.append(20);
        keyboard.append(System.lineSeparator());
        keyboard.append("end");
        //keyboard.append("fin");

        System.setIn(new ByteArrayInputStream(keyboard.toString().getBytes()));
        ByteArrayOutputStream systemOutContent2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent2));
        String newLine = System.getProperty("line.separator");

        MachineConversion machineConversion = new MachineConversion();
        machineConversion.conversion();
        int lenght  =("78\tCHF\t40 Centimes (CHF\t50 20 5 2 1 )").length();
        int taille = systemOutContent2.toString().length();
        assertEquals("78\tCHF\t40 Centimes 0 40 (CHF\t50 20 5 2 1 )",systemOutContent2.toString().substring(taille-(lenght+6),taille+lenght-38).trim().replaceAll(newLine," "));

        try {
            systemOutContent2.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }



    @Test
    void conversionInvalide() {

        StringBuilder keyboard = new StringBuilder();
        keyboard.append("get-euros");
        keyboard.append(System.lineSeparator());
        keyboard.append(1000);
        keyboard.append(System.lineSeparator());
        //  keyboard.append(20);
        //keyboard.append(System.lineSeparator());
        keyboard.append("end");
        System.setIn(new ByteArrayInputStream(keyboard.toString().getBytes()));


        System.setIn(new ByteArrayInputStream(keyboard.toString().getBytes()));
        ByteArrayOutputStream systemOutContent2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent2));
        String newLine = System.getProperty("line.separator");

        MachineConversion mc3 = new MachineConversion();
        mc3.conversion();

        int lenght  =("Mauvais montant !!!\nTransaction invalidée !!!").length();
        int taille = systemOutContent2.toString().length();
        //assertTrue(systemOutContent2.toString().endsWith("78\tCHF\t40 Centimes (CHF\t50 20 5 2 1 )"));
        //System.out.println(systemOutContent2.toString().trim().replaceAll(newLine," "));
        assertEquals("Mauvais montant !!! Transaction invalidée !!!",systemOutContent2.toString().replaceAll(newLine," ").substring(taille-(lenght+17),taille-(lenght-28)));

        try {
            systemOutContent2.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}