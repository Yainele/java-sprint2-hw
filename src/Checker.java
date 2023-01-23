import java.util.HashMap;
public class Checker {
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;

    public Checker(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }
    public boolean check() {
        boolean check = true;
        HashMap<String,Integer> generalExpenditure =  monthlyReport.getGeneralExpenditure();
        HashMap<String,Integer> generalIncome =  monthlyReport.getGeneralIncome();

        for (String month : yearlyReport.expenditure.keySet()) {

                    int yearlyReportExpenditure = yearlyReport.expenditure.get(month);
                    int monthlyReportExpenditure = generalExpenditure.get(month);

                    int yearlyReportIncome = yearlyReport.income.get(month);
                    int monthlyReportIncome = generalIncome.get(month);

                    if(!compareAmounts(monthlyReportExpenditure, yearlyReportExpenditure)){
                        System.out.println("В отчетах обнаружено несоответствие по расходам. Месяц: " + month);
                        check = false;
                    }
                    if(!compareAmounts(monthlyReportIncome, yearlyReportIncome)){
                        System.out.println("В отчетах обнаружено несоответствие по доходам. Месяц: " + month);
                        check = false;
                    }
        }
        return check;
    }

    boolean compareAmounts(int monthlyAmount, int yearlyAmount){
        return monthlyAmount == yearlyAmount;
    }

}
