package responsi_5210411352;

public class Pegawai {
    private String Nama,Posisi,noPegawai;
    private Integer Gaji;

    public Pegawai() {
    }

    public Pegawai(String Nama, String Posisi, String noPegawai, Integer Gaji) {
        this.Nama = Nama;
        this.Posisi = Posisi;
        this.noPegawai = noPegawai;
        this.Gaji = Gaji;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public void setPosisi(String Posisi) {
        this.Posisi = Posisi;
    }

    public void setNoPegawai(String noPegawai) {
        this.noPegawai = noPegawai;
    }

    public void setGaji(Integer Gaji) {
        this.Gaji = Gaji;
    }

    public String getNama() {
        return Nama;
    }

    public String getPosisi() {
        return Posisi;
    }

    public String getNoPegawai() {
        return noPegawai;
    }

    public Integer getGaji() {
        return Gaji;
    }
    
    public void Cetak(){
        System.out.println(getNama());
    }
}
