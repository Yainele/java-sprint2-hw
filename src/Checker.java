import java.util.HashMap;
public class Checker {
    public MonthlyReportManager monthlyReportManager;
    public YearlyReportManager yearlyReportManager;
    boolean check = true;

    public Checker(MonthlyReportManager monthlyReportManager, YearlyReportManager yearlyReportManager) {
        this.monthlyReportManager = monthlyReportManager;
        this.yearlyReportManager = yearlyReportManager;
    }
    public boolean check() {
        HashMap<String,Integer> generalExpenditure =  monthlyReportManager.getGeneralExpenditure();
        HashMap<String,Integer> generalIncome =  monthlyReportManager.getGeneralIncome();
        yearlyReportManager.qualifier();

        for (String month : yearlyReportManager.expenditure.keySet()) {

                    int yearlyReportExpenditure = yearlyReportManager.expenditure.get(month);
                    int monthlyReportExpenditure = generalExpenditure.get(month);

                    int yearlyReportIncome = yearlyReportManager.income.get(month);
                    int monthlyReportIncome = generalIncome.get(month);

                    if(!compareAmounts(monthlyReportExpenditure, yearlyReportExpenditure)){
                        System.out.println("В отчетах обнаружено несоответствие по расходам. Месяц: " + month);
                        check = false;
                        continue;
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
