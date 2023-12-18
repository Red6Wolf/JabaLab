public class Palindrome {

    public static String reverseString(String s) {
        String reversed = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            reversed += s.charAt(i);
        }
        return reversed;
    }

    public static boolean isPalindrome(String s) {
        String reversed = reverseString(s);
        return s.equalsIgnoreCase(reversed);
    }

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String input = args[i];
            boolean isPal = isPalindrome(input);
            System.out.println("Is \"" + input + "\" a palindrome? " + isPal);
        }
    }
}
