
/**
 * ISvcSassc_BuscarAfiliado_DefaultFaultContractFault_FaultMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package serviceimplementation.services.sass.mediaccess._2014._08;

public class ISvcSassc_BuscarAfiliado_DefaultFaultContractFault_FaultMessage extends java.lang.Exception{

    private static final long serialVersionUID = 1468858296759L;
    
    private serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.DefaultFaultContractE faultMessage;

    
        public ISvcSassc_BuscarAfiliado_DefaultFaultContractFault_FaultMessage() {
            super("ISvcSassc_BuscarAfiliado_DefaultFaultContractFault_FaultMessage");
        }

        public ISvcSassc_BuscarAfiliado_DefaultFaultContractFault_FaultMessage(java.lang.String s) {
           super(s);
        }

        public ISvcSassc_BuscarAfiliado_DefaultFaultContractFault_FaultMessage(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ISvcSassc_BuscarAfiliado_DefaultFaultContractFault_FaultMessage(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.DefaultFaultContractE msg){
       faultMessage = msg;
    }
    
    public serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.DefaultFaultContractE getFaultMessage(){
       return faultMessage;
    }
}
    