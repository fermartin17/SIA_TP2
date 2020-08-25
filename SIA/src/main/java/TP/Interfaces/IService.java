package TP.Interfaces;

import java.util.Set;

public interface IService {

    Set<String> getByIndex(String key);
    void setData(String key,String data);
}
