package edu.iastate.cs228.tests;

/**
 * @author Tyler Jaacks
 */

import edu.iastate.cs228.hw1.DNASequence;
import edu.iastate.cs228.hw1.Sequence;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DNASequenceTest {
    @Test
    public void lowercaseDNACharacters() {
        char[] arr = {'a', 'c', 'g', 't'};

        DNASequence sequence = new DNASequence(arr);
    }

    @Test
    public void uppercaseDNACharacters() {
        char[] arr = {'A', 'C', 'G', 'T'};

        DNASequence sequence = new DNASequence(arr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lowercaseAlphabeticCharacters() {
        char[] arr = {'a', 'b', 'c', 'd', 'e'};

        DNASequence sequence = new DNASequence(arr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void uppercaseAlphabeticCharacters() {
        char[] arr = {'A', 'B', 'C', 'D', 'E'};

        DNASequence sequence = new DNASequence(arr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonalphabeticCharacters() {
        char[] arr = {'A', '!', '%', 'B', '?'};

        DNASequence sequence = new DNASequence(arr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void numericalCharacters() {
        char[] arr = {'0', '1', '2', '3', '4'};

        DNASequence sequence = new DNASequence(arr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyCharacterArray() {
        char[] arr = new char[5];

        DNASequence sequence = new DNASequence(arr);
    }

    @Test
    public void seqLengthTest() {
        char[] arr = {'a', 'c', 'g', 't'};

        Sequence sequence = new Sequence(arr);

        assertEquals(sequence.seqLength(), 4);
    }

    @Test
    public void toStringTest() {
        char[] arr = {'a', 'c', 'g', 't'};

        Sequence sequence = new Sequence(arr);

        assertEquals(sequence.toString(), "acgt");
    }

    @Test
    public void equals1Test() {
        char[] arr1 = {'a', 'c', 'g', 't'};
        char[] arr2 = {'a', 'c', 'g', 't'};

        Sequence sequence1 = new Sequence(arr1);
        Sequence sequence2 = new Sequence(arr2);

        assertEquals(sequence1.equals(sequence2), true);
    }

    @Test
    public void equals2Test() {
        char[] arr1 = {'a', 'g', 'g', 't'};
        char[] arr2 = {'a', 'c', 'g', 't'};

        Sequence sequence1 = new Sequence(arr1);
        Sequence sequence2 = new Sequence(arr2);

        assertEquals(sequence1.equals(sequence2), false);
    }

    @Test
    public void isValidLetterWithInvalidLetterTest() {
        char[] arr = {'a', 'c', 'g', 't'};

        Sequence sequence = new Sequence(arr);

        assertEquals(sequence.isValidLetter('A'), true);
    }

    @Test
    public void isValidLetterWithValidLetterTest() {
        char[] arr = {'a', 'c', 'g', 't'};

        Sequence sequence = new Sequence(arr);

        assertEquals(sequence.isValidLetter('*'), false);
    }
}