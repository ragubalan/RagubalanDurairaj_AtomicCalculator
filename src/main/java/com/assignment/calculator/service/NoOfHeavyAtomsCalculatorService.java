/*
 * Copyright (C) 2016
 *
 * Created on Feb 2016.
 */
package com.assignment.calculator.service;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;

/**
 * Number of heavy atoms calculator service
 * @author Ragubalan Durairaj
 *
 */
public class NoOfHeavyAtomsCalculatorService implements CalculatorService {

    public int main(IAtomContainer mol) {
        int numHeavies = 0;
        for (IAtom atom : mol.atoms()) {
            if (atom.getAtomicNumber() > 1) {
                numHeavies++;
            }
        }
        return numHeavies;
    }

}
