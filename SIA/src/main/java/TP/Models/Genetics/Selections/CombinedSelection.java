package TP.Models.Genetics.Selections;

import TP.Interfaces.ISelection;
import TP.Models.Player.BasePlayer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
public class CombinedSelection implements ISelection {

    private final Selection method1;
    private final Selection method2;

    public CombinedSelection(Selection method1, Selection method2){
        this.method1 = method1;
        this.method2 = method2;
    }

    public List<BasePlayer> makeSelection(List<BasePlayer> population) {
        //nos fijamos el índice donde corta, por ejemplo, el 10% de 200 es 20
        //entones la primer lista va de 0 a 20 y la otra de 21 a 200
        int aux = (int) (population.size() * method1.getPercentage());
        List<BasePlayer> method1List = population.subList(0, aux);
        List<BasePlayer> method2List = population.subList(aux, population.size());
        //creamos una nueva lista para guardar a los elegidos
        List<BasePlayer> ret = new ArrayList<>(population.size());
        ret.addAll(method1.makeSelection(method1List));
        ret.addAll(method2.makeSelection(method2List));
        return ret;
    }

    public void changeK(int newK){
        this.method1.setK(newK);
        this.method2.setK(newK);
    }
}
