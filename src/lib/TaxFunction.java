package lib;

public class TaxFunction {

private static final int BASE_NON_TAXABLE_INCOME = 54000000;
private static final int MARRIED_ALLOWANCE = 4500000;
private static final int CHILD_ALLOWANCE = 4500000;
private static final int MAX_CHILDREN = 3;
private static final double TAX_RATE = 0.05;

	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	 public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking,
                                   int deductible, boolean isSingle, int numberOfChildren) {

        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 months working per year");
            numberOfMonthWorking = 12;
        }

        numberOfChildren = Math.min(numberOfChildren, MAX_CHILDREN);

        int grossAnnualIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int nonTaxableIncome = BASE_NON_TAXABLE_INCOME;

        if (!isSingle) {
            nonTaxableIncome += MARRIED_ALLOWANCE + (CHILD_ALLOWANCE * numberOfChildren);
        }

        int taxableIncome = grossAnnualIncome - deductible - nonTaxableIncome;

        if (taxableIncome <= 0) {
            return 0;
        }

        return (int) Math.round(TAX_RATE * taxableIncome);
    }
}
	

