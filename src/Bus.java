import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Bus {
    public static final int ONGKOS = 2000;

    private ArrayList<Penumpang> penumpangBiasa;
    private ArrayList<Penumpang> penumpangPrioritas;
    private ArrayList<Penumpang> penumpangBerdiri;
    private int totalPendapatan;

    public Bus()
    {
        penumpangBiasa = new ArrayList<>();
        penumpangPrioritas = new ArrayList<>();
        penumpangBerdiri = new ArrayList<>();
        totalPendapatan = 0;
    }

    public ArrayList<Penumpang> getPenumpangBiasa()
    {
        return this.penumpangBiasa;
    }

    public ArrayList<Penumpang> getPenumpangPrioritas()
    {
        return this.penumpangPrioritas;
    }

    public ArrayList<Penumpang> getPenumpangBerdiri()
    {
        return this.penumpangBerdiri;
    }

    public int getJumlahPenumpangBiasa()
    {
        return this.penumpangBiasa.size();
    }

    public int getJumlahPenumpangPrioritas()
    {
        return this.penumpangPrioritas.size();
    }

    public int getJumlahPenumpangBerdiri()
    {
        return this.penumpangBerdiri.size();
    }

    public boolean naikkanPenumpang(Penumpang penumpang)
    {
        Scanner sc = new Scanner(System.in);
        if (penumpang.getSaldo() >= ONGKOS) {
            if (penumpang.isPrioritas() && penumpangPrioritas.size() < 4) {
                penumpang.kurangiSaldo(ONGKOS);
                totalPendapatan += ONGKOS;
                penumpangPrioritas.add(penumpang);
                return true;
            }
            else if (penumpangBiasa.size() < 16) {
                penumpang.kurangiSaldo(ONGKOS);
                totalPendapatan += ONGKOS;
                penumpangBiasa.add(penumpang);
                return true;
            }
            else if (penumpangBerdiri.size() < 20) {
                penumpang.kurangiSaldo(ONGKOS);
                totalPendapatan += ONGKOS;
                penumpangBerdiri.add(penumpang);
                return true;
            }
            else {
                System.out.println("Maaf, penumpang telah penuh!\nSilakan tunggu bus berikutnya");
                return false;
            }
        }
        else {
            System.out.println("Maaf, saldo tidak cukup");
            System.out.print("Apakah ingin menambah saldo?(pilih 'y' jika ya)");
            String tambahSaldo = sc.next();
            if (tambahSaldo.equalsIgnoreCase("y")) {
                tambahSaldoPenumpang(penumpang);
                return naikkanPenumpang(penumpang);
            } else {
                System.out.println("Maaf, Penumpang tidak dapat naik!");
                return false;
            }
        }
    }

    public boolean turunkanPenumpang(int id)
    {
        for (Penumpang p : penumpangBiasa) {
            if (p.getID() == id) {
                penumpangBiasa.remove(p);
//                penumpangBiasa.add(penumpangBerdiri.get(0));
                return true;
            }
        }

        for (Penumpang p : penumpangPrioritas) {
            if (p.getID() == id) {
                penumpangPrioritas.remove(p);
                return true;
            }
        }

        for (Penumpang p : penumpangBerdiri) {
            if (p.getID() == id) {
                penumpangBerdiri.remove(p);
                return true;
            }
        }

        return false;
    }

    private void tambahSaldoPenumpang(Penumpang penumpang)
    {
        Scanner sc = new Scanner(System.in);
        int saldoBaru;

        while (true) {
            try {
                System.out.print("Saldo baru: ");
                saldoBaru = sc.nextInt();
            } catch (InputMismatchException e) {
                saldoBaru = 0;
                sc.next();
            }
            if (saldoBaru+penumpang.getSaldo() >= Bus.ONGKOS) {
                break;
            }
            System.out.println("\nSaldo yang Anda masukkan tidak cukup!");
            System.out.println("Anda memerlukan minimal Rp" + (Bus.ONGKOS-penumpang.getSaldo()) + " lagi agar dapat naik!\n");
        }

        penumpang.tambahSaldo(saldoBaru);
        System.out.println("Saldo sebesar "+ saldoBaru +" berhasil ditambahkan");
    }

    @Override
    public String toString()
    {
        String listBiasa="", listPrioritas="", listBerdiri="";
        if(getJumlahPenumpangBiasa() == 0){
            listBiasa = "<kosong>";
        }else{
            listBiasa = penumpangBiasa.get(0).getNama();
            for (int i=1; i<getJumlahPenumpangBiasa(); ++i) {
                listBiasa = listBiasa.concat(", ");
                listBiasa = listBiasa.concat(penumpangBiasa.get(i).getNama());
            }
        }

        if(getJumlahPenumpangPrioritas() == 0){
            listPrioritas = "<kosong>";
        }else{
            listPrioritas = penumpangPrioritas.get(0).getNama();
            for (int i=1; i<getJumlahPenumpangPrioritas(); ++i) {
                listPrioritas = listPrioritas.concat(", ");
                listPrioritas = listPrioritas.concat(penumpangPrioritas.get(i).getNama());
            }
        }

        if(getJumlahPenumpangBerdiri() == 0){
            listBerdiri= "<kosong>";
        }else{
            listBerdiri = penumpangBerdiri.get(0).getNama();
            for (int i=1; i<getJumlahPenumpangBerdiri(); ++i) {
                listBerdiri = listBerdiri.concat(", ");
                listBerdiri = listBerdiri.concat(penumpangBerdiri.get(i).getNama());
            }
        }

        return "\n=========== DATA BUS ===========" +
                "\nPenumpang Biasa     : " + listBiasa +
                "\nPenumpang Prioritas : " + listPrioritas +
                "\nPenumpang Beridiri  : " + listBerdiri +
                "\nJumlah Penumpang : " + (getJumlahPenumpangBiasa()+getJumlahPenumpangPrioritas()+getJumlahPenumpangBerdiri()) +
                "\nTotal Pendapatan : Rp" + totalPendapatan +
                "\n================================";
    }
}
