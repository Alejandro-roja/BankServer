package com.atoudeft.banque;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompteClient implements Serializable {
    private String numero;
    private String nip;
    private List<CompteBancaire> comptes;

    /**
     * Crée un compte-client avec un numéro et un nip.
     *
     * @param numero le numéro du compte-client
     * @param nip le nip
     */
    public CompteClient(String numero, String nip) {
        this.numero = numero;
        this.nip = nip;
        comptes = new ArrayList<>();
    }

    /**
     * Ajoute un compte bancaire au compte-client.
     *
     * @param compte le compte bancaire
     * @return true si l'ajout est réussi
     */
    public boolean ajouter(CompteBancaire compte) {
        return this.comptes.add(compte);
    }

    public String getNumero() { return numero; }
    public List<CompteBancaire> getComptes() { return this.comptes; }

    /**
     * Verifie si le nip fournit est egal au nip du
     * compte-client.
     *
     * @param nipFournit le nip fournit
     * @return true si le nip fournit est egal au nip du compte-client
     */
    public boolean verifierNip(String nipFournit) { return this.nip.equals(nipFournit); }

    /**
     * Recupere le compte bancaire correspondant au numero donné.
     *
     * @param numeroCompte le numero du compte bancaire
     * @return le compte bancaire correspondant, null si introuvable
     */
    //Jiayi Xu
    public CompteBancaire getCompte(String numeroCompte) {
        for (CompteBancaire compte : comptes) {
            if (compte.getNumero().equals(numeroCompte)) {
                return compte;
            }
        }
        return null;
    }
}
