package edu.iastate.cs228.hw1;

/**
 * @author Tyler Jaacks
 */

public class DNASequence extends Sequence {
    /**
     * Constructs a new Sequence based on an array of characters.
     *
     * @param dnaarr A sequence of character based on valid elements in the set of valid genomic DNA alphabet
     */
    public DNASequence(char[] dnaarr) {
        super(dnaarr);
    }

    /**
     * Returns whether or not the letter is contained in the genomic DNA alphabet.
     *
     * @param let The letter to be checked.
     * @return Returns true if letter is in the alphabet and false if it isn't contained in the alphabet.
     */
    @Override
    public boolean isValidLetter(char let) {
        return (Character.toUpperCase(let) == 'A' ||
                Character.toUpperCase(let) == 'C' ||
                Character.toUpperCase(let) == 'G' ||
                Character.toUpperCase(let) == 'T');
    }
}