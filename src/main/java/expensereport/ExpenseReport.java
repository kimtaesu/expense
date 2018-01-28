package expensereport;

import java.util.ArrayList;
import java.util.List;

import static expensereport.Expense.Type.BREAKFAST;
import static expensereport.Expense.Type.DINNER;


public class ExpenseReport {
    private List<Expense> expenses = new ArrayList<Expense>();
    private int total;
    private int mealExpenses;
    private ReportPrinter printer;

    public void printReport(ReportPrinter printer) {
        this.printer = printer;
        total = 0;
        mealExpenses = 0;
        totalUpExpenses();

        printExpensesAndTotals();
    }

    private void printExpensesAndTotals() {
        printHeader();
        printExpenses();
        printTotals();
    }

    private void printExpenses() {
        for (Expense expense : expenses) {
            printer.print(String.format("%s\t%s\t$%.02f\n",
                    expense.isOverage() ? "X" : " ",
                    expense.getName(), expense.amount / 100.0));
        }
    }

    private void totalUpExpenses() {
        for (Expense expense : expenses)
            totalUpExpense(expense);
    }

    private void totalUpExpense(Expense expense) {
        if (isMeal(expense))
            mealExpenses += expense.amount;

        total += expense.amount;
    }

    private boolean isMeal(Expense expense) {
        return expense.type == BREAKFAST || expense.type == DINNER;
    }

    private void printTotals() {
        printer.print(String.format("\nMeal expenses $%.02f", mealExpenses / 100.0));
        printer.print(String.format("\nTotal $%.02f", total / 100.0));
    }

    private void printHeader() {
        printer.print("Expenses " + getDate() + "\n");
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    private String getDate() {
        return "9/12/2002";
    }
}
