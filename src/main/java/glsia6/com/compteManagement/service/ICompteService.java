package glsia6.com.compteManagement.service;

import glsia6.com.compteManagement.entity.Compte;

import java.util.List;

public interface ICompteService {
    public List<Compte> getAllComptes();
    public Compte getOneCompte(int id);
    public Compte saveCompte(Compte compte);
    public void deleteCompte(int id);
}
