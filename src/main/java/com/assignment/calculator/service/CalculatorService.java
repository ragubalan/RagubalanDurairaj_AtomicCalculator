/*
 * Copyright (C) 2016
 *
 * Created on Feb 2016.
 */
package com.assignment.calculator.service;

import org.openscience.cdk.interfaces.IAtomContainer;

/**
 * An interface for all services used for atomic calculations
 * @author Ragubalan Durairaj
 *
 */
public interface CalculatorService {
    public int main(IAtomContainer mol);
}
