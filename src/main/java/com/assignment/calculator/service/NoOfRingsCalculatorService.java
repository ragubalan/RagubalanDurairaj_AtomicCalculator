/*
 * Copyright (C) 2016
 *
 * Created on Feb 2016.
 */
package com.assignment.calculator.service;

import org.openscience.cdk.graph.ConnectivityChecker;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IAtomContainerSet;
import org.openscience.cdk.interfaces.IRingSet;
import org.openscience.cdk.ringsearch.SSSRFinder;

/**
 * Number of rings calculator service
 * @author Ragubalan Durairaj
 *
 */
public class NoOfRingsCalculatorService implements CalculatorService {

    public int main(IAtomContainer mol) {
        IAtomContainerSet components = ConnectivityChecker.partitionIntoMolecules(mol);
        int totalRingCount = 0;
        for (int i = 0; i < components.getAtomContainerCount() - 1; i++) {
            IAtomContainer component = components.getAtomContainer(i);
            IRingSet ringset = new SSSRFinder(component).findSSSR();
            totalRingCount += ringset.getAtomContainerCount();
        }
        return totalRingCount;
    }

}
