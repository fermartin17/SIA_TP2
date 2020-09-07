package TP.Models;

import TP.Interfaces.ICrossover;
import TP.Interfaces.IFillMethod;
import TP.Interfaces.IService;
import TP.Models.Genetics.Mutations.Mutation;
import TP.Models.Genetics.Selections.CombinedSelection;
import TP.Models.Player.BasePlayer;
import TP.Services.RedisService;

import java.util.List;

public class FillParent implements IFillMethod {

    private final ICrossover crossover;
    private final Mutation mutation;
    private final IService service;

    public FillParent(ICrossover crossover, Mutation mutation,
                      IService service){
        this.crossover = crossover;
        this.mutation = mutation;
        this.service = service;
    }

    @Override
    public List<BasePlayer> fill(List<BasePlayer> parents,
                                       CombinedSelection parentSel,
                                       CombinedSelection replaceSel) {
        //elegir K padres a cruzar
        List<BasePlayer> selectedParents = parentSel.makeSelection(parents);
        //cruzar a los padres elegidos
        List<BasePlayer> offspring = Generation.breed(parents, crossover, (RedisService) service);
        //mutamos a los hijos
        offspring.forEach(o -> o.mutate(mutation, (RedisService) service));
        //la diferencia entre la cantidad de hijos y la generación actual
        int generationDifference = parents.size() - offspring.size();
        //si K >= N, devolvemos N hijos
        if(generationDifference <= 0) return replaceSel.makeSelection(offspring);
        //sino, agarramos (K-N) padres
        //cambiamos la cantidad de individuos que vamos a agarrar
        replaceSel.changeK(generationDifference);
        //sumamos a la lista de hijos a los seleccionados de los padres
        offspring.addAll(replaceSel.makeSelection(parents));
        return offspring;
    }

}
