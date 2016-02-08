/*
 * Copyright (C) 2016
 *
 * Created on Feb 2016.
 */
package com.assignment.calculator.service;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IMolecularFormula;
import org.openscience.cdk.tools.manipulator.MolecularFormulaManipulator;

/**
 * Molecular weight calculator service
 * @author Ragubalan Durairaj
 *
 */
public class MoleculeWeightCalculatorService implements CalculatorService {

    public int main(IAtomContainer mol) {
        IMolecularFormula formula = MolecularFormulaManipulator.getMolecularFormula(mol);
        double mass = MolecularFormulaManipulator.getNaturalExactMass(formula);

        BigDecimal bd = new BigDecimal(mass);
        bd = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
        
        String formattedOutput = String.valueOf(bd.doubleValue()).replace('.', '_');
        int molecularWeight = 0;
        
        try{
            molecularWeight = Integer.parseInt(formattedOutput);
        }
        catch(NumberFormatException ex){
            Pattern p = Pattern.compile("\"([0-9]{0,})(_)([0-9]{1,})\"");
            Matcher m = p.matcher(ex.getMessage());
            while (m.find()) {
                String s1 = m.group(1);
                String s3 = m.group(3);
                molecularWeight = Integer.parseInt(s1 + s3 + (s1 + s3).length());
            }
        }
        return molecularWeight;
    }

}
