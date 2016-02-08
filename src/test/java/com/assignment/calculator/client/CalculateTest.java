package com.assignment.calculator.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import org.junit.Test;

public class CalculateTest {
    public ScanInput input;

    @Test
    public void whenProperInputIsPassed_thenValidateOutputValues() {
        new ScanInput(
                "4\n1\nO=C(Oc1ccccc1C(=O)O)C\n2\nO=C(Oc1ccccc1C(=O)O)C\n3\nCN=C=O\n4\nCN1CCN(S(=O)(C2=CC=C(OCC)C(C3=NC4=C(N(C)N=C4CCC)C(N3)=O)=C2)=O)CC1");
        Solution.main(null);
        assertEquals(getFileContents(), "13.000\n13.000\n0.000\n474.578\n");
    }

    @Test
    public void whenInvalidInputIsPassed_thenValidateErrorMessage() {
        new ScanInput("1r\n1\nO=C(Oc1ccccc1C(=O)O)C");
        Solution.main(null);
        assertEquals(getFileContents(), "The passed in value is not a proper number. For input string: \"1r\"\n");
    }

    @Test
    public void whenZeroNoOfTestCasesIsPassed_thenValidateErrorMessage() {
        new ScanInput("0\n1\nO=C(Oc1ccccc1C(=O)O)C");
        Solution.main(null);
        assertEquals(getFileContents(), "One of the input values is not valid. The actual exception reads : The program can handle only 1 - 1000 test cases. The passed in value of 0 is not acceptable.\n");
    }

    @Test
    public void when1001NoOfTestCasesIsPassed_thenValidateErrorMessage() {
        new ScanInput("1001\n1\nO=C(Oc1ccccc1C(=O)O)C");
        Solution.main(null);
        assertEquals(getFileContents(), "One of the input values is not valid. The actual exception reads : The program can handle only 1 - 1000 test cases. The passed in value of 1001 is not acceptable.\n");
    }

    @Test
    public void whenNoSmilesValueIsPassed_thenValidateErrorMessage() {
        new ScanInput("1\n1");
        Solution.main(null);
        assertEquals(getFileContents(), "One of the input values is not valid. The actual exception reads : SMILES input value is not valid. The passed in value of null is not acceptable as a SMILE value.\n");
    }

    @Test
    public void whenInvalidSmilesValueIsPassed_thenValidateErrorMessage() {
        new ScanInput("1\n1\nO=C(Oc1ccccc1C(=O)O");
        Solution.main(null);
        assertTrue(getFileContents().contains("The smiles value cannot be parsed. The actual exception reads : could not parse 'O=C(Oc1ccccc1C(=O)O', Unclosed branch detected:\nO=C(Oc1ccccc1C(=O)O"));
    }
    
    @Test
    public void whenInvalidOptionIsPassed_thenValidateErrorMessage() {
        new ScanInput(
                "2\n1\nO=C(Oc1ccccc1C(=O)O)C\n6\nCN1CCN(S(=O)(C2=CC=C(OCC)C(C3=NC4=C(N(C)N=C4CCC)C(N3)=O)=C2)=O)CC1");
        Solution.main(null);
        assertEquals(getFileContents(), "13.000\nOne of the input values is not valid. The actual exception reads : Character input value is not valid. The passed in value of 6 is not acceptable as a valid input value.\n");
    }

    private String getFileContents() {
        File f = new File("output.txt");
        String contents = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                contents += line + "\n";
            }
            br.close();
        } catch (IOException e) {
            Logger LOG = Logger.getLogger(CalculateTest.class.getName());
            LOG.severe("Cannot read the output file.");
        }
        return contents;
    }

}

class ScanInput {
    public ScanInput(String content) {
        try {
            URL path = ClassLoader.getSystemResource("10_Random.txt");
            File file = new File(path.getFile());
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
