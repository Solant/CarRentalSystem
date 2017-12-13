package by.skakun.carrentalsystem.command;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Skakun
 * Interface for all commands
 */
public interface ActionCommand {

    /**
     *
     * @param request from jsp
     * @return page address
     */
    String execute(HttpServletRequest request);

}
