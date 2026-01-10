package com.maBibliotheque.service;

import com.maBibliotheque.repository.DashboardRepository;
import com.maBibliotheque.repository.StatistiqueRepository;

import java.util.*;

public class DashboardService {

    private DashboardRepository dashboardRepository;
    private StatistiqueRepository statistiqueRepository;

    public DashboardService(DashboardRepository dashboardRepository, StatistiqueRepository statistiqueRepository) {
        this.dashboardRepository = dashboardRepository;
        this.statistiqueRepository = new StatistiqueRepository();
    }

    public List<String> getLivresLesPlusEmpruntes() {
        return dashboardRepository.getLivresLesPlusEmpruntes();
    }

    public List<String> getAdherentsLesPlusActifs() {
        return dashboardRepository.getAdherentsLesPlusActifs();
    }

    // public List<Map<String, Object>> getNombreEmpruntsParProfil() {
    //     return dashboardRepository.getNombreEmpruntsParProfil();
    // }

    public double getTauxDeRetard() {
        return dashboardRepository.getTauxDeRetard();
    }

    public Map<String, Integer> getLivresPlusEmpruntes() {
    return statistiqueRepository.getLivresLesPlusEmpruntes();
}

public Map<String, Integer> getAdherentsActifs() {
    return statistiqueRepository.getAdherentsLesPlusActifs();
}


}
