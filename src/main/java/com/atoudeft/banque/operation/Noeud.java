package com.atoudeft.banque.operation;

import java.io.Serializable;

/**
 * Cette classe implémente le noeud d'une liste chainée.
 *
 * @author Jiayi Xu
 */
public class Noeud implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Operation operation;
    private Noeud suivant;

    public Noeud(Operation operation) {
        this.operation = operation;
    }

    public Noeud getSuivant() { return this.suivant; }

    public Operation getOperation() { return this.operation; }

    public void setSuivant(Noeud suivant) { this.suivant = suivant; }

    public String toString() {
        return "Noeud : " + this.operation.toString();
    }
}
