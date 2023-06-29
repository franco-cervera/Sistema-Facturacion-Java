import javax.swing.*;

public class facturar1 {
    private float descuento, cantidad, valor, subtotal, total;

    public void ingresarn1(float d) {
        descuento = d;
    }

    public void ingresarn2(float e) {
        cantidad = e;
    }

    public void ingresarn3(float f) {
        valor = f;
    }


    public float calcular(){
        if (cantidad==0 & valor==0) {
            JOptionPane.showMessageDialog(null, "Dato incorrecto");
            return 0;
        }else{
            subtotal=cantidad*valor+subtotal;
            return subtotal;
        }
    }
    public float calcular2(){
        if(descuento==0){
            return subtotal;
        }else{
            total =subtotal-subtotal*descuento/100;
            return total;
        }
    }
    public float facturar(){
        if(descuento==0){
            return valor*cantidad;
        }else{
            subtotal=valor*cantidad-valor*cantidad*descuento/100;
            return subtotal;
        }
    }
    public float restar(){
        if(subtotal==0){
            return subtotal;
        }else{
            subtotal=subtotal-valor;
            return subtotal;
        }
    }
    public void reinicio(){
        subtotal=0;
        descuento=0;
        total=0;

    }
}