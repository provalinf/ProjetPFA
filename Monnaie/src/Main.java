public class Main {

    public static void main(String[] args) {

        Euro euro=new Euro() ;
        Franc franc=new Franc() ;


        MachineConversion machineConversion= new MachineConversion(euro,franc) ;

        machineConversion.Conversion() ;




    }
}
