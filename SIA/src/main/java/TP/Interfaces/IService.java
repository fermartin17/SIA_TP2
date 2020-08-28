package TP.Interfaces;

import TP.Models.Equipment;

import java.util.Map;
import java.util.Set;

public interface IService {

    Map<Integer,Equipment> getData(String key);
    void setData(String key,String data);
    void deleteByKey(String key);
}
