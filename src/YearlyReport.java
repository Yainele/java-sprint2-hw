
public class YearlyReport {
    public int month; //месяц. Целое число
    public int amount; //сумма
    public boolean isExpense; //Обозначает, является ли запись тратой (true) или доходом (false)

    public YearlyReport(int month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
