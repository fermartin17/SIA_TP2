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
        //creamos una nueva lista para guardar a los elegidos
        List<BasePlayer> ret = new ArrayList<>();
        ret.addAll(method1.makeSelection(population));
        ret.addAll(method2.makeSelection(population));
        return ret;
    }

    public void changeK(int newK){
        this.method1.setK(newK);
        this.method2.setK(newK);
    }
}
