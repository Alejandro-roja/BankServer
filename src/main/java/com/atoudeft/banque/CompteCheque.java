package com.atoudeft.banque;

import com.atoudeft.banque.operation.OperationDepot;
import com.atoudeft.banque.operation.OperationFacture;
import com.atoudeft.banque.operation.OperationRetrait;

/**
 * Represente un compte cheque dans une banque.
 *
 * @author Alejandro Rojas
 */
public class CompteCheque extends CompteBancaire {

    /**
     * Crée un compte bancaire.
     *
     * @param numero numéro du compte
     */
    //Alejandro Rojas
    public CompteCheque(String numero) {
        super(numero, TypeCompte.CHEQUE);
    }

    /**
     * Credite un montant au solde s'il est strictement positif.
     *
     * @param montant Le montant a ajouter au solde du compte
     * @return true si l'operation reussit, false sinon
     */
    //Alejandro Rojas
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
    //Alejandro Rojas
    @Override
    public boolean debiter(double montant) {
        boolean repSolde = true;
        if (montant > 0 && getSolde() >= montant) {
            setSolde(getSolde() - montant);
            OperationRetrait o = new OperationRetrait(montant);
            ajouterOp(o);
        } else {
            repSolde = false;
        }
        return repSolde;
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
        if (montant > 0 && getSolde() >= montant) {
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