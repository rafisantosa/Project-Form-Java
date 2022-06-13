package responsi_5210411352;

public class NoPegawaiHarus10Digit extends Exception{
    @Override
    public String getMessage(){
        return "No Pegawai di Luar range";
    }
}
