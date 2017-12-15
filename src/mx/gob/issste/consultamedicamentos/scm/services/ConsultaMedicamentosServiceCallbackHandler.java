
/**
 * ConsultaMedicamentosServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package mx.gob.issste.consultamedicamentos.scm.services;

    /**
     *  ConsultaMedicamentosServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ConsultaMedicamentosServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ConsultaMedicamentosServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ConsultaMedicamentosServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for consultaMedicamentos method
            * override this method for handling normal response from consultaMedicamentos operation
            */
           public void receiveResultconsultaMedicamentos(
                    mx.gob.issste.consultamedicamentos.scm.services.ConsultaMedicamentosServiceStub.ConsultaMedicamentosResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultaMedicamentos operation
           */
            public void receiveErrorconsultaMedicamentos(java.lang.Exception e) {
            }
                


    }
    