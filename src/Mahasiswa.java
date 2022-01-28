import java.util.Date;

public class Mahasiswa {
    protected String nim;
    protected String nama;
    protected Date tanggal;

    public boolean setData(String nim, String nama){
        this.nim = nim;
        this.nama = nama;
        this.tanggal = new Date();

        return true;
    }

    public String[] showData(){
        String[] data = {this.nim, this.nama, this.tanggal.toString()};
        return data;
    }
}
