package edu.iastate.cs228.hw1;

/**
 * @author Tyler Jaacks
 */

public class ProteinSequence extends Sequence {
    /**
     * Constructs a new ProteinSequence based on an array of characters.
     *
     * @param psarr A sequence of character based on valid elements in the set of valid protein sequence alphabet
     */
    public ProteinSequence(char[] psarr) {
        super(psarr);
    }

    /**
     * Returns whether or not the letter is contained in the protein sequence alphabet.
     *
     * @param aa The letter to be checked.
     * @return Returns true if letter is in the alphabet and false if it isn't contained in the alphabet.
     */
    @Override
    public boolean isValidLetter(char aa) {
        if ((Character.toUpperCase(aa) == 'B')) {
            return true;
        } else if ((Character.toUpperCase(aa) == 'J')) {
            return false;
        } else if ((Character.toUpperCase(aa) == 'O')) {
            return false;
        } else if ((Character.toUpperCase(aa) == 'U')) {
            return false;
        } else if ((Character.toUpperCase(aa) == 'X')) {
            return false;
        } else if ((Character.toUpperCase(aa) == 'Z')) {
            return false;
        } else if ((!Character.isAlphabetic(aa))) {
            return false;
        }

        return true;
    }
}