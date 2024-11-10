package com.atoudeft.serveur;

import com.atoudeft.banque.Banque;
import com.atoudeft.banque.CompteBancaire;
import com.atoudeft.banque.CompteClient;
import com.atoudeft.banque.CompteEpargne;
import com.atoudeft.banque.serveur.ConnexionBanque;
import com.atoudeft.banque.serveur.ServeurBanque;
import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.evenement.GestionnaireEvenement;
import com.atoudeft.commun.net.Connexion;

/**
 * Cette classe représente un gestionnaire d'événement d'un serveur. Lorsqu'un serveur reçoit un texte d'un client,
 * il crée un événement à partir du texte reçu et alerte ce gestionnaire qui réagit en gérant l'événement.
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-09-01
 */
public class GestionnaireEvenementServeur implements GestionnaireEvenement {
    private Serveur serveur;

    /**
     * Construit un gestionnaire d'événements pour un serveur.
     *
     * @param serveur Serveur Le serveur pour lequel ce gestionnaire gère des événements
     */
    public GestionnaireEvenementServeur(Serveur serveur) {
        this.serveur = serveur;
    }

    /**
     * Méthode de gestion d'événements. Cette méthode contiendra le code qui gère les réponses obtenues d'un client.
     *
     * @param evenement L'événement à gérer.
     */
    @Override
    public void traiter(Evenement evenement) {
        Object source = evenement.getSource();
        ServeurBanque serveurBanque = (ServeurBanque) serveur;
        Banque banque;
        ConnexionBanque cnx;
        String msg, typeEvenement, argument, numCompteClient, nip;
        String[] t;

        if (source instanceof Connexion) {
            cnx = (ConnexionBanque) source;
            System.out.println("SERVEUR: Recu : " + evenement.getType() + " " + evenement.getArgument());
            typeEvenement = evenement.getType();
            cnx.setTempsDerniereOperation(System.currentTimeMillis());
            switch (typeEvenement) {
                /******************* COMMANDES GÉNÉRALES *******************/
                case "EXIT": //Ferme la connexion avec le client qui a envoyé "EXIT":
                    cnx.envoyer("END");
                    serveurBanque.enlever(cnx);
                    cnx.close();
                    break;
                case "LIST": //Envoie la liste des numéros de comptes-clients connectés :
                    cnx.envoyer("LIST " + serveurBanque.list());
                    break;
                /******************* COMMANDES DE GESTION DE COMPTES *******************/
                case "NOUVEAU": //Crée un nouveau compte-client :
                    if (cnx.getNumeroCompteClient() != null) {
                        cnx.envoyer("NOUVEAU NO deja connecte");
                        break;
                    }
                    argument = evenement.getArgument();
                    t = argument.split(":");
                    if (t.length < 2) {
                        cnx.envoyer("NOUVEAU NO");
                    } else {
                        numCompteClient = t[0];
                        nip = t[1];
                        banque = serveurBanque.getBanque();
                        if (banque.ajouter(numCompteClient, nip)) {
                            //    cnx.setNumeroCompteClient(numCompteClient);
                            //    cnx.setNumeroCompteActuel(banque.getNumeroCompteParDefaut(numCompteClient));
                            cnx.envoyer("NOUVEAU OK " + t[0] + " cree");
                        } else
                            cnx.envoyer("NOUVEAU NO " + t[0] + " existe");
                    }
                    break;

                //Jiayi Xu
                case "CONNECT":
                    //1. Recuperer le numero de compte-client et le nip envoyé par le client
                    argument = evenement.getArgument();
                    t = argument.split(":");
                    if (t.length < 2) {
                        cnx.envoyer("CONNECT NO trop court");
                        break;
                    }
                    numCompteClient = t[0];
                    nip = t[1];
                    boolean dejaUtil = false;
                    for (Connexion connexion : serveurBanque.connectes) {
                        ConnexionBanque connexionBanque = (ConnexionBanque) connexion;
                        //2. Verifier qu'il n'y a pas un des connectes qui utilise deja ce compte
                        if (numCompteClient.equals(connexionBanque.getNumeroCompteClient())) {
                            dejaUtil = true;
                            break;
                        }
                    }
                    //Sinon, le serveur refuse la demande et envoie la reponse ci-dessous au client
                    if (dejaUtil) {
                        cnx.envoyer("CONNECT NO deja utilise");
                        break;
                    }

                    CompteClient cptClient = serveurBanque.getBanque().getCompteClient(numCompteClient);
                    //3. Si le compte n'existe pas ou nip incorrect
                    if (cptClient == null || !cptClient.verifierNip(nip)) {
                        //Envoyer reponse
                        cnx.envoyer("CONNECT NO compte inexistant ou nip incorrect");
                        break;
                    }

                    //4. Inscrire numero de compte-client
                    cnx.setNumeroCompteClient(numCompteClient);
                    cnx.setNumeroCompteActuel(serveurBanque.getBanque().getNumeroCompteParDefaut(numCompteClient));
                    //Envoyer reponse de succes
                    cnx.envoyer("CONNECT OK");
                    break;

                //Jiayi Xu
                case "EPARGNE":
                    if (cnx.getNumeroCompteClient() == null) {
                        cnx.envoyer("EPARGNE NO pas encore connecte");
                        break;
                    }
                    numCompteClient = cnx.getNumeroCompteClient();
                    banque = ((ServeurBanque) serveur).getBanque();

                    if (banque.possedeCompteEpargne(numCompteClient)) {
                        cnx.envoyer("EPARGNE NO");
                        break;
                    }
                    String numBancaire;
                    do {
                        numBancaire = CompteBancaire.genereNouveauNumero();
                    } while (banque.compteBancaireExiste(numBancaire));

                    CompteEpargne cptEpargne = new CompteEpargne(numBancaire, 5.0);
                    cptClient = banque.getCompteClient(numCompteClient);
                    cptClient.ajouter(cptEpargne);
                    cnx.envoyer("EPARGNE OK compte-epargne cree avec le numero: " + numBancaire);
                    break;
                /******************* TRAITEMENT PAR DÉFAUT *******************/
                default: //Renvoyer le texte recu convertit en majuscules :
                    msg = (evenement.getType() + " " + evenement.getArgument()).toUpperCase();
                    cnx.envoyer(msg);
            }
        }
    }
}