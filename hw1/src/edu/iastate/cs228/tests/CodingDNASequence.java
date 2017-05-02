package edu.iastate.cs228.tests;

/**
 * @author Tyler Jaacks
 */

import edu.iastate.cs228.hw1.GenomicDNASequence;
import edu.iastate.cs228.hw1.ProteinSequence;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CodingDNASequence {
    @Test
    public void checkStartCodon() {
        String demodna = new String("AATGCCAGTCAGCATAGCGTAGACT");

        int[] ardemo = {1, 5, 8, 10, 13, 16};

        GenomicDNASequence gdemo = new GenomicDNASequence(demodna.toCharArray());

        gdemo.markCoding(1, 16);

        edu.iastate.cs228.hw1.CodingDNASequence cdemo = new edu.iastate.cs228.hw1.CodingDNASequence(gdemo.extractExons(ardemo));

        assertEquals(cdemo.checkStartCodon(), true);
    }

    @Test
    public void translate() {
        String demodna = new String("AATGCCAGTCAGCATAGCGTAGACT");

        int[] ardemo = {1, 5, 8, 10, 13, 16};

        GenomicDNASequence gdemo = new GenomicDNASequence(demodna.toCharArray());

        gdemo.markCoding(1, 16);

        edu.iastate.cs228.hw1.CodingDNASequence cdemo = new edu.iastate.cs228.hw1.CodingDNASequence(gdemo.extractExons(ardemo));

        ProteinSequence aademo = new ProteinSequence(cdemo.translate());

        assertEquals(aademo.toString().trim(), "MPQ");
    }
}
