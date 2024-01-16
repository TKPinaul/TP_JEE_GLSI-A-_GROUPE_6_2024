package glsia6.com.compteManagement.serviceImpl;

import glsia6.com.compteManagement.entity.Compte;
import glsia6.com.compteManagement.repository.CompteRepository;
import glsia6.com.compteManagement.service.ICompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteService implements ICompteService {
    @Autowired
    private CompteRepository compteRepository;
    @Override
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    @Override
    public Compte getOneCompte(int id) {
        return compteRepository.findById(id).get();
    }

    @Override
    public Compte saveCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    @Override
    public void deleteCompte(int id) {
         compteRepository.deleteById(id);
    }


}
