package com.atoudeft.banque.operation;

import java.io.Serializable;

/**
 * Cette classe implemente une Pile chaineee
 *
 * @author Jiayi Xu
 */
public class PileChainee implements Serializable {
    private static final long serialVersionUID = 1L;

    private Noeud tete;
    private int taille;

    public PileChainee() {
        this.tete = null;
        this.taille = 0;
    }

    // accesseurs
    public boolean estVide() { return taille == 0; }
    public int getTaille() { return this.taille; }

    /**
     * Ajoute une operation à la tête de la liste.
     *
     * @param o L'operation a empiler
     */
    public void empiler(Operation o) {
        Noeud nouveau = new Noeud(o);
        if (tete == null) {
            tete = nouveau;
        } else {
            nouveau.setSuivant(tete);
            tete = nouveau;
        }
        taille++;
    }

    /**
     * Retirer une operation de la pile.
     *
     * @return L'operation depilée, null si la pile est vide.
     */
    public Operation depiler() {
        if (estVide()) {
            return null;
        }

        Operation contenu = tete.getOperation();
        taille--;

        if (taille == 0) {
            tete = null;
        } else {
            tete = tete.getSuivant();
        }
        return contenu;
    }

    /**
     * Retourne l'operation au sommet sans la retirer.
     *
     * @return L'operation au sommet
     */
    public Operation regarder() {
        if (estVide()) {
            return null;
        }
        return tete.getOperation();
    }
}
