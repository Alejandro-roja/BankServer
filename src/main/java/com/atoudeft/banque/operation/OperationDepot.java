package com.atoudeft.banque.operation;

import com.atoudeft.banque.TypeOperation;

/**
 * Represente des operations de type DEPOT
 *
 * @author Jiayi Xu
 */
public class OperationDepot extends Operation {
    private double montant;

    /**
     * Constructeur qui initialise le type de l'operation et le montant de depot
     *
     * @param montant Le montant de depot
     */
    public OperationDepot(double montant) {
        super(TypeOperation.DEPOT);
        this.montant = montant;
    }

}
