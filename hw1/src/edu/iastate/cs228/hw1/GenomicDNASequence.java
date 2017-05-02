package edu.iastate.cs228.hw1;

/**
 * @author Tyler Jaacks
 */

public class GenomicDNASequence extends DNASequence {
    private boolean[] iscoding;

    public GenomicDNASequence(char[] gdnaarr) {
        super(gdnaarr);

        iscoding = new boolean[gdnaarr.length];
    }

    public void markCoding(int first, int last) {
        if (first > last || first < 0 || last >= seqLength()) {
            throw new IllegalArgumentException("Coding border is out of bound.");
        } else {
            for (int i = first; i <= last; i++) {
                iscoding[i] = true;
            }
        }
    }

    public char[] extractExons(int[] exonpos) {
        int length = 0;

        if (exonpos == null || (exonpos.length % 2) != 0) {
            throw new IllegalArgumentException("Empty array or odd number of array.");
        }

        for (int i = 0; i < exonpos.length; i++) {
            if (exonpos[i] < 0 || exonpos[i] >= seqLength()) {
                throw new IllegalArgumentException("Exon position out of bound.");
            } else if (iscoding[exonpos[i]] == false) {
                throw new IllegalStateException("Noencoding position is found.");
            }

            if (i == exonpos.length - 2) {
                if (exonpos[i] > exonpos[i + 1]) {
                    throw new IllegalArgumentException("Exon positions not in order.");
                }
            }
        }
        for (int k = 0; k < exonpos.length; k += 2) {
            int temp = exonpos[k + 1] - exonpos[k] + 1;

            length += temp;
        }

        char[] result = new char[length];

        int counter = 0;

        for (int i = 0; i < exonpos.length; i += 2) {
            int b = i;
            int e = i + 1;

            for (int j = exonpos[b]; j <= exonpos[e]; j++) {
                result[counter] = seqarr[j];
                counter++;
            }
        }

        return result;
    }
}