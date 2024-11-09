package com.atoudeft.banque;

public class CompteCheque extends CompteBancaire {
    /**
     * Crée un compte bancaire.
     *
     * @param numero numéro du compte
     * @param type   type du compte
     */
    public CompteCheque(String numero, TypeCompte type) {
        super(numero, type);
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
        if (montant > 0) {
            setSolde(getSolde() - montant);
        } else {
            repSolde = false;
        }
        return repSolde;
    }

    @Override
    public boolean payerFacture(String numeroFacture, double montant, String description) {
        return false;
    }

    @Override
    public boolean transferer(double montant, String numeroCompteDestinataire) {
        return false;
    }
}