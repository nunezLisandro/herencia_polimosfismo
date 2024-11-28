import java.util.ArrayList;

// Clase abstracta Vuelo
abstract class Vuelo {
    String numeroVuelo;
    String origen;
    String destino;
    String fecha;

    public Vuelo(String numeroVuelo, String origen, String destino, String fecha) {
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
    }

    abstract double calcularPrecio();
    abstract void aplicarPromocion(double descuento);

    @Override
    public String toString() {
        return "Vuelo: " + numeroVuelo + ", Origen: " + origen + ", Destino: " + destino + ", Fecha: " + fecha;
    }
}

// Clase VueloRegular
class VueloRegular extends Vuelo {
    int numeroAsientos;
    double precioBasePorAsiento;

    public VueloRegular(String numeroVuelo, String origen, String destino, String fecha, int numeroAsientos, double precioBasePorAsiento) {
        super(numeroVuelo, origen, destino, fecha);
        this.numeroAsientos = numeroAsientos;
        this.precioBasePorAsiento = precioBasePorAsiento;
    }

    @Override
    double calcularPrecio() {
        return numeroAsientos * precioBasePorAsiento;
    }

    @Override
    void aplicarPromocion(double descuento) {
        precioBasePorAsiento -= precioBasePorAsiento * descuento / 100;
    }
}

// Clase VueloCharter
class VueloCharter extends Vuelo {
    double precioTotal;

    public VueloCharter(String numeroVuelo, String origen, String destino, String fecha, double precioTotal) {
        super(numeroVuelo, origen, destino, fecha);
        this.precioTotal = precioTotal;
    }

    @Override
    double calcularPrecio() {
        return precioTotal;
    }

    @Override
    void aplicarPromocion(double descuento) {
        precioTotal -= precioTotal * descuento / 100;
    }
}

// Clase Reservas
class Reservas {
    private final ArrayList<Vuelo> vuelos = new ArrayList<>();

    void agregarVuelo(Vuelo vuelo) {
        vuelos.add(vuelo);
    }

    double calcularPrecioTotal() {
        double total = 0;
        for (Vuelo vuelo : vuelos) {
            total += vuelo.calcularPrecio();
        }
        return total;
    }

    void aplicarPromocion(double descuento) {
        for (Vuelo vuelo : vuelos) {
            vuelo.aplicarPromocion(descuento);
        }
    }

    void mostrarVuelos() {
        for (Vuelo vuelo : vuelos) {
            System.out.println(vuelo + " - Precio: $" + vuelo.calcularPrecio());
        }
    }
}

// Clase principal SistemaReservas
public class ejercicio1 {
    public static void main(String[] args) {
        Reservas reservas = new Reservas();

        VueloRegular vuelo1 = new VueloRegular("VR101", "Bogotá", "Medellín", "2024-12-10", 150, 200);
        VueloCharter vuelo2 = new VueloCharter("VC202", "Miami", "New York", "2024-12-20", 5000);

        reservas.agregarVuelo(vuelo1);
        reservas.agregarVuelo(vuelo2);

        System.out.println("Vuelos antes de aplicar promoción:");
        reservas.mostrarVuelos();

        reservas.aplicarPromocion(10); // Aplicar 10% de descuento
        System.out.println("\nVuelos después de aplicar promoción:");
        reservas.mostrarVuelos();

        System.out.println("\nPrecio total de las reservas: $" + reservas.calcularPrecioTotal());
    }
}

