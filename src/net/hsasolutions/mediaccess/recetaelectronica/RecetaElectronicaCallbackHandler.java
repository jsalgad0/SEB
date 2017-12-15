
/**
 * RecetaElectronicaCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package net.hsasolutions.mediaccess.recetaelectronica;

    /**
     *  RecetaElectronicaCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class RecetaElectronicaCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public RecetaElectronicaCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public RecetaElectronicaCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for sesionReceta method
            * override this method for handling normal response from sesionReceta operation
            */
           public void receiveResultsesionReceta(
                    net.hsasolutions.mediaccess.recetaelectronica.RecetaElectronicaStub.SesionRecetaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sesionReceta operation
           */
            public void receiveErrorsesionReceta(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for publicacionDeRecetasElectronicas method
            * override this method for handling normal response from publicacionDeRecetasElectronicas operation
            */
           public void receiveResultpublicacionDeRecetasElectronicas(
                    net.hsasolutions.mediaccess.recetaelectronica.RecetaElectronicaStub.PublicacionDeRecetasElectronicasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from publicacionDeRecetasElectronicas operation
           */
            public void receiveErrorpublicacionDeRecetasElectronicas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for busquedaMedicamentos method
            * override this method for handling normal response from busquedaMedicamentos operation
            */
           public void receiveResultbusquedaMedicamentos(
                    net.hsasolutions.mediaccess.recetaelectronica.RecetaElectronicaStub.BusquedaMedicamentosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from busquedaMedicamentos operation
           */
            public void receiveErrorbusquedaMedicamentos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for seleccionDeMedicamentos method
            * override this method for handling normal response from seleccionDeMedicamentos operation
            */
           public void receiveResultseleccionDeMedicamentos(
                    net.hsasolutions.mediaccess.recetaelectronica.RecetaElectronicaStub.SeleccionDeMedicamentosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from seleccionDeMedicamentos operation
           */
            public void receiveErrorseleccionDeMedicamentos(java.lang.Exception e) {
            }
                


    }
    