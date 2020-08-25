package TP.Interfaces;

import TP.Models.Equipment;

import java.util.Set;

public interface IService {

    Set<Equipment> getByIndex(String key);
    void setData(String key,String data);
}
