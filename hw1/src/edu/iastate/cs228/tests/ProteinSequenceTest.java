package edu.iastate.cs228.tests;

/**
 * @author Tyler Jaacks
 */

import edu.iastate.cs228.hw1.ProteinSequence;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProteinSequenceTest {
    @Test
    public void invalidCharacters() {
        char[] arr = {'b', 'j', 'o', 'u', 'x'};

        ProteinSequence sequence = new ProteinSequence(arr);
    }

    @Test
    public void validCharacters() {
        char[] arr = {'a', 't', 'c', 'd', 'e'};

        ProteinSequence sequence = new ProteinSequence(arr);
    }


    @Test
    public void seqLengthTest() {
        char[] arr = {'a', 't', 'c', 'd', 'e'};

        ProteinSequence sequence = new ProteinSequence(arr);

        assertEquals(sequence.seqLength(), 5);
    }

    @Test
    public void toStringTest() {
        char[] arr = {'a', 'a', 'a', 'a', 'a'};

        ProteinSequence sequence = new ProteinSequence(arr);

        assertEquals(sequence.toString(), "aaaaa");
    }

    @Test
    public void equals1Test() {
        char[] arr1 = {'a', 't', 'c', 'd', 'e'};
        char[] arr2 = {'a', 't', 'c', 'd', 'e'};

        ProteinSequence sequence1 = new ProteinSequence(arr1);
        ProteinSequence sequence2 = new ProteinSequence(arr2);

        assertEquals(sequence1.equals(sequence2), true);
    }

    @Test
    public void equals2Test() {
        char[] arr1 = {'a', 't', 'c', 'd', 'e'};
        char[] arr2 = {'a', 'q', 'c', 'd', 'e'};

        ProteinSequence sequence1 = new ProteinSequence(arr1);
        ProteinSequence sequence2 = new ProteinSequence(arr2);

        assertEquals(sequence1.equals(sequence2), false);
    }
}
