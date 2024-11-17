package com.atoudeft.banque.operation;

import com.atoudeft.banque.TypeOperation;

/**
 * Represente des operations de type TRANSFER
 *
 * @author Jiayi Xu
 */
public class OperationTransfer extends Operation {
    private double montant;
    private String numDesti;

    /**
     * Constructeur qui initialise le type de l'operation, le montant de depot et
     * le numero destinataire
     *
     * @param montant  Le montant de depot
     * @param numDesti Le numero de compte destinataire
     */
    public OperationTransfer(double montant, String numDesti) {
        super(TypeOperation.TRANSFER);
        this.montant = montant;
        this.numDesti = numDesti;
    }

}
