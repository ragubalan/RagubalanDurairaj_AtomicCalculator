/*
 * Copyright (C) 2016
 *
 * Created on Feb 2016.
 */
package com.assignment.calculator.service;

import org.openscience.cdk.interfaces.IAtomContainer;

/**
 * Number of bonds calculator service
 * @author Ragubalan Durairaj
 *
 */
public class NoOfBondsCalculatorService implements CalculatorService {

    public int main(IAtomContainer mol) {
        return mol.getBondCount();
    }

}
