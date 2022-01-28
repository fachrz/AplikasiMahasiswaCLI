import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Aplikasi manajemen data mahasiswa
 *
 * @author Fachrurozi
 *         Github : Fachrz
 *         email  : rfachru3@outlook.com
 */
public class CLI {
    public static final int maxData = 4;
    public static Mahasiswa[] mahasiswa = new Mahasiswa[maxData];
    static int lastRow = 0;

    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args) throws Exception {
        System.out.println("===================================");
        System.out.println("Aplikasi Data mahasiswa (CLI)");
        System.out.println("===================================");

        helpProcess();

        Scanner cliInput = new Scanner(System.in);

        String cmdInput;

        try {
            do {
                System.out.print(">>");
                cmdInput = cliInput.nextLine();
            }while(cmdProcessing(cmdInput));
        }catch (Exception e){
            throw e;
        }

    }

    /**
     * Semua perintah didaftarkan dan di proses disini
     *
     * @param in
     */
    public static boolean cmdProcessing(String in) {
        boolean result = true;
        String[] cmd = in.split(" ");

        try {
            switch (cmd[0]){
                case "add":
                    if (cmd.length == 4){
                        addProcess(cmd);
                    }else if(cmd.length < 4){
                        System.out.println("NIM, Nama, Tngl. Lahir dibutuhkan (add <nim> <nama> <tngl_lahir>)");
                        System.out.println("ex: add 20394808 John_Doe 12/12/1992");
                    }else{
                        System.out.println("Perintah \"add\" tidak sesuai!");
                    }
                    break;
                case "delete":
                    if (cmd.length == 2){
                        deleteProcess(cmd[1]);
                    }else if(cmd.length == 1){
                        System.out.println("NIM dibutuhkan (delete <nim>)");
                        System.out.println("ex: delete 20394808");
                    }else{
                        System.out.println("Perintah \"delete\" tidak sesuai!");
                    }
                    break;
                case "edit":
                    if (cmd.length == 4){
                        editProcess(cmd);
                    }else if(cmd.length < 4){
                        System.out.println("NIM, Nama, Tngl. Lahir dibutuhkan (edit <nim> <nama> <tngllahir>)");
                        System.out.println("ex: edit 20029348 John_This 16/09/1995)");
                    }else{
                        System.out.println("Perintah \"edit\" tidak sesuai!");
                    }
                    break;
                case "list":
                    listProcess();
                    break;
                case "exit":
                    System.out.println("dadah!!");
                    System.exit(0);
                case "help":
                    helpProcess();
                    break;
                default:
                    System.out.println("Perintah tidak tersedia");
                    break;
            }
        }catch (Exception e){
            System.out.println("Terjadi masalah pada aplikasi");
        }

        return result;
    }

    /**
     * Memeriksa apakah string itu numerik atau tidak
     * @param strNum
     * @return
     */
    public static boolean isNumber(String strNum){
        if (strNum == null){
            return false;
        }

        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return pattern.matcher(strNum).matches();
    }

    /**
     * Mengubah string tanggal menjadi Date jika sesuai dengan format
     *
     * @param string
     * @return
     */
    public static Date toDate(String string){
        Date tglLhr;
        try {
            tglLhr = new SimpleDateFormat("dd/MM/yyyy").parse(string);
        } catch (ParseException pe) {
            tglLhr = null;
        }

        return tglLhr;
    }

    /**
     * Proses perintah "add"
     *
     * add <nim: string> <nama: string> <tnglLahir: String>
     */
    public static void addProcess(String[] input) throws Exception {
        String nim, nama;
        Date tglLhr;

        nim = input[1];
        nama = input[2].replace("_", " ");
        tglLhr = toDate(input[3]);

        //validation
        if (!isNumber(nim)){
            System.out.println("NIM harus berupa angka");
            return;
        }

        if (tglLhr == null){
            System.out.println("Format Tanggal Lahir tidak valid");
            return;
        }

        //insert process
        if (lastRow < maxData){
            mahasiswa[lastRow] = new Mahasiswa();
            if (mahasiswa[lastRow].setMahasiswa(nim, nama, tglLhr)) {
                lastRow++;
                System.out.println("Data berhasil disimpan");
            }
        }else{
            System.out.println("Maaf penyimpanan sudah penuh");
        }
    }

    /**
     * Proses perintah "delete"
     *
     * delete <nim>
     */
    public static void deleteProcess(String nim){
        int dindex = 0;
        boolean flag = false;

        //mencari index
        for (int x = 0; x < lastRow; x++){
            if (nim.equals(mahasiswa[x].getMahasiswa()[0])){
                dindex = x;
                flag = true;
            }
        }

        if (flag){
            //delete process begin
            for (int i = dindex; i < lastRow; i++){
                if (i + 1 != lastRow){
                    mahasiswa[i] = mahasiswa[i+1];
                }else{
                    mahasiswa[i] = null;
                }
            }
            System.out.println("NIM " + nim + " berhasil dihapus");
            lastRow--;
        }else{
            System.out.println("NIM tidak ditemukan");
        }
    }

    /**
     * Proses perintah "edit"
     *
     * edit <nim> <nama> <tnglLahir>
     */
    public static void editProcess(String[] input) throws Exception {
        String nim, nama;
        Date tglLhr;
        boolean flag = false;
        int eindex = 0;

        //validation
        nim = input[1];
        nama = input[2].replace("_", " ");
        tglLhr = toDate(input[3]);

        //cari nomor indexnya dari nim
        for (int x = 0; x < lastRow; x++){
            if (input[1].equals(mahasiswa[x].getMahasiswa()[0])){
                eindex = x;
                flag = true;
            }
        }

        //process edit
        if (flag) {
            mahasiswa[eindex].setMahasiswa(nim, nama, tglLhr);
        }
    }

    /**
     * Proses perintah "list"
     *
     */
    public static void listProcess()
    {
        System.out.println("Nim \t\t Nama \t\t Tanggal");
        for (int i = 0; i < lastRow; i++){
            String[] mhsw = mahasiswa[i].getMahasiswa();
            System.out.println(mhsw[0] + "\t\t" + mhsw[1] + "\t\t" + mhsw[2]);
        }


    }

    /**
     * Proses perintah "help"
     *
     */
    public static void helpProcess(){
        String[][] cmd = {
                {"add", "Menambah data mahasiswa"},
                {"delete", "Menghapus data mahasiswa"},
                {"edit", "Mengubah data mahasiswa"},
                {"list", "Menampilkan semua data mahasiswa yang ada"},
                {"help", "Menampilkan daftar perintah yang bisa dilakukan"},
                {"exit", "Keluar dari aplikasi"},
        };

        System.out.println("Command: ");
        for (String[] command : cmd) {
            System.out.println("\t" + command[0] + "\t\t" + command[1]);
        }
    }
}
