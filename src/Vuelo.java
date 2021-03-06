import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Vuelo implements Saveable{
    private Aeropuerto aeropuertoSalida;
    private Aeropuerto aeropuertoLlegada;
    private LocalDate fechaSalida;
    private Avion avion;
    private int codigoDeVuelo;
    public Map<Asiento, Boolean> ocupacion = new HashMap<>();
    private LocalDateTime horarioSalida;
    private List<PersonalAbordo> listaPersonalAbordo = new ArrayList<>();
    private int mes;
    private int minutosDuracion;
    private List<Asiento> asientos = new ArrayList<>();


    public Vuelo(Aeropuerto aeropuertoSalida, Aeropuerto aeropuertoLlegada, int dia, int mes, int ano, int hora, int minutos,int minutosDuracion, Avion avion, int codigoDeVuelo) {
        this.fechaSalida = LocalDate.of(ano,mes,dia);
        this.mes = mes;
        this.horarioSalida = fechaSalida.atTime(hora, minutos);
        this.aeropuertoSalida = aeropuertoSalida;
        this.aeropuertoLlegada = aeropuertoLlegada;
        this.avion = avion;
        this.codigoDeVuelo = codigoDeVuelo;
        this.minutosDuracion = minutosDuracion;
        for (Asiento a : avion.getAsientos()) {
            ocupacion.put(a, false);
        }
        asientos.addAll(avion.getAsientos());
    }

    public Avion getAvion() {
        return avion;
    }

    public String getUbicacionSalida() {
        return aeropuertoSalida.getUbicacion();
    }

    public String getUbicacionLlegada() {
        return aeropuertoLlegada.getUbicacion();
    }

    public LocalDate getFechaSalida() {

        return fechaSalida;
    }

    public int getCodigoDeVuelo() {
        return codigoDeVuelo;
    }

    public int cantidadAsientosDisponibles() {
        int result = 0;
        for (Asiento a : avion.getAsientos()) {
            if (!ocupacion.get(a)) {
                result++;
            }
        }
        return result;
    }

    @Override
    public String toString() {

        String result = "Fecha: " + fechaSalida.getMonthValue() + "/" + (fechaSalida.getDayOfMonth()  + "/" + fechaSalida.getYear() +", Horario De Salida: "+ horarioSalida.getHour() +":" + horarioSalida.getMinute() +",  Desde: " + aeropuertoSalida.getUbicacion() + ",  Hasta: " + aeropuertoLlegada.getUbicacion()
                + ", Codigo: " + codigoDeVuelo);

        return result;
    }

    public void ocupar(int fila, String columna) {
        for (Asiento asiento:asientos
             ) {
        if (asiento.getFila() == fila && asiento.getColumna() == columna.charAt(0)){
            ocupacion.put(asiento, true);
            }
        }
    }
    public void ocupar(Asiento newasiento) {
        for (Asiento asiento:asientos
                ) {
            if (newasiento.equals(asiento)){
                ocupacion.put(asiento, true);
            }
        }
    }

    public List<Asiento> asientosDisponibles() {
        List<Asiento> asientosDisponibles = new ArrayList<>();
        for (Asiento a : avion.getAsientos()) {
            if (!ocupacion.get(a) ) {
                asientosDisponibles.add(a);
            }
        }
        return asientosDisponibles;
    }


    public Asiento getAsiento(int fila, char columna){
        for (Asiento asiento: avion.getAsientos()) {
            if(asiento.getFila() == (fila) && asiento.getColumna() == columna){
                return asiento;
            }
        }
        throw new RuntimeException("El avion asignado al vuelo no cuenta con ese asiento");
    }

    public boolean getOcupacion(int fila, String columna) {
        for (Asiento asiento:asientos
             ) {
            if (asiento.getColumna() ==  columna.charAt(0) && asiento.getFila() == fila){
                return ocupacion.get(asiento);
            }
        }
        throw new RuntimeException("No existe el asiento");
    }


    public int getCantidadPersonal(){return  avion.getTipoDeAvion().getCantidadDePersonalAbordo();}

    public List<PersonalAbordo> getListaPersonalAbordo() {
        return listaPersonalAbordo;
    }

    @Override
    public String getSavingFormat(){
        return aeropuertoSalida.getCodigo() + "," + aeropuertoLlegada.getCodigo() + "," + fechaSalida.getDayOfMonth() + "," + mes + ","+fechaSalida.getYear() + "," + horarioSalida.getHour() + "," + horarioSalida.getMinute()+ "," + minutosDuracion + "," + avion.getCodigo() + "," + codigoDeVuelo + ".";
    }

    public static List<Vuelo> build(List<String> elementosStr, ServerInterface server){

        List<Vuelo> elementos = new ArrayList<>();
        for (String elemento :elementosStr ) {
            int corte1 = 0;
            int corte2 = 0;
            int corte3 = 0;
            int corte4 = 0;
            int corte5 = 0;
            int corte6 = 0;
            int corte7 = 0;
            int corte8 = 0;
            int corte9 = 0;

            for (int i = 0; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte1 = i;
                    break;
                }
            }
            for (int i = corte1 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte2 = i;
                    break;
                }
            }
            for (int i = corte2 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte3 = i;
                    break;
                }
            }
            for (int i = corte3 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte4 = i;
                    break;
                }
            }
            for (int i = corte4 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte5 = i;
                    break;
                }
            }
            for (int i = corte5 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte6 = i;
                    break;
                }
            }
            for (int i = corte6 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte7 = i;
                    break;
                }
            }
            for (int i = corte7 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte8 = i;
                    break;
                }
            }
            for (int i = corte8 +1 ; i < elemento.length(); i++) {
                if (elemento.charAt(i) == ','){
                    corte9 = i;
                    break;
                }
            }

            String field1 = elemento.substring(0, corte1);
            String field2 = elemento.substring(corte1+1 , corte2);
            String field3 = elemento.substring(corte2+1, corte3);
            String field4 = elemento.substring(corte3+1, corte4);
            String field5 = elemento.substring(corte4+1, corte5);
            String field6 = elemento.substring(corte5+1, corte6);
            String field7 = elemento.substring(corte6+1, corte7);
            String field8 = elemento.substring(corte7+1, corte8);
            String field9 = elemento.substring(corte8+1, corte9);
            String field10 = elemento.substring(corte9+1, elemento.length()-1);




            Vuelo vuelo = new Vuelo(server.getAeropuerto(field1), server.getAeropuerto(field2), Integer.parseInt(field3), Integer.parseInt(field4), Integer.parseInt(field5), Integer.parseInt(field6), Integer.parseInt(field7),Integer.parseInt(field8), server.getAvion(field9), Integer.parseInt(field10));
            elementos.add(vuelo);

        }

        return elementos;
    }

    public void addTripulacion(PersonalAbordo piloto) {
        listaPersonalAbordo.add(piloto);
    }


}


