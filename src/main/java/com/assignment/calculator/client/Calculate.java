/*
 * Copyright (C) 2016
 *
 * Created on Feb 2016.
 */
package com.assignment.calculator.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;

import com.assignment.calculator.service.CalculatorService;
import com.assignment.calculator.service.MoleculeWeightCalculatorService;
import com.assignment.calculator.service.NoOfBondsCalculatorService;
import com.assignment.calculator.service.NoOfHeavyAtomsCalculatorService;
import com.assignment.calculator.service.NoOfRingsCalculatorService;

/**
 * Service which parses the input and has a factory method incorporated.
 * 
 * @author Ragubalan Durairaj
 * 
 */
public class Calculate {
    private Logger LOG = Logger.getLogger(Calculate.class.getName());

    private Scanner scanner;
    public Output output = new Output();

    class Output {
        private Logger LOG = Logger.getLogger(Output.class.getName());
        PrintWriter out;

        Output() {
            try {
                File f = new File("output.txt");
                out = new PrintWriter(new FileWriter(f));
            } catch (IOException e) {
                LOG.severe("Cannot open output file. the actual exception reads : " + e.getMessage());
            }
        }

        public void display(int value) {
            if (value != 0 && value % 10 == String.valueOf(value).length() - 1) {
                String strValue = String.valueOf(value);
                strValue = strValue.substring(0, strValue.length() - 1);
                int insertDecimalAt = strValue.length() - 3;
                strValue = strValue.substring(0, insertDecimalAt) + "." + strValue.substring(insertDecimalAt);
                out.print(strValue);
            } else {
                out.print(String.format("%.3f%n", Float.valueOf(value)));
            }
        }

        public void displayError(String value) {
            out.print(value);
        }
    };

    public Calculate() {
        try {
            URL path = ClassLoader.getSystemResource("10_Random.txt");
            File file = new File(path.getFile());
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            LOG.severe("Input file is not found in class path. the actual exception reads : " + e.getMessage());
        }
    }

    public String getINTVal() {
        if (scanner.hasNextInt()) {
            return String.valueOf(scanner.nextInt());
        }
        return scanner.next();
    }

    public String getStringVal() {
        if (scanner.hasNext()) {
            return scanner.next();
        }
        return null;
    }

    public static CalculatorService get_Prop(int ch) {
        CalculatorService service = null;
        switch (ch) {
        case 1:
            service = new NoOfHeavyAtomsCalculatorService();
            break;
        case 2:
            service = new NoOfBondsCalculatorService();
            break;
        case 3:
            service = new NoOfRingsCalculatorService();
            break;
        case 4:
            service = new MoleculeWeightCalculatorService();
            break;
        }
        return service;
    }
}
