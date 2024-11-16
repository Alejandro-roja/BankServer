package com.atoudeft.banque;

public class CompteCheque extends CompteBancaire {
    /**
     * Crée un compte bancaire.
     *
     * @param numero numéro du compte
     */
    public CompteCheque(String numero) {
        super(numero, TypeCompte.CHEQUE);
    }

    @Override
    public boolean crediter(double montant) {
        boolean repSolde = true;
        if (montant > 0) {
            setSolde(getSolde() + montant);
        } else {
            repSolde = false;
        }
        return repSolde;
    }

    @Override
    public boolean debiter(double montant) {
        boolean repSolde = true;
        if (montant > 0 && getSolde() >= montant) {
            setSolde(getSolde() - montant);
        } else {
            repSolde = false;
        }
        return repSolde;
    }

    @Override
    public boolean payerFacture(String numeroFacture, double montant, String description) {
        boolean facture=false;
        if(montant>0){
            setSolde(getSolde() - montant);
            facture=true;
        }else{
            facture=false;
        }
        return facture;
    }

    @Override
    public boolean transferer(double montant, String numeroCompteDestinataire) {
        return false;
    }
}