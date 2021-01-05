package com.pokemon.app.service.common;

import com.pokemon.app.model.NotEnoughMoneyException;
import com.pokemon.app.model.Trainer;
import com.pokemon.app.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerAccessService {

    private LoginService loginService;
    private TrainerRepository trainerRepository;

    public TrainerAccessService(LoginService loginService, TrainerRepository trainerRepository) {
        this.loginService = loginService;
        this.trainerRepository = trainerRepository;
    }

    public Optional<Trainer> getLoggedTrainer() {
        return Optional.ofNullable(loginService.getLoggedUser().getTrainer());
    }

    public void save(Trainer trainer) {
        trainerRepository.save(trainer);
    }

    public void addMoneyToTrainer(){
        Trainer trainer = getLoggedTrainer().orElseThrow( () -> new TrainerAccessServiceException("Brak zalogowanego użytkownika lub jego trenera"));
        trainer.addMoney();
        trainerRepository.save(trainer);
    }

    public void subtractMoneyFromTrainer(int moneyToSubtract){
        Trainer trainer = getLoggedTrainer().orElseThrow( () -> new TrainerAccessServiceException("Brak zalogowanego użytkownika lub jego trenera"));

        if(trainer.getMoney()>0){
            trainer.subtractMoney(moneyToSubtract);
            trainerRepository.save(trainer);
        }else{
            throw new NotEnoughMoneyException("Masz za mało pieniędzy!");
        }

    }

}
