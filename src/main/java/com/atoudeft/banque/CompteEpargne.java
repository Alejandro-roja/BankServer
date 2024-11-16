package com.atoudeft.banque;

public class CompteEpargne extends CompteBancaire {
    private double tauxInteret;
    private static final double LIMITE = 1000.0;
    private static final double FRAIS = 2.0;





    /**
     * Crée un compte bancaire.
     *
     * @param numero numéro du compte
     */
    public CompteEpargne(String numero, Double tauxInteret) {
        super(numero, TypeCompte.EPARGNE);
        this.tauxInteret = tauxInteret;
    }

    /**
     * @param montant
     * @return
     */
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

    /**
     * @param montant
     * @return
     */
    @Override
    public boolean debiter(double montant) {
        boolean repSolde = true;
        if (montant > 0 && getSolde() >= montant)  {
            double avantSolde = getSolde();
            setSolde(getSolde() - montant);
            if (avantSolde < LIMITE) {
                setSolde(getSolde() - FRAIS);
            }
        } else {
            repSolde = false;
        }
        return false;
    }

    /*
     * Calcule les intérêts et les ajoute au solde
     */
    public void ajouterInterets() {
        double interets = getSolde() * tauxInteret/100;
        setSolde(getSolde() + interets);
    }

    /**
     * @param numeroFacture
     * @param montant
     * @param description
     * @return
     */
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

    /**
     * @param montant
     * @param numeroCompteDestinataire
     * @return
     */
    @Override
    public boolean transferer(double montant, String numeroCompteDestinataire) {
        return false;
    }


}
