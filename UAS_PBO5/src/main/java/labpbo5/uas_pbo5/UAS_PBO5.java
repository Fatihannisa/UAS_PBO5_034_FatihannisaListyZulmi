/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package labpbo5.uas_pbo5;

/**
 *
 * @author LENOVO
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Calendar;

class Item {
    private String judul;
    private String penulis;
    private String penerbit;
    private int jumlahHalaman;
    private int tahunTerbit;
    private boolean dipinjam;
    private String peminjam;
    private Date tanggalPinjam;
    private Date tanggalKembali;

    public Item(String judul, String penulis, String penerbit, int jumlahHalaman, int tahunTerbit) {
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.jumlahHalaman = jumlahHalaman;
        this.tahunTerbit = tahunTerbit;
        this.dipinjam = false;
        this.peminjam = "";
        this.tanggalPinjam = null;
        this.tanggalKembali = null;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public int getJumlahHalaman() {
        return jumlahHalaman;
    }

    public void setJumlahHalaman(int jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public boolean isDipinjam() {
        return dipinjam;
    }

    public void setDipinjam(boolean dipinjam) {
        this.dipinjam = dipinjam;
    }

    public String getPeminjam() {
        return peminjam;
    }

    public void setPeminjam(String peminjam) {
        this.peminjam = peminjam;
    }

    public Date getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(Date tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public Date getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(Date tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public void displayInfo() {
        System.out.println("Judul: " + judul);
        System.out.println("Penulis: " + penulis);
        System.out.println("Penerbit: " + penerbit);
        System.out.println("Jumlah Halaman: " + jumlahHalaman);
        System.out.println("Tahun Terbit: " + tahunTerbit);
        System.out.println("Status: " + (dipinjam ? "Dipinjam" : "Tersedia"));
        if (dipinjam) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tanggalPinjam);
            calendar.add(Calendar.DAY_OF_YEAR, 7);  // Menambahkan 7 hari pada tanggal pinjam
            Date tanggalKembali = calendar.getTime();
            
            System.out.println("Peminjam: " + peminjam);
            System.out.println("Tanggal Pinjam: " + tanggalPinjam);
            System.out.println("Tanggal Kembali: " + tanggalKembali);
        }
    }
}

class Buku extends Item {
    public Buku(String judul, String penulis, String penerbit, int jumlahHalaman, int tahunTerbit) {
        super(judul, penulis, penerbit, jumlahHalaman, tahunTerbit);
    }
}

class Majalah extends Item {
    private int nomorEdisi;

    public Majalah(String judul, String penulis, String penerbit, int jumlahHalaman, int tahunTerbit, int nomorEdisi) {
        super(judul, penulis, penerbit, jumlahHalaman, tahunTerbit);
        this.nomorEdisi = nomorEdisi;
    }

    public int getNomorEdisi() {
        return nomorEdisi;
    }

    public void setNomorEdisi(int nomorEdisi) {
        this.nomorEdisi = nomorEdisi;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Nomor Edisi: " + nomorEdisi);
    }
}

class Anggota {
    private String nama;
    private List<Item> daftarPinjaman;

    public Anggota(String nama) {
        this.nama = nama;
        this.daftarPinjaman = new ArrayList<>();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<Item> getDaftarPinjaman() {
        return daftarPinjaman;
    }

    public void pinjamItem(Item item, Date tanggalPinjam) {
        item.setDipinjam(true);
        item.setPeminjam(nama);
        item.setTanggalPinjam(tanggalPinjam);
        daftarPinjaman.add(item);
        System.out.println("Yeiy! " + nama + " berhasil meminjam '" + item.getJudul() + "'");
    }

    public void kembalikanItem(Item item, Date tanggalKembali) {
        item.setDipinjam(false);
        item.setPeminjam("");
        item.setTanggalKembali(tanggalKembali);
        daftarPinjaman.remove(item);
        System.out.println("Yeiy! " + nama + " berhasil mengembalikan '" + item.getJudul()+ "'");
    }

    public void displayInfoPinjaman() {
        System.out.println("    === Daftar Item yang Dipinjam oleh " + nama + " ===");
        System.out.println("---------------------------------------------------");
        if (daftarPinjaman.isEmpty()) {
            System.out.println("Hm, ngga ada item yang dipinjam. \nAyo mulai pinjam buku!");
            return;
        } else {
            for (Item item : daftarPinjaman) {
                item.displayInfo();
            }
        }
    }
}

public class UAS_PBO5{
    private static List<Item> daftarItem = new ArrayList<>();
    private static List<Anggota> daftarAnggota = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int pilihan;
        do {
            System.out.println("\n=====================================");
            System.out.println("    *** La Place de Etoile ***");
            System.out.println("-------------------------------------");
            System.out.println("1. Tambah Item");
            System.out.println("2. Tampilkan Item / Laporan Item");
            System.out.println("3. Pinjam Item");
            System.out.println("4. Item yang Dipinjam");
            System.out.println("5. Kembalikan Item");
            System.out.println("6. Tambah Anggota");
            System.out.println("7. Tampilkan Anggota");
            System.out.println("0. Keluar");
            System.out.print("Input menu yang ingin kamu pilih: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tambahItem();
                    break;
                case 2:
                    tampilkanItem();
                    break;
                case 3:
                    pinjamItem();
                    break;
                case 4:
                    itemDipinjam();
                    break;
                case 5:
                    kembalikanItem();
                    break;
                case 6:
                    tambahAnggota();
                    break;
                case 7:
                    tampilkanAnggota();
                    break;
                case 0:
                    System.out.println("=================================================================================================================");
                    System.out.println("\nLa Place de Etoile berarti The Star's Place. "
                            + "\nTempat berkumpulnya para bintang ialah 'Luar Angkasa' yang sangat misterius dan menyimpan keunikannya tersendiri. "
                            + "\nSama seperti Perpustakaan, tempat yang misterius, penuh dengan berbagai buku, "
                            + "\nyang tiap-tiap bukunya pasti memiliki keunikannya masing-masing.");
                    System.out.println("-----------------------------------------------------------------------------------------------------------------");
                    System.out.println("\nTerima kasih ya, sudah berkunjung ke La Place de Etoile! "
                            + "\nDatang lagi di lain waktu. Buku-buku disini pasti merindukanmu.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Salah nih! Ayo coba lagi!");
                    break;
            }
        } while (pilihan != 0);
    }

    private static void tambahItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n          === Tambah Item ===");
        System.out.println("-------------------------------------");
        System.out.print("\nJenis item (Buku/Majalah): ");
        String jenis = scanner.nextLine();

        System.out.print("Judul: ");
        String judul = scanner.nextLine();

        System.out.print("Penulis: ");
        String penulis = scanner.nextLine();

        System.out.print("Penerbit: ");
        String penerbit = scanner.nextLine();

        System.out.print("Jumlah Halaman: ");
        int jumlahHalaman = scanner.nextInt();

        System.out.print("Tahun Terbit: ");
        int tahunTerbit = scanner.nextInt();

        if (jenis.equalsIgnoreCase("Buku")) {
            Buku buku = new Buku(judul, penulis, penerbit, jumlahHalaman, tahunTerbit);
            daftarItem.add(buku);
            System.out.println("Buku '" + judul + "' berhasil ditambahkan.");
        } else if (jenis.equalsIgnoreCase("Majalah")) {
            System.out.print("Nomor Edisi: ");
            int nomorEdisi = scanner.nextInt();
            Majalah majalah = new Majalah(judul, penulis, penerbit, jumlahHalaman, tahunTerbit, nomorEdisi);
            daftarItem.add(majalah);
            System.out.println("Majalah '" + judul + "' berhasil ditambahkan.");
        } else {
            System.out.println("Jenis itemnya salah nih!");
        }
    }

    private static void tampilkanItem() {
    System.out.println("\n         === Daftar Item ===");
    System.out.println("-------------------------------------");
    
    if (daftarItem.isEmpty()) {
        System.out.println("Wah, perpustakaan kita masih kosong nih. Ayo diisi!");
    } else {
        for (Item item : daftarItem) {
            item.displayInfo();
            System.out.println();
        }
    }

    int totalItemDipinjam = 0;
    for (Item item : daftarItem) {
        if (item.isDipinjam()) {
            totalItemDipinjam++;
        }
    }
    System.out.println("Jumlah Item Dipinjam: " + totalItemDipinjam);
}


    private static void pinjamItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n         === Pinjam yuk! ===");
        System.out.println("-------------------------------------");
        System.out.print("\nNama Anggota: ");
        String namaAnggota = scanner.nextLine();

        Anggota anggota = cariAnggota(namaAnggota);
        if (anggota == null) {
            System.out.println(namaAnggota + " belum terdaftar sebagai anggota nih. Ayo daftar!");
            return;
        }

        tampilkanItem();

        System.out.print("Judul Item yang akan dipinjam: ");
        String judulItem = scanner.nextLine();

        Item item = cariItem(judulItem);
        if (item == null) {
            System.out.println("Yah, '" + judulItem + "' tidak ditemukan.");
            return;
        }

        if (item.isDipinjam()) {
            System.out.println("Sayang banget, '" + judulItem + "' sedang dipinjam. Kamu keduluan orang nih.");
        } else {
            Date tanggalPinjam = new Date();
            anggota.pinjamItem(item, tanggalPinjam);
        }
    }

    private static void itemDipinjam() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n  === Daftar Item yang Dipinjam ===");
        System.out.println("-------------------------------------");
        System.out.print("\nNama Anggota: ");
        String namaAnggota = scanner.nextLine();

        Anggota anggota = cariAnggota(namaAnggota);
        if (anggota == null) {
            System.out.println("Anggota dengan nama '" + namaAnggota + "' tidak ditemukan.");
            return;
        }

        anggota.displayInfoPinjaman();
    }

    private static void kembalikanItem() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("\n      === Pengembalian Item ===");
    System.out.println("-------------------------------------");
    System.out.print("\nNama Anggota: ");
    String namaAnggota = scanner.nextLine();

    Anggota anggota = cariAnggota(namaAnggota);
    if (anggota == null) {
        System.out.println("Anggota dengan nama '" + namaAnggota + "' tidak ditemukan.");
        return;
    }

    anggota.displayInfoPinjaman();
    
    System.out.print("Judul buku yang ingin dikembalikan : ");
    
    if (anggota.getDaftarPinjaman().isEmpty()) {
        return; 
    }
    String judulItem = scanner.nextLine();

    Item item = cariItem(judulItem);
    if (item == null) {
        System.out.println("'" + judulItem + "' tidak ditemukan.");
        return;
    }

    if (!item.isDipinjam() || !item.getPeminjam().equals(namaAnggota)) {
        System.out.println("'" + judulItem + "' tidak sedang dipinjam oleh " + namaAnggota + ".");
    } else {
        Date tanggalKembali = new Date();
        anggota.kembalikanItem(item, tanggalKembali);

        // Mengurangi jumlah item yang dipinjam
        int totalItemDipinjam = 0;
        for (Item i : daftarItem) {
            if (i.isDipinjam()) {
                totalItemDipinjam++;
            }
        }
        System.out.println("Jumlah Item Dipinjam: " + totalItemDipinjam);
    }
}

    private static void tambahAnggota() {
        System.out.println("\n     === Pendaftaran Anggota ===");
        System.out.println("-------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nNama Anggota: ");
        String namaAnggota = scanner.nextLine();

        Anggota anggota = cariAnggota(namaAnggota);
        if (anggota != null) {
            System.out.println(namaAnggota + ", kamu sudah terdaftar loh.");
        } else {
            anggota = new Anggota(namaAnggota);
            daftarAnggota.add(anggota);
            System.out.println("Hallo " + namaAnggota + " selamat bergabung.");
        }
    }

    private static void tampilkanAnggota() {
        System.out.println("\n       === Daftar Anggota ===");
        System.out.println("-------------------------------------");
        if (daftarAnggota.isEmpty()) {
            System.out.println("Yah, tidak ada anggota yang terdaftar nih. Ayo daftar!");
        } else {
            for (Anggota anggota : daftarAnggota) {
                System.out.println("Nama: " + anggota.getNama());
                System.out.println("Jumlah Item Dipinjam: " + anggota.getDaftarPinjaman().size());
                System.out.println();
            }
        }
    }
    
    private static Anggota cariAnggota(String namaAnggota) {
        for (Anggota anggota : daftarAnggota) {
            if (anggota.getNama().equalsIgnoreCase(namaAnggota)) {
                return anggota;
            }
        }
        return null;
    }

    private static Item cariItem(String judulItem) {
        for (Item item : daftarItem) {
            if (item.getJudul().equalsIgnoreCase(judulItem)) {
                return item;
            }
        }
        return null;
    }
}



