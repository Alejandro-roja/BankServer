package com.atoudeft.banque.operation;

import com.atoudeft.banque.TypeOperation;

/**
 * Represente des operations de type FACTURE
 *
 * @author Jiayi Xu
 */
public class OperationFacture extends Operation {
    private double montant;
    private String numDeFacture;
    private String description;

    /**
     * Constructeur qui initialise le type de l'operation, le numero de facture et
     * la description de l'operation
     *
     * @param montant      Le montant payé
     * @param numDeFacture Le numero de facture
     * @param description  La description pour l'operation de paiement de facture
     */
    public OperationFacture(double montant, String numDeFacture, String description) {
        super(TypeOperation.FACTURE);
        this.montant = montant;
        this.numDeFacture = numDeFacture;
        this.description = description;
    }

    public String toString() {
        return getDate() + " " + getType() + " " + this.montant +
                " No de facture: " + this.numDeFacture + " Description: " + this.description;
    }

    //getters pour le montant, numero de facture et description
    public double getMontant() { return this.montant; }
    public String getNumDeFacture() { return this.numDeFacture; }
    public String getDescription() { return this.description; }
}
