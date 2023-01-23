
public class YearlyRow {
    public int month; //месяц. Целое число
    public int amount; //сумма
    public boolean isExpense; //Обозначает, является ли запись тратой (true) или доходом (false)

    public YearlyRow(int month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
