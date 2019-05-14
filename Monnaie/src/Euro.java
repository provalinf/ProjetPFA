import java.util.ArrayList;
import java.util.List;

public class Euro {


    private List<PieceQuantite> listEuro ;

    public Euro(){
        listEuro=initEuro() ;
    }

    public List<PieceQuantite> getListEuro() {
        return listEuro;
    }

    public void setListEuro(List<PieceQuantite> listEuro) {
        this.listEuro = listEuro;
    }

    public List<PieceQuantite> initEuro(){

        List<PieceQuantite> list=new ArrayList<>() ;

        PieceQuantite piece10C =new PieceQuantite(10,10,Symbole.CENTIME) ;
        PieceQuantite piece20C =new PieceQuantite(20,10,Symbole.CENTIME) ;
        PieceQuantite piece50C =new PieceQuantite(50,10,Symbole.CENTIME) ;
        PieceQuantite piece1E =new PieceQuantite(1,10,Symbole.EURO) ;
        PieceQuantite piece2E =new PieceQuantite(2,5,Symbole.EURO) ;
        PieceQuantite billet5E =new PieceQuantite(5,5,Symbole.EURO) ;
        PieceQuantite billet10E =new PieceQuantite(10,5,Symbole.EURO) ;
        PieceQuantite billet20E =new PieceQuantite(20,5,Symbole.EURO) ;
        PieceQuantite billet50E =new PieceQuantite(50,4,Symbole.EURO) ;

        list.add(piece10C) ;
        list.add(piece20C) ;
        list.add(piece50C) ;
        list.add(piece1E) ;
        list.add(piece2E) ;
        list.add(billet5E) ;
        list.add(billet10E) ;
        list.add(billet20E) ;
        list.add(billet50E) ;

        return list ;


    }



}
