/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observers;

/**
 *
 * @author Vlad
 */
public interface Subject {
    void addObserver(VObserver observer);
    void removeObserver(VObserver observer);
    void notifyObservers(Object subject);
}
