package TP.Models;

import TP.Interfaces.IService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public abstract class Selection {

    private double percentage;

    public Selection(double percentage){
        this.percentage = percentage;
    }

    /**
     * Método para crear una selección de la población total
     * @param population Población sobre la cual se va a aplicar la selección
     * @return Lista conteniendo a individuos de la población original
     */
    public abstract List<BasePlayer> makeSelection(List<BasePlayer> population);
}
