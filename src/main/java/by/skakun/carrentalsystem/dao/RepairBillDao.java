
package by.skakun.carrentalsystem.dao;

import by.skakun.carrentalsystem.entity.RepairBill;
import by.skakun.carrentalsystem.exception.DAOException;
import java.util.List;

/**
 *
 * @author Skakun
 */
public interface RepairBillDao extends IDao{
    
    /**
     *
     * @return
     * @throws DAOException
     */
    @Override
    List<RepairBill> getAll() throws DAOException;
    
    /**
     *
     * @param id
     * @return
     * @throws DAOException
     */
    boolean repair(int id) throws DAOException;
}
