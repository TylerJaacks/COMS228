package edu.iastate.cs228.hw2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author tylerjaacks
 */
public class LexiconImpl implements Lexicon, Comparator<String> {
    /***
     * Lookup table mapping characters in lexicographical order to
     * to their input order. This is public to support automated grading. 
     */
    public CharacterValue[] characterOrdering;

    /***
     * Creates an array of CharacterValue from characterOrdering2.  Sorts
     * it using Arrays.sort().
     * @param characterOrdering2 character order from configuration file
     */
    public LexiconImpl(char[] characterOrdering2) {
        characterOrdering = new CharacterValue[characterOrdering2.length];

        for (int i = 0; i < characterOrdering2.length; i++) {
            characterOrdering[i] = new CharacterValue(i, characterOrdering2[i]);
        }

        Arrays.sort(characterOrdering, new Comparator<CharacterValue>() {
            @Override
            public int compare(CharacterValue o1, CharacterValue o2) {
                return o1.character - o2.character;
            }
        });
    }

    /***
     * Compares two words based on the config uration
     * @param a first word
     * @param b second word
     * @return negative if a<b, 0 if equal, postive if a>b
     */
    @Override
    public int compare(String a, String b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException();
        } else if (isValid(a) && isValid(b)) {
            for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
                if (getCharacterOrdering(a.charAt(i)) != getCharacterOrdering(b.charAt(i))) {
                    return getCharacterOrdering(a.charAt(i)) - getCharacterOrdering(b.charAt(i));
                }
            }
        }

        return 0;
    }

    /**
     * Uses binary search to find the order of key.
     *
     * @param key
     * @return ordering value for key or -1 if key is an invalid character.
     */
    public int getCharacterOrdering(char key) {
        int lower = 0;
        int higher = characterOrdering.length - 1;

        while (higher >= lower) {
            int middle = (lower + higher) / 2;
            char middleValue = characterOrdering[middle].character;

            if (middleValue < key) {
                lower = middle + 1;
            } else if (middleValue > key) {
                higher = middle - 1;
            } else {
                return characterOrdering[middle].value;
            }
        }

        return -1;
    }

    /**
     * Returns whether or not word is valid according to the alphabet
     * known to this lexicon.
     *
     * @param word word to be checked.
     * @return true if valid. false otherwise
     */
    public boolean isValid(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (getCharacterOrdering(word.charAt(i)) <= -1) {
                return false;
            }
        }

        return true;
    }

    /**
     * Searches characterOrdering for key via binary search.
     * This is public only to facilitate automated grading.
     *
     * @param characterOrdering the specified sort order
     * @param key               the search term
     * @return ordering value for key or -1 if key is an invalid character.
     */
    public static class CharacterValue {
        public int value;
        public char character;

        public CharacterValue(int value, char character) {
            this.value = value;
            this.character = character;
        }

        public boolean equals(Object o) {
            if (o != null && o == this) {
                return true;
            }

            CharacterValue characterValue = (CharacterValue) o;

            return (this.value == characterValue.value && this.character == characterValue.character);
        }
    }
}