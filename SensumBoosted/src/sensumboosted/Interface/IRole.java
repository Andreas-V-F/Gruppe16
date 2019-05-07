/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensumboosted.Interface;

/**
 *
 * @author krute
 */
public interface IRole {

    public boolean hasPermission(String s);

    public void addPermission(String s);

    public void addPermissions();

}
