package TP.Models;

import TP.Interfaces.ICrossover;
import TP.Interfaces.IFillMethod;
import TP.Interfaces.IMutation;
import TP.Interfaces.IService;
import TP.Models.Genetics.Mutations.Mutation;
import TP.Models.Genetics.Selections.CombinedSelection;
import TP.Models.Player.BasePlayer;
import TP.Services.RedisService;

import java.util.List;

public class FillAll implements IFillMethod {

    private final ICrossover crossover;
    private final Mutation mutation;
    private final IService service;

    public FillAll(ICrossover crossover, Mutation mutation,
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
        //mutar los hijos
        offspring.forEach(o -> o.mutate(mutation, (RedisService) service));
        //añadir a todos los hijos a la población actual
        parents.addAll(offspring);
        //agarrar N individuos haciendo la selección con métodos 3 y 4
        return replaceSel.makeSelection(selectedParents);
    }
}
