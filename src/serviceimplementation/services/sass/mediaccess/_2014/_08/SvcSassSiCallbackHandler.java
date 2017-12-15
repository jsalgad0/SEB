
/**
 * SvcSassSiCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package serviceimplementation.services.sass.mediaccess._2014._08;

    /**
     *  SvcSassSiCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class SvcSassSiCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public SvcSassSiCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public SvcSassSiCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for generarElegibilidad method
            * override this method for handling normal response from generarElegibilidad operation
            */
           public void receiveResultgenerarElegibilidad(
                    serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.GenerarElegibilidadResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from generarElegibilidad operation
           */
            public void receiveErrorgenerarElegibilidad(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerAfiliadoSiEsElegible method
            * override this method for handling normal response from obtenerAfiliadoSiEsElegible operation
            */
           public void receiveResultobtenerAfiliadoSiEsElegible(
                    serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.ObtenerAfiliadoSiEsElegibleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerAfiliadoSiEsElegible operation
           */
            public void receiveErrorobtenerAfiliadoSiEsElegible(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for autorizar method
            * override this method for handling normal response from autorizar operation
            */
           public void receiveResultautorizar(
                    serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.AutorizarResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from autorizar operation
           */
            public void receiveErrorautorizar(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for buscarAfiliado method
            * override this method for handling normal response from buscarAfiliado operation
            */
           public void receiveResultbuscarAfiliado(
                    serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.BuscarAfiliadoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from buscarAfiliado operation
           */
            public void receiveErrorbuscarAfiliado(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for buscarCitasDeClinicaConFecha method
            * override this method for handling normal response from buscarCitasDeClinicaConFecha operation
            */
           public void receiveResultbuscarCitasDeClinicaConFecha(
                    serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.BuscarCitasDeClinicaConFechaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from buscarCitasDeClinicaConFecha operation
           */
            public void receiveErrorbuscarCitasDeClinicaConFecha(java.lang.Exception e) {
            }
                


    }
    