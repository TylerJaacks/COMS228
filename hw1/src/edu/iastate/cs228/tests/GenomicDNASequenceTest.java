package edu.iastate.cs228.tests;

/**
 * @author Tyler Jaacks
 */

import edu.iastate.cs228.hw1.GenomicDNASequence;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GenomicDNASequenceTest {
    @Test
    public void markEncoding() {
        String dnastr = new String("CTTCCATGCCGCCACCGCTGCAGATTGACGACGTCGTCTACGAAACTCAGATGGACAAGGC");
        GenomicDNASequence gseq = new GenomicDNASequence(dnastr.toCharArray());

        int[] parr = {5, 20, 30, 50};

        gseq.markCoding(5, 50);

        System.out.println(gseq.toString());

        assertEquals(gseq.toString(), "CTTCCATGCCGCCACCGCTGCAGATTGACGACGTCGTCTACGAAACTCAGATGGACAAGGC");
    }

    @Test
    public void extractExons() {
        String demodna = new String("AATGCCAGTCAGCATAGCGTAGACT");

        int[] ardemo = {1, 5, 8, 10, 13, 16};

        GenomicDNASequence gdemo = new GenomicDNASequence(demodna.toCharArray());

        gdemo.markCoding(1, 16);

        edu.iastate.cs228.hw1.CodingDNASequence cdemo = new edu.iastate.cs228.hw1.CodingDNASequence(gdemo.extractExons(ardemo));

        assertEquals(cdemo.toString(), "ATGCCTCAATAG");
    }
}
