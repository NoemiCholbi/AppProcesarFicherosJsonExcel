package Modelo;

public class Apx {

    private static final String VALORFIJO = "stacker createDeployment --namespace es.qnue.app-id-31446.pro --payload {\"version\":\"//stacker.central/ns/es.qnue.app-id-31446.dsg/stacks/apxalarmstack/versions/91143bb0-db83-4602-8715-60a8b75dbba1\\\",\\\"params\\\":{\\\"parameters_value\\\":\\\"{";
    private String alarmType;
    // porque si isCritical es igual a true, el valor de errorRateCritical será 10 y
    // errorRateWarn, 5. Por el contrario si isCritical es igual a false, el valor
    // de errorRateCritical será 20 y errorRateWarn, 10.
    private String errorRateCritical;
    private String errorRateWarn;
    private String isCritical;
    private String remedyGroup;
    private String responseTimeCritical; // opcional  //Valores posibles: 500, 2000, 5000, 10000
    private String responseTimeWarn = "0"; // opcional
    private String serviceOwner;
    // hacer condiciones para que sea un email
    private String supportEmail;
    // Hacer condiciones para que sean 9 números con .length
    private String supportPhone;
    private String timeWindow; // Tres valores fijos: 24H, WORK_HOURS, NON_WORK_HOURS
    private String uuaa;
    private String volumen; // Tres valores fijos: ALTO, MEDIO, BAJO.
    private String documentation; // = isCritical + " - " + "Ventana horaria " + timeWindow + " - "
    // + " Volumen de ejecuciones " + volumen;
    private String trx = alarmType;

    public Apx() {

    }

    // Constructor sin los valores opcionales
    public Apx(String alarmType, String errorRateCritical, String errorRateWarn, String remedyGroup,
            String serviceOwner, String supportEmail, String supportPhone, String timeWindow,
            String volumen, String documentation) {
        this.alarmType = alarmType;
        this.errorRateCritical = errorRateCritical;
        this.errorRateWarn = errorRateWarn;
        this.remedyGroup = remedyGroup;
        this.serviceOwner = serviceOwner;
        this.supportEmail = supportEmail;
        this.supportPhone = supportPhone;
        this.timeWindow = timeWindow;
        this.volumen = volumen;
        this.documentation = documentation;
    }

    // Constructor con los valores opcionales (aunque solo está responseTimeCritical
    // porque responseTimeWarn es siempre 0, pero añadir al to String
    public Apx(String alarmType, String errorRateCritical, String errorRateWarn, String remedyGroup,
            String responseTimeCritical, String serviceOwner, String supportEmail, String supportPhone, String timeWindow,
            String volumen, String documentation) {
        this.alarmType = alarmType;
        this.errorRateCritical = errorRateCritical;
        this.errorRateWarn = errorRateWarn;
        this.remedyGroup = remedyGroup;
        this.responseTimeCritical = responseTimeCritical;
        this.serviceOwner = serviceOwner;
        this.supportEmail = supportEmail;
        this.supportPhone = supportPhone;
        this.timeWindow = timeWindow;
        this.volumen = volumen;
        this.documentation = documentation;
    }

    public String getVALORFIJO() {
        return VALORFIJO;
    }

    public String getAlarmType() {
        uuaa = alarmType.substring(0, 4);
        trx = alarmType;
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
        //Como uuaa depende de alarmType, lo metemos dentro del set de alarmType 
        //para que se genere automáticamente al settear el valor de alarmType
        uuaa = getAlarmType().substring(0, 4);
        trx = alarmType;
    }

    public void setIsCritical(String isCritical) {
        if (isCritical.equals("CRITICO")){
            errorRateCritical = "10";
            errorRateWarn = "5";
            isCritical = "true";            
        }
        else if(isCritical.equals("NOCRITICO")){
            errorRateCritical = "20";
            errorRateWarn = "10";
            isCritical = "false"; 
        }
        this.isCritical = isCritical;
    }
    
    public String getRemedyGroup() {
        return remedyGroup;
    }

    public void setRemedyGroup(String remedyGroup) {
        this.remedyGroup = remedyGroup;
    }

    public String getResponseTimeCritical() {
        return responseTimeCritical;
    }

    public void setResponseTimeCritical(String responseTimeCritical) {
        this.responseTimeCritical = responseTimeCritical;
    }
    public String getResponseTimeWarn() {
        return responseTimeWarn;
    }
    public String getServiceOwner() {
        return serviceOwner;
    }

