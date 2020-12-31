/**
 * @author Siti Mawaddah
 * @version 31/12/2020
 */
public class Penumpang {
    private int id;
    private String nama;
    private int umur;
    private boolean hamil;
    private int saldo;

    public Penumpang(int id, String nama, int umur, boolean hamil)
    {
        this.id = id;
        this.nama = nama;
        this.umur = umur;
        this.hamil = hamil;
        this.saldo = 10000;
    }

    public int getID()
    {
        return this.id;
    }

    public String getNama()
    {
        return this.nama;
    }

    public int getUmur()
    {
        return this.umur;
    }

    public boolean getHamil()
    {
        return this.hamil;
    }

    public boolean isPrioritas()
    {
        return (getHamil() || getUmur() < 10 || getUmur() > 60);
    }

    public int getSaldo()
    {
        return this.saldo;
    }

    public void tambahSaldo(int saldoBaru)
    {
        this.saldo += saldoBaru;
    }

    public void kurangiSaldo(int ongkos)
    {
        this.saldo -= ongkos;
    }
}
