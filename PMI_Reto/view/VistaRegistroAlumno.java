package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VistaRegistroAlumno extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtEdad;
    private JTextField txtCorreo;
    private JButton btnRegistrar;
    private JButton btnVolver;
    private String dni;
    private String nombre;
    private String apellido;
    private String contraseña;

    /**
     * Create the frame.
     */
    public VistaRegistroAlumno(String dni, String nombre, String apellido, String contraseña) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        
        setTitle("Registro de Alumno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 250);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 10));
        
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblInfo = new JLabel("Complete la información del alumno:");
        panelInfo.add(lblInfo);
        contentPane.add(panelInfo, BorderLayout.NORTH);
        
        // Panel de datos específicos de alumno
        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(2, 2, 5, 10));
        
        // Edad
        panelDatos.add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        panelDatos.add(txtEdad);
        
        // Correo
        panelDatos.add(new JLabel("Correo electrónico:"));
        txtCorreo = new JTextField();
        panelDatos.add(txtCorreo);
        
        contentPane.add(panelDatos, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        btnVolver = new JButton("Volver");
        panelBotones.add(btnVolver);
        
        btnRegistrar = new JButton("Registrar");
        panelBotones.add(btnRegistrar);
        
        contentPane.add(panelBotones, BorderLayout.SOUTH);
    }
    
    // Getters para acceder a los componentes y datos desde el controlador
    public JTextField getTxtEdad() {
        return txtEdad;
    }
    
    public JTextField getTxtCorreo() {
        return txtCorreo;
    }
    
    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }
    
    public JButton getBtnVolver() {
        return btnVolver;
    }
    
    public String getDni() {
        return dni;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public String getContraseña() {
        return contraseña;
    }
}