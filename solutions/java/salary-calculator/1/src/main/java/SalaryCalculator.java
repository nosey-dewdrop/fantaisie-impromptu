
public class SalaryCalculator {
    final double BASE_SALARY = 1000;
    double salaryMultiply = 1;
    double prodMultiplier = 10;
    final double MAX_SALARY = 2000;
    
    public double salaryMultiplier(double daysSkipped) {
        return salaryMultiply = daysSkipped >= 5 ? 0.85 : salaryMultiply;
    }

    public double bonusMultiplier(double productsSold) {
        return productsSold >= 20 ? 13 : prodMultiplier;
    }

    public double bonusForProductsSold(double productsSold) {
        double multiplier = bonusMultiplier(productsSold);
        return productsSold*multiplier;
    }

    public double finalSalary(double daysSkipped, double productsSold) {
        double bonus = bonusForProductsSold(productsSold);
        double finalSalary = BASE_SALARY*salaryMultiplier(daysSkipped) + bonus;
        
        return finalSalary > MAX_SALARY ? MAX_SALARY : finalSalary;
    } 
}