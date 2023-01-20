import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class YearlyReportManager {
    HashMap<String, Integer> income = new HashMap<>();
    HashMap<String, Integer> expenditure = new HashMap<>();
    HashMap<String, Integer> incomePerMonth = new HashMap<>();
    public ArrayList<YearlyReport> yearlyReportsList = new ArrayList<>();
    int year = 2021;
    public void readYearFile() {
        String path = "resources/y.2021.csv";
        List<String> content = readFileContents(path);
        for (int i = 1; i < content.size(); i++) {

            String line = content.get(i);
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            YearlyReport yearlyReport = new YearlyReport(month, amount, isExpense);
            yearlyReportsList.add(yearlyReport);
        }
    }
    boolean isYearlyReportsRead(){
        boolean isRead = true;
        if(yearlyReportsList.isEmpty()){
            System.out.println("Сначала нужно считать отчет за год: " + year);
            isRead = false;
        }
        return isRead;
    }

    public void qualifier(){
        income = new HashMap<>();
        expenditure = new HashMap<>();
        for (YearlyReport yearlyReport : yearlyReportsList) {
            if(!yearlyReport.isExpense){
                income.put(getMonthByNumber(yearlyReport.month), yearlyReport.amount);
            }
            else {
                expenditure.put(getMonthByNumber(yearlyReport.month), yearlyReport.amount);
            }
        }
    }

    public void getIncomePerMonth(){
        for (String month : income.keySet()) {
                    int incomeSum = income.get(month) - expenditure.get(month);
                    incomePerMonth.put(month,incomeSum);
        }
    }
    public Integer getAvgValue(HashMap<String,Integer> fileInfo){
        int avgValue = 0;
        for(Integer amount : fileInfo.values()){
            avgValue += amount;
        }
        avgValue /= fileInfo.size();
        return avgValue;
    }
    void printYearInfo() {

        System.out.println("Рассматриваемый год: " + year);
        System.out.println("Прибыль по каждому месяцу - " + "\n");

        qualifier();
        getIncomePerMonth();
        for (String month : incomePerMonth.keySet()) {
            System.out.println("Рассматриваемый месяц: " + month);
            System.out.println("Прибыль: " + incomePerMonth.get(month) + "\n");
        }

        System.out.println("Средний расход за все месяцы в году: " + getAvgValue(expenditure));
        System.out.println("Средний доход за все месяцы в году: " + getAvgValue(income) + "\n");
    }

    public String getMonthByNumber(int monthNumber) {
        if (monthNumber == 1) {
            return "Январь";
        } else if (monthNumber == 2) {
            return "Февраль";
        } else if (monthNumber == 3) {
            return "Март";
        } else {
            return "Январь";
        }
    }

    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}
