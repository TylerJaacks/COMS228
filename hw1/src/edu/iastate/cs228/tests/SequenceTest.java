package edu.iastate.cs228.tests;

/**
 * @author Tyler Jaacks
 */

import edu.iastate.cs228.hw1.Sequence;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SequenceTest {
    @Test
    public void lowercaseAlphabeticCharacters() {
        char[] arr = {'a', 'b', 'c', 'd', 'e'};

        Sequence sequence = new Sequence(arr);
    }

    @Test
    public void uppercaseAlphabeticCharacters() {
        char[] arr = {'A', 'B', 'C', 'D', 'E'};

        Sequence sequence = new Sequence(arr);
    }

    @Test
    public void nonalphabeticCharacters() {
        char[] arr = {'A', '!', '%', 'B', '?'};

        Sequence sequence = new Sequence(arr);
    }

    @Test
    public void numericalCharacters() {
        char[] arr = {'0', '1', '2', '3', '4'};

        Sequence sequence = new Sequence(arr);
    }

    @Test
    public void emptyCharacterArray() {
        char[] arr = new char[5];

        Sequence sequence = new Sequence(arr);
    }

    @Test
    public void seqLengthTest() {
        char[] arr = {'a', 'b', 'c', 'd', 'e'};

        Sequence sequence = new Sequence(arr);

        assertEquals(sequence.seqLength(), 5);
    }

    @Test
    public void toStringTest() {
        char[] arr = {'a', 'b', 'c', 'd', 'e'};

        Sequence sequence = new Sequence(arr);

        assertEquals(sequence.toString(), "abcde");
    }

    @Test
    public void equals1Test() {
        char[] arr1 = {'a', 'b', 'c', 'd', 'e'};
        char[] arr2 = {'a', 'b', 'c', 'd', 'e'};

        Sequence sequence1 = new Sequence(arr1);
        Sequence sequence2 = new Sequence(arr2);

        assertEquals(sequence1.equals(sequence2), true);
    }

    @Test
    public void equals2Test() {
        char[] arr1 = {'a', 'b', 'c', 'd', 'e'};
        char[] arr2 = {'a', 'b', 'd', 'd', 'e'};

        Sequence sequence1 = new Sequence(arr1);
        Sequence sequence2 = new Sequence(arr2);

        assertEquals(sequence1.equals(sequence2), false);
    }

    @Test
    public void isValidLetterWithInvalidLetterTest() {
        char[] arr = {'a', 'b', 'c', 'd', 'e'};

        Sequence sequence = new Sequence(arr);

        assertEquals(sequence.isValidLetter('A'), true);
    }

    @Test
    public void isValidLetterWithValidLetterTest() {
        char[] arr = {'a', 'b', 'c', 'd', 'e'};

        Sequence sequence = new Sequence(arr);

        assertEquals(sequence.isValidLetter('*'), false);
    }
}