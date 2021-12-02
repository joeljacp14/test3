/*
Manejo de los principales eventos
/**
 *
 * @author Pedro Diaz
 */

// 1.- Importar las librerias awt y Event swing
import java.awt.*;
import java.awt.event.*;

// 2a. Extender la clase Frame
// 2b. Implementar la interfaz ActionListener
public class PrimerEvento extends Frame implements ActionListener,ItemListener{
    // 3. Declarar los objetos a utilizar. Eventos
    Label lresul, lnum1,lnum2; // Poner letreros en la ventana. No genera ninguna acción
    Button boton1,boton2,limpia,salir; // Tomar decisiones si genera accion.
    TextField n1,n2,resul; // Es un buffer donde se leen valores. No genera acción
    List lista;
    Choice color;
    CheckboxGroup colorBox;
    Checkbox blanco,negro,azul;
    // 3.1 Para crear un tipo especifico de letra para la ventana es el Font
    // Parametros: 1) El tipo de letra   2) Estilo (Negritas, Italica, normal  3)Tamaño
    Font fuente=new Font("Arial",Font.BOLD,15);
    
    public PrimerEvento(){
        // 4.- Crear la ventana y darle nombre
        super("Mi Primer Evento");
        // 5.- Generar un Layout que maneje la ventana
        setLayout(null);
        // 6.- Mostrar la ventana
        setVisible(true);
        // 7.- Tamaño de la ventana
        setSize(400,210); // primer eje x y segundo eje y
        // 8.- Darle un color al frente y fondo de la pantalla
        //setBackground(Color.blue);
        //setForeground(Color.red);
    }
    
    // Generar los objetos que contendrá la ventana
    public void alta(){
        //9. Crear el objeto deseado
        boton1 = new Button("Suma");
        //10. Asociarle un Listener
        boton1.addActionListener(this);
        //11. DOnde colocarlo dentro de la ventana(posición)
        // 1. eje x  2. eje y  3. largo  4. ancho  PIXELES
        boton1.setBounds(50,50,70,30);
        add(boton1);  

        boton2 = new Button("Resta");
        boton2.addActionListener(this);
        boton2.setBounds(50,90,70,30);
        add(boton2);  

        limpia = new Button("Limpia");
        limpia.addActionListener(this);
        limpia.setBounds(50,130,70,30);
        add(limpia);  

        salir = new Button("Salir");
        salir.addActionListener(this);
        salir.setBounds(50,170,70,30);
        add(salir);  
        
        lnum1 = new Label("Num 1:");
        lnum1.setBounds(130,50,65,20);
        lnum1.setBackground(Color.yellow);
        add(lnum1);  

        n1 = new TextField("0");
        n1.setBounds(205,50,50,20);
        add(n1);  

        n2 = new TextField("0");
        n2.setBounds(205,90,50,20);
        add(n2);  

        resul = new TextField("0");
        resul.setBounds(205,130,50,20);
        resul.setFont(fuente);
        resul.setEditable(false);
        add(resul);  

        lnum2 = new Label("Num 2:");
        lnum2.setBounds(130,90,65,20);
        lnum2.setBackground(Color.yellow);
        lnum2.setForeground(Color.black);
        add(lnum2);  

        lresul = new Label("Resultado:");
        lresul.setBounds(130,130,70,20);
        lresul.setBackground(Color.yellow);
        lresul.setForeground(Color.black);
        lresul.setFont(fuente);
        add(lresul);  
        
        lista = new List();
        lista.addActionListener(this);
        lista.setBounds(270,50,90,70);
        lista.add("Suma");
        lista.add("Resta");
        lista.add("Multiplicacion");
        lista.add("Division");
        add(lista);
        
        color = new Choice();
        color.addItemListener(this);
        color.setBounds(270,130,80,30);
        color.addItem("Azul");
        color.addItem("Verde");
        color.addItem("Rojo");
        add(color);
        
        colorBox = new CheckboxGroup();
        // 1. Nombre 2. AL grupo al que pertenece  3. Activo / No activo
        blanco = new Checkbox("Blanco",colorBox,true);
        blanco.setBounds(150,170,70,30);
        blanco.addItemListener(this);
        add(blanco);

        negro = new Checkbox("Negro",colorBox,false);
        negro.setBounds(230,170,70,30);
        negro.addItemListener(this);
        add(negro);

        azul = new Checkbox("Azul",colorBox,false);
        azul.setBounds(310,170,70,30);
        azul.addItemListener(this);
        add(azul);
        
        //12. Procedimiento para cerrar la ventana
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                //13. Aqui va el codigo para cerrar la ventana
                System.exit(0);
            }
        });
    }
    
    public void listaOperaciones(){
        int num1 = Integer.parseInt(n1.getText());
        int num2 = Integer.parseInt(n2.getText());
        //16. Revisar cual fue el elemento seleccionado
        String opcion = lista.getSelectedItem();
        if (opcion.equals("Suma")) num1+=num2;
        else if (opcion.equals("Resta")) num1-=num2;
             else if (opcion.equals("Multiplicacion")) num1*=num2;
                  else num1/=num2;
        resul.setText(String.valueOf(num1));  // Integer.toString(num1)
        
    }
    
    public void itemStateChanged(ItemEvent e){
        if (e.getSource()==color){
           int indice = color.getSelectedIndex();
           if (indice==0) resul.setBackground(Color.BLUE);
           else if (indice==1) resul.setBackground(Color.GREEN);
           else                resul.setBackground(Color.RED);
        } else{
            // El getState() me trae el estado si esta activo o no esta activo
            if (blanco.getState()) resul.setForeground(Color.WHITE);
            if (negro.getState())  resul.setForeground(Color.BLACK);
            if (azul.getState()) resul.setForeground(Color.BLUE);
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==lista) { listaOperaciones(); return; }
        // 15. Leer los valore que se encuentra en los textField
        if (e.getSource()==salir) System.exit(0);
        else if (e.getSource()==limpia){
                // Si el resultado es positivo  negro si es cero en azul y si es negativo en blanco
                int r= Integer.parseInt(resul.getText());
                if (r>0){
                    negro.setState(true);
                    blanco.setState(false);
                    azul.setState(false);
                }else if(r==0){
                    negro.setState(false);
                    blanco.setState(false);
                    azul.setState(true);                    
                }else{
                    negro.setState(false);
                    blanco.setState(true);
                    azul.setState(false);

                }
                if (r>0) {color.select("Azul");lista.select(0);}
                else if (r==0){ color.select("Verde"); lista.select(2);}
                else { color.select("Rojo"); lista.select(1);}
                /*n1.setText("0");
                n2.setText("0");
                resul.setText("0");*/
             }else{
                 int num1 = Integer.parseInt(n1.getText());
                 int num2 = Integer.parseInt(n2.getText());
                 //14.- El getSource reconoce el obejto que lo llamo
                 if (e.getSource() == boton1) num1 +=num2;
                 else                         num1 -=num2;
                 //System.out.println("El resultado es: "+num1); Ya no se utiliza
                resul.setText(String.valueOf(num1)); 
        }
   }
    
    public static void main(String[] args) {
        PrimerEvento xx=new PrimerEvento();
        xx.alta();
    }
    
}
