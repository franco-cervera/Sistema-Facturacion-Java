import javax.swing.*;

import javax.swing.JOptionPane;

public class Facturar1 {
    private float descuento2;
    private float cantidad2;
    private float valor;
    private float subtotal;
    private float total;

    // Constructor vacío
    public Facturar1() {}

    // Constructor con parámetros
    public Facturar1(float descuento, float cantidad, float valor, float subtotal, float total) {
        this.descuento2 = descuento;
        this.cantidad2 = cantidad;
        this.valor = valor;
        this.subtotal = subtotal;
        this.total = total;
    }

    // Getters y Setters
    public float getDescuento2() {
        return descuento2;
    }

    public void setDescuento2(float descuento) {
        this.descuento2 = descuento;
    }

    public float getCantidad2() {
        return cantidad2;
    }

    public void setCantidad2(float cantidad) {
        this.cantidad2 = cantidad;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    // Métodos de negocio
    public void ingresarN1(float d) {
        setDescuento2(d);
    }

    public void ingresarN2(float e) {
        setCantidad2(e);
    }

    public void ingresarN3(float f) {
        setValor(f);
    }

    public float calcularSub() {
        if (cantidad2 == 0 && valor == 0) {
            JOptionPane.showMessageDialog(null, "Dato incorrecto");
            return 0;
        } else {
            subtotal += cantidad2 * valor;
            return subtotal;
        }
    }

    public float calcularTotal() {
        if (cantidad2 == 0 && valor == 0) {
            JOptionPane.showMessageDialog(null, "Dato incorrecto");
            return 0;
        } else {
            total += cantidad2 * valor;
            return total;
        }
    }

    public float calcularConDescuento() {
        if (descuento2 == 0) {
            return subtotal;
        } else {
            total = subtotal - (subtotal * descuento2 / 100);
            return total;
        }
    }

    public float calcularFactura() {
        if (descuento2 == 0) {
            return valor * cantidad2;
        } else {
            subtotal = valor * cantidad2 - (valor * cantidad2 * descuento2 / 100);
            return subtotal;
        }
    }
    public float restarSub() {
        if (subtotal == 0) {
            return subtotal;
        } else {
            subtotal -= valor;
            return subtotal;
        }
    }

    public float restarTotalDescuento() {
        if (total == 0) {
            return total;
        } else {
            total -= subtotal * descuento2 / 100;
            return total;
        }
    }

    public float restarTotal() {
        if (total == 0) {
            return total;
        } else {
            total -= valor;
            return total;
        }
    }

    public float resetSubtotal() {
        subtotal = 0;
        return subtotal;
    }

    public float resetTotal() {
        total = 0;
        return total;
    }

    // Método toString
    @Override
    public String toString() {
        return "Facturar1{" +
                "descuento=" + descuento2 +
                ", cantidad=" + cantidad2 +
                ", valor=" + valor +
                ", subtotal=" + subtotal +
                ", total=" + total +
                '}';
    }
}
