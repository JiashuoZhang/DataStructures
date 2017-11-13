/** A Palindrome class checks if a given word is a palindrome.
 *  @author Jiashuo Zhang
 */

public class Palindrome {

    /** Converts a word to a Deque of characters. */
    public static Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    /** Returns true if a given word is palindrome, false otherwise. */
    public static boolean isPalindrome(String word) {
        return word != null && isPalindrome(wordToDeque(word));
    }

    private static boolean isPalindrome(Deque<Character> word) {
        return word.isEmpty() || word.size() == 1 ||
                word.removeFirst().equals(word.removeLast()) && isPalindrome(word);
    }

    /** Returns true if a given word is palindrome with a given comparison rule. */
    public static boolean isPalindrome(String word, CharacterComparator cc) {
        return word != null && isPalindrome(wordToDeque(word), cc);
    }

    private static boolean isPalindrome(Deque<Character> word, CharacterComparator cc) {
        return word.isEmpty() || word.size() == 1 ||
                cc.equalChars(word.removeFirst(), word.removeLast()) && isPalindrome(word, cc);
    }
}
