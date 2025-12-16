package ec.edu.epn;

public class StringValidator {

    /**
     * Checks if a string is null or empty.
     * @param input The string to check.
     * @throws IllegalArgumentException if the input is null or empty.
     */
    public void validateNotEmpty(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty.");
        }
    }

    /**
     * Checks if a string is a palindrome, ignoring spaces and case.
     * Returns false if the input is null.
     * @param input The string to check.
     * @return true if the string is a palindrome, false otherwise.
     */
    public boolean isPalindrome(String input) {
        // Manejar el caso de entrada nula
        if (input == null) {
            return false;
        }

        // Eliminar espacios y convertir a minúsculas para la validación
        String cleaned = input.replaceAll("\\s+", "").toLowerCase();

        // Invertir la cadena
        String reversed = new StringBuilder(cleaned).reverse().toString();

        // Comparar la cadena limpia con la cadena invertida
        return cleaned.equals(reversed);
    }
}