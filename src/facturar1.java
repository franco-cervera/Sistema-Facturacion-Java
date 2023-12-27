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


    public float calcularsub(){
        if (cantidad==0 & valor==0) {
            JOptionPane.showMessageDialog(null, "Dato incorrecto");
            return 0;
        }else{
            subtotal=cantidad*valor+subtotal;
            return subtotal;
        }
    }
    public float calculartotal(){
        if (cantidad==0 & valor==0) {
            JOptionPane.showMessageDialog(null, "Dato incorrecto");
            return 0;
        }else{
            total=cantidad*valor+total;
            return total;
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
    public float RestarSub(){
        if(subtotal==0){
            return subtotal;
        }else{
            subtotal=subtotal-valor;
            return subtotal;
        }
    }
   public float RestarTotalDescuento(){
        if(total==0) {
            return total;
        }else{
            total=total-subtotal*descuento/100;
            return total;
        }
    }

    public float RestarTotal()
    {
        if(total==0){
            return total;
        } else {
            total=total-valor;
            return total;
        }
    }
  /*public float restartotal() {
      if (descuento > 0 && descuento <= 100) {
          float descuentoAplicado = valor * descuento / 100;
          total = total - descuentoAplicado;
      } else {
          total = total - valor;
      }
      return total;
  }
*/
    public float ResetSubtotal(){
        subtotal=0;
       return subtotal;

    }
    public float ResetTotal() {
        total=0;
        return total;
    }
}