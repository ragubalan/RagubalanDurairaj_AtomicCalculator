/*
 * Copyright (C) 2016
 *
 * Created on Feb 2016.
 */
package com.assignment.calculator.client;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.assignment.calculator.exception.InvalidDataException;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesParser;

/**
 * Main method
 * 
 * @author Ragubalan Durairaj
 * 
 */
public class Solution {
    private static final Logger LOG = Logger.getLogger(Solution.class.getName());

    public static void main(String[] args) {
        IChemObjectBuilder bldr = SilentChemObjectBuilder.getInstance();
        SmilesParser smipar = new SmilesParser(bldr);

        Calculate cal = new Calculate();

        try {

            Solution sol = new Solution();

            // Validate the number of test cases.
            int T = Integer.valueOf(cal.getINTVal());
            boolean validNoOfTestCases = sol.validateNoOfTestCases(T);

            while (validNoOfTestCases && T-- > 0) {
                int ch = Integer.valueOf(cal.getINTVal());

                // Validate Smiles value
                String smiles = cal.getStringVal();
                boolean validSmilesValue = sol.validateSmilesValue(smiles);

                if (validSmilesValue) {
                    // Parse smiles value
                    IAtomContainer mol = smipar.parseSmiles(smiles);

                    int prop = 0;

                    switch (ch) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        prop = Calculate.get_Prop(ch).main(mol);
                        break;
                    default:
                        throw new InvalidDataException("Character input value is not valid. The passed in value of "
                                + ch + " is not acceptable as a valid input value.");
                    }

                    // Output the result
                    cal.output.display(prop);
                }
            }
        } catch (NumberFormatException e) {
            LOG.severe("The passed in value is not a proper number. " + e.getMessage());
            cal.output.displayError("The passed in value is not a proper number. " + e.getMessage());
        } catch (InvalidSmilesException e) {
            LOG.severe("The smiles value cannot be parsed. The actual exception reads : " + e.getMessage());
            cal.output
                    .displayError("The smiles value cannot be parsed. The actual exception reads : " + e.getMessage());
        } catch (InvalidDataException e) {
            LOG.severe("One of the input values is not valid. The actual exception reads : " + e.getMessage());
            cal.output.displayError("One of the input values is not valid. The actual exception reads : "
                    + e.getMessage());
        }
        cal.output.out.close();
    }

    /**
     * Validates whether the number of test cases is within 1 - 1000.
     * 
     * @param T
     * @return
     * @throws InvalidDataException
     */
    private boolean validateNoOfTestCases(int T) throws InvalidDataException {
        if (1 <= T && T <= 1000) {
            return true;
        } else {
            throw new InvalidDataException("The program can handle only 1 - 1000 test cases. The passed in value of "
                    + T + " is not acceptable.");
        }
    }

    /**
     * Validates the SMILE value
     * 
     * @param smiles
     * @return
     * @throws InvalidDataException
     */
    private boolean validateSmilesValue(String smiles) throws InvalidDataException {
        if (smiles == null || smiles.length() < 4 || smiles.length() > 1000) {
            throw new InvalidDataException("SMILES input value is not valid. The passed in value of " + smiles
                    + " is not acceptable as a SMILE value.");
        } else {
            return true;
        }
    }
}