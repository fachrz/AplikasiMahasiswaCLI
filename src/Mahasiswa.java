import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mahasiswa {
    protected String nim;
    protected String nama;
    protected Date tnglLahir;

    public boolean setMahasiswa(String nim, String nama, String tnglLahir) throws Exception {
        this.nim = nim;
        this.nama = nama;
        this.tnglLahir = new SimpleDateFormat("dd/MM/yyyy").parse(tnglLahir);

        return true;
    }

    public String[] getMahasiswa(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        String[] data = {this.nim, this.nama, formatter.format(this.tnglLahir)};
        return data;
    }
}