    public void setServiceOwner(String serviceOwner) {
        this.serviceOwner = serviceOwner;
    }

    public String getSupportEmail() {
        return supportEmail;
    }

    public void setSupportEmail(String supportEmail) {
        this.supportEmail = supportEmail;
    }

    public String getSupportPhone() {
        return supportPhone;
    }

    public void setSupportPhone(String supportPhone) {
        this.supportPhone = supportPhone;
    }

    public String getTimeWindow() {
        return timeWindow;
    }

    public void setTimeWindow(String timeWindow) {
        this.timeWindow = timeWindow;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getDocumentation() {
        String resultado = "";
        if (isCritical.equals("true")) {
            resultado = "TRX CRITICO - Ventana horaria " + timeWindow + " - Volumen de ejecuciones " + volumen;
        } else if(isCritical.equals("false")) {
            resultado = "TRX NOCRITICO - Ventana horaria " + timeWindow + " - Volumen de ejecuciones " + volumen;
        }
        return resultado;
    }

    // No hace falta un set de Documentation porque se obtiene de valores previos


    @Override
    public String toString() { // Falta hacer una condicion para que solo se imprima responseTimeCritical y
        // TimeWarn si no son null
        String resultado = "";
        String textoNoVariable1 = VALORFIJO + "\\\\\\\"" + "alarmType" + "\\\\\\\":" + "\\\\\\\"" + alarmType + "\\\\\\\","
                + "\\\\\\\"" + "errorRateCritical" + "\\\\\\\":" + "\\\\\\\"" + errorRateCritical + "\\\\\\\","
                + "\\\\\\\"" + "errorRateWarn" + "\\\\\\\":" + "\\\\\\\"" + errorRateWarn + "\\\\\\\","
                + "\\\\\\\"" + "isCritical" + "\\\\\\\":" + "\\\\\\\"" + isCritical + "\\\\\\\","
                + "\\\\\\\"" + "isRateType" + "\\\\\\\":" + "\\\\\\\"" + "true" + "\\\\\\\","
                + "\\\\\\\"" + "remedyGroup" + "\\\\\\\":" + "\\\\\\\"" + remedyGroup + "\\\\\\\",";
        String textoNoVariable2 = "\\\\\\\"" + "serviceOwner" + "\\\\\\\":" + "\\\\\\\"" + serviceOwner + "\\\\\\\","
                + "\\\\\\\"" + "supportEmail" + "\\\\\\\":" + "\\\\\\\"" + supportEmail + "\\\\\\\","
                + "\\\\\\\"" + "supportPhone" + "\\\\\\\":" + "\\\\\\\"" + supportPhone + "\\\\\\\","
                + "\\\\\\\"" + "timeWindow" + "\\\\\\\":" + "\\\\\\\"" + timeWindow + "\\\\\\\","
                + "\\\\\\\"" + "uuaa" + "\\\\\\\":" + "\\\\\\\"" + uuaa + "\\\\\\\","
                + "\\\\\\\"" + "volumen" + "\\\\\\\":" + "\\\\\\\"" + volumen + "\\\\\\\","
                + "\\\\\\\"" + "documentation" + "\\\\\\\":" + "\\\\\\\"" + getDocumentation() + "\\\\\\\","
                + "\\\\\\\"" + "trx" + "\\\\\\\":" + "\\\\\\\"" + trx + "\\\\\\\"}\\\"}}\"";

        if (getResponseTimeCritical() != null) {
            //	COMPROBAR QUE SE CUMPLEN BIEN LAS CONDICIONES
            if ((getResponseTimeCritical().equals("500") || getResponseTimeCritical().equals("2000") || getResponseTimeCritical().equals("5000") || getResponseTimeCritical().equals("10000"))) {
                resultado = textoNoVariable1
                        + "\\\\\\\"" + "responseTimeCritical" + "\\\\\\\":" + "\\\\\\\"" + responseTimeCritical + "\\\\\\\","
                        + "\\\\\\\"" + "responseTimeWarn" + "\\\\\\\":" + "\\\\\\\"" + responseTimeWarn + "\\\\\\\","
                        + textoNoVariable2;                    
            } else {
                resultado = textoNoVariable1 + textoNoVariable2;
            }
        } else {
            resultado = textoNoVariable1 + textoNoVariable2;
        }
        return resultado;

    }

}
