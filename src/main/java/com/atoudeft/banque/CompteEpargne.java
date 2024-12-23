package com.atoudeft.banque;

import com.atoudeft.banque.operation.OperationDepot;
import com.atoudeft.banque.operation.OperationFacture;
import com.atoudeft.banque.operation.OperationRetrait;

/**
 * Represente un compte epargne dans une banque.
 *
 * @author Jiayi Xu
 */
public class CompteEpargne extends CompteBancaire {
    private double tauxInteret;
    private static final double LIMITE = 1000.0;
    private static final double FRAIS = 2.0;

    /**
     * Crée un compte bancaire.
     *
     * @param numero numéro du compte
     */
    //Jiayi Xu
    public CompteEpargne(String numero, Double tauxInteret) {
        super(numero, TypeCompte.EPARGNE);
        this.tauxInteret = tauxInteret;
    }

    /**
     * Credite un montant au solde s'il est strictement positif.
     *
     * @param montant Le montant a ajouter au solde du compte
     * @return true si l'operation reussit, false sinon
     */
    //Jiayi Xu
    @Override
    public boolean crediter(double montant) {
        boolean repSolde = true;
        if (montant > 0) {
            setSolde(getSolde() + montant);
            OperationDepot o = new OperationDepot(montant);
            ajouterOp(o);
        } else {
            repSolde = false;
        }
        return repSolde;
    }

    /**
     * Debite le montant au solde s'il est strictement positif
     * et qu'il y a assez de fonds.
     *
     * @param montant Le montant a debiter au solde du compte
     * @return true si l'operation reussit, false sinon
     */
    //Jiayi Xu
    @Override
    public boolean debiter(double montant) {
        boolean repSolde = true;
        if (montant > 0 && getSolde() >= montant) {
            double avantSolde = getSolde();
            setSolde(getSolde() - montant);
            OperationRetrait o = new OperationRetrait(montant);
            ajouterOp(o);
            if (avantSolde < LIMITE) {
                setSolde(getSolde() - FRAIS);
            }
        }
        return repSolde;
    }

    /*
     * Calcule les intérêts et les ajoute au solde
     */
    //Jiayi Xu
    public void ajouterInterets() {
        double interets = getSolde() * tauxInteret / 100;
        setSolde(getSolde() + interets);
    }

    /**
     * Paie une facture en debitant le montant au compte.
     *
     * @param numeroFacture Le numero de la facture a payer
     * @param montant       Le montant de la facture
     * @param description   Description de la facture
     * @return true si l'operation reussit, false sinon
     */
    //Alejandro Rojas
    @Override
    public boolean payerFacture(String numeroFacture, double montant, String description) {
        boolean facture = false;
        if (montant > 0) {
            setSolde(getSolde() - montant);
            OperationFacture o = new OperationFacture(montant, numeroFacture, description);
            ajouterOp(o);
            facture = true;
        } else {
            facture = false;
        }
        return facture;
    }

    /**
     * Effectue un transfert vers un autre compte.
     *
     * @param montant                  Le montant a transferer
     * @param numeroCompteDestinataire Le numero du compte destinataire
     * @return true si l'operation reussit, false sinon
     */
    //Alejandro Rojas
    @Override
    public boolean transferer(double montant, String numeroCompteDestinataire) {
        return false;
    }


}
