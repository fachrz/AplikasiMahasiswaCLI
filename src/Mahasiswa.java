import java.util.Date;

public class Mahasiswa {
    protected int nim;
    protected String nama;
    protected Date tanggal;

    public boolean setData(int nim, String nama){
        this.nim = nim;
        this.nama = nama;
        this.tanggal = new Date();

        return true;
    }

    public String[] showData(){
        Integer nim = this.nim;
        String[] array = {nim.toString(), this.nama, this.tanggal.toString()};

        return array;
    }
}
