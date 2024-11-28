import java.util.ArrayList;

// Clase abstracta MetodoPago
abstract class MetodoPago {
    String titular;
    String numero;

    public MetodoPago(String titular, String numero) {
        this.titular = titular;
        this.numero = numero;
    }

    abstract void realizarPago(double monto);
    abstract void cancelarPago();

    @Override
    public String toString() {
        return "Titular: " + titular + ", Número: " + numero;
    }
}

// Clase TarjetaCredito
class TarjetaCredito extends MetodoPago {
    String fechaExpiracion;
    String codigoSeguridad;

    public TarjetaCredito(String titular, String numero, String fechaExpiracion, String codigoSeguridad) {
        super(titular, numero);
        this.fechaExpiracion = fechaExpiracion;
        this.codigoSeguridad = codigoSeguridad;
    }

    @Override
    void realizarPago(double monto) {
        System.out.println("Pago de $" + monto + " realizado con tarjeta de crédito.");
    }

    @Override
    void cancelarPago() {
        System.out.println("Pago con tarjeta de crédito cancelado.");
    }
}

// Clase PayPal
class PayPal extends MetodoPago {
    String correoElectronico;

    public PayPal(String titular, String numero, String correoElectronico) {
        super(titular, numero);
        this.correoElectronico = correoElectronico;
    }

    @Override
    void realizarPago(double monto) {
        System.out.println("Pago de $" + monto + " realizado con PayPal.");
    }

    @Override
    void cancelarPago() {
        System.out.println("Pago con PayPal cancelado.");
    }
}

// Clase Pagos
class Pagos {
    private final ArrayList<MetodoPago> metodosPago = new ArrayList<>();

    void agregarMetodoPago(MetodoPago metodoPago) {
        metodosPago.add(metodoPago);
    }

    void realizarPagos(double monto) {
        for (MetodoPago metodoPago : metodosPago) {
            metodoPago.realizarPago(monto);
        }
    }

    void cancelarPagos() {
        for (MetodoPago metodoPago : metodosPago) {
            metodoPago.cancelarPago();
        }
    }

    void mostrarMetodosPago() {
        for (MetodoPago metodoPago : metodosPago) {
            System.out.println(metodoPago);
        }
    }
}

// Clase principal SistemaPagos
public class ejercicio2 {
    public static void main(String[] args) {
        Pagos pagos = new Pagos();

        TarjetaCredito tarjeta = new TarjetaCredito("Juan Pérez", "1234-5678-9012-3456", "12/26", "123");
        PayPal paypal = new PayPal("Ana López", "paypal-001", "ana.lopez@gmail.com");

        pagos.agregarMetodoPago(tarjeta);
        pagos.agregarMetodoPago(paypal);

        System.out.println("Métodos de pago disponibles:");
        pagos.mostrarMetodosPago();

        System.out.println("\nRealizando pagos...");
        pagos.realizarPagos(150.00);

        System.out.println("\nCancelando pagos...");
        pagos.cancelarPagos();
    }
}

