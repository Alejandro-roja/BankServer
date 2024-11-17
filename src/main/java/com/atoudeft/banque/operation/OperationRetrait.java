package com.atoudeft.banque.operation;

import com.atoudeft.banque.TypeOperation;

/**
 * Represente des operations de type RETRAIT
 *
 * @author Jiayi Xu
 */
public class OperationRetrait extends Operation {
    private double montant;

    /**
     * Constructeur qui initialise le type de l'operation et le montant de depot
     *
     * @param montant Le montant de depot
     */
    public OperationRetrait(double montant) {
        super(TypeOperation.RETRAIT);
        this.montant = montant;
    }

}
