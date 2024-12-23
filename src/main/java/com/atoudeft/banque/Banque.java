package com.atoudeft.banque;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Banque implements Serializable {
    private String nom;
    private List<CompteClient> comptes;
private String numeroCompteBanque;

    public Banque(String nom) {
        this.nom = nom;
        this.comptes = new ArrayList<>();
    }

    /**
     * Recherche un compte-client à partir de son numéro.
     *
     * @param numeroCompteClient le numéro du compte-client
     * @return le compte-client s'il a été trouvé. Sinon, retourne null
     */
    public CompteClient getCompteClient(String numeroCompteClient) {
        for (CompteClient compte : this.comptes) {
            if (compte.getNumero().equals(numeroCompteClient)) {
                return compte;
            }
        }
        return null;
    }

    /**
     * Verifie si le numero du compte-client n'est pas deja utilise
     * par un compte de la banque
     *
     * @param numeroCompteClient le numéro du compte-client
     * @return true si le numero est deja utilise, sinon retourne false
     */
    //Jiayi Xu
    public boolean compteBancaireExiste(String numeroCompteClient) {
        for (CompteClient client : this.comptes) {
            for (CompteBancaire compte: client.getComptes()) {
                if (compte.getNumero().equals(numeroCompteClient)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Vérifier qu'un compte-bancaire appartient bien au compte-client.
     *
     * @param numeroCompteBancaire numéro du compte-bancaire
     * @param numeroCompteClient   numéro du compte-client
     * @return true si le compte-bancaire appartient au compte-client
     */
    public boolean appartientA(String numeroCompteBancaire, String numeroCompteClient) {
        throw new NotImplementedException();
    }

    /**
     * Effectue un dépot d'argent dans un compte-bancaire
     *
     * @param montant      montant à déposer
     * @param numeroCompte numéro du compte
     * @return true si le dépot s'est effectué correctement
     */
    public boolean deposer(double montant, String numeroCompte) {
        throw new NotImplementedException();
    }

    /**
     * Effectue un retrait d'argent d'un compte-bancaire
     *
     * @param montant      montant retiré
     * @param numeroCompte numéro du compte
     * @return true si le retrait s'est effectué correctement
     */
    public boolean retirer(double montant, String numeroCompte) {
        throw new NotImplementedException();
    }

    /**
     * Effectue un transfert d'argent d'un compte à un autre de la même banque
     *
     * @param montant             montant à transférer
     * @param numeroCompteInitial numéro du compte d'où sera prélevé l'argent
     * @param numeroCompteFinal   numéro du compte où sera déposé l'argent
     * @return true si l'opération s'est déroulée correctement
     */
    public boolean transferer(double montant, String numeroCompteInitial, String numeroCompteFinal) {
        throw new NotImplementedException();
    }

    /**
     * Effectue un paiement de facture.
     *
     * @param montant       montant de la facture
     * @param numeroCompte  numéro du compte bancaire d'où va se faire le paiement
     * @param numeroFacture numéro de la facture
     * @param description   texte descriptif de la facture
     * @return true si le paiement s'est bien effectuée
     */
    public boolean payerFacture(double montant, String numeroCompte, String numeroFacture, String description) {
        throw new NotImplementedException();
    }

    /**
     * Crée un nouveau compte-client avec un numéro et un nip et l'ajoute à la liste des comptes.
     *
     * @param numCompteClient numéro du compte-client à créer
     * @param nip             nip du compte-client à créer
     * @return true si le compte a été créé correctement
     */
    //Jiayi Xu
    public boolean ajouter(String numCompteClient, String nip) {
        /*
         * Vérifier que le numéro a entre 6 et 8 caractères et ne contient que des lettres majuscules et des chiffres.
         * Sinon, retourner false.
         */
        if (!numCompteClient.matches("^[A-Z0-9]{6,9}$")) {
            return false;
        }

        /*
         * Vérifier que le nip a entre 4 et 5 caractères et ne contient que des chiffres.
         * Sinon, retourner false.
         */
        if (!nip.matches("^[0-9]{4,5}$")) {
            return false;
        }

        /*
         * Vérifier s'il y a déjà un compte-client avec le numéro.
         * Sinon, retourner false.
         */
        if (getCompteClient(numCompteClient) != null) {
            return false;
        }
        // Créer un compte-client avec le numéro et le nip
        CompteClient cptClient = new CompteClient(numCompteClient, nip);
        // Générer  un nouveau numéro de compte bancaire qui n'est pas déjà utilisé
        numeroCompteBanque = CompteBancaire.genereNouveauNumero();
        // Créer un compte-chèque avec ce numéro et l'ajouter au compteclient
        CompteCheque cptCheque = new CompteCheque(numeroCompteBanque);
        // Ajouter le compte-client à la liste des comptes de la banque
        cptClient.ajouter(cptCheque);
        this.comptes.add(cptClient);
        //Retourner true
        return true;
    }

    /**
     * Retourne le numéro du compte-chèque d'un client à partir de son numéro de compte-client.
     *
     * @param numCompteClient numéro de compte-client
     * @return numéro du compte-chèque du client ayant le numéro de compte-client
     */
    //Jiayi Xu
    public String getNumeroCompteParDefaut(String numCompteClient) {
        CompteClient cptClient = getCompteClient(numCompteClient);

        if (cptClient == null) {
            return null;
        }
        List<CompteBancaire> comptes = cptClient.getComptes();

        for (CompteBancaire compteBancaire : comptes) {
            if (compteBancaire instanceof CompteCheque) {
                return compteBancaire.getNumero();
            }
        }
        return null;
    }

    /**
     * Verifier si le client possede un compte-epargne
     * Sinon retourne false
     *
     * @param numCompteClient numero de compte-client
     * @return true si le client possede un compte-epargne
     */
    //Jiayi Xu
    public boolean possedeCompteEpargne (String numCompteClient) {
        CompteClient cptClient = getCompteClient(numCompteClient);
        if (cptClient != null) {
            for (CompteBancaire cptBancaire : cptClient.getComptes()) {
                if (cptBancaire instanceof CompteEpargne) {
                    return true;
                }
            }
        }
        return false;
    }
//Alejandro
    public String getNumeroCompteBanque() {
        return numeroCompteBanque;
    }
//Alejandro
    public void setNumeroCompteBanque(String numeroCompteBanque) {
        this.numeroCompteBanque = numeroCompteBanque;
    }
}