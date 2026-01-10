package com.maBibliotheque;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.maBibliotheque.service.EmpruntService;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmpruntService service = (EmpruntService) context.getBean("empruntService");

        // Exemple d'appel
        String resultat = service.emprunterLivre(1, 1);
        System.out.println(resultat);
    }
}
