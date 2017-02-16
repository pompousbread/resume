/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package csci4401.service;

import java.io.Serializable;

/**
 * Defines an abstract worker as a <i>Thread</i> and some internal state.
 * <b>Provided class--do not modify</b>.
 */
public abstract class AbstractServiceWorker extends Thread {

    protected Serializable parameters;
    protected MsgQ resultQ;

    /**
     * @param parameters    initialization parameters needed for the computation.
     * @param resultQ       message queue for output.
     */
    public AbstractServiceWorker(Serializable parameters, MsgQ resultQ) {
        this.parameters = parameters;
        this.resultQ = resultQ;
    }

}
