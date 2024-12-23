package com.atoudeft.banque.operation;

import com.atoudeft.banque.TypeOperation;

import java.io.Serializable;
import java.util.Date;

/**
 * Represente les operations bancaires
 *
 * @author Jiayi Xu
 */
public abstract class Operation implements Serializable {
    private static final long serialVersionUID = 1L;

    private TypeOperation type;
    private Date date;

    /**
     * Constructeur qui initialise le type de l'operation et la date actuelle.
     *
     * @param type le type de l'opération
     */
    //Jiayi Xu
    public Operation(TypeOperation type) {
        this.type = type;
        this.date = new Date(System.currentTimeMillis());
    }

    //getters pour la date et le type
    public Date getDate(){ return this.date; }
    public TypeOperation getType() { return this.type; }

}
