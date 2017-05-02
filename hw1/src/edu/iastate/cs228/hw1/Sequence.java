package edu.iastate.cs228.hw1;

/**
 * @author Tyler Jaacks
 */

public class Sequence {
    protected char[] seqarr;

    /**
     * Constructs a new Sequence based on an array of characters.
     *
     * @param sarr A sequence of character based on valid elements in the set of valid genomic DNA alphabet
     */
    public Sequence(char[] sarr) {
        seqarr = new char[sarr.length];

        boolean isValid = true;

        for (int i = 0; i < seqLength(); i++) {
            if (!isValidLetter(sarr[i])) {
                isValid = false;
            }
        }

        if (isValid) {
            for (int i = 0; i < seqLength(); i++) {
                seqarr[i] = sarr[i];
            }

        } else {
            throw new IllegalArgumentException("Invalid sequence letter for class " + this.getClass() + ".");
        }
    }

    /**
     * Get the length of the sequence.
     *
     * @return Returns the length of the sequence.
     */
    public int seqLength() {
        return seqarr.length;
    }

    /**
     * Gets the sequence.
     *
     * @return Returns the sequence.
     */
    public char[] getSeq() {
        return seqarr;
    }

    /**
     * Gets the string representation of a sequence.
     *
     * @return Returns the string representation of a sequence.
     */
    public String toString() {
        String seq = "";

        for (int i = 0; i < seqarr.length; i++) {
            seq += seqarr[i];
        }

        return seq;
    }

    /**
     * Determines whether or not to sequence are equal.
     *
     * @param obj The object that is being compared.
     * @return Returns true if the two objects are equal.
     */
    public boolean equals(Object obj) {
        Sequence sequence = (Sequence) obj;

        if (sequence.toString().equals(this.toString()) && sequence != null) {
            return true;
        }

        return false;
    }

    /**
     * Returns whether or not the letter is contained in the  DNA alphabet.
     *
     * @param let The letter to be checked.
     * @return Returns true if letter is in the alphabet and false if it isn't contained in the alphabet.
     */
    public boolean isValidLetter(char let) {
        if (Character.isUpperCase(let)) {
            return true;
        } else if (Character.isLowerCase(let)) {
            return true;
        }

        return false;
    }
}