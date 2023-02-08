package com.sdzee.tp.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Client;
import com.sdzee.tp.forms.CreationClientForm;

public class CreationClient extends HttpServlet {
    public static final String ATT_CLIENT = "client";
    public static final String ATT_FORM   = "form";

    public static final String VUE_SUCCES = "/afficherClient.jsp";
    public static final String VUE_FORM   = "/creerClient.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* À la réception d'une requête GET, simple affichage du formulaire */
        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        CreationClientForm form = new CreationClientForm();

        /* Traitement de la requête et récupération du bean en résultant */
        Client client = form.creerClient( request );

        /* Ajout du bean et de l'objet métier à l'objet requête */
        request.setAttribute( ATT_CLIENT, client );
        request.setAttribute( ATT_FORM, form );

        if ( form.getErreurs().isEmpty() ) {
            /* Si aucune erreur, alors affichage de la fiche récapitulative */
            this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward( request, response );
        } else {
            /* Sinon, ré-affichage du formulaire de création avec les erreurs */
            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
        }
    }
}

//Tp Precedent
//package com.sdzee.tp.servlets;
//
//import java.io.IOException;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import com.sdzee.tp.beans.Client;
//
//public class CreationClient extends HttpServlet {
//    /* Constantes */
//    public static final String CHAMP_NOM       = "nomClient";
//    public static final String CHAMP_PRENOM    = "prenomClient";
//    public static final String CHAMP_ADRESSE   = "adresseClient";
//    public static final String CHAMP_TELEPHONE = "telephoneClient";
//    public static final String CHAMP_EMAIL     = "emailClient";
//
//    public static final String ATT_CLIENT      = "client";
//    public static final String ATT_MESSAGE     = "message";
//    public static final String ATT_ERREUR      = "erreur";
//
//    public static final String VUE             = "/afficherClient.jsp";
//
//    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
//        /*
//         * Récupération des données saisies, envoyées en tant que paramètres de
//         * la requête GET générée à la validation du formulaire
//         */
//        String nom = request.getParameter( CHAMP_NOM );
//        String prenom = request.getParameter( CHAMP_PRENOM );
//        String adresse = request.getParameter( CHAMP_ADRESSE );
//        String telephone = request.getParameter( CHAMP_TELEPHONE );
//        String email = request.getParameter( CHAMP_EMAIL );
//
//        String message;
//        boolean erreur;
//        /*
//         * Initialisation du message à afficher : si un des champs obligatoires
//         * du formulaire n'est pas renseigné, alors on affiche un message
//         * d'erreur, sinon on affiche un message de succès
//         */
//        if ( nom.trim().isEmpty() || adresse.trim().isEmpty() || telephone.trim().isEmpty() ) {
//            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires. <br> <a href=\"creerClient.jsp\">Cliquez ici</a> pour accéder au formulaire de création d'un client.";
//            erreur = true;
//        } else {
//            message = "Client créé avec succès !";
//            erreur = false;
//        }
//        /*
//         * Création du bean Client et initialisation avec les données récupérées
//         */
//        Client client = new Client();
//        client.setNom( nom );
//        client.setPrenom( prenom );
//        client.setAdresse( adresse );
//        client.setTelephone( telephone );
//        client.setEmail( email );
//
//        /* Ajout du bean et du message à l'objet requête */
//        request.setAttribute( ATT_CLIENT, client );
//        request.setAttribute( ATT_MESSAGE, message );
//        request.setAttribute( ATT_ERREUR, erreur );
//
//        /* Transmission à la page JSP en charge de l'affichage des données */
//        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
//    }
//}