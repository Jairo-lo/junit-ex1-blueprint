package ec.edu.epn;

/**
 * Clase para el cálculo de valores con impuestos.
 */
public class TaxCalculator {

    /**
     * Calculates the total amount including tax.
     * @param value The base value.
     * @param tax The tax rate (as a decimal, e.g., 0.15 for 15%).
     * @return The total value including tax.
     */
    public double calculateTax(double value, double tax) {
        return value + (value * tax);
    }
}